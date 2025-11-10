package com.saborgourmet.restaurante.domain.persistence;

import com.saborgourmet.restaurante.domain.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    // Buscar mesas por estado (disponible, ocupada, reservada, mantenimiento)
    List<Mesa> findByEstado(String estado);

    // Buscar mesa por número (para evitar duplicados)
    Mesa findByNumero(Integer numero);
}
