package com.saborgourmet.restaurante.services;

import com.saborgourmet.restaurante.domain.entities.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarTodos();
    Cliente buscarPorId(Long id);
    Cliente guardar(Cliente cliente);
    void eliminar(Long id);
    List<Cliente> buscarPorEstado(String estado);
    List<Cliente> buscarPorNombreOApellido(String filtro);
}
