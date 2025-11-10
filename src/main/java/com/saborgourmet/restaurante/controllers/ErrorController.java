package com.saborgourmet.restaurante.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/access_denied")
    public String accessDenied() {
        return "error/access_denied"; // apunta a templates/error/access_denied.html
    }
}
