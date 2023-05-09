package ru.netology.cloudservice.util;

import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservice.entity.FileHolder;
import ru.netology.cloudservice.entity.User;

@NoArgsConstructor
public class FileHolderBuilder {

    public FileHolder buildFileHolder(MultipartFile file, String filename, User user) {
        String username = user.getUsername();
        FolderCreator creator = new FolderCreator(username);
        if (creator.userFolderNotExists()) {
            creator.createUserFolderInDrive();
        }
        return FileHolder.builder()
                .name(filename)
                .size(getFileSize(file))
                .user(user)
                .link(createLinkToFile(filename, username))
                .build();
    }

    private long getFileSize(MultipartFile file) {
        return file.getSize();
    }

    private String createLinkToFile(String filename, String username) {
        String path = System.getProperty("user.home");
        String dataFolder = "CloudData";
        return path + "/" + dataFolder + "/" + username + "/" + filename;
    }
}