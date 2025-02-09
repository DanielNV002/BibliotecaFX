package org.example.bibliotecajavafx.DAO;

import org.example.bibliotecajavafx.Util.HibernateUtil;
import org.example.bibliotecajavafx.entities.Prestamos;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class IGestionPrestamosImpl implements IGestionPrestamos {
    @Override
    public void addPrestamo(Prestamos prestamo) {
        // Configuración de Hibernate
        Session session = null;
        Transaction transaction = null;

        try {
            // Crear sesión de Hibernate utilizando SessionFactory
            session = new Configuration().configure().addAnnotatedClass(Prestamos.class).buildSessionFactory().openSession();

            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Guardar el libro (se genera el id automáticamente por Hibernate si es un @GeneratedValue)
            session.persist(prestamo);

            // Confirmar la transacción
            transaction.commit();

            System.out.println("Prestamo agregado con éxito: " + prestamo.getTituloLibro() + "Socio: " + prestamo.getNombreSocio());
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
    public List<Prestamos> listarPrestamosXLibro(String titulo) {
        List<Prestamos> listaPrestamos;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Prestamos p WHERE p.tituloLibro = :titulo";
            Query<Prestamos> query = session.createQuery(hql, Prestamos.class);
            query.setParameter("titulo", titulo);
            listaPrestamos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            listaPrestamos = null;
        }

        return listaPrestamos;
    }

    @Override
    public List<Prestamos> listarHistorialPrestamos(String nombreSocio) {
        List<Prestamos> listaPrestamos;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Prestamos p WHERE p.nombreSocio = :socio";
            Query<Prestamos> query = session.createQuery(hql, Prestamos.class);
            query.setParameter("socio", nombreSocio);
            listaPrestamos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            listaPrestamos = null;
        }

        return listaPrestamos;
    }
}
