package com.example.project;

import com.example.project.model.UserInfo;
import com.example.project.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Application(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        createDefaultUser("admin", "admin123", Set.of("ROLE_ADMIN"));
        createDefaultUser("user", "user123", Set.of("ROLE_USER"));
    }

    private void createDefaultUser(String username, String password, Set<String> roles) {
        if (userInfoRepository.findByUsername(username).isEmpty()) {
            UserInfo userInfo = new UserInfo(username, passwordEncoder.encode(password), roles);
            userInfoRepository.save(userInfo);
            System.out.println("Created default user: " + username);
        }
    }
}
