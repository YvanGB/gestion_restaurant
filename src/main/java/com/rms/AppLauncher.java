package com.rms;

import com.rms.model.Commande;
import com.rms.model.Recette;
import com.rms.ui.*;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AppLauncher extends Application {
    private Stage primaryStage;
    private StackPane authUI;

    private static AppLauncher instance = null;
    public static AppLauncher getInstance() { return instance; }

    private AuthUiController authUiController = null;
    private AdminDashController adminDashController = null;
    private ChefUIController chefUIController = null;
    private CommandesUIController commandesUIController = null;
    private PaiementUIController paiementUIController = null;
    private SuiviRecettesUIController suiviRecettesUIController = null;

    public PaiementUIController getPaiementUIController() {
        return paiementUIController;
    }
    public void setPaiementUIController(PaiementUIController paiementUIController) {
        this.paiementUIController = paiementUIController;
    }

    public CommandesUIController getCommandesUIController() {
        return commandesUIController;
    }
    public void setCommandesUIController(CommandesUIController commandesUIController) {
        this.commandesUIController = commandesUIController;
    }

    public void setAdminDashController(AdminDashController adminDashController) {
        this.adminDashController = adminDashController;
    }

    public void setAuthUiController(AuthUiController authUiController) {
        this.authUiController = authUiController;
    }

    public void setChefUIController(ChefUIController chefUIController) {
        this.chefUIController = chefUIController;
    }

    public SuiviRecettesUIController getSuiviRecettesUIController() {
        return suiviRecettesUIController;
    }

    public void setSuiviRecettesUIController(SuiviRecettesUIController suiviRecettesUIController) {
        this.suiviRecettesUIController = suiviRecettesUIController;
    }

//    private EditUserUiController editUserUiController = null;
//    private AddUserUiController addUserUiController = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Restaurant Management System");

        initRootLayout();
    }

    // Initializes the root layout.
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            authUI = (StackPane) FXMLLoader.load(getClass().getClassLoader().getResource("Auth.fxml"));
            // Show the scene containing the root layout.
            Scene scene = new Scene(authUI);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initDashLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AdminUI.fxml"));
        Parent rootParent = loader.load();
        adminDashController = loader.getController();
        Scene scene = new Scene(rootParent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Administrator Dashboard");
        stage.show();
        primaryStage.close();
    }

    @FXML
    public void initChefLayout() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ChefCuisinierUI.fxml"));
        Parent rootParent = loader.load();
        chefUIController = loader.getController();
        Scene scene = new Scene(rootParent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chef cuisinier Dashboard");
        stage.show();
        primaryStage.close();
    }

    @FXML
    public void initRestaurateurLayout() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CommandesUI.fxml"));
        Parent rootParent = loader.load();
        commandesUIController = loader.getController();
        Scene scene = new Scene(rootParent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Restaurateur Dashboard");
        stage.show();
        primaryStage.close();
    }

    @FXML
    public void initPaimentLayout(Commande commande) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PaiementUI.fxml"));
        Parent rootParent = loader.load();
        paiementUIController = loader.getController();
        paiementUIController.setCommande(commande);
        Scene scene = new Scene(rootParent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Paiement");
        stage.show();
        primaryStage.close();
    }

    public void initRecetteLayout() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SuiviRecettes.fxml"));
        Parent rootParent = loader.load();
        suiviRecettesUIController = loader.getController();
        Scene scene = new Scene(rootParent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Suivi des recette");
        stage.show();
        primaryStage.close();
    }
}
