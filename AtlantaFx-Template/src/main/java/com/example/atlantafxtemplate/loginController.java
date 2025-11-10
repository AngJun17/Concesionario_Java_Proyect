package com.example.atlantafxtemplate;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.example.atlantafxtemplate.sceneChanger;
import com.example.atlantafxtemplate.Logica.LoginManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML private AnchorPane AnchorPaneLogIn;

    @FXML private AnchorPane LoginPane;

    @FXML private AnchorPane AnchorPaneSignUp;

    @FXML private Button SignIn;

    @FXML private PasswordField confirmPassword;

    @FXML private TextField confirmPasswordTextField;

    @FXML private ImageView hideShowPswrdImage;

    @FXML private ImageView hideShowPswrdImage2;

    @FXML private ImageView hideShowPswrdImage3;

    @FXML private Label lblClose;

    @FXML private Button logInButton;

    @FXML private TextField newPasswordTextField;

    @FXML private PasswordField newpassword;

    @FXML private TextField newUserNameTxtField;

    @FXML private PasswordField passwordTextField;

    @FXML private TextField pswVisibleTextField;

    @FXML private Label returnLogInLBL;

    @FXML private AnchorPane rootPane;

    @FXML private Button signUpButton;

    @FXML private TextField usernameTextField;

    @FXML void CloseLogIn(MouseEvent event) {
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
        newUserNameTxtField.setText("");

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicioTextFields();
        pswVisibleTextField.textProperty().bindBidirectional(passwordTextField.textProperty());
        newPasswordTextField.textProperty().bindBidirectional(newpassword.textProperty());
        confirmPasswordTextField.textProperty().bindBidirectional(confirmPassword.textProperty());


        AnchorPaneSignUp.setVisible(false);
        AnchorPaneLogIn.setVisible(true);
        signUpButton.setVisible(true);

        // Arratrar la ventana
        sceneChanger.makeDraggable(LoginPane);

    }

    @FXML private Label warningLbl;

    private final LoginManager loginManager = new LoginManager();

    @FXML
    private void LogInPress(MouseEvent event) {

        String user = usernameTextField.getText().trim();
        String pass = pswVisibleTextField.getText().trim();
        boolean valido = loginManager.validar(user, pass);
        if (valido == true) {
            try {
                sceneChanger.cambiarEscena("compraVehiculos-view.fxml", event);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                warningLbl.setText("Error inesperado: " + ex.getMessage());
            }
        }
        else{
            warningLbl.setText("Contraseña o usuario incorrectos");
        }
    }

    @FXML private Label WarningSingInLbl;
    @FXML
    private void SignInbtt(MouseEvent eventt){

        String user = newUserNameTxtField.getText().trim();
        String pass = confirmPasswordTextField.getText().trim();
        String pass2 = newpassword.getText().trim();
        if (user.isEmpty() || pass.isEmpty() || pass2.isEmpty()){
            WarningSingInLbl.setText("Debe completar todos los campos.");
            return;
        }

        if (!newpassword.getText().trim().equals(confirmPassword.getText().trim())) {
            WarningSingInLbl.setText("Las Contraseñas no coinciden. Intentelo de nuevo.");
            return;
        }

        boolean registrado = loginManager.registrarUsuario(user, pass2);
        if (registrado){
            sceneChanger.mensajeRegistro(eventt);
            WarningSingInLbl.setText("Usuario registrado");
            newUserNameTxtField.setText("");
            confirmPasswordTextField.setText("");
            newpassword.setText("");
            returnLogIn(eventt);
        }else{
            WarningSingInLbl.setText("El usuario ya existe");
        }
    }



}
