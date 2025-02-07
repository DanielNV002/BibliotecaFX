package org.example.bibliotecajavafx.DAO;

import javafx.scene.control.ChoiceBox;
import org.example.bibliotecajavafx.Util.HibernateUtil;
import org.example.bibliotecajavafx.entities.Libros;
import org.example.bibliotecajavafx.entities.Socios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class IGestionSociosImpl implements IGestionSocios {

    @Override
    public void addSocio(Socios socio) {
        // Configuración de Hibernate
        Session session = null;
        Transaction transaction = null;

        try {
            // Crear sesión de Hibernate utilizando SessionFactory
            session = new Configuration().configure().addAnnotatedClass(Libros.class).buildSessionFactory().openSession();

            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Guardar el libro (se genera el id automáticamente por Hibernate si es un @GeneratedValue)
            session.persist(socio);

            // Confirmar la transacción
            transaction.commit();

            System.out.println("Socio agregado con éxito: " + socio.getNombre());
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
    public void loadSocios(ChoiceBox<Socios> choiceBox) {
        // Crear sesión de Hibernate
        Session session = null;

        try {
            // Abrir sesión con la configuración de Hibernate
            session = new Configuration().configure().addAnnotatedClass(Libros.class).buildSessionFactory().openSession();

            // Realizar la consulta para obtener todos los libros
            String hql = "FROM Socios";  // HQL para obtener todos los libros
            Query<Socios> query = session.createQuery(hql, Socios.class);
            List<Socios> socioList = query.getResultList();  // Ejecutar la consulta y obtener la lista

            // Limpiar el ChoiceBox antes de agregar nuevos elementos
            choiceBox.getItems().clear();
            // Agregar todos los libros al ChoiceBox
            choiceBox.getItems().addAll(socioList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateSocio(String nombre, String direccion, Integer NTelefono) {
        // Obtener la sesión de Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciar una transacción
            Transaction transaction = session.beginTransaction();

            // Buscar el socio por el Nombre
            Socios socio = session.createQuery("FROM Socios WHERE Nombre LIKE :Nombre", Socios.class)
                    .setParameter("Nombre",nombre)
                    .uniqueResult();

            // Si se encuentra el libro, actualizarlo
            if (socio != null) {
                socio.setNombre(nombre);
                socio.setDireccion(direccion);
                socio.setNTelefono(NTelefono);

                // Guardar los cambios
                session.merge(socio);
                transaction.commit();  // Confirmar la transacción
            } else {
                System.out.println("Socio no encontrado con el Nombre proporcionado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de excepciones, en caso de que ocurra algún error en la base de datos
        }
    }

    @Override
    public void deleteSocio(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciar una transacción
            Transaction transaction = session.beginTransaction();

            // Buscar el libro por el ISBN
            Socios socio = session.createQuery("FROM Socios WHERE Nombre = :Nombre", Socios.class)
                    .setParameter("Nombre", nombre)
                    .uniqueResult();

            // Si el libro existe, lo eliminamos
            if (socio != null) {
                session.remove(socio); // Eliminar el libro de la base de datos
                transaction.commit();   // Confirmar la transacción
                System.out.println("Socio eliminado con éxito.");
            } else {
                System.out.println("No se encontró el Socio con el Nombre proporcionado.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir la traza
        }
    }

    @Override
    public Socios searchSocio(String nombre, String NTelefono) {
        // Crear sesión de Hibernate
        Session session = null;
        String hql = "FROM Socios s WHERE ";
        Socios socioEncontrado = null;

        // Condiciones para construir la consulta
        boolean firstCondition = true;

        // Concatenamos las condiciones de búsqueda
        if (nombre != null && !nombre.isEmpty()) {
            hql += "s.Nombre LIKE :Nombre";
            firstCondition = false;
        }

        if (NTelefono != null && !NTelefono.isEmpty()) {
            if (!firstCondition) {
                hql += " AND ";
            }
            hql += "s.NTelefono LIKE :NTelefono";
        }

        // Crear la sesión de Hibernate y ejecutar la consulta
        try {
            // Abrir sesión con la configuración de Hibernate
            session = new Configuration().configure().addAnnotatedClass(Socios.class).buildSessionFactory().openSession();

            // Crear la consulta HQL
            Query<Socios> query = session.createQuery(hql, Socios.class);

            // Establecer los parámetros de la consulta
            if (nombre != null && !nombre.isEmpty()) {
                query.setParameter("Nombre", "%" + nombre + "%");
            }
            if (NTelefono != null && !NTelefono.isEmpty()) {
                query.setParameter("NTelefono", "%" + NTelefono + "%");
            }

            // Ejecutar la consulta y obtener la lista de resultados
            List<Socios> sociosList = query.getResultList();
            socioEncontrado = sociosList.getFirst();  // Obtener el primer resultado

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return socioEncontrado;
    }

}
