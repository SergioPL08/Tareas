module interfaz.tareas2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens interfaz.tareas2 to javafx.fxml;
    exports interfaz.tareas2;
}
