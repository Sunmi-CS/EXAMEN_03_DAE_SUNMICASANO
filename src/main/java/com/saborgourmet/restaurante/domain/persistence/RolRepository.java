package com.saborgourmet.restaurante.domain.persistence;

import com.saborgourmet.restaurante.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> { }
