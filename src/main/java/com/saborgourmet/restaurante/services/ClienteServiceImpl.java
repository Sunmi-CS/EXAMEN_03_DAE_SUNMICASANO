package com.saborgourmet.restaurante.services;

import com.saborgourmet.restaurante.domain.entities.Cliente;
import com.saborgourmet.restaurante.domain.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> buscarPorEstado(String estado) {
        return clienteRepository.findByEstado(estado);
    }

    @Override
    public List<Cliente> buscarPorNombreOApellido(String filtro) {
        return clienteRepository.buscarPorNombreOApellido(filtro);
    }
}
