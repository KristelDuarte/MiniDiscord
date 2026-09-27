module cr.ac.una.est.minidiscord {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;

    // Permite que FXMLLoader acceda a los campos @FXML privados por reflexión
    opens cr.ac.una.est.minidiscord.presentation.controller to javafx.fxml;

    // Exporta el paquete principal de la aplicación
    exports cr.ac.una.est.minidiscord;

    // SOLUCIÓN: Exporta públicamente el paquete del controlador
    // para que JavaFX pueda instanciar la clase ChatViewController
    exports cr.ac.una.est.minidiscord.presentation.controller;
}