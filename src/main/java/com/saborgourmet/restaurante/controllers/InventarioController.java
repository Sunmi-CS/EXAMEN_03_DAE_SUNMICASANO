package com.saborgourmet.restaurante.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @GetMapping
    public String listarInventario() {
        return "redirect:/error/access_denied"; // igual que arriba
    }
}

