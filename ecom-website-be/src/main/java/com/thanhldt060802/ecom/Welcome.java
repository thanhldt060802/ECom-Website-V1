package com.thanhldt060802.ecom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @GetMapping("/")
    public String greeting() {
        return "Hello, World!";
    }

}
