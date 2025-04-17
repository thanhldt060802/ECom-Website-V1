package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.*;
import com.thanhldt060802.ecom.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;

    public boolean isCartExistedById(Long id) {
        return this.cartRepository.existsById(id);
    }

    public List<Cart> findAllCarts() {
        return this.cartRepository.findAll();
    }

    public Cart findCartById(Long id) {
        return this.cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of cart is not valid!"));
    }

    public void addProductToCart(Long id, CartDetail newCartDetail) {
        Cart foundCart = this.cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of cart is not valid!"));
        Product foundProduct = this.productService.findProductById(newCartDetail.getProduct().getProductId());
        CartDetail foundCartDetail = foundCart.getCartDetails().stream()
                .filter((cartDetail) -> cartDetail.getProduct().getProductId().equals(newCartDetail.getProduct().getProductId()))
                .findFirst()
                .orElse(null);
        if(foundCartDetail != null) {
            foundCartDetail.setQuantity(foundCartDetail.getQuantity() + 1);
        }else {
            newCartDetail.setCart(foundCart);
            newCartDetail.setProduct(foundProduct);
            newCartDetail.setQuantity(1);
            foundCart.getCartDetails().add(newCartDetail);
        }

        this.cartRepository.save(foundCart);
    }

    public void updateProductInCart(Long id, CartDetail updatingCartDetail) {
        Cart foundCart = this.cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of cart is not valid!"));
        CartDetail foundCartDetail = foundCart.getCartDetails().stream()
                .filter((cartDetail) -> cartDetail.getProduct().getProductId().equals(updatingCartDetail.getProduct().getProductId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of product in cart is not valid!"));
        foundCartDetail.setQuantity(updatingCartDetail.getQuantity());

        this.cartRepository.save(foundCart);
    }

    public void removeProductFromCart(Long id, Long productId) {
        Cart foundCart = this.cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of cart is not valid!"));
        if(!this.productService.isProductExistedById(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of product is not valid!");
        }
        CartDetail foundCartDetail = foundCart.getCartDetails().stream()
                .filter((cartDetail) -> cartDetail.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of product in cart is not valid!"));
        foundCart.getCartDetails().remove(foundCartDetail);

        this.cartRepository.save(foundCart);
    }

    @Transactional
    public void order(Long id) {
        Cart foundCart = this.cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of user is not valid!"));
        Invoice newInvoice = new Invoice();
        newInvoice.setUser(foundCart.getUser());
        for(CartDetail cartDetail : foundCart.getCartDetails()) {
            InvoiceDetail newInvoiceDetail = new InvoiceDetail();
            newInvoiceDetail.setInvoice(newInvoice);
            newInvoiceDetail.setProduct(cartDetail.getProduct());
            newInvoiceDetail.setPrice(cartDetail.getProduct().getPrice());
            newInvoiceDetail.setDiscountPercentage(cartDetail.getProduct().getDiscountPercentage());
            if(cartDetail.getQuantity() > cartDetail.getProduct().getStock()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock of product is not valid!");
            }
            newInvoiceDetail.setQuantity(cartDetail.getQuantity());
            newInvoiceDetail.setTotalPrice(newInvoiceDetail.getPrice().subtract(newInvoiceDetail.getPrice().multiply(BigDecimal.valueOf(newInvoiceDetail.getDiscountPercentage() / 100.0))).multiply(BigDecimal.valueOf(newInvoiceDetail.getQuantity())));
            newInvoiceDetail.getProduct().setStock(newInvoiceDetail.getProduct().getStock() - newInvoiceDetail.getQuantity());
            newInvoice.getInvoiceDetails().add(newInvoiceDetail);

            this.productService.updateProduct(newInvoiceDetail.getProduct().getProductId(), newInvoiceDetail.getProduct());
        }
        newInvoice.setTotalAmount(newInvoice.getInvoiceDetails().stream()
                .map(InvoiceDetail::getTotalPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add));
        newInvoice.setStatus("Pending");
        foundCart.getCartDetails().clear();

        this.invoiceService.order(newInvoice);
        this.cartRepository.save(foundCart);
    }

}
