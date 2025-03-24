
package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class XPService {

    private final UserRepository userRepository;

    public XPService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void awardXp(User user, int amount) {
        user.setXp(user.getXp() + amount); // this also auto-updates level
        userRepository.save(user);
    }
}
