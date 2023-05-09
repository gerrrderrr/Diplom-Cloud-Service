package ru.netology.cloudservice.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.cloudservice.entity.FileHolder;

import java.util.List;
import java.util.Optional;

public interface FilesRepository extends JpaRepository<FileHolder, String> {
    @Transactional
    @Query("select f from FileHolder f where f.user.username = :username and lower(f.name) = lower(:filename)")
    Optional<FileHolder> getFileByFilenameAndUsername(String filename, String username);

    @Modifying
    @Transactional
    @Query("delete from FileHolder f where f.user.username = :username and lower(f.name) = lower(:filename)")
    void deleteFileByFilenameAndUsername(String filename, String username);

    @Modifying
    @Transactional
    @Query("update FileHolder f set f.name = :filenameUpdate where f.user.username = :username and lower(f.name) = lower(:filename)")
    void updateFilenameByNameAndUsername(String filename, String filenameUpdate, String username);

    @Transactional
    @Query("select f from FileHolder f left join fetch f.user where f.user.username = :username")
    List<FileHolder> getFiles(String username, PageRequest pageable);

    @Transactional
    @Query("select f.link from FileHolder f where f.user.username = :username and lower(f.name) = lower(:filename)")
    String getLinkByFilenameNameAndUsername(String filename, String username);
}