package com.saborgourmet.restaurante.services;

import com.saborgourmet.restaurante.domain.entities.Mesa;
import java.util.List;

public interface MesaService {
    List<Mesa> listarTodas();
    Mesa buscarPorId(Long id);
    Mesa guardar(Mesa mesa);
    void eliminar(Long id);
    List<Mesa> buscarPorEstado(String estado);
}
