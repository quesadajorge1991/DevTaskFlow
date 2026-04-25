package com.DevTaskFlow.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.DevTaskFlow.dto.UserRegistrationDto;
import com.DevTaskFlow.model.User;
import com.DevTaskFlow.repository.UserRepository;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User registerUser(UserRegistrationDto dto) {
        User user = User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .role(dto.getRole())
            .enabled(true)
            .build();
        return userRepository.save(user);
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
