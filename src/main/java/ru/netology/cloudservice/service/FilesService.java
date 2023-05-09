package ru.netology.cloudservice.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservice.advice.exception.*;
import ru.netology.cloudservice.dto.FileDetails;
import ru.netology.cloudservice.dto.FilenameUpdate;
import ru.netology.cloudservice.entity.FileHolder;
import ru.netology.cloudservice.entity.User;
import ru.netology.cloudservice.repository.FilesRepository;
import ru.netology.cloudservice.repository.UserRepository;
import ru.netology.cloudservice.util.FileHolderBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilesService {

    private final FilesRepository filesRepository;
    private final UserRepository userRepository;

    public ResponseEntity<byte[]> download(String filename, Principal principal) {
        String username = principal.getName();
        FileHolder holder = getFileHolder(filename, username);
        Path path = Path.of(holder.getLink());
        byte[] file = readFile(path);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    private FileHolder getFileHolder(String filename, String username) {
        return filesRepository
                .getFileByFilenameAndUsername(filename, username)
                .orElseThrow(UnableToGetFile::new);
    }

    private byte[] readFile(Path path) {
        byte[] file;
        try {
            file = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    @Transactional
    public ResponseEntity<String> upload(MultipartFile file, String filename, Principal principal) {
        FileHolderBuilder builder = new FileHolderBuilder();
        String username = principal.getName();
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(UnableToFindUser::new);
        FileHolder holder = builder.buildFileHolder(file, filename, user);
        uploadFileToDrive(holder.getLink(), file);
        filesRepository.save(holder);
        return new ResponseEntity<>("File was successfully uploaded to user cloud", HttpStatus.OK);
    }

    private void uploadFileToDrive(String link, MultipartFile file) {
        try (FileOutputStream stream = new FileOutputStream(link)) {
            stream.write(file.getBytes());
            stream.flush();
        } catch (IOException e) {
            throw new UnableToUploadFile("Unable to upload: " + file.getName());
        }
    }

    public ResponseEntity<String> update(String filename, FilenameUpdate update, Principal principal) {
        String filenameUpdate = update.getName();
        String username = principal.getName();
        filesRepository.updateFilenameByNameAndUsername(filename, filenameUpdate, username);
        if (fileExists(filenameUpdate, username)) {
            return new ResponseEntity<>("File was updated", HttpStatus.OK);
        } else {
            throw new UnableToUpdateFile("Unable to update filename");
        }
    }

    private boolean fileExists(String filename, String username) {
        Optional<FileHolder> holder = filesRepository.getFileByFilenameAndUsername(filename, username);
        return holder.isPresent();
    }

    public ResponseEntity<String> delete(String fileName, Principal principal) {
        String username = principal.getName();
        String link = filesRepository.getLinkByFilenameNameAndUsername(fileName, username);
        Path path = Path.of(link);
        filesRepository.deleteFileByFilenameAndUsername(fileName, username);
        if (!fileExists(fileName, username)) {
            deleteFileFromDrive(path);
            return new ResponseEntity<>("File was deleted", HttpStatus.OK);
        } else {
            throw new UnableToDeleteFile("Unable to delete file");
        }
    }

    private void deleteFileFromDrive(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<FileDetails>> getFiles(int limit, Principal principal) {
        String username = principal.getName();
        List<FileHolder> page = filesRepository.getFiles(username, PageRequest.of(0, limit));
        List<FileDetails> files = page.stream().map(a -> new FileDetails(a.getName(), a.getSize())).collect(Collectors.toList());
        return new ResponseEntity<>(files, HttpStatus.OK);
    }
}
