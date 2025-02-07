package org.example.bibliotecajavafx.DAO;

import javafx.scene.control.ChoiceBox;
import org.example.bibliotecajavafx.entities.Autores;
import org.example.bibliotecajavafx.entities.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class IGestionAutoresImpl implements IGestionAutores {
    @Override
    public void addAutor(Autores autor) {
        // Configuración de Hibernate
        Session session = null;
        Transaction transaction = null;

        try {
            // Crear sesión de Hibernate utilizando SessionFactory
            session = new Configuration().configure().addAnnotatedClass(Autores.class).buildSessionFactory().openSession();

            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Guardar el libro (se genera el id automáticamente por Hibernate si es un @GeneratedValue)
            session.persist(autor);

            // Confirmar la transacción
            transaction.commit();

            System.out.println("Autor agregado con éxito: " + autor.getNombre());
        } catch (Exception e) {
            // Si hay un error, hacer rollback de la transacción
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión para liberar recursos
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void loadAutores(ChoiceBox<Autores> choiceBox) {

    }

    @Override
    public void updateAutor(String nombre, String nacionalidad) {

    }

    @Override
    public void deleteAutor(String nombre) {

    }

    @Override
    public Autores searchAutor(String nombre) {
        return null;
    }
}
