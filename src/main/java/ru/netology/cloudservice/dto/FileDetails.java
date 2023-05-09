package ru.netology.cloudservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileDetails {
    private String filename;
    private long Size; // некорректное имя из-за того, что Front принимает именно Size с большой буквы
}
