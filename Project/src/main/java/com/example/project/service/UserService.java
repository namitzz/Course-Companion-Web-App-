package com.example.project.service;

import com.example.project.model.CompletedCourse;
import com.example.project.model.Course;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    /**
     * Save the user without altering the password.
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Add XP to a user and update their level.
     */
    public void addXpToUser(User user, int amount) {
        user.setXp(user.getXp() + amount); // setXp will auto-update level
        userRepository.save(user);
    }

    /**
     * Reset XP and level.
     */
    public void resetXp(User user) {
        user.setXp(0); // level will reset to 1 based on logic in User
        userRepository.save(user);
    }

    /**
     * Add a completed course and reward XP.
     */
    public void completeCourse(Long userId, Course course) {
        User user = getUserById(userId);

        CompletedCourse completedCourse = new CompletedCourse();
        completedCourse.setUser(user);
        completedCourse.setCourse(course);

        user.getCompletedCourses().add(completedCourse);
        user.addXp(50); // or any reward logic you want

        userRepository.save(user);
    }
}
