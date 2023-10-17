package com.backproject.userMicroservice.dao;

import com.backproject.userMicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
