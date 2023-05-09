package ru.netology.cloudservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import ru.netology.cloudservice.dto.FileDetails;
import ru.netology.cloudservice.dto.FilenameUpdate;
import ru.netology.cloudservice.entity.FileHolder;
import ru.netology.cloudservice.entity.User;
import ru.netology.cloudservice.repository.FilesRepository;
import ru.netology.cloudservice.repository.UserRepository;
import ru.netology.cloudservice.service.FilesService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class FilesTest {
    @InjectMocks
    private FilesService filesService;
    @Mock
    private FilesRepository filesRepository;

    @Test
    public void download() throws URISyntaxException, IOException {
        String username = "User";
        String filename = "File";
        URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource("test.txt")).toURI();
        String link = Paths.get(uri).toString();
        Path path = Paths.get(link);
        byte[] savedFile = Files.readAllBytes(path);
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn(username);
        Mockito.doReturn(Optional.of(new FileHolder(1,
                        Mockito.mock(User.class),
                        filename,
                        1,
                        link))).when(filesRepository)
                .getFileByFilenameAndUsername(filename, username);
        try (MockedStatic<Files> mockedFiles = Mockito.mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.readAllBytes(path)).thenReturn(savedFile);
        }
        byte[] file = filesService.download(filename, principal).getBody();
        Assertions.assertArrayEquals(savedFile, file);
    }

    @Test
    public void update() {
        String username = "User";
        String filename = "File";
        String filenameUpdate = "Update";
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn(username);
        Mockito.doNothing().when(filesRepository)
                .updateFilenameByNameAndUsername(any(), any(), any());
        FilenameUpdate update = new FilenameUpdate(filenameUpdate);
        Mockito.doReturn(Optional.of(new FileHolder(1,
                        Mockito.mock(User.class),
                        filename,
                        1,
                        ""))).when(filesRepository)
                .getFileByFilenameAndUsername(update.getName(), username);
        filesService.update(filename, update, principal);
        Mockito.verify(filesRepository, Mockito.times(1))
                .getFileByFilenameAndUsername(update.getName(), username);
    }

    @Test
    public void getList() {
        String username = "User";
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn(username);
        List<FileHolder> request = new ArrayList<>();
        request.add(new FileHolder(1, Mockito.mock(User.class), "1", 1, ""));
        request.add(new FileHolder(2, Mockito.mock(User.class), "2", 2, ""));
        request.add(new FileHolder(3, Mockito.mock(User.class), "3", 3, ""));
        List<FileDetails> expected = new ArrayList<>();
        expected.add(new FileDetails("1", 1));
        expected.add(new FileDetails("2", 2));
        expected.add(new FileDetails("3", 3));
        Mockito.doReturn(request).when(filesRepository)
                .getFiles(username, PageRequest.of(0, 3));
        List<FileDetails> actual = filesService.getFiles(3, principal).getBody();
        assertThat(actual.get(0).getFilename()).isSameAs(expected.get(0).getFilename());
        assertThat(actual.get(1).getFilename()).isSameAs(expected.get(1).getFilename());
        assertThat(actual.get(2).getFilename()).isSameAs(expected.get(2).getFilename());
        assertThat(actual.get(0).getClass()).isSameAs(expected.get(0).getClass());
        assertThat(actual.get(1).getClass()).isSameAs(expected.get(1).getClass());
        assertThat(actual.get(2).getClass()).isSameAs(expected.get(2).getClass());
    }
}
