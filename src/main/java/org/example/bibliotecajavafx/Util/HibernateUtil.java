package org.example.bibliotecajavafx.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase utilitaria para la configuración y creación de una fábrica de sesiones de Hibernate.
 * Proporciona un método estático para obtener una instancia de {@link SessionFactory},
 * que es utilizada para crear sesiones para interactuar con la base de datos.
 *
 * Esta clase se encarga de cargar la configuración de Hibernate a partir de un archivo
 * de configuración estándar y construir la fábrica de sesiones.
 *
 * @author [Daniel Navarro]
 * @version 1.0
 * @since 2025-01-23
 */
public class HibernateUtil {

    /**
     * Obtiene una instancia de {@link SessionFactory} configurada con los parámetros
     * definidos en el archivo de configuración de Hibernate (hibernate.cfg.xml).
     *
     * @return una instancia de {@link SessionFactory} configurada
     */
    public static SessionFactory getSessionFactory(){
        // Crea una instancia de la configuración de Hibernate
        Configuration configuration = new Configuration();

        // Configura Hibernate con el archivo de configuración predeterminado (hibernate.cfg.xml)
        configuration.configure();

        // Devuelve la fábrica de sesiones construida a partir de la configuración
        return configuration.buildSessionFactory();
    }
}
