package com.saborgourmet.restaurante.controllers;

import com.saborgourmet.restaurante.domain.entities.Cliente;
import com.saborgourmet.restaurante.domain.persistence.ClienteRepository;
import com.saborgourmet.restaurante.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository; // ✅ agregado

    // ✅ Redirigir a la lista cuando entran a /clientes
    @GetMapping("")
    public String index() {
        return "redirect:/clientes/listar";
    }

    // ✅ Mostrar la lista de clientes
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Cliente> clientes = clienteService.listarTodos();
        model.addAttribute("clientes", clientes);
        model.addAttribute("title", "Listado de Clientes");
        return "clientes/listar"; // templates/clientes/listar.html
    }

    // ✅ Buscar clientes por nombre, apellido o DNI
    @GetMapping("/buscar")
    public String buscar(@RequestParam("buscar") String buscar, Model model) {
        List<Cliente> clientes;

        if (buscar != null && !buscar.isEmpty()) {
            // puedes usar un nuevo método más completo en el repositorio (ver abajo)
            clientes = clienteRepository.buscarPorNombreApellidoODni(buscar);
        } else {
            clientes = clienteService.listarTodos();
        }

        model.addAttribute("clientes", clientes);
        model.addAttribute("title", "Listado de Clientes");
        model.addAttribute("buscar", buscar);
        return "clientes/listar";
    }

    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente, Model model) {
        Cliente existente = clienteRepository.findByDni(cliente.getDni());
        if (existente != null && (cliente.getIdCliente() == null || !existente.getIdCliente().equals(cliente.getIdCliente()))) {
            model.addAttribute("error", "El DNI ya está registrado");
            model.addAttribute("cliente", cliente);
            return "clientes/form"; // vuelve al formulario mostrando el error
        }

        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        try {
            clienteService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "Cliente eliminado correctamente.");
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar este cliente porque tiene mesas asignadas.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al intentar eliminar el cliente.");
        }
        return "redirect:/clientes/listar";
    }
}
