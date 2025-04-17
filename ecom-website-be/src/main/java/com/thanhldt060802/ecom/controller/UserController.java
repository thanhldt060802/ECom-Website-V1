package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.User;
import com.thanhldt060802.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_website_v1/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.findAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.findUserByUsername(username));
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody User newUser) {
        this.userService.createUser(newUser);
        return ResponseEntity.ok("Create user success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatingUser) {
        this.userService.updateUser(id, updatingUser);
        return ResponseEntity.ok("Update user success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        this.userService.deleteUserById(id);
        return ResponseEntity.ok("Delete user success!");
    }

}
