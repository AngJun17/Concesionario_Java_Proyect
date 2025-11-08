module com.example.atlantaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires java.desktop;
    requires com.google.gson;

    opens com.example.atlantafxtemplate to javafx.fxml;
    opens com.example.atlantafxtemplate.Modelo to com.google.gson;

    exports com.example.atlantafxtemplate;

}
