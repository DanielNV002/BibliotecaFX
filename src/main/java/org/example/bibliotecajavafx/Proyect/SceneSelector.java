package org.example.bibliotecajavafx.Proyect;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class SceneSelector {

    public SceneSelector(VBox currentAnchorPane, String fxml)throws IOException{
        VBox nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(MainInterfaz.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }

}