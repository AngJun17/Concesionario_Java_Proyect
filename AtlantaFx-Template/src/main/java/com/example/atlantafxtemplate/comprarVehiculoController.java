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
import javafx.scene.image.Image;

import java.io.File;
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

        for (Carro carro : carros) {
            VBox card = crearCardCarros(carro);
            contenedorProductos.getChildren().add(card);
        }

    }

    @FXML
    private FlowPane contenedorProductos;

    private VBox crearCardCarros(Carro carro) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);
        box.setPrefWidth(280);
        box.setStyle("-fx-background-color: #2DFCD9; -fx-padding: 10; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");

        ImageView image = new ImageView();
        image.setFitHeight(146);
        image.setFitWidth(241);
        image.setPreserveRatio(true);


        image.setPreserveRatio(true);

        try {
            String basePath = "C:\\Users\\Angel\\OneDrive\\Desktop\\Segundo Semestre\\Progra II\\Java\\ProyectoFinal\\Concesionario_Java_Proyect\\AtlantaFx-Template\\src\\main\\resources\\Imagenes";
            File file = new File(basePath, carro.getImagen());
            if (file.exists()) {
                image.setImage(new Image(file.toURI().toString()));
            } else {
                // If car image doesn't exist, try to load default image
                String defaultImagePath = "C:\\Users\\Angel\\OneDrive\\Desktop\\Segundo Semestre\\Progra II\\Java\\ProyectoFinal\\Concesionario_Java_Proyect\\AtlantaFx-Template\\src\\main\\resources\\Imagenes\\default.png";
                URL resourceUrl = getClass().getResource(defaultImagePath);

                if (resourceUrl != null) {
                    image.setImage(new Image(resourceUrl.toExternalForm()));
                } else {
                    // If default image can't be loaded, create a placeholder
                    image.setStyle("-fx-background-color: lightgray;");
                }
            }
        } catch (Exception e) {
            // If any error occurs, use a styled placeholder
            image.setStyle("-fx-background-color: lightgray;");
            System.err.println("Error loading image for car: " + carro.getMarca() + " " + carro.getModelo());
            e.printStackTrace();
        }
        Label nombre = new Label(carro.getMarca() + " " + carro.getModelo());
        Label precio = new Label("Precio: Q." + carro.getPrecio());
        Label anio = new Label("Año: " + carro.getAnio());
        Label estado = new Label("Estado: " + carro.getEstado());

        Button btn = new Button("Cotizar");
        btn.setStyle("-fx-background-color: #36F5A4; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        btn.setOnAction(e -> sceneChanger.cambiarEscena("cotizador-view.fxml", e));

        box.getChildren().addAll(image, nombre, anio, estado, precio, btn);
        return box;
    }

}
