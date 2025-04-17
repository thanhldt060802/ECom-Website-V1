package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.*;
import com.thanhldt060802.ecom.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public boolean isInvoiceExistedById(Long id) {
        return this.invoiceRepository.existsById(id);
    }

    public List<Invoice> findAllInvoices() {
        return this.invoiceRepository.findAll();
    }

    public Invoice findInvoiceById(Long id) {
        return this.invoiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of invoice is not valid!"));
    }

    public List<Invoice> findAllInvoicesByUserId(Long userId) {
        return this.invoiceRepository.findAllByUserId(userId);
    }

    public Invoice findInvoiceByIdAndUserId(Long id, Long userId) {
        return this.invoiceRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of invoice or id of user is not valid!"));
    }

    public void createInvoice(Invoice newInvoice) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "This feature is not implemented yet!");
        // Unfinished
    }

    public void order(Invoice newInvoice) {
        this.invoiceRepository.save(newInvoice);
    }

    public void updateInvoice(Long id, Invoice updatingInvoice) {
        Invoice foundInvoice = this.invoiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of invoice is not valid!"));
        foundInvoice.setStatus(updatingInvoice.getStatus());

        this.invoiceRepository.save(foundInvoice);
    }

    public void deleteInvoiceById(Long id) {
        if(!this.isInvoiceExistedById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of invoice is not valid!");
        }

        this.invoiceRepository.deleteById(id);
    }

}
