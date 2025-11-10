package com.example.atlantafxtemplate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class cotizadorController implements Initializable {

    @FXML
    private Label returnCotizacion;

    @FXML
    private TextField CampoDeBusqueda;

    @FXML
    private Button CotizarBtt;

    @FXML
    private TextField InteresLbl;

    @FXML
    private TextField MontoLbl;

    @FXML
    private TableView<ObservableList<String>> TableViewCotizacion;

    @FXML
    private ComboBox<?> TipoCuota;

    @FXML
    private ComboBox<?> TipoPago;

    @FXML
    private Spinner<?> buscarModelo;

    @FXML
    private ImageView carritoImage;

    @FXML
    private TableColumn<ObservableList<String>, String> columnaInteres;

    @FXML
    private TableColumn<ObservableList<String>, String> columnaNo;

    @FXML
    private TableColumn<ObservableList<String>, String> columnaSaldoCapital;

    @FXML
    private TableColumn<ObservableList<String>, String> columnaValordeCuota;

    @FXML
    private Button comprarButton;

    @FXML
    private AnchorPane cotizadorMainPane;

    @FXML
    private Button cotizarButton;

    @FXML
    private ImageView exitImage;

    @FXML
    private Button serviciosButton;

    @FXML
    private Button venderBuatton;

    @FXML
    private AnchorPane AnchorpaneTablaCot;

    @FXML
    void returnCotizacion(MouseEvent event) {
        AnchorpaneTablaCot.setVisible(false);
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
        sceneChanger.makeDraggable(cotizadorMainPane);
        AnchorpaneTablaCot.setVisible(false);

        columnaNo.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(0)));
        columnaValordeCuota.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(1)));
        columnaInteres.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(2)));
        columnaSaldoCapital.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(3)));

        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();
        datos.add(FXCollections.observableArrayList("hola", "dwadawd", "fsfsf", "dfsfs"));
        datos.add(FXCollections.observableArrayList("hola2", "dwadawd2", "fsfsf2", "dfsfs2"));
        datos.add(FXCollections.observableArrayList("hola2", "dwadawd2", "fsfsf2", "dfsfs2"));
        datos.add(FXCollections.observableArrayList("hola2", "dwadawd2", "fsfsf2", "dfsfs2"));
        datos.add(FXCollections.observableArrayList("hola2", "dwadawd2", "fsfsf2", "dfsfs2"));

        TableViewCotizacion.setItems(datos);
    }

    @FXML
    private void CotizarClick(MouseEvent event) {
        AnchorpaneTablaCot.setVisible(true);
    }

}
