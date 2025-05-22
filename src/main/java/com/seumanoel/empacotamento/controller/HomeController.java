package com.seumanoel.empacotamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Olá, sua aplicação Spring Boot está funcionando!";
    }
}
