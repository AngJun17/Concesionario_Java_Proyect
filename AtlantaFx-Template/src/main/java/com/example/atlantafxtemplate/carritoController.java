package com.example.atlantafxtemplate;

import com.example.atlantafxtemplate.sceneChanger;
import com.example.atlantafxtemplate.Logica.CarritoManager;
import com.example.atlantafxtemplate.Modelo.CarritoItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class carritoController implements Initializable {
    @FXML
    private ScrollPane ScrollPaneCarrito;
    
    @FXML
    private VBox carritoItemsContainer;
    
    @FXML  // Add this annotation
    private Label totalLabel;

    private CarritoManager carritoManager = CarritoManager.getInstance();
    // ... otros campos existentes ...
    
    @FXML
    private AnchorPane AnchorPaneFactura;

    @FXML
    private Button BotonComprarCarrito;

    @FXML
    void carritoPresionado(MouseEvent event) { sceneChanger.cambiarEscena("carrito-view.fxml", event); }

    @FXML
    private TextField detallesDePagoTextField;

    @FXML
    private TextField InformeFacturacionTextField;

    @FXML
    private TextField MetododePagoTextField;


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
        // Eliminar: carritoManager = new CarritoManager();
        actualizarCarrito();
    }

    private void actualizarCarrito() {
        carritoItemsContainer.getChildren().clear();
        
        for (CarritoItem item : carritoManager.getItems()) {
            carritoItemsContainer.getChildren().add(crearItemCard(item));
        }
        
        // Add null check
        if (totalLabel != null) {
            totalLabel.setText(String.format("Total: Q%.2f", carritoManager.calcularTotal()));
        }
    }

    private HBox crearItemCard(CarritoItem item) {
        HBox itemCard = new HBox(10);  // 10 es el spacing entre elementos
        itemCard.setAlignment(Pos.CENTER_LEFT);
        itemCard.setPadding(new Insets(10));
        itemCard.setStyle("-fx-background-color: #71a164; -fx-background-radius: 10;");

        // Crear imagen si existe
        ImageView imageView = new ImageView();
        if (item.getImagen() != null && !item.getImagen().isEmpty()) {
            try {
                // Cargar imagen desde resources
                String ruta = "/com/example/atlantafxtemplate/Imagenes/" + item.getImagen();
                URL imagenUrl = getClass().getResource(ruta);

                if (imagenUrl != null) {
                    Image image = new Image(imagenUrl.toExternalForm());
                    imageView.setImage(image);
                } else {
                    System.out.println("⚠️ Imagen no encontrada en resources: " + ruta);
                }
            } catch (Exception e) {
                System.out.println("❌ Error al cargar la imagen: " + e.getMessage());
            }
        }

        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        itemCard.getChildren().add(imageView);

        // Detalles del producto
        VBox detalles = new VBox(5);
        Label nombreLabel = new Label(item.getNombre());
        Label precioLabel = new Label(String.format("Q%.2f", item.getPrecio()));
        detalles.getChildren().addAll(nombreLabel, precioLabel);

        // Spinner de cantidad
        Spinner<Integer> cantidadSpinner = new Spinner<>(1, 99, item.getCantidad());
        cantidadSpinner.setEditable(true);
        cantidadSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            item.setCantidad(newValue);
            actualizarCarrito();
        });

        // Botón eliminar
        Button eliminarButton = new Button("Eliminar");
        eliminarButton.getStyleClass().add("jfx-button-green");
        eliminarButton.setOnAction(e -> {
            carritoManager.eliminarItem(item);
            actualizarCarrito();
        });

        itemCard.getChildren().addAll(detalles, cantidadSpinner, eliminarButton);
        return itemCard;
    }


    // ... otros campos y métodos existentes ...

    @FXML
    void comprarPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("compraVehiculos-view.fxml", event);
    }

    @FXML
    void cotizarPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("cotizador-view.fxml", event);
    }

    @FXML
    void venderPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("venderVehiculo-view.fxml", event);
    }

    @FXML
    void serviciosPresionado(MouseEvent event) {
        sceneChanger.cambiarEscena("servicios-view.fxml", event);
    }

    @FXML
    void salirSesion(MouseEvent event) {
        sceneChanger.exitConfirmation(event);
    }

}