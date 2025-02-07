package org.example.bibliotecajavafx.DAO;

import javafx.scene.control.ChoiceBox;
import org.example.bibliotecajavafx.entities.Autores;

public interface IGestionAutores {

    // Metodo para agregar un autor
    void addAutor(Autores autor);

    // Metodo para cargar los autores en un ChoiceBox
    void loadAutores(ChoiceBox<Autores> choiceBox);

    // Metodo para actualizar un autor dado el id
    void updateAutor(String nombre, String nacionalidad);

    // Metodo para eliminar un autor dado el id
    void deleteAutor(String nombre);

    // Metodo para buscar un autor segun nombre o id
    Autores searchAutor(String nombre);
}
