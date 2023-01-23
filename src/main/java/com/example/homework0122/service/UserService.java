package com.example.homework0122.service;

import com.example.homework0122.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User insertUser(User user);

    public List<User> getAllUser();

    public void deleteAllUser();
}
