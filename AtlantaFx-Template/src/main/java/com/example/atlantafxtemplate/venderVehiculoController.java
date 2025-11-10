package com.example.atlantafxtemplate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class venderVehiculoController implements Initializable {
    @FXML
    private Button ButtonSubirFotos;

    @FXML
    private ImageView ImagenSubida1;

    @FXML
    private ImageView ImagenSubida2;

    @FXML
    private VBox VBoxDatos;

    @FXML
    private ImageView carritoImage;

    @FXML
    private ComboBox<?> comboBoxAnio;

    @FXML
    private Button comprarButton;

    @FXML
    private Button cotizarButton;

    @FXML
    private TextField estadoVehiculoTextField;

    @FXML
    private ImageView exitImage;

    @FXML
    private FlowPane flowPaneFotos;

    @FXML
    private TextField informacionVehiculoTextFIeld;

    @FXML
    private TextField precioTextField;

    @FXML
    private Button serviciosButton;

    @FXML
    private Button venderBuatton;

    @FXML
    private AnchorPane venderMainPane;

    @FXML
    void subirFotos(MouseEvent event) {

    }

    @FXML
    void publicarVehiculo(MouseEvent event) {

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
        sceneChanger.makeDraggable(venderMainPane);
    }
}
