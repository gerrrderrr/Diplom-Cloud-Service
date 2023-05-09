package ru.netology.cloudservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.netology.cloudservice.util.validator.FilenameNotExists;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilenameUpdate {
    @FilenameNotExists
    private String name;
}
