module com.example.atlantaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires java.desktop;

    opens com.example.atlantafxtemplate to javafx.fxml;
    exports com.example.atlantafxtemplate;
}
