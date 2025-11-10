package com.saborgourmet.restaurante.services;

import com.saborgourmet.restaurante.domain.entities.Auditoria;
import com.saborgourmet.restaurante.domain.persistence.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepo;

    @Override
    public List<Auditoria> listarTodas() {
        return auditoriaRepo.findAll();
    }

    @Override
    public Auditoria guardar(Auditoria auditoria) {
        return auditoriaRepo.save(auditoria);
    }
}
