package com.saborgourmet.restaurante.domain.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mesas")
public class Mesa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesa;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(length = 20, nullable = false)
    private String estado = "disponible"; // disponible, ocupada, reservada, mantenimiento

    // 🔹 Relación con Cliente (muchas mesas pueden haber sido usadas por muchos clientes, pero solo 1 activo)
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    // --- Constructores ---
    public Mesa() {}

    public Mesa(Integer numero, Integer capacidad, String estado, Cliente cliente) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.cliente = cliente;
    }

    // --- Getters y Setters ---
    public Long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
