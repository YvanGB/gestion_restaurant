package com.rms.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.rms.AppLauncher;

import java.io.IOException;

public class AuthUiController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;


    @FXML
    void seConnecter(ActionEvent event) throws IOException {
        if(loginField.getText().equals("Admin") && passwordField.getText().equals("adminpasser")){
            AppLauncher.getInstance().initDashLayout();
        }else if(loginField.getText().equals("Chef") && passwordField.getText().equals("chef")){
            AppLauncher.getInstance().initChefLayout();
        }else if(loginField.getText().equals("Resto") && passwordField.getText().equals("resto")){
            AppLauncher.getInstance().initRestaurateurLayout();
        }else {
            errorLabel.setVisible(true);
            loginField.setText("");
            passwordField.setText("");
        }
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(TextField passwordField) {
        this.passwordField = (PasswordField) passwordField;
    }

    public TextField getLoginField() {
        return loginField;
    }

    public void setLoginField(TextField loginField) {
        this.loginField = loginField;
    }
}
