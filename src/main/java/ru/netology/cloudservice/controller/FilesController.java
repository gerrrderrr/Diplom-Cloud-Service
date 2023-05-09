package ru.netology.cloudservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudservice.dto.FileDetails;
import ru.netology.cloudservice.dto.FilenameUpdate;
import ru.netology.cloudservice.service.FilesService;
import ru.netology.cloudservice.util.validator.FilenameExists;
import ru.netology.cloudservice.util.validator.FilenameNotExists;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class FilesController {
    private final FilesService service;

    @GetMapping("/file")
    public ResponseEntity<byte[]> download(@RequestParam("filename")
                                           @FilenameExists String filename,
                                           Principal user) {
        return service.download(filename, user);
    }

    @PostMapping("/file")
    public ResponseEntity<String> upload(@RequestParam("filename")
                                         @FilenameNotExists String filename,
                                         @RequestBody MultipartFile file,
                                         Principal user) {
        return service.upload(file, filename, user);
    }

    @PutMapping("/file")
    public ResponseEntity<String> update(@RequestParam("filename")
                                         @FilenameExists String filename,
                                         @RequestBody @Valid FilenameUpdate filenameUpdate,
                                         Principal user) {
        return service.update(filename, filenameUpdate, user);
    }

    @DeleteMapping("/file")
    public ResponseEntity<String> delete(@RequestParam("filename")
                                         @FilenameExists String filename,
                                         Principal user) {
        return service.delete(filename, user);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileDetails>> getFiles(@RequestParam("limit") int limit,
                                                      Principal user) {
        return service.getFiles(limit, user);
    }
}