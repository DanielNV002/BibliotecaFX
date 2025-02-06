package org.example.bibliotecajavafx.Proyect;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.example.bibliotecajavafx.DAO.IGestionLibrosImpl;
import org.example.bibliotecajavafx.entities.Libros;

import java.io.IOException;

public class GestionesInterfaz {

    @FXML
    private VBox GLibros;
    @FXML
    private VBox GSocios;
    @FXML
    private VBox PPrincipal;
    @FXML
    private TextField tituloLibro;
    @FXML
    private TextField ISBNLibro;
    @FXML
    private TextField autorLibro;
    @FXML
    private TextField editorialLibro;
    @FXML
    private TextField anyoPubliLibro;
    @FXML
    private ChoiceBox<Libros> listaLibros;

    @FXML
    private void initialize() {
        // Cargar los libros cuando la vista haya sido completamente inicializada
        new IGestionLibrosImpl().loadLibros(listaLibros);
        if (ISBNLibro != null) {
            // Limitar a 10 caracteres al presionar teclas en el TextField ISBNLibro
            ISBNLibro.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (ISBNLibro.getText().length() >= 10) {
                    event.consume(); // Descartar el evento si el límite se ha alcanzado
                }
            });
        }
    }

    private void clean() {
        tituloLibro.setText("");
        ISBNLibro.setText("");
        autorLibro.setText("");
        editorialLibro.setText("");
        anyoPubliLibro.setText("");
    }

    @FXML
    private void handleExitButtonClick() {
        // Cerrar la aplicación usando Platform.exit() o primaryStage.close()
        Platform.exit();  // Esto cierra toda la aplicación
        System.exit(0);  // Esto garantiza que la aplicación termine correctamente
    }

    @FXML
    private void GestionLibroOnClick() throws IOException {
        new SceneSelector(PPrincipal, "/org/example/bibliotecajavafx/GestionLibros.fxml");
    }

    @FXML
    private void GestionSociosOnClick() throws IOException {
        new SceneSelector(PPrincipal, "/org/example/bibliotecajavafx/GestionSocios.fxml");
    }

    @FXML
    private void BackButtonLibros() throws IOException {
        new SceneSelector(GLibros, "/org/example/bibliotecajavafx/PantallaPrincipal.fxml");
    }
    @FXML
    private void BackButtonSocios() throws IOException {
        new SceneSelector(GSocios, "/org/example/bibliotecajavafx/PantallaPrincipal.fxml");
    }

    @FXML
    private void addLibro() {
        new IGestionLibrosImpl().addLibro(new Libros(null, tituloLibro.getText(), ISBNLibro.getText(), autorLibro.getText(), editorialLibro.getText(), Integer.parseInt(anyoPubliLibro.getText())));
        clean();
        new IGestionLibrosImpl().loadLibros(listaLibros);
    }

    @FXML
    private void cargarChoiceBox() {
        listaLibros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Rellenar los campos con los datos del libro seleccionado
                tituloLibro.setText(newValue.getTitulo());
                ISBNLibro.setText(newValue.getISBN());
                autorLibro.setText(newValue.getAutor());
                editorialLibro.setText(newValue.getEditorial());
                anyoPubliLibro.setText(String.valueOf(newValue.getAnyoPubli())); // Convertir Int a String
            }
        });
    }

    @FXML
    private void updateLibro() {
        new IGestionLibrosImpl().updateLibro(ISBNLibro.getText(), tituloLibro.getText(), autorLibro.getText(), editorialLibro.getText(), Integer.parseInt(anyoPubliLibro.getText()));
        clean();
        new IGestionLibrosImpl().loadLibros(listaLibros);
    }

    @FXML
    private void deleteLibro() {
        new IGestionLibrosImpl().deleteLibro(ISBNLibro.getText());
        clean();
        new IGestionLibrosImpl().loadLibros(listaLibros);
    }
}