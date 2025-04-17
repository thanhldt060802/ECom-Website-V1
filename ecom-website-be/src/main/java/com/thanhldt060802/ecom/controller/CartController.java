package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.Cart;
import com.thanhldt060802.ecom.model.CartDetail;
import com.thanhldt060802.ecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_website_v1/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(this.cartService.findAllCarts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(this.cartService.findCartById(id));
    }

    @PostMapping("/cart-items/id/{id}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long id, @RequestBody CartDetail newCartDetail) {
        this.cartService.addProductToCart(id, newCartDetail);
        return ResponseEntity.ok("Add product to cart success!");
    }

    @PutMapping("/cart-items/id/{id}")
    public ResponseEntity<String> updateProductInCart(@PathVariable Long id, @RequestBody CartDetail updatingCartDetail) {
        this.cartService.updateProductInCart(id, updatingCartDetail);
        return ResponseEntity.ok("Update product in cart success!");
    }

    @DeleteMapping("/cart-items/id/{id}/product-id/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
        this.cartService.removeProductFromCart(id, productId);
        return ResponseEntity.ok("Remove product from cart success!");
    }

    @PostMapping("/order/id/{id}")
    public ResponseEntity<String> confirmOrder(@PathVariable Long id) {
        this.cartService.order(id);
        return ResponseEntity.ok("Order success!");
    }

}
