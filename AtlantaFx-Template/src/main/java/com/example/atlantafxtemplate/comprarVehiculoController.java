package com.example.atlantafxtemplate;

import com.example.atlantafxtemplate.Logica.VenderManager;
import com.example.atlantafxtemplate.Modelo.Carro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

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
        box.setPrefWidth(260);
        box.setSpacing(10);
        box.setMinHeight(300);  // Altura mínima fija
        box.setMaxHeight(350);
        box.setStyle("-fx-background-color: #99deaf; -fx-padding: 10; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 3);");

        ImageView image = new ImageView();
        image.setFitHeight(150);
        image.setFitWidth(241);
        image.setPreserveRatio(true);


        image.setPreserveRatio(true);

        try {
            String basePath = "C:\\Users\\Angel\\OneDrive\\Desktop\\Segundo Semestre\\Progra II\\Java\\ProyectoFinal\\Concesionario_Java_Proyect\\AtlantaFx-Template\\src\\main\\resources\\Imagenes";
            File file = new File(basePath, carro.getImagen());
            if (file.exists()) {
                image.setImage(new Image(file.toURI().toString()));
            } else {
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


        Button btn = new Button("COTIZAR");
        btn.setStyle("-fx-background-color: #4d9179; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        btn.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cotizador-view.fxml"));
                Parent root = loader.load();

                // obtener el controlador  del FXML
                cotizadorController cot = loader.getController();
                cot.setDatos(carro.getPrecio());

                // cambiar la escena
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        box.getChildren().addAll(image, nombre, anio, estado, precio, btn);
        return box;
    }

}
