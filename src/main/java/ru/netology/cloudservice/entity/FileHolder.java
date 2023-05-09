package ru.netology.cloudservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class FileHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long size;
    @Column(nullable = false)
    private String link;
}
