package com.saborgourmet.restaurante.controllers;

import com.saborgourmet.restaurante.domain.entities.Cliente;
import com.saborgourmet.restaurante.domain.entities.Mesa;
import com.saborgourmet.restaurante.domain.persistence.ClienteRepository;
import com.saborgourmet.restaurante.domain.persistence.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaRepository.findAll());
        return "mesas/listar";
    }

    @GetMapping("/nueva")
    public String nuevaMesa(Model model) {
        model.addAttribute("mesa", new Mesa());

        // Solo clientes activos
        List<Cliente> clientesActivos = clienteRepository.findAll().stream()
                .filter(c -> "Activo".equalsIgnoreCase(c.getEstado()))
                .collect(Collectors.toList());
        model.addAttribute("clientes", clientesActivos);

        return "mesas/form";
    }

    @PostMapping("/guardar")
    public String guardarMesa(@ModelAttribute Mesa mesa) {
        mesaRepository.save(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable Long id, Model model) {
        Mesa mesa = mesaRepository.findById(id).orElse(null);
        if (mesa == null) return "redirect:/mesas";
        model.addAttribute("mesa", mesa);

        // Solo clientes activos
        List<Cliente> clientesActivos = clienteRepository.findAll().stream()
                .filter(c -> "Activo".equalsIgnoreCase(c.getEstado()))
                .collect(Collectors.toList());
        model.addAttribute("clientes", clientesActivos);

        return "mesas/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id) {
        mesaRepository.deleteById(id);
        return "redirect:/mesas";
    }

}
