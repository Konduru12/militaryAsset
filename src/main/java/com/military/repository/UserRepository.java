package com.military.repository;
import com.military.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByBaseId(Long baseId);
}

