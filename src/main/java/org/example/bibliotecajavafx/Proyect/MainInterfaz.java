package org.example.bibliotecajavafx.Proyect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.bibliotecajavafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class MainInterfaz extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/bibliotecajavafx/PantallaPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false); // no se puede agrandar o disminuir
        stage.initStyle(StageStyle.UNDECORATED); // elimina los botones de cerrar, minimizar, etc
        stage.getIcons().add(new Image(getClass().getResource("/imagenes/PilaLibros.png").toString()));
        stage.setTitle("BibliotecaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void crearTablas() {
        // Abrir una sesión de Hibernate
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }


    public static void main(String[] args) {
        crearTablas();
        launch();
    }
}

