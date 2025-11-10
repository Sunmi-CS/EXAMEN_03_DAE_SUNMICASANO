package com.saborgourmet.restaurante.domain.persistence;

import com.saborgourmet.restaurante.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar por DNI (para evitar duplicados)
    Cliente findByDni(String dni);

    // Buscar por estado (activo/inactivo)
    List<Cliente> findByEstado(String estado);

    // Buscar por nombre o apellido (búsqueda parcial)
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombres) LIKE LOWER(CONCAT('%', :filtro, '%')) OR LOWER(c.apellidos) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Cliente> buscarPorNombreOApellido(String filtro);

    // ✅ Buscar por nombre, apellido o DNI
    @Query("SELECT c FROM Cliente c " +
            "WHERE LOWER(c.nombres) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(c.apellidos) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR c.dni LIKE CONCAT('%', :filtro, '%')")
    List<Cliente> buscarPorNombreApellidoODni(String filtro);


}
