package com.military.controller;

import com.military.entity.User;
import com.military.entity.Base;
import com.military.entity.User.UserRole;
import com.military.repository.BaseRepository;
import com.military.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private BaseRepository baseRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, Object> request) {
        
        String username = (String) request.get("username");
        String password = (String) request.get("password");
        String roleStr = (String) request.get("role");

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (roleStr == null || roleStr.isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }

        Long baseId = null;
        if (request.get("base") instanceof Map) {
            Map<?, ?> baseMap = (Map<?, ?>) request.get("base");
            Object id = baseMap.get("id");
            if (id instanceof Number) {
                baseId = ((Number) id).longValue();
            }
        } else if (request.get("baseId") != null) {
            if (request.get("baseId") instanceof Number) {
                baseId = ((Number) request.get("baseId")).longValue();
            }
        }

        if (baseId == null) {
            throw new IllegalArgumentException("Base ID is required");
        }

        Base base = baseRepository.findById(baseId)
            .orElseThrow(() -> new RuntimeException("Base not found"));

        UserRole role = UserRole.valueOf(roleStr);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setBase(base);

        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
