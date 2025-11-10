package com.example.atlantafxtemplate;

import com.example.atlantafxtemplate.Logica.VenderManager;
import com.example.atlantafxtemplate.Modelo.Carro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class comprarVehiculoController implements Initializable {


    @FXML
    private ScrollPane ScrollPaneMarketPlace;

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

        VenderManager venderManager = new VenderManager();
        List<Carro> carros = venderManager.getCarros();

        for (Carro carro : carros){
            VBox vbox = new VBox();
            contenedorProductos.getChildren().add(vbox);
        }
    }

    @FXML
    private FlowPane contenedorProductos;

    private VBox crearCardCarros(Carro carro){
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);
        box.setStyle("-fx-background-color: transparent;");

        ImageView image = new ImageView(new javafx.scene.image.Image(getClass().getResourceAsStream("Concesionario_Java_Proyect\\Imagenes\"")));
        image.setFitHeight(146);
        image.setFitWidth(241);
        image.setPreserveRatio(true);

        Label nombre = new Label(carro.getMarca() + " " + carro.getModelo());
        Label precio = new Label("$" + carro.getPrecio());

        Button btn = new Button("Cotizar");
        btn.getStyleClass().add("jfx-button-OC");
        btn.setOnAction(e -> {sceneChanger.cambiarEscena("cotizador-view.fxml", null);});

        box.getChildren().addAll(image, nombre, precio,btn);

        return box;

    }
}
