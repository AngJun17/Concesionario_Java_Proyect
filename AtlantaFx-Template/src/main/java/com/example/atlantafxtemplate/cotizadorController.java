package com.example.atlantafxtemplate;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;

public class cotizadorController implements Initializable {
    @FXML
    private VBox VBox1;

    @FXML
    private VBox VBox2;

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
    private ComboBox<String> TipoCuota;

    @FXML
    private ComboBox<String> TipoPago;

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

    private boolean plazos = false;

    @FXML
    void EleccionTipoPago(ActionEvent event) {
        if (TipoPago.getValue().equals("PLAZOS"))  {
            TipoCuota.setDisable(false);
            InteresLbl.setText("%");
            TipoCuota.applyCss();
            TipoCuota.layout();
            plazos = true;
        }
        else {
            InteresLbl.setText("0%");
            plazos = false;

            TipoCuota.setDisable(true);
            TipoCuota.setPromptText("Cuota");

            TipoCuota.getSelectionModel().clearSelection();
            TipoCuota.setValue(null);

            TipoCuota.applyCss();
            TipoCuota.layout();

        }
    }

    private double interes = 0;


    @FXML
    void EleccionTipoCuota(ActionEvent event) {
        if (TipoCuota.getValue().equalsIgnoreCase("1 año") || TipoCuota.getValue().equalsIgnoreCase("2 años") || TipoCuota.getValue().equalsIgnoreCase("3 años") || TipoCuota.getValue().equalsIgnoreCase("4 años") || TipoCuota.getValue().equalsIgnoreCase("5 años")) {
            InteresLbl.setText("5%");
            interes = 5;
        } else if (TipoCuota.getValue().equalsIgnoreCase("6 años") || TipoCuota.getValue().equalsIgnoreCase("7 años") || TipoCuota.getValue().equalsIgnoreCase("8 años") || TipoCuota.getValue().equalsIgnoreCase("9 años") || TipoCuota.getValue().equalsIgnoreCase("10 años")) {
            InteresLbl.setText("7%");
            interes = 7;
        } else if (TipoCuota.getValue().equalsIgnoreCase("11 años") || TipoCuota.getValue().equalsIgnoreCase("12 años") || TipoCuota.getValue().equalsIgnoreCase("13 años") || TipoCuota.getValue().equalsIgnoreCase("14 años") || TipoCuota.getValue().equalsIgnoreCase("15 años")) {
            InteresLbl.setText("8.5%");
            interes = 8.5;
        } else if (TipoCuota.getValue().equalsIgnoreCase("16 años")) {
            InteresLbl.setText("10%");
            interes = 10;
        } else {
            InteresLbl.setText("0%");
            interes = 0;
        }

        InteresLbl.applyCss();
        InteresLbl.layout();
    }

   @FXML
   public void setDatos(double monto) {
        MontoLbl.setText("Q" + monto);

   }


    @FXML
    void returnCotizacion(MouseEvent event) {
        AnchorpaneTablaCot.setVisible(false);
        VBox1.setVisible(true);
        VBox2.setVisible(true);
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
        VBox1.setVisible(true);
        VBox2.setVisible(true);

        TipoCuota.setDisable(true);

        TipoPago.getItems().add("CONTADO");
        TipoPago.getItems().add("PLAZOS");

        for (int i = 1; i <= 16; i++) {
            TipoCuota.getItems().add(i + (i == 1 ? " Año" : " Años"));
        }

        columnaNo.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(0)));
        columnaValordeCuota.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(1)));
        columnaInteres.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(2)));
        columnaSaldoCapital.setCellValueFactory( data -> new SimpleStringProperty(data.getValue().get(3)));
    }

    @FXML
    private void CotizarClick(MouseEvent event) {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();
        AnchorpaneTablaCot.setVisible(true);
        VBox1.setVisible(false);
        VBox2.setVisible(false);

        double monto = Double.parseDouble(MontoLbl.getText().split("Q")[1]);

        if (plazos) {
            String anios = TipoCuota.getValue();
            anios = anios.split(" ")[0];

            int tiempoDePagoAnio = Integer.parseInt(anios);
            int mesesPago = 12 * tiempoDePagoAnio;

            double interesPorMes = interes / 12;
            double cuota = monto / mesesPago;


            for (int i = 0; i < mesesPago; i++) {
                double saldoActual = monto - (cuota * i);
                double interesCalculado = saldoActual * (interesPorMes / 100);

                datos.add(FXCollections.observableArrayList(
                        String.valueOf(i + 1),
                        String.format("Q" + "%.2f", cuota),
                        String.format("Q" + "%.2f", interesCalculado),
                        String.format("Q" + "%.2f", saldoActual)
                ));
            }
        } else {
            datos.add(FXCollections.observableArrayList(
                    "1", String.format("Q" + "%.2f", monto),
                    "Q0", String.format("Q" + "%.2f", monto)
            ));
        }
        TableViewCotizacion.setItems(datos);
    }

}
