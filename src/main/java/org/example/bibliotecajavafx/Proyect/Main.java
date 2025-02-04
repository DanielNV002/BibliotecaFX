package org.example.bibliotecajavafx.Proyect;

import org.example.bibliotecajavafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        crearTablas();
    }
    public static void crearTablas() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }
}
