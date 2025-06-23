package com.military.service;
import com.military.entity.User;
import com.military.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

