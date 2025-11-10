package com.saborgourmet.restaurante.domain.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "auditoria")
public class Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tabla;

    @Column(name = "id_registro")
    private Long idRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private String usuario;

    private String tipo; // crear, actualizar, eliminar

    public Auditoria() {}

    public Auditoria(String tabla, Long idRegistro, Date fecha, String usuario, String tipo) {
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
