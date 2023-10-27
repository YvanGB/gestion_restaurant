package com.rms.ui;

import com.rms.AppLauncher;
import com.rms.db.AccessDB;
import com.rms.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableCell;


import java.io.IOException;
import java.util.Optional;

public class AdminDashController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField nomField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField searchField;

    @FXML
    private TextField updatedLoginField;

    @FXML
    private TextField updatedNomField;

    @FXML
    private TextField updatedPasswordField;

    @FXML
    private TextField updatedPrenomField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button addUserBtn;

    @FXML
    private Button clearFieldBtn;

    @FXML
    private Button deleteUserBtn;

    @FXML
    private Button updateUserBtn;

    @FXML
    private Button updateUserBtn2;

    @FXML
    private Button menuAddUserBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableColumn<?, ?> nomCol;

    @FXML
    private TableColumn<?, ?> prenomCol;

    @FXML
    private TableColumn<?, ?> roleCol;

    @FXML
    private TableColumn<?, ?> passwordCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> loginCol;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private AnchorPane usersTableAnchor;

    @FXML
    private AnchorPane updateForm;

    @FXML
    private AnchorPane usersView;

    private FilteredList<User> filteredUsers;

    private User selectedUser;

    private Alert alert;
    ObservableList<User> usersList = FXCollections.observableArrayList();


    public void initialize(){
        AppLauncher.getInstance().setAdminDashController(this);
        initUserTableView();

        ObservableList<String> roles = FXCollections.observableArrayList("Administrateur", "ChefCuisinier", "Restaurateur");
        roleComboBox.setItems(roles);

        searchUser();
    }

    public class PasswordTableCell extends TableCell<User, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText("*".repeat(item.length()));
            }
        }
    }

    public void initUserTableView(){
        usersList.clear();
        usersList.addAll(AccessDB.getInstance().getUsers());

        usersTable.setItems(usersList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
//        passwordCol.setCellFactory(column -> new PasswordTableCell());



        usersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Gérer l'événement de sélection ici
            if (newValue != null) {
                // Vous pouvez accéder aux propriétés de l'utilisateur sélectionné
                selectedUser = newValue;
//                displayUser();
            }
        });
    }

    @FXML
    public void clearFields(){
        nomField.clear();
        prenomField.clear();
        loginField.clear();
        passwordField.clear();
    }

    @FXML
    public void updatedClearFields(){
        updatedNomField.clear();
        updatedPrenomField.clear();
        updatedLoginField.clear();
        updatedPasswordField.clear();
    }

    public void populate(){
        updatedNomField.setText(selectedUser.getNom());
        updatedPrenomField.setText(selectedUser.getPrenom());
        updatedLoginField.setText(selectedUser.getLogin());
        updatedPasswordField.setText(selectedUser.getPassword());
    }

    @FXML
    public void switchForm(ActionEvent event){
        if(event.getSource() == updateUserBtn){
            updateForm.setVisible(true);
            usersView.setVisible(false);
            usersTableAnchor.setVisible(false);
            populate();
        }else if(event.getSource() == menuAddUserBtn){
            usersTableAnchor.setVisible(true);
            updateForm.setVisible(false);
            usersView.setVisible(false);
        }
    }

    @FXML
    public void addUser(ActionEvent event){
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String login = loginField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        User newUser = new User(nom, prenom, login, password, role);
        try {
            AccessDB.getInstance().addUser(newUser);
        } catch (Exception e) {
            System.out.println(e);
        }

        usersList.add(newUser);
        clearFields();
    }

    public void searchUser(){
        filteredUsers = new FilteredList<>(usersList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredUsers.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getLogin().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                // Add more conditions for other fields if needed

                return false;
            });
        });
    }

    @FXML
    public void updateUser(ActionEvent event) {
        if (selectedUser != null) {
            String newNom = updatedNomField.getText();
            String newPrenom = updatedPrenomField.getText();
            String newLogin = updatedLoginField.getText();
            String newPassword = updatedPasswordField.getText();
//            String newRole = roleComboBox.getValue();

            if (newNom.isEmpty() || newPrenom.isEmpty() || newLogin.isEmpty() || newPassword.isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs vides");
                alert.setHeaderText("");
                alert.setContentText("Les champs ne peuvent etre vides");
                return;
            }

            selectedUser.setNom(newNom);
            selectedUser.setPrenom(newPrenom);
            selectedUser.setLogin(newLogin);
            selectedUser.setPassword(newPassword);
//            selectedUser.setRole(newRole);

            if(event.getSource() == updateUserBtn2){
                try {
                    AccessDB.getInstance().updateUser(selectedUser);
                    usersTable.refresh();
                    updatedClearFields();

                    usersTableAnchor.setVisible(true);
                    updateForm.setVisible(false);
                    usersView.setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }


        }


    }

    @FXML
    public void deleteUser(ActionEvent event) {
        if (selectedUser != null) {
            // Show a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Suppression d'utilisateur");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try{
                    AccessDB.getInstance().deleteUser(selectedUser);
                    usersList.remove(selectedUser);
                    clearFields();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        try{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sûr de vouloir quitter?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                logoutBtn.getScene().getWindow().hide();
                AppLauncher.getInstance().initRootLayout();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }






//    public void displayUser(){
//        if(selectedUser!=null) {
//            nomLabel.textProperty().set(UtilisateurSelectionne.getNom());
//            prenomLabel.textProperty().set(UtilisateurSelectionne.getPrenom());
//            loginLabel.textProperty().set(UtilisateurSelectionne.getLogin());
//            passwordLabel.textProperty().set(UtilisateurSelectionne.getPassword());
//            roleLabel.textProperty().set(UtilisateurSelectionne.getRole());
//        }
//    }

}


/********************************************************************************************************************************/

//public class AdminUIController {
//
//    @FXML
//    private TableView<Utilisateur> utilisateurTable;
//    @FXML
//    private TableColumn<Utilisateur, String> nomColumn;
//    @FXML
//    private TableColumn<Utilisateur, String> prenomColumn;
//
//
//    @FXML
//    private Label nomLabel;
//    @FXML
//    private Label prenomLabel;
//    @FXML
//    private Label loginLabel;
//    @FXML
//    private Label passwordLabel;
//    @FXML
//    private Label roleLabel;
//
//    private Utilisateur UtilisateurSelectionne;
//
//    ObservableList<Utilisateur> usersList = FXCollections.observableArrayList();
//
//    @FXML
//    private void initialize() {
//        UMSApplication.getInstance().setAdminUC(null);
//        initUserTableView();
//
//    }
//
//    private void initUserTableView() {
//        usersList.clear();
//        usersList.addAll(DBAccess.getInstance().getUtilisateurs());
//
//        utilisateurTable.setItems(usersList);
//        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//
//        utilisateurTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            // Gérer l'événement de sélection ici
//            if (newValue != null) {
//                // Vous pouvez accéder aux propriétés de l'utilisateur sélectionné
//                UtilisateurSelectionne = newValue;
//                afficherDetailsUtilisateur();
//            }
//        });
//
//    }
//
//
//    public AdminUIController() {}
//
//    @FXML
//    private void openAddUserUI() throws IOException {
//        UMSApplication.getInstance().openAddUserUI();
//    }
//
//    @FXML
//    private void openUpdateUserUI() throws IOException {
//        if(UtilisateurSelectionne != null) {
//            UMSApplication.getInstance().openAddUserUIForUpdate(UtilisateurSelectionne);
//        } else {
//            BoiteDialog.showWarning("Attention", "Sélectionnez un utilisateur d'abord !");
//        }
//    }
//
//    @FXML
//    private void deleteUser() {
//        if(UtilisateurSelectionne != null) {
//            DBAccess.getInstance().deleteUtilisateur(UtilisateurSelectionne);
//            initialize();
//            UtilisateurSelectionne = null;
//            BoiteDialog.showInformation("Information", "Suppression réussie !");
//        } else {
//            BoiteDialog.showWarning("Attention", "Sélectionnez un utilisateur d'abord !");
//        }
//    }
//
//    public void button() {
//
//    }
//
//    @FXML
//    public void closeUI() {
//        Stage stage = (Stage) nomLabel.getScene().getWindow();
//        stage.close();
//    }
//
//
//
//}