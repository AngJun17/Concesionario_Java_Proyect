package com.example.atlantafxtemplate;

import com.example.atlantafxtemplate.Logica.VenderManager;
import com.example.atlantafxtemplate.Modelo.Carro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
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
    private ComboBox<String> comboBoxAnio;

    @FXML
    private ComboBox<String> comboBoxCategoria;


    @FXML
    private Button comprarButton;

    @FXML
    private Button cotizarButton;

    @FXML
    private TextField estadoVehiculoTextField;

    @FXML
    private TextField MarcaTextField1;

    @FXML
    private TextField ModeloTextField11;

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

    private final VenderManager venderManager = new VenderManager();
    private final List<File> imageneseSeleccionadas = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneChanger.makeDraggable(venderMainPane);

        for (int i = 2025;i >= 1990; i--) {
            comboBoxAnio.getItems().add(String.valueOf(i));
        }

        comboBoxCategoria.getItems().add("Suv");
        comboBoxCategoria.getItems().add("Pickup");
        comboBoxCategoria.getItems().add("Hatchback");
        comboBoxCategoria.getItems().add("Coupe");
        comboBoxCategoria.getItems().add("Van");
        comboBoxCategoria.getItems().add("Moto");
        comboBoxCategoria.getItems().add("Camion");
    }

    @FXML
    private void subirFotos(MouseEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona imagenes del vehiculo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagenes", "*.png","*.jpg","*/jpeg")
        );
        List<File> archivos = fileChooser.showOpenMultipleDialog(null);
        if (archivos != null){
            imageneseSeleccionadas.clear();
            imageneseSeleccionadas.addAll(archivos);

            if (imageneseSeleccionadas.size() > 0){
                ImagenSubida1.setImage(new javafx.scene.image.Image(imageneseSeleccionadas.get(0).toURI().toString()));
            }
            if (imageneseSeleccionadas.size() > 1){
                ImagenSubida2.setImage(new javafx.scene.image.Image(imageneseSeleccionadas.get(1).toURI().toString()));
            }
        }
    }

    @FXML
    void publicarVehiculo(MouseEvent event) {
        try {
            String marca = MarcaTextField1.getText();
            String modelo = ModeloTextField11.getText();
            double precioOriginal = Double.parseDouble(precioTextField.getText());
            int anio = Integer.parseInt(comboBoxAnio.getValue().toString());
            String estado = estadoVehiculoTextField.getText();
            String descripcion = informacionVehiculoTextFIeld.getText();
            String imagen = (imageneseSeleccionadas != null)
                    ? imageneseSeleccionadas.get(0).getName()
                    : "default.png";
            ;
            String categoria = comboBoxCategoria.getValue().toString();

            if (imageneseSeleccionadas != null && !imageneseSeleccionadas.isEmpty()) {
                File destinoCarpeta = new File("data/imagenes/");
                if (!destinoCarpeta.exists()) {
                    destinoCarpeta.mkdirs(); // crea la carpeta si no existe
                }

                File origen = imageneseSeleccionadas.get(0);
                File destino = new File(destinoCarpeta, origen.getName());

                try {
                    java.nio.file.Files.copy(
                            origen.toPath(),
                            destino.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                    mostrarAlerta("Error", "No se pudo guardar la imagen seleccionada.", Alert.AlertType.ERROR);
                }
            }



            VenderManager manager = new VenderManager();
            boolean exito = manager.agregarCarro(marca, modelo, precioOriginal, anio,categoria, descripcion, imagen,estado);

            if (exito) {
                mostrarAlerta("Exito","Vehículo publicado con éxito.",Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error"," el vehículo ya existe o no puede cotizarse.", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            mostrarAlerta("Error","Error al publicar el vehículo. Verifica los datos.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void limpiarCarros(){
        informacionVehiculoTextFIeld.clear();
        estadoVehiculoTextField.clear();
        precioTextField.clear();
        comboBoxAnio.getSelectionModel().clearSelection();
        ImagenSubida1.setImage(null);
        ImagenSubida2.setImage(null);
        imageneseSeleccionadas.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
