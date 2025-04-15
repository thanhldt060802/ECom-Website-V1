package com.thanhldt060802.ecom.repository;

import com.thanhldt060802.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(user) > 0 FROM User user " +
            "WHERE LOWER(user.username) = LOWER(:username)")
    public boolean existsByUsername(@Param("username") String username);

    @Query("SELECT COUNT(user) > 0 FROM User user " +
            "WHERE LOWER(user.email) = LOWER(:email)")
    public boolean existsByEmail(@Param("email") String email);

    @Query("SELECT user FROM User user " +
            "WHERE LOWER(user.username) = LOWER(:username)")
    public Optional<User> findByUsername(@Param("username") String username);

}
