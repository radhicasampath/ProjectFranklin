package com.practice.dbops.service;

import com.practice.dbops.model.User;
import com.practice.dbops.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
