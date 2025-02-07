package org.example.bibliotecajavafx.DAO;

import javafx.scene.control.ChoiceBox;
import org.example.bibliotecajavafx.entities.Socios;

public interface IGestionSocios {

    // Metodo para agregar un socio
    void addSocio(Socios socio);

    // Metodo para cargar los socios en un ChoiceBox
    void loadSocios(ChoiceBox<Socios> choiceBox);

    // Metodo para actualizar un socio dado el id
    void updateSocio(String nombre, String direccion, Integer NTelefono);

    // Metodo para eliminar un socio dado el id
    void deleteSocio(String nombre);

    // Metodo para buscar un socio segun nombre, direccion o id
    Socios searchSocio(String nombre, String NTelefono);
}
