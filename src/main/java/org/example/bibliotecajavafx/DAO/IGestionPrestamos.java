package org.example.bibliotecajavafx.DAO;

import org.example.bibliotecajavafx.entities.Prestamos;

import java.util.List;

public interface IGestionPrestamos {

    // Método para registrar un préstamo de libro a un socio
    void addPrestamo(Prestamos prestamo);

    // Método para listar los libros actualmente prestados
    List<Prestamos> listarPrestamosXLibro(String titulo);

    // Método para listar el historial de préstamos de un socio dado su nombre
    List<Prestamos> listarHistorialPrestamos(String nombreSocio);
}
