package com.rms.ui;

import com.rms.model.Commande;
import com.rms.model.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddOrderUIController {

    @FXML
    private TableView<Produit> dataGridViewProducts;

    @FXML
    private TableColumn<Produit, Integer> id;

    @FXML
    private TableColumn<Produit, String> nom;

    @FXML
    private TableColumn<Produit, Integer> quantite;

    @FXML
    private TableColumn<Produit, Double> prix;

    @FXML
    private TableColumn<Produit, Double> total;

    @FXML
    private Button cancelButton;

    @FXML
    private Button btnSaveOrder;

    @FXML
    private TableView<Commande> orderdataGridView;

    @FXML
    private TableColumn<Commande, String> orderreference;

    @FXML
    private TableColumn<Commande, String> date;

    @FXML
    private TableColumn<Commande, Double> totalprice;

    @FXML
    private Button cancelOrderBtn;

    @FXML
    private Button payOrderBtn;

    @FXML
    private ComboBox<String> comboBoxProducts;

    @FXML
    private TextField numericUpDownQuantity;

    @FXML
    private DatePicker dateTimePicker1;

    // Définissez ici vos méthodes de gestion d'événements et d'initialisation

    // Par exemple, une méthode d'initialisation appelée lorsque le FXML est chargé
    public void initialize() {
        // Initialisez les combobox, les champs de texte et les tables ici
        // Liez les données aux colonnes des tables
    }

    // Méthode de gestion de clic pour le bouton "Ajouter"
    @FXML
    private void handleAddProduct(ActionEvent event) {
        // Implémentez le code pour ajouter un produit à la liste
    }

    // Méthode de gestion de clic pour le bouton "Enregistrer"
    @FXML
    private void handleSaveOrder(ActionEvent event) {
        // Implémentez le code pour enregistrer la commande
    }

    // Méthode de gestion de clic pour le bouton "Payer"
    @FXML
    private void handlePayOrder(ActionEvent event) {
        // Implémentez le code pour payer la commande
    }

    // Ajoutez d'autres méthodes de gestion d'événements au besoin
}
