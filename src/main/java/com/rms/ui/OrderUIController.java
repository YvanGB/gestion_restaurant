package com.rms.ui;

import com.rms.model.Commande;
import com.rms.model.Paiement;
import com.rms.model.Produit;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderUIController {
    @FXML
    private TableView<Produit> produitTableView;

    @FXML
    private TableColumn<Produit, String> intituleColumn;

    @FXML
    private TableColumn<Produit, String> descriptionColumn;

    @FXML
    private TableColumn<Produit, Float> prixColumn;

    @FXML
    private TableColumn<Produit, Integer> quantiteColumn;

    @FXML
    private TextField montantTotalField;

    @FXML
    private Button ajouterProduitButton;

    @FXML
    private Button passerCommandeButton;

    @FXML
    private TextField montantPayeField;

    @FXML
    private Button procederPaiementButton;

    private Commande commande = new Commande();
    private Paiement paiement = new Paiement();

    @FXML
    public void initialize() {
        intituleColumn.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
        produitTableView.setItems(FXCollections.observableArrayList(commande.getListeDeProduit()));
        miseAJourMontantTotal();

        ajouterProduitButton.setOnAction(event -> ajouterProduit());
        passerCommandeButton.setOnAction(event -> passerCommande());
        procederPaiementButton.setOnAction(event -> procederPaiement());
    }

    private void miseAJourMontantTotal() {
        montantTotalField.setText(String.valueOf(commande.getMontantTotal()));
    }

    private void ajouterProduit() {
        // ouvrez le dialogue pour ajouter un produit
    }

    private void passerCommande() {
        // Validation de la commande
    }

    private void procederPaiement() {
        // procéder à l'action de paiement
    }
}
