package com.example.atlantafxtemplate;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Aplica el tema de AtlantaFX
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        //fxml Scene login
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Scene sceneLogin = new Scene(fxmlLoaderLogin.load());
        stage.setScene(sceneLogin);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show(); //mostrar en pantalla

    }

    public static void main(String[] args) {
        launch();
    }
}
