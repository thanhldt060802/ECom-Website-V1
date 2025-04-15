package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.Cart;
import com.thanhldt060802.ecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_v1/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(this.cartService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(this.cartService.getCartById(id));
    }

}
