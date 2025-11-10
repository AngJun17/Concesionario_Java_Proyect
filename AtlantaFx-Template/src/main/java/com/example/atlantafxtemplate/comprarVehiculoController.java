package com.example.atlantafxtemplate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class comprarVehiculoController implements Initializable {

    @FXML
    private Button ButtonProducto1;

    @FXML
    private Button ButtonProducto2;

    @FXML
    private Button ButtonProducto3;

    @FXML
    private Button ButtonProducto4;

    @FXML
    private Button ButtonProducto5;

    @FXML
    private Button ButtonProducto6;

    @FXML
    private Button ButtonProducto7;

    @FXML
    private Button ButtonProducto8;

    @FXML
    private Button ButtonProducto9;

    @FXML
    private ImageView ImagenProducto1;

    @FXML
    private ImageView ImagenProducto2;

    @FXML
    private ImageView ImagenProducto3;

    @FXML
    private ImageView ImagenProducto4;

    @FXML
    private ImageView ImagenProducto5;

    @FXML
    private ImageView ImagenProducto6;

    @FXML
    private ImageView ImagenProducto7;

    @FXML
    private ImageView ImagenProducto8;

    @FXML
    private ImageView ImagenProducto9;

    @FXML
    private AnchorPane PaneMarketPlace;

    @FXML
    private ScrollPane ScrollPaneMarketPlace;

    @FXML
    private VBox VBoxProducto1;

    @FXML
    private VBox VBoxProducto2;

    @FXML
    private VBox VBoxProducto3;

    @FXML
    private VBox VBoxProducto4;

    @FXML
    private VBox VBoxProducto5;

    @FXML
    private VBox VBoxProducto6;

    @FXML
    private VBox VBoxProducto7;

    @FXML
    private VBox VBoxProducto8;

    @FXML
    private VBox VBoxProducto9;

    @FXML
    private ImageView carritoImage;

    @FXML
    private Button comprarButton;

    @FXML
    private AnchorPane comprarMainPane;

    @FXML
    private Button cotizarButton;

    @FXML
    private ImageView exitImage;

    @FXML
    private FlowPane flowPane1;

    @FXML
    private FlowPane flowPane2;

    @FXML
    private FlowPane flowPane3;

    @FXML
    private Label labelNombreProducto1;

    @FXML
    private Label labelNombreProducto2;

    @FXML
    private Label labelNombreProducto3;

    @FXML
    private Label labelNombreProducto4;

    @FXML
    private Label labelNombreProducto5;

    @FXML
    private Label labelNombreProducto6;

    @FXML
    private Label labelNombreProducto7;

    @FXML
    private Label labelNombreProducto8;

    @FXML
    private Label labelNombreProducto9;

    @FXML
    private Label labelPrecioProducto1;

    @FXML
    private Label labelPrecioProducto2;

    @FXML
    private Label labelPrecioProducto3;

    @FXML
    private Label labelPrecioProducto4;

    @FXML
    private Label labelPrecioProducto5;

    @FXML
    private Label labelPrecioProducto6;

    @FXML
    private Label labelPrecioProducto7;

    @FXML
    private Label labelPrecioProducto8;

    @FXML
    private Label labelPrecioProducto9;

    @FXML
    private Button serviciosButton;

    @FXML
    private Button venderButton;

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
        sceneChanger.makeDraggable(comprarMainPane);

    }

}
