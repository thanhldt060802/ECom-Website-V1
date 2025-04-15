package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.Invoice;
import com.thanhldt060802.ecom.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public boolean isIdExisted(Long id) {
        return this.invoiceRepository.existsById(id);
    }

    public List<Invoice> getAll() {
        return this.invoiceRepository.findAll();
    }

    public Invoice getById(Long id) {
        return this.invoiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id!"));
    }

    public List<Invoice> getAllByUserId(Long userId) {
        return this.invoiceRepository.findAllByUserId(userId);
    }

    public Invoice getByIdAndUserId(Long id, Long userId) {
        return this.invoiceRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id or user id"));
    }

}
