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
@RequestMapping("ecom_v1/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.getByUsername(username));
    }

    @PostMapping("/")
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        this.userService.add(newUser);
        return ResponseEntity.ok("Add success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatingUser) {
        this.userService.update(id, updatingUser);
        return ResponseEntity.ok("Update success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok("Delete success!");
    }

}
