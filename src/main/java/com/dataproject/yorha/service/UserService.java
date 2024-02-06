package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.User;
import com.dataproject.yorha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser() { return userRepository.findAll(); };
}
