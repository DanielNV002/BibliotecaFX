package org.example.bibliotecajavafx.DAO;

import javafx.scene.control.ChoiceBox;
import org.example.bibliotecajavafx.entities.Libros;

public interface IGestionLibros {

    // Metodo para agregar un libro
    void addLibro(Libros libro);

    // Metodo para cargar los libros en un ChoiceBox
    void loadLibros(ChoiceBox<Libros> choiceBox);

    // Metodo para actualizar un libro dado el ISBN
    void updateLibro(String ISBN, String titulo, String autor, String editorial, int anyoPubli);

    // Metodo para eliminar un libro dado el ISBN
    void deleteLibro(String ISBN);

}
