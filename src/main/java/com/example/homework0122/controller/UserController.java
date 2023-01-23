package com.example.homework0122.controller;

import com.example.homework0122.pojo.User;
import com.example.homework0122.service.UserService;
import com.example.homework0122.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
public class UserController {
    private final UserService service;
    
    
    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        this.service.insertUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(this.service.getAllUser(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUser() {
        this.service.deleteAllUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
