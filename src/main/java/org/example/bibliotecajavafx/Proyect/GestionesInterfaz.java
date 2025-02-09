package org.example.bibliotecajavafx.Proyect;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.example.bibliotecajavafx.DAO.IGestionAutoresImpl;
import org.example.bibliotecajavafx.DAO.IGestionLibrosImpl;
import org.example.bibliotecajavafx.DAO.IGestionSociosImpl;
import org.example.bibliotecajavafx.entities.Autores;
import org.example.bibliotecajavafx.entities.Libros;
import org.example.bibliotecajavafx.entities.Socios;

import java.io.IOException;

public class GestionesInterfaz {

    @FXML
    private VBox GLibros;
    @FXML
    private VBox GSocios;
    @FXML
    private VBox GAutores;
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
    private TextField nombreSocio;
    @FXML
    private TextField TlfSocio;
    @FXML
    private TextField DirecSocio;
    @FXML
    private ChoiceBox<Socios> listaSocios;

    @FXML
    private TextField nombreAutor;
    @FXML
    private TextField NacionalAutor;
    @FXML
    private ChoiceBox<Autores> listaAutores;



    @FXML
    private void initialize() {
        // Cargar los libros cuando la vista haya sido completamente inicializada
        new IGestionLibrosImpl().loadLibros(listaLibros);
        new IGestionSociosImpl().loadSocios(listaSocios);
        new IGestionAutoresImpl().loadAutores(listaAutores);
        if (ISBNLibro != null) {
            // Limitar a 10 caracteres al presionar teclas en el TextField ISBNLibro
            ISBNLibro.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (ISBNLibro.getText().length() >= 10) {
                    event.consume(); // Descartar el evento si el límite se ha alcanzado
                }
            });
        }
        if (TlfSocio != null) {
            // Limitar a 10 caracteres al presionar teclas en el TextField ISBNLibro
            TlfSocio.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (TlfSocio.getText().length() >= 10) {
                    event.consume(); // Descartar el evento si el límite se ha alcanzado
                }
            });
        }
    }


    //BOTON DE SALIR
    @FXML
    private void handleExitButtonClick() {
        // Cerrar la aplicación usando Platform.exit() o primaryStage.close()
        Platform.exit();  // Esto cierra toda la aplicación
        System.exit(0);  // Esto garantiza que la aplicación termine correctamente
    }

    //Cambiar de Pantalla
    @FXML
    private void GestionLibroOnClick() throws IOException {
        new SceneSelector(PPrincipal, "/org/example/bibliotecajavafx/GestionLibros.fxml");
    }
    @FXML
    private void GestionSociosOnClick() throws IOException {
        new SceneSelector(PPrincipal, "/org/example/bibliotecajavafx/GestionSocios.fxml");
    }
    @FXML
    private void GestionAutoresOnClick() throws IOException {
        new SceneSelector(PPrincipal, "/org/example/bibliotecajavafx/GestionAutores.fxml");
    }

    //Botones Back
    @FXML
    private void BackButtonLibros() throws IOException {
        new SceneSelector(GLibros, "/org/example/bibliotecajavafx/PantallaPrincipal.fxml");
    }
    @FXML
    private void BackButtonSocios() throws IOException {
        new SceneSelector(GSocios, "/org/example/bibliotecajavafx/PantallaPrincipal.fxml");
    }
    @FXML
    private void BackButtonAutores() throws IOException {
        new SceneSelector(GAutores, "/org/example/bibliotecajavafx/PantallaPrincipal.fxml");
    }

    //METODOS DE LIBROS
    @FXML
    private void addLibro() {
        new IGestionLibrosImpl().addLibro(new Libros(null, tituloLibro.getText(), ISBNLibro.getText(), autorLibro.getText(), editorialLibro.getText(), Integer.parseInt(anyoPubliLibro.getText())));
        cleanLibro();
        new IGestionLibrosImpl().loadLibros(listaLibros);
    }

    @FXML
    private void updateLibro() {
        new IGestionLibrosImpl().updateLibro(ISBNLibro.getText(), tituloLibro.getText(), autorLibro.getText(), editorialLibro.getText(), Integer.parseInt(anyoPubliLibro.getText()));
        cleanLibro();
        new IGestionLibrosImpl().loadLibros(listaLibros);
    }

    @FXML
    private void deleteLibro() {
        new IGestionLibrosImpl().deleteLibro(ISBNLibro.getText());
        cleanLibro();
        new IGestionLibrosImpl().loadLibros(listaLibros);
    }

    @FXML
    private void searchLibro(){

        Libros L = new IGestionLibrosImpl().searchLibro(tituloLibro.getText(), autorLibro.getText(), ISBNLibro.getText());

        tituloLibro.setText(L.getTitulo());
        autorLibro.setText(L.getAutor());
        ISBNLibro.setText(L.getISBN());
        editorialLibro.setText(L.getEditorial());
        anyoPubliLibro.setText(Integer.toString(L.getAnyoPubli()));
    }

    @FXML
    private void cargarChoiceBoxLibros() {
        listaLibros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Rellenar los campos con los datos del libro seleccionado
                tituloLibro.setText(newValue.getTitulo());
                ISBNLibro.setText(newValue.getISBN());
                ISBNLibro.setEditable(false);
                autorLibro.setText(newValue.getAutor());
                editorialLibro.setText(newValue.getEditorial());
                anyoPubliLibro.setText(String.valueOf(newValue.getAnyoPubli())); // Convertir Int a String
            }
        });
    }

    @FXML
    private void cleanLibro() {
        tituloLibro.setText("");
        tituloLibro.setEditable(true);
        ISBNLibro.setText("");
        ISBNLibro.setEditable(true);
        autorLibro.setText("");
        autorLibro.setEditable(true);
        editorialLibro.setText("");
        editorialLibro.setEditable(true);
        anyoPubliLibro.setText("");
        anyoPubliLibro.setEditable(true);
    }


    //METODOS DE SOCIOS
    @FXML
    private void addSocio() {
        new IGestionSociosImpl().addSocio(new Socios(null, nombreSocio.getText(), DirecSocio.getText(), Integer.parseInt(TlfSocio.getText())));
        cleanSocio();
        new IGestionSociosImpl().loadSocios(listaSocios);
    }

    @FXML
    private void updateSocio() {
        new IGestionSociosImpl().updateSocio(nombreSocio.getText(), DirecSocio.getText(), Integer.parseInt(TlfSocio.getText()));
        cleanSocio();
        new IGestionSociosImpl().loadSocios(listaSocios);
    }

    @FXML
    private void deleteSocio() {
        new IGestionSociosImpl().deleteSocio(nombreSocio.getText());
        cleanSocio();
        new IGestionSociosImpl().loadSocios(listaSocios);
    }

    @FXML
    private void searchSocio(){

        Socios S = new IGestionSociosImpl().searchSocio(nombreSocio.getText(), TlfSocio.getText());

        nombreSocio.setText(S.getNombre());
        DirecSocio.setText(S.getDireccion());
        TlfSocio.setText(S.getNTelefono().toString());
    }

    @FXML
    private void cargarChoiceBoxSocios() {
        listaSocios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Rellenar los campos con los datos del libro seleccionado
                nombreSocio.setText(newValue.getNombre());
                nombreSocio.setEditable(false);
                TlfSocio.setText(newValue.getNTelefono().toString());
                DirecSocio.setText(newValue.getDireccion());
            }
        });
    }

    @FXML
    private void cleanSocio() {
        nombreSocio.setText("");
        nombreSocio.setEditable(true);
        DirecSocio.setText("");
        DirecSocio.setEditable(true);
        TlfSocio.setText("");
        TlfSocio.setEditable(true);
    }


    //METODOS DE AUTORES
    @FXML
    private void addAutor() {
        new IGestionAutoresImpl().addAutor(new Autores(null, nombreAutor.getText(), NacionalAutor.getText()));
        cleanAutor();
        new IGestionAutoresImpl().loadAutores(listaAutores);
    }

    @FXML
    private void updateAutor() {
        new IGestionAutoresImpl().updateAutor(nombreAutor.getText(), NacionalAutor.getText());
        cleanAutor();
        new IGestionAutoresImpl().loadAutores(listaAutores);
    }

    @FXML
    private void deleteAutor() {
        new IGestionAutoresImpl().deleteAutor(nombreAutor.getText());
        cleanAutor();
        new IGestionAutoresImpl().loadAutores(listaAutores);
    }

    @FXML
    private void searchAutor(){

        Autores A = new IGestionAutoresImpl().searchAutor(nombreAutor.getText());

        nombreAutor.setText(A.getNombre());
        NacionalAutor.setText(A.getNacionalidad());
    }

    @FXML
    private void cargarChoiceBoxAutores() {
        listaAutores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Rellenar los campos con los datos del libro seleccionado
                nombreAutor.setText(newValue.getNombre());
                nombreAutor.setEditable(false);
                NacionalAutor.setText(newValue.getNacionalidad());
            }
        });
    }

    @FXML
    private void cleanAutor() {
        nombreAutor.setText("");
        nombreAutor.setEditable(true);
        NacionalAutor.setText("");
        NacionalAutor.setEditable(true);
    }

    //METODOS DE PRESTAMOS

}