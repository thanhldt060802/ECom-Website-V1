package com.thanhldt060802.ecom.repository;

import com.thanhldt060802.ecom.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT invoice FROM Invoice invoice " +
            "INNER JOIN invoice.user user " +
            "WHERE user.id = :user_id")
    public List<Invoice> findAllByUserId(@Param("user_id") Long userId);

    @Query("SELECT invoice FROM Invoice invoice " +
            "INNER JOIN invoice.user user " +
            "WHERE invoice.id = :id AND user.id = :user_id")
    public Optional<Invoice> findByIdAndUserId(@Param("id") Long id, @Param("user_id") Long userId);

}
