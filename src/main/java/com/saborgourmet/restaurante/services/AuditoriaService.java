package com.saborgourmet.restaurante.services;

import com.saborgourmet.restaurante.domain.entities.Auditoria;
import java.util.List;

public interface AuditoriaService {
    List<Auditoria> listarTodas();
    Auditoria guardar(Auditoria auditoria);
}
