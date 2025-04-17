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

    public boolean isUserExistedById(Long id) {
        return this.userRepository.existsById(id);
    }

    public boolean isUserExistedByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean isUserExistedByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of user is not valid!"));
    }

    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username of user is not valid!"));
    }

    public void createUser(User newUser) {
        if(this.isUserExistedByUsername(newUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username of user already exists!");
        }
        if(this.isUserExistedByEmail(newUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email of user already exists!");
        }
        newUser.setCart(new Cart());
        newUser.getCart().setUser(newUser);
        newUser.setRole("ROLE_CUSTOMER");

        this.userRepository.save(newUser);
    }

    public void updateUser(Long id, User updatingUser) {
        User foundUser = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of user is not valid!"));
        foundUser.setPassword(updatingUser.getPassword());
        if(!updatingUser.getEmail().equals(foundUser.getEmail())) {
            if(this.isUserExistedByEmail(updatingUser.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email of user already exists!");
            }else {
                foundUser.setEmail(updatingUser.getEmail());
            }
        }
        foundUser.setEmail(updatingUser.getEmail());
        foundUser.setName(updatingUser.getName());
        foundUser.setAddress(updatingUser.getAddress());

        this.userRepository.save(foundUser);
    }

    public void deleteUserById(Long id) {
        if(!this.isUserExistedById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of user is not valid!");
        }

        this.userRepository.deleteById(id);
    }

}
