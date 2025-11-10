package com.example.atlantafxtemplate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class serviciosController implements Initializable {
    @FXML
    private Button ButtonCleanServicios;

    @FXML
    private Button ButtonRepuesto1;

    @FXML
    private ImageView ImagenRepuesto1;

    @FXML
    private VBox VBoxProducto1;

    @FXML
    private ImageView carritoImage;

    @FXML
    private Button comprarButton;

    @FXML
    private Button cotizarButton;

    @FXML
    private ImageView exitImage;

    @FXML
    private Label labelNombreRepuesto1;

    @FXML
    private Label labelPrecioRepuesto1;

    @FXML
    private Button serviciosButton;

    @FXML
    private AnchorPane serviciosMainPane;

    @FXML
    private ComboBox<String> tipoVehiculoComboBox;

    @FXML
    private Button venderBuatton;

    @FXML
    void limpiarComboBox(MouseEvent event) {

    }

    @FXML
    void carritoPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("carrito-view.fxml", event);
    }

    @FXML
    void comprarPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("compraVehiculos-view.fxml", event);
    }

    @FXML
    void cotizarPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("cotizador-view.fxml", event);
    }

    @FXML
    void salirSesion(MouseEvent event) {
        sceneChanger.exitConfirmation(event);
    }

    @FXML
    void serviciosPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("servicios-view.fxml", event);
    }

    @FXML
    void venderPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("venderVehiculo-view.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneChanger.makeDraggable(serviciosMainPane);
        tipoVehiculoComboBox.getItems().add("Hola");
        tipoVehiculoComboBox.getItems().add("Hola2");
        tipoVehiculoComboBox.getItems().add("Hola3");
    }
}
