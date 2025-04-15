package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.Cart;
import com.thanhldt060802.ecom.model.User;
import com.thanhldt060802.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isIdExisted(Long id) {
        return this.userRepository.existsById(id);
    }

    public boolean isUsernameExisted(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean isEmailExisted(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id!"));
    }

    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid username!"));
    }

    public User add(User newUser) {
        if(this.isUsernameExisted(newUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        if(this.isEmailExisted(newUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }
        newUser.setCart(new Cart());
        newUser.getCart().setUser(newUser);
        newUser.setRole("ROLE_CUSTOMER");

        return this.userRepository.save(newUser);
    }

    public User update(Long id, User updatingUser) {
        User foundUser = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id!"));
        foundUser.setPassword(updatingUser.getPassword());
        if(!updatingUser.getEmail().equals(foundUser.getEmail())) {
            if(this.isEmailExisted(updatingUser.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
            }else {
                foundUser.setEmail(updatingUser.getEmail());
            }
        }
        foundUser.setEmail(updatingUser.getEmail());
        foundUser.setName(updatingUser.getName());
        foundUser.setAddress(updatingUser.getAddress());

        return this.userRepository.save(foundUser);
    }

    public void delete(Long id) {
        if(!this.isIdExisted(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id!");
        }

        this.userRepository.deleteById(id);
    }

}
