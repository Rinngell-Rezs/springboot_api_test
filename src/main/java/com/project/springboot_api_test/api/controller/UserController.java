package com.project.springboot_api_test.api.controller;

import com.project.springboot_api_test.api.model.User;
import com.project.springboot_api_test.dto.Filter;
import com.project.springboot_api_test.dto.Attribute;
import com.project.springboot_api_test.dto.LoginRequest;
import com.project.springboot_api_test.dto.UpdateUserRequest;
import com.project.springboot_api_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users" )
    public ArrayList<User> getUsers(
        @RequestParam (required = false) Attribute sortedBy,
        @RequestParam (required = true) Filter filter
    ) {
        return userService.getUsers(sortedBy, filter);
    }
    @PostMapping("/users" )
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping("/users/{id}" )
    public User updateUser(@PathVariable String id, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @DeleteMapping("/users/{id}" )
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/login" )
    public User loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }
}