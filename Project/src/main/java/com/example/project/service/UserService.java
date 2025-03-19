package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Fetch a user by their ID.
     *
     * @param userId The ID of the user to fetch.
     * @return The User object.
     * @throws RuntimeException if the user is not found.
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Fetch a user by their username.
     *
     * @param username The username of the user to fetch.
     * @return The User object.
     * @throws RuntimeException if the user is not found.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Save or update a user in the database with password encryption.
     *
     * @param user The user to save or update.
     * @return The saved or updated User object.
     */
    public User saveUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Delete a user by their ID.
     *
     * @param userId The ID of the user to delete.
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
