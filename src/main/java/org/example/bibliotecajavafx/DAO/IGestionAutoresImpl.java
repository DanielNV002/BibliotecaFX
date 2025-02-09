package org.example.bibliotecajavafx.DAO;

import javafx.scene.control.ChoiceBox;
import org.example.bibliotecajavafx.Util.HibernateUtil;
import org.example.bibliotecajavafx.entities.Autores;
import org.example.bibliotecajavafx.entities.Libros;
import org.example.bibliotecajavafx.entities.Socios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

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
        // Crear sesión de Hibernate
        Session session = null;

        try {
            // Abrir sesión con la configuración de Hibernate
            session = new Configuration().configure().addAnnotatedClass(Autores.class).buildSessionFactory().openSession();

            // Realizar la consulta para obtener todos los libros
            String hql = "FROM Autores";  // HQL para obtener todos los libros
            Query<Autores> query = session.createQuery(hql, Autores.class);
            List<Autores> autoresList = query.getResultList();  // Ejecutar la consulta y obtener la lista

            // Limpiar el ChoiceBox antes de agregar nuevos elementos
            choiceBox.getItems().clear();
            // Agregar todos los libros al ChoiceBox
            choiceBox.getItems().addAll(autoresList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateAutor(String nombre, String nacionalidad) {
        // Obtener la sesión de Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciar una transacción
            Transaction transaction = session.beginTransaction();

            // Buscar el socio por el Nombre
            Autores autor = session.createQuery("FROM Autores WHERE Nombre LIKE :Nombre", Autores.class)
                    .setParameter("Nombre",nombre)
                    .uniqueResult();

            // Si se encuentra el libro, actualizarlo
            if (autor != null) {
                autor.setNombre(nombre);
                autor.setNacionalidad(nacionalidad);

                // Guardar los cambios
                session.merge(autor);
                transaction.commit();  // Confirmar la transacción
            } else {
                System.out.println("Autor no encontrado con el Nombre proporcionado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de excepciones, en caso de que ocurra algún error en la base de datos
        }
    }

    @Override
    public void deleteAutor(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciar una transacción
            Transaction transaction = session.beginTransaction();

            // Buscar el libro por el ISBN
            Autores autor = session.createQuery("FROM Autores WHERE Nombre = :Nombre", Autores.class)
                    .setParameter("Nombre", nombre)
                    .uniqueResult();

            // Si el libro existe, lo eliminamos
            if (autor != null) {
                session.remove(autor); // Eliminar el libro de la base de datos
                transaction.commit();   // Confirmar la transacción
                System.out.println("Autor eliminado con éxito.");
            } else {
                System.out.println("No se encontró el Autor con el Nombre proporcionado.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir la traza
        }
    }

    @Override
    public Autores searchAutor(String nombre) {
        Autores autorEncontrado = null;

        try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Autores.class).buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            String hql = "FROM Autores a WHERE a.Nombre LIKE :Nombre";
            Query<Autores> query = session.createQuery(hql, Autores.class);
            query.setParameter("Nombre", "%" + nombre + "%");

            autorEncontrado = query.getResultList().stream().findFirst().orElse(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return autorEncontrado;
    }
}
