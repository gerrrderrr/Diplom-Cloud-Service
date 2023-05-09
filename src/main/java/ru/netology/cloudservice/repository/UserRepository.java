package ru.netology.cloudservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.cloudservice.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Query("select u from User u where u.username = :username")
    Optional<User> findUserByUsername(String username);

    @Transactional
    boolean existsUserByUsername(String username);
}
