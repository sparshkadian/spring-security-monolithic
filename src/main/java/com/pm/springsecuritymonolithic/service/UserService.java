package com.pm.springsecuritymonolithic.service;

import com.pm.springsecuritymonolithic.model.User;
import com.pm.springsecuritymonolithic.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
