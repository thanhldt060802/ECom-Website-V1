package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.Invoice;
import com.thanhldt060802.ecom.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_website_v1/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(this.invoiceService.findAllInvoices());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(this.invoiceService.findInvoiceById(id));
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<List<Invoice>> getAllInvoicesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(this.invoiceService.findAllInvoicesByUserId(userId));
    }

    @GetMapping("/id/{id}/user-id/{userId}")
    public ResponseEntity<Invoice> getInvoiceByIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(this.invoiceService.findInvoiceByIdAndUserId(id, userId));
    }

    @PostMapping("/")
    public ResponseEntity<String> createInvoice(@RequestBody Invoice newInvoice) {
        this.invoiceService.createInvoice(newInvoice);
        return ResponseEntity.ok("Create invoice success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody Invoice updatingInvoice) {
        this.invoiceService.updateInvoice(id, updatingInvoice);
        return ResponseEntity.ok("Update invoice success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
        this.invoiceService.deleteInvoiceById(id);
        return ResponseEntity.ok("Delete invoice success!");
    }

}
