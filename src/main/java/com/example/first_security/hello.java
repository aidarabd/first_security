package com.example.first_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class hello {

    @GetMapping("/")
    public String home(){
        return "index.html";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello.html";
    }
}
