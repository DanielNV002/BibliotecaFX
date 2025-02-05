module org.example.bibliotecajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;


    // Exporta los paquetes para otros módulos
    exports org.example.bibliotecajavafx.Proyect;

    // Abre el paquete a javafx.fxml
    opens org.example.bibliotecajavafx.Proyect to javafx.fxml;

    // Abre el paquete de las entidades a Hibernate
    opens org.example.bibliotecajavafx.entities to org.hibernate.orm.core;
}