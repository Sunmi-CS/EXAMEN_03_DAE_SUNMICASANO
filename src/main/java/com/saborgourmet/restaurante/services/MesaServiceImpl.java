package com.saborgourmet.restaurante.services;

import com.saborgourmet.restaurante.domain.entities.Mesa;
import com.saborgourmet.restaurante.domain.persistence.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository mesaRepo;

    @Override
    public List<Mesa> listarTodas() {
        return mesaRepo.findAll();
    }

    @Override
    public Mesa buscarPorId(Long id) {
        return mesaRepo.findById(id).orElse(null);
    }

    @Override
    public Mesa guardar(Mesa mesa) {
        return mesaRepo.save(mesa);
    }

    @Override
    public void eliminar(Long id) {
        mesaRepo.deleteById(id);
    }

    @Override
    public List<Mesa> buscarPorEstado(String estado) {
        return mesaRepo.findByEstado(estado);
    }
}
