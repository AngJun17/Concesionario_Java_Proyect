package com.example.atlantafxtemplate;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    private ImageView hideShowPswrdImage;

    @FXML
    private Label lblClose;

    @FXML
    private Button logInButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField pswVisibleTextField;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameTextField;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void CloseLogIn(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void TooglePasswordView(MouseEvent event) {
        if (pswVisibleTextField.isVisible()) {
            //ocultar
            pswVisibleTextField.setVisible(false);
            passwordTextField.setVisible(true);
            hideShowPswrdImage.setOpacity(0.5);
        } else {
            //mostrar
            pswVisibleTextField.setVisible(true);
            passwordTextField.setVisible(false);
            hideShowPswrdImage.setOpacity(1);
            pswVisibleTextField.requestFocus();
            pswVisibleTextField.positionCaret(pswVisibleTextField.getText().length());
        }
    }

    @FXML
    public void initialize() {
        //Sincronizar Campos}
        pswVisibleTextField.textProperty().bindBidirectional(passwordTextField.textProperty());
        pswVisibleTextField.setVisible(false);
        hideShowPswrdImage.setOpacity(0.5);

        // Arratrar la ventana
        rootPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        rootPane.setOnMouseDragged(event -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
