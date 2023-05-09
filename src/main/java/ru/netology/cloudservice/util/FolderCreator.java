package ru.netology.cloudservice.util;

import lombok.AllArgsConstructor;
import ru.netology.cloudservice.advice.exception.UnableToCreateFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
public class FolderCreator {

    private String username;

    public boolean userFolderNotExists() {
        return !Files.exists(getPathToUser());
    }

    private Path getPathToUser() {
        final String dataFolder = "CloudData";
        String pathToSystemUser = System.getProperty("user.home");
        return Path.of(pathToSystemUser + "/" + dataFolder + "/" + username);
    }

    public void createUserFolderInDrive() {
        try {
            Files.createDirectories(getPathToUser());
        } catch (IOException e) {
            throw new UnableToCreateFolder("Wasn't able to create " + username + " folder");
        }
    }
}
