package com.yoongi.springweb.repository;

import com.yoongi.springweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
//    Optional<User> findById(String id);
}
