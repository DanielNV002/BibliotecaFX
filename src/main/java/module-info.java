module org.example.bibliotecajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires jakarta.persistence;


    exports org.example.bibliotecajavafx.Proyect;
    opens org.example.bibliotecajavafx.Proyect to javafx.fxml;
}