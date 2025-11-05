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

public class loginController {

    @FXML
    private AnchorPane AnchorPaneLogIn;

    @FXML
    private AnchorPane AnchorPaneSignUp;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private ImageView hideShowPswrdImage;

    @FXML
    private ImageView hideShowPswrdImage2;

    @FXML
    private ImageView hideShowPswrdImage3;

    @FXML
    private Label lblClose;

    @FXML
    private Button logInButton;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private PasswordField newpassword;

    @FXML
    private TextField newusermaneTxtField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField pswVisibleTextField;

    @FXML
    private Label returnLogInLBL;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameTextField;

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
    void TooglePasswordView2(MouseEvent event) {
        if (newPasswordTextField.isVisible()) {
            //ocultar
            newPasswordTextField.setVisible(false);
            newpassword.setVisible(true);
            hideShowPswrdImage2.setOpacity(0.5);
        } else {
            //mostrar
            newPasswordTextField.setVisible(true);
            newpassword.setVisible(false);
            hideShowPswrdImage2.setOpacity(1);
            newPasswordTextField.requestFocus();
            newPasswordTextField.positionCaret(newPasswordTextField.getText().length());
        }
    }

    @FXML
    void TooglePasswordView3(MouseEvent event) {
        if (confirmPasswordTextField.isVisible()) {
            //ocultar
            confirmPasswordTextField.setVisible(false);
            confirmPassword.setVisible(true);
            hideShowPswrdImage3.setOpacity(0.5);
        } else {
            //mostrar
            confirmPasswordTextField.setVisible(true);
            confirmPassword.setVisible(false);
            hideShowPswrdImage3.setOpacity(1);
            confirmPasswordTextField.requestFocus();
            confirmPasswordTextField.positionCaret(newPasswordTextField.getText().length());
        }
    }

    @FXML
    void inicioTextFields() {
        //Sincronizar Campos}
        pswVisibleTextField.setVisible(false);
        passwordTextField.setVisible(true);
        hideShowPswrdImage.setOpacity(0.5);

        newPasswordTextField.setVisible(false);
        newpassword.setVisible(true);
        hideShowPswrdImage2.setOpacity(0.5);

        confirmPasswordTextField.setVisible(false);
        confirmPassword.setVisible(true);
        hideShowPswrdImage3.setOpacity(0.5);
    }

    @FXML
    void returnLogIn(MouseEvent event) {
        AnchorPaneSignUp.setVisible(false);
        AnchorPaneLogIn.setVisible(true);
        signUpButton.setVisible(true);

        newpassword.setText("");
        newPasswordTextField.setText("");
        confirmPassword.setText("");
        confirmPasswordTextField.setText("");
        newusermaneTxtField.setText("");

        passwordTextField.setText("");
        pswVisibleTextField.setText("");
        usernameTextField.setText("");
        inicioTextFields();
    }

    @FXML
    void signUpBtt(MouseEvent event) {
        AnchorPaneSignUp.setVisible(true);
        AnchorPaneLogIn.setVisible(false);
        signUpButton.setVisible(false);
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        inicioTextFields();
        pswVisibleTextField.textProperty().bindBidirectional(passwordTextField.textProperty());
        newPasswordTextField.textProperty().bindBidirectional(newpassword.textProperty());
        confirmPasswordTextField.textProperty().bindBidirectional(confirmPassword.textProperty());


        AnchorPaneSignUp.setVisible(false);
        AnchorPaneLogIn.setVisible(true);
        signUpButton.setVisible(true);

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
