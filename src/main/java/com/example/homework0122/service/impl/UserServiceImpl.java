package com.example.homework0122.service.impl;

import com.example.homework0122.pojo.User;
import com.example.homework0122.repository.UserRepository;
import com.example.homework0122.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public User insertUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public void deleteAllUser() {
        this.repository.deleteAll();
    }
}
