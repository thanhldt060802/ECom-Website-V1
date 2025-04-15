package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.Invoice;
import com.thanhldt060802.ecom.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_v1/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(this.invoiceService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(this.invoiceService.getById(id));
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<List<Invoice>> getAllInvoicesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(this.invoiceService.getAllByUserId(userId));
    }

    @GetMapping("/id/{id}/user-id/{userId}")
    public ResponseEntity<Invoice> getInvoiceByIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(this.invoiceService.getByIdAndUserId(id, userId));
    }

}
