package org.example.bibliotecajavafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Libros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Titulo;

    @Column(nullable = false, unique = true)
    private String ISBN;

    private String autor;

    private String editorial;

    private Date anyoPubli;

    //CONSTRUCTORES
    public Libros(){

    }

    public Libros(Integer id, String titulo, String ISBN, String autor, String editorial, Date anyoPubli) {
        this.id = id;
        Titulo = titulo;
        this.ISBN = ISBN;
        this.autor = autor;
        this.editorial = editorial;
        this.anyoPubli = anyoPubli;
    }

    //GETTER Y SETTER
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Date getAnyoPubli() {
        return anyoPubli;
    }

    public void setAnyoPubli(Date anyoPubli) {
        this.anyoPubli = anyoPubli;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Libros{" +
                "id=" + id +
                ", Titulo='" + Titulo + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anyoPubli=" + anyoPubli +
                '}';
    }
}
