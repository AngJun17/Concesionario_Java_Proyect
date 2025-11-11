package com.example.atlantafxtemplate;

import com.example.atlantafxtemplate.Logica.CarritoManager;
import com.example.atlantafxtemplate.Logica.ServiciosManager;
import com.example.atlantafxtemplate.Modelo.CarritoItem;
import com.example.atlantafxtemplate.Modelo.Servicios;
import com.example.atlantafxtemplate.Modelo.Repuestos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class serviciosController implements Initializable {

    @FXML
    private ComboBox<String> tipoVehiculoComboBox;

    @FXML
    private ComboBox<String> tipoServicioComboBox;

    @FXML
    private FlowPane flowPaneServicios;

    @FXML
    private Button ButtonCleanServicios;

    @FXML
    private AnchorPane serviciosMainPane;

    private ServiciosManager manager;
    // En serviciosController.java, donde creas el CarritoItem:
    private CarritoManager carritoManager = CarritoManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneChanger.makeDraggable(serviciosMainPane);
        manager = new ServiciosManager();

        // Configurar el FlowPane
        flowPaneServicios.setPrefWrapLength(850);
        flowPaneServicios.setMinHeight(200);
        flowPaneServicios.setPrefWidth(850);
        
        // Permitir que el FlowPane crezca verticalmente
        flowPaneServicios.setMinHeight(Region.USE_PREF_SIZE);
        flowPaneServicios.setPrefHeight(Region.USE_COMPUTED_SIZE);
        flowPaneServicios.setMaxHeight(Double.MAX_VALUE);
        
        flowPaneServicios.setAlignment(javafx.geometry.Pos.CENTER);

        // Rellenamos los combos
        tipoVehiculoComboBox.getItems().addAll("Carro", "Camioneta", "Moto");
        tipoServicioComboBox.getItems().addAll("Servicios", "Repuestos");

        tipoVehiculoComboBox.setOnAction(e -> cargarItems());
        tipoServicioComboBox.setOnAction(e -> cargarItems());
    }

    @FXML
    private void limpiarComboBox(MouseEvent event) {
        tipoVehiculoComboBox.getSelectionModel().clearSelection();
        tipoServicioComboBox.getSelectionModel().clearSelection();
        flowPaneServicios.getChildren().clear();
    }

    private void cargarItems() {
        flowPaneServicios.getChildren().clear();

        String tipoVehiculo = tipoVehiculoComboBox.getValue();
        String tipoServicio = tipoServicioComboBox.getValue();

        if (tipoVehiculo == null || tipoServicio == null) return;

        System.out.println("Cargando para vehículo: " + tipoVehiculo + ", tipo: " + tipoServicio);

        if (tipoServicio.equals("Servicios")) {
            var servicios = manager.getServiciosPorTipo(tipoVehiculo, null);
            System.out.println("Servicios encontrados: " + servicios.size());
            for (Servicios s : servicios) {
                flowPaneServicios.getChildren().add(crearCardServicio(s));
            }
        } else if (tipoServicio.equals("Repuestos")) {
            var repuestos = manager.getRepuestosPorTipo(tipoVehiculo, null);
            System.out.println("Repuestos encontrados: " + repuestos.size());
            for (Repuestos r : repuestos) {
                flowPaneServicios.getChildren().add(crearCardRepuesto(r));
            }
        }
    }

    private VBox crearCardServicio(Servicios s) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPrefWidth(200);
        box.setMinHeight(200);  // Altura mínima fija
        box.setMaxHeight(200);  // Altura máxima fija
        box.setStyle("-fx-background-color: #71a164; -fx-padding: 15; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");

        Label nombre = new Label(s.getNombre());
        nombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: white;");
        
        Label precio = new Label("Q." + s.getPrecio());
        precio.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");
        
        Label desc = new Label(s.getDescripcion());
        desc.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");
        desc.setWrapText(true);
        
        Button btn = new Button("Comprar");
        btn.setStyle("-fx-background-color: #2DFCD9; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 15;");

        btn.setOnAction(e -> {
            CarritoItem nuevoItem = new CarritoItem(
                s.getNombre(),
                s.getPrecio(),
                s.getDescripcion(),
                "",  // imagen (vacía para servicios)
                "Servicio", // tipo
                1  // cantidad inicial
            );
            carritoManager.agregarItem(nuevoItem);
            sceneChanger.cambiarEscena("carrito-view.fxml", e);
        });
        
        box.getChildren().addAll(nombre, precio, desc, btn);

        return box;
    }

    private VBox crearCardRepuesto(Repuestos r) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPrefWidth(200);
        box.setMinHeight(300);  // Altura mínima fija
        box.setMaxHeight(315);  // Altura máxima fija
        box.setStyle("-fx-background-color: #71a164; -fx-padding: 10; -fx-background-radius: 10;");

        ImageView image = new ImageView();
        image.setFitHeight(150);
        image.setFitWidth(165);

        try {
            String basePath = "C:\\Users\\Angel\\OneDrive\\Desktop\\Segundo Semestre\\Progra II\\Java\\ProyectoFinal\\Concesionario_Java_Proyect\\AtlantaFx-Template\\src\\main\\resources\\Imagenes";
            File file = new File(basePath, r.getImagen());
            if (file.exists()) {
                image.setImage(new Image(file.toURI().toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Label nombre = new Label(r.getNombre());
        Label precio = new Label("Precio: Q." + r.getPrecio());
        Label desc = new Label(r.getDescripcion());
        Button btn = new Button("Comprar");
        btn.setStyle("-fx-background-color: #2DFCD9; -fx-text-fill: white; -fx-font-weight: bold;");
        btn.setOnAction(e -> {
            CarritoItem nuevoItem = new CarritoItem(
                r.getNombre(),
                r.getPrecio(),
                r.getDescripcion(),
                r.getImagen(),  // imagen del repuesto
                "Repuesto", // tipo
                1  // cantidad inicial
            );
            carritoManager.agregarItem(nuevoItem);
            sceneChanger.cambiarEscena("carrito-view.fxml", e);
        });
        
        box.getChildren().addAll(image, nombre, precio, desc, btn);
        return box;
    }

    // ---- MÉTODOS DE NAVEGACIÓN ----
    @FXML
    void carritoPresionado(MouseEvent event) { sceneChanger.cambiarEscena("carrito-view.fxml", event); }

    @FXML
    void comprarPresionado(MouseEvent event) { sceneChanger.cambiarEscena("compraVehiculos-view.fxml", event); }

    @FXML
    void cotizarPresionado(MouseEvent event) { sceneChanger.cambiarEscena("cotizador-view.fxml", event); }

    @FXML
    void venderPresionado(MouseEvent event) { sceneChanger.cambiarEscena("venderVehiculo-view.fxml", event); }

    @FXML
    void serviciosPresionado(MouseEvent event) { sceneChanger.cambiarEscena("servicios-view.fxml", event); }

    @FXML
    void salirSesion(MouseEvent event) { sceneChanger.exitConfirmation(event); }
}