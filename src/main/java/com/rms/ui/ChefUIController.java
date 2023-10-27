package com.rms.ui;

import com.rms.AppLauncher;
import com.rms.dao.DAOException;
import com.rms.dao.ProduitDaoImpl;
import com.rms.db.AccessDB;
import com.rms.db.RMSDBException;
import com.rms.model.Produit;
import com.rms.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ChefUIController {

    @FXML
    private Button addProductBtn;

    @FXML
    private TableColumn<Produit, String> categorieCol;

    @FXML
    private ComboBox<String> categorieComboBox;

    @FXML
    private Button clearFieldBtn;

    @FXML
    private TableColumn<Produit, Date> dateCol;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private TableColumn<Produit, Integer> idCol;

    @FXML
    private TextField idProductField;

    @FXML
    private Button imgImportBtn;

    @FXML
    private Button signoutBtn;

    @FXML
    private Label logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TableColumn<Produit, String> nomCol;

    @FXML
    private TextField nomProductField;

    @FXML
    private TextField descriptionProductField;

    @FXML
    private TableColumn<Produit, Double> prixCol;

    @FXML
    private TextField prixProductField;

    @FXML
    private ImageView productImage;

    @FXML
    private TableView<Produit> productsTable;

    @FXML
    private TableColumn<Produit, Integer> qteCol;

    @FXML
    private TextField quantiteProductField;

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane updatePage;

    @FXML
    private Button updateProductBtn;

    @FXML
    private AnchorPane usersTableAnchor;

    private ProduitDaoImpl produitDao;

    private Produit selectedProduct;

    private Alert alert;

    private FilteredList<Produit> filteredProducts;

    private String[] listeCategorie = {"Repas", "Boissons", "Desserts", "Cocktail", "Fast food"};

    ObservableList<Produit> productList = FXCollections.observableArrayList();

    public void initialize() throws DAOException, RMSDBException {
        AppLauncher.getInstance().setChefUIController(this);
        initProductsTableView();
        productCategorieList();
        refreshProductTable();
        productSelection();
    }

    public void initProductsTableView() {
        productList.clear();
        productList.addAll(AccessDB.getInstance().getProduits());

        productsTable.setItems(productList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        categorieCol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        productsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Gérer l'événement de sélection ici
            if (newValue != null) {
                // Vous pouvez accéder aux propriétés de l'utilisateur sélectionné
                selectedProduct = newValue;
            }
        });
    }

    private void productSelection(){
//        if(selectedProduct != null){
//            idProductField.setText(String.valueOf(selectedProduct.getId()));
//            nomProductField.setText(selectedProduct.getIntitule());
//            descriptionProductField.setText(selectedProduct.getDescription());
//            prixProductField.setText(String.valueOf(selectedProduct.getPrix()));
//            quantiteProductField.setText(String.valueOf(selectedProduct.getQte()));
//            categorieComboBox.getSelectionModel().select(selectedProduct.getCategorie());
//            if (selectedProduct.getImage() != null) {
//                Image image = new Image(selectedProduct.getImage());
//                productImage.setImage(image);
//            } else {
//                productImage.setImage(null);
//            }
//        }

        // Écouteur pour la sélection d'un produit dans la TableView
        productsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Mettre à jour le formulaire avec les informations du produit sélectionné
                idProductField.setText(String.valueOf(newValue.getId()));
                nomProductField.setText(newValue.getIntitule());
                descriptionProductField.setText(newValue.getDescription());
                prixProductField.setText(String.valueOf(newValue.getPrix()));
                quantiteProductField.setText(String.valueOf(newValue.getQte()));
                categorieComboBox.getSelectionModel().select(newValue.getCategorie());

                // Mettre à jour l'image du produit (si disponible)
                if (newValue.getImage() != null) {
                    Image image = new Image(newValue.getImage());
                    productImage.setImage(image);
                } else {
                    // Si l'image n'est pas disponible, vous pouvez définir une image par défaut
                    // Par exemple : productImage.setImage(defaultImage);
                }
            }
        });
    }

    public void productCategorieList(){
        List<String> categories = new ArrayList<>();

        for(String data : listeCategorie){
            categories.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(categories);
        categorieComboBox.setItems(listData);
    }

//    public ObservableList<Produit> produitObservableList(){
//        ObservableList<Produit> listeProduits = FXCollections.observableArrayList();
//
//
//    }

    private void refreshProductTable() throws DAOException, RMSDBException {
        List<Produit> produits = AccessDB.getInstance().getProduits();
        ObservableList<Produit> observableProduits = FXCollections.observableArrayList(produits);
        productsTable.setItems(observableProduits);
    }

    @FXML
    private void importImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            productImage.setImage(image);
        }
    }

    @FXML
    void addProduct(ActionEvent event) throws DAOException, RMSDBException {
        String nomProduit = nomProductField.getText();
        String categorie = categorieComboBox.getSelectionModel().getSelectedItem().toString();
        int quantite = Integer.parseInt(quantiteProductField.getText());
        double prix = Double.parseDouble(prixProductField.getText());
        String description = descriptionProductField.getText();
        String image = productImage.getImage().getUrl();
        Date currentDate = new Date();

        Produit produit = new Produit(nomProduit, description, prix, categorie, image, quantite, currentDate);
        try {
            AccessDB.getInstance().addProduit(produit);
        } catch (Exception e) {
            System.out.println(e);
        }
        productList.add(produit);
        clearFields();
        refreshProductTable();
    }

    @FXML
    void clearFields() {
        idProductField.clear();
        nomProductField.clear();
        categorieComboBox.getSelectionModel().clearSelection();
        quantiteProductField.clear();
        descriptionProductField.clear();
        productImage.setImage(null);
        prixProductField.clear();
    }


    @FXML
    void searchProduct(ActionEvent event) {
        filteredProducts = new FilteredList<>(productList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProducts.setPredicate(produit -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (produit.getIntitule().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (produit.getCategorie().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    @FXML
    void updateProduct(ActionEvent event) throws DAOException, RMSDBException {
        Produit selectedProduct = (Produit) productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            String nomProduit = nomProductField.getText();
            String categorie = (String) categorieComboBox.getSelectionModel().getSelectedItem();
            int quantite = Integer.parseInt(quantiteProductField.getText());
            double prix = Double.parseDouble(prixProductField.getText());
            String description = descriptionProductField.getText();
            String image = productImage.getImage().getUrl();
            Date currentDate = new Date();

            selectedProduct.setIntitule(nomProduit);
            selectedProduct.setCategorie(categorie);
            selectedProduct.setQte(quantite);
            selectedProduct.setPrix(prix);
            selectedProduct.setDescription(description);
            selectedProduct.setImage(image);
            selectedProduct.setDate(currentDate);

            try {
                AccessDB.getInstance().updateProduit(selectedProduct);
                productsTable.refresh();
                clearFields();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un produit.");
            alert.showAndWait();
            return;
        }
    }

//    @FXML
//    void switchForm(ActionEvent event) {
//
//    }

    @FXML
    void deleteProduct(ActionEvent event) throws DAOException, RMSDBException {
        Produit selectedProduct = (Produit) productsTable.getSelectionModel().getSelectedItem();

        if(selectedProduct != null) {
            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sûr de vouloir supprimer le produit?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    AccessDB.getInstance().deleteProduit(selectedProduct);
                    refreshProductTable();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun produit n'a été sélectionné.");
            alert.showAndWait();
        }
    }

    @FXML
    void logout(ActionEvent event) {
        try{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quitter");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sûr de vouloir quitter?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                signoutBtn.getScene().getWindow().hide();
                AppLauncher.getInstance().initRootLayout();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
