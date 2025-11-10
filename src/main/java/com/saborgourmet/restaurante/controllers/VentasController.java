package com.saborgourmet.restaurante.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventas")
public class VentasController {

    @GetMapping
    public String listarVentas() {
        return "redirect:/error/access_denied"; // si no tienes plantilla, puedes redirigir al acceso denegado
    }
}

