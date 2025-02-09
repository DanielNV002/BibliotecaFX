package org.example.bibliotecajavafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Prestamos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tituloLibro;
    private String nombreSocio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    //CONSTRUCTORES
    public Prestamos(){
    }

    public Prestamos(Integer id, String tituloLibro, String nombreSocio, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.id = id;
        this.tituloLibro = tituloLibro;
        this.nombreSocio = nombreSocio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    //GETTER Y SETTER
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    //TOSTRING

    @Override
    public String toString() {
        return "Prestamos{" +
                "id=" + id +
                ", tituloLibro='" + tituloLibro + '\'' +
                ", nombreSocio='" + nombreSocio + '\'' +
                '}';
    }
}
