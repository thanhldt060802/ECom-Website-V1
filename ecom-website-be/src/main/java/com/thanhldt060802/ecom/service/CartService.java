package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.Cart;
import com.thanhldt060802.ecom.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public boolean isIdExisted(Long id) {
        return this.cartRepository.existsById(id);
    }

    public List<Cart> getAll() {
        return this.cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return this.cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id!"));
    }

}
