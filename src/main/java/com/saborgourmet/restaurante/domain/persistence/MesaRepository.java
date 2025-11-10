package com.saborgourmet.restaurante.domain.persistence;

import com.saborgourmet.restaurante.domain.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    List<Mesa> findByEstado(String estado);
    Mesa findByNumero(Integer numero);

    // 🔍 Búsqueda flexible por número, estado o cliente
    @Query("""
        SELECT m FROM Mesa m 
        LEFT JOIN m.cliente c 
        WHERE LOWER(CAST(m.numero AS string)) LIKE LOWER(CONCAT('%', :filtro, '%'))
           OR LOWER(m.estado) LIKE LOWER(CONCAT('%', :filtro, '%'))
           OR LOWER(CONCAT(c.nombres, ' ', c.apellidos)) LIKE LOWER(CONCAT('%', :filtro, '%'))
    """)
    List<Mesa> buscarPorNumeroEstadoOCliente(@Param("filtro") String filtro);
}

