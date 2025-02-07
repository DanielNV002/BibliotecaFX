package org.example.bibliotecajavafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Socios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Nombre;

    private String Direccion;

    private Integer NTelefono;


    //CONSTRUCTORES
    public Socios(){

    }

    public Socios(Integer id, String nombre, String direccion, Integer NTelefono) {
        this.id = id;
        Nombre = nombre;
        Direccion = direccion;
        this.NTelefono = NTelefono;
    }

    //GETTER Y SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public Integer getNTelefono() {
        return NTelefono;
    }

    public void setNTelefono(Integer NTelefono) {
        this.NTelefono = NTelefono;
    }

    //ToString

    @Override
    public String toString() {
        return Nombre;
    }
}
