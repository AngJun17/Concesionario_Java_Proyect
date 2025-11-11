package com.example.atlantafxtemplate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class carritoController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneFactura;

    @FXML
    private AnchorPane AnchorPaneScrollPaneCarrito;

    @FXML
    private Button BotonComprarCarrito;

    @FXML
    private ImageView ImagenProducto1;

    @FXML
    private TextField InformeFacturacionTextField;

    @FXML
    private TextField MetododePagoTextField;

    @FXML
    private ScrollPane ScrollPaneCarrito;

    @FXML
    private ImageView carritoImage;

    @FXML
    private AnchorPane carritoMainPane;

    @FXML
    private Button comprarButton;

    @FXML
    private Button cotizarButton;

    @FXML
    private TextField detallesDePagoTextField;

    @FXML
    private ImageView exitImage;

    @FXML
    private Label returnCarrito;

    @FXML
    private Button serviciosButton;

    @FXML
    private TextArea textAreaDetalles1;

    @FXML
    private Button venderBuatton;

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

    @FXML
    void botonComprarCarritoPressed(MouseEvent event) {
        AnchorPaneFactura.setVisible(true);
        ScrollPaneCarrito.setVisible(false);
    }

    @FXML
    void returnCarrito(MouseEvent event) {
        detallesDePagoTextField.setText("");
        InformeFacturacionTextField.setText("");
        MetododePagoTextField.setText("");
        AnchorPaneFactura.setVisible(false);
        ScrollPaneCarrito.setVisible(true);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScrollPaneCarrito.setVisible(true);
        AnchorPaneFactura.setVisible(false);
        sceneChanger.makeDraggable(carritoMainPane);
    }
}
