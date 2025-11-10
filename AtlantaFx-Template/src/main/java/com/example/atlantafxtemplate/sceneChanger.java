package com.example.atlantafxtemplate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class sceneChanger {

    private static double xOffset = 0;
    private static double yOffset = 0;

    //metodo para mostrar diferentes stage segun boton
    public static void cambiarEscena(String fxml, javafx.event.Event event) {
        try {
            Parent root = FXMLLoader.load(sceneChanger.class.getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///metodo para arrastrar la ventana
    public static void makeDraggable(Pane rootPane) {
        rootPane.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        rootPane.setOnMouseDragged((MouseEvent event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    public static void exitConfirmation(javafx.event.Event event) {

        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Confirmacion Cierre de Sesion");
        mensaje.setHeaderText("CERRAR SESION");
        mensaje.setContentText("¿Está seguro de cerrar sesión? (Perderá el carrito)");
        ButtonType botonSi = new ButtonType("SI");
        ButtonType botonNo = new ButtonType("NO");
        mensaje.getButtonTypes().setAll(botonSi, botonNo);

        DialogPane dialogPane = mensaje.getDialogPane();
        Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);
        dialogPane.getStylesheets().add(sceneChanger.class.getResource("style.css").toExternalForm());
        dialogPane.getStyleClass().add("jfoenix-alert");

        Optional<ButtonType> opcion = mensaje.showAndWait();
        if (opcion.get() == botonSi) {
            cambiarEscena("loginPage.fxml", event);
        } else if (opcion.get() == botonNo) {}
    }

    public static void mensajeRegistro(javafx.event.Event event) {
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("REGISTRO");
        mensaje.setHeaderText("NUEVO USUARIO");
        mensaje.setContentText("Usuario registrado exitosamente");
        ButtonType botonAceptar = new ButtonType("ACEPTAR");
        mensaje.getButtonTypes().setAll(botonAceptar);

        DialogPane dialogPane = mensaje.getDialogPane();
        Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);
        dialogPane.getStylesheets().add(sceneChanger.class.getResource("style.css").toExternalForm());
        dialogPane.getStyleClass().add("jfoenix-alert");

        Optional<ButtonType> opcion = mensaje.showAndWait();
        if (opcion.get() == botonAceptar) {
            cambiarEscena("loginPage.fxml", event);
        }
    }
}
