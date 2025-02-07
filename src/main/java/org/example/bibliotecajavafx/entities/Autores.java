package org.example.bibliotecajavafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Autores implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Nombre;

    private String Nacionalidad;


    //CONSTRUCTORES
    public Autores(){

    }

    public Autores(Integer id, String nombre, String nacionalidad) {
        this.id = id;
        Nombre = nombre;
        Nacionalidad = nacionalidad;
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

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    //TOSTRING
    @Override
    public String toString() {
        return Nombre;
    }
}
