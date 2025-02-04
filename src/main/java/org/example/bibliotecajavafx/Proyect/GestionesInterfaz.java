package org.example.bibliotecajavafx.Proyect;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GestionesInterfaz {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}