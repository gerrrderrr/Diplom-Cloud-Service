package ru.netology.cloudservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class FileElement {
    private String name;
    private MultipartFile multipartFile;
}
