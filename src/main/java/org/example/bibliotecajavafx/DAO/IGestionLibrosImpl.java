package org.example.bibliotecajavafx.DAO;

import javafx.scene.control.ChoiceBox;
import org.example.bibliotecajavafx.Util.HibernateUtil;
import org.example.bibliotecajavafx.entities.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class IGestionLibrosImpl implements IGestionLibros {

    @Override
    public void addLibro(Libros libro) {
        // Configuración de Hibernate
        Session session = null;
        Transaction transaction = null;

        try {
            // Crear sesión de Hibernate utilizando SessionFactory
            session = new Configuration().configure().addAnnotatedClass(Libros.class).buildSessionFactory().openSession();

            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Guardar el libro (se genera el id automáticamente por Hibernate si es un @GeneratedValue)
            session.persist(libro);

            // Confirmar la transacción
            transaction.commit();

            System.out.println("Libro agregado con éxito: " + libro.getTitulo());
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
    public void loadLibros(ChoiceBox<Libros> choiceBox) {
        // Crear sesión de Hibernate
        Session session = null;

        try {
            // Abrir sesión con la configuración de Hibernate
            session = new Configuration().configure().addAnnotatedClass(Libros.class).buildSessionFactory().openSession();

            // Realizar la consulta para obtener todos los libros
            String hql = "FROM Libros";  // HQL para obtener todos los libros
            Query<Libros> query = session.createQuery(hql, Libros.class);
            List<Libros> librosList = query.getResultList();  // Ejecutar la consulta y obtener la lista

            // Limpiar el ChoiceBox antes de agregar nuevos elementos
            choiceBox.getItems().clear();
            // Agregar todos los libros al ChoiceBox
            choiceBox.getItems().addAll(librosList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateLibro(String ISBN, String titulo, String autor, String editorial, int anyoPubli) {
        // Obtener la sesión de Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciar una transacción
            Transaction transaction = session.beginTransaction();

            // Buscar el libro por el ISBN
            Libros libro = session.createQuery("FROM Libros WHERE ISBN = :isbn", Libros.class)
                    .setParameter("isbn", ISBN)
                    .uniqueResult();

            // Si se encuentra el libro, actualizarlo
            if (libro != null) {
                libro.setTitulo(titulo);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setAnyoPubli(anyoPubli);

                // Guardar los cambios
                session.merge(libro);
                transaction.commit();  // Confirmar la transacción
            } else {
                System.out.println("Libro no encontrado con el ISBN proporcionado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de excepciones, en caso de que ocurra algún error en la base de datos
        }
    }

    @Override
    public void deleteLibro(String ISBN) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciar una transacción
            Transaction transaction = session.beginTransaction();

            // Buscar el libro por el ISBN
            Libros libro = session.createQuery("FROM Libros WHERE ISBN = :isbn", Libros.class)
                    .setParameter("isbn", ISBN)
                    .uniqueResult();

            // Si el libro existe, lo eliminamos
            if (libro != null) {
                session.remove(libro); // Eliminar el libro de la base de datos
                transaction.commit();   // Confirmar la transacción
                System.out.println("Libro eliminado con éxito.");
            } else {
                System.out.println("No se encontró el libro con el ISBN proporcionado.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir la traza
        }
    }

    @Override
    public Libros searchLibro(String titulo, String autor, String ISBN) {
        // Crear sesión de Hibernate
        Session session = null;
        String hql = "FROM Libros l WHERE ";
        Libros libroEncontrado = null;

        // Condiciones para construir la consulta
        boolean firstCondition = true;

        // Concatenamos las condiciones de búsqueda
        if (titulo != null && !titulo.isEmpty()) {
            hql += "l.titulo LIKE :titulo";
            firstCondition = false;
        }

        if (autor != null && !autor.isEmpty()) {
            if (!firstCondition) {
                hql += " AND ";
            }
            hql += "l.autor LIKE :autor";
            firstCondition = false;
        }

        if (ISBN != null && !ISBN.isEmpty()) {
            if (!firstCondition) {
                hql += " AND ";
            }
            hql += "l.ISBN LIKE :ISBN";
        }
        // Crear la sesión de Hibernate y ejecutar la consulta
        try {
            // Abrir sesión con la configuración de Hibernate
            session = new Configuration().configure().addAnnotatedClass(Libros.class).buildSessionFactory().openSession();

            // Crear la consulta HQL
            Query<Libros> query = session.createQuery(hql, Libros.class);

            // Establecer los parámetros de la consulta
            if (titulo != null && !titulo.isEmpty()) {
                query.setParameter("titulo", "%" + titulo + "%");
            }
            if (autor != null && !autor.isEmpty()) {
                query.setParameter("autor", "%" + autor + "%");
            }
            if (ISBN != null && !ISBN.isEmpty()) {
                query.setParameter("ISBN", "%" + ISBN + "%");
            }

            // Ejecutar la consulta y obtener la lista de resultados
            List<Libros> librosList = query.getResultList();
            libroEncontrado = librosList.getFirst();  // Aquí puedes elegir cómo manejar la lista (solo tomo el primer resultado como ejemplo)

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return libroEncontrado;
    }
}
