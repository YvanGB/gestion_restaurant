package com.rms.ui;

import com.rms.AppLauncher;
import com.rms.db.AccessDB;
import com.rms.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommandesUIController {

    @FXML
    private TableView<Produit> productsTable;
    @FXML
    private TableColumn<Produit, Integer> idProdCol;
    @FXML
    private TableColumn<Produit, String> nomProdCol;
    @FXML
    private TableColumn<Produit, Double> priceProdCol;
    @FXML
    private TableColumn<Produit, Integer> qteProdCol;

    @FXML
    private TableView<ProductOrder> ordersTable;
    @FXML
    private TableColumn<ProductOrder, Integer> idOrdCol;
    @FXML
    private TableColumn<ProductOrder, String> productOrdCol;
    @FXML
    private TableColumn<ProductOrder, Integer> qteOrdCol;
    @FXML
    private TableColumn<ProductOrder, Double> totalOrdCol;



    @FXML
    private TableView<Commande> tableCommandes;
    @FXML
    private TableColumn<Commande, Integer> idComCol;
    @FXML
    private TableColumn<Commande, Date> dateComCol;
    @FXML
    private TableColumn<Commande, Double> montantComCol;
    @FXML
    private TableColumn<Commande, String> produitsComCol;
    @FXML
    private TableColumn<Commande, Paiement> paiementComCol;
    @FXML
    private TableColumn<Commande, String> paymentComCol;
    @FXML
    private TableColumn<ProductOrder, Integer> orderedProdId;
    @FXML
    private TableColumn<ProductOrder, String> orderedProdName;
    @FXML
    private TableColumn<ProductOrder, Double> orderedProdPrice;
    @FXML
    private TableColumn<ProductOrder, Double> orderedProdTotal;
    @FXML
    private TableView<ProductOrder> orderedProductsTable;

    @FXML
    private Button addItemBtn;
    @FXML
    private Button removeItemBtn;
    @FXML
    private Button confirmOrderBtn;

    @FXML
    private TableColumn<Paiement, Double> amountPaidCol;
    @FXML
    private TableColumn<Paiement, Date> datePaidCol;
    @FXML
    private TableColumn<Paiement, Integer> idPaidCol;
    @FXML
    private TableColumn<Paiement, String> refPaidCol;
    @FXML
    private TableColumn<Paiement, Double> reliquatPaidCol;
    @FXML
    private TableView<Paiement> paymentsTable;

    @FXML
    private Button recettesBtn;

    @FXML
    private Button manageComBtn;
    @FXML
    private AnchorPane paymentListAnchor;
    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private Button paymentListBtn;

    private ObservableList<Produit> productsList = FXCollections.observableArrayList();
    private ObservableList<Commande> ordersList = FXCollections.observableArrayList();
    private ObservableList<Produit> selectedProductsList = FXCollections.observableArrayList();

    private Produit selectedProduct;
    private ProductOrder selectedProductOrder;

    private Commande selectedCommande;
    private Commande commande;
    private ObservableList<CommandeItem> orderedItems = FXCollections.observableArrayList();

    private ObservableList<ProductOrder> productOrderList = FXCollections.observableArrayList();

    private ObservableList<Commande> listeCommande = FXCollections.observableArrayList();

    private ObservableList<Paiement> listePaiments = FXCollections.observableArrayList();

    private Alert alert;

    @FXML
    private Button logoutBtn;

    public void initialize() {
        AppLauncher.getInstance().setCommandesUIController(this);

        commande = new Commande();

        initProductsTable();
        initOrdersTable();
        initTableCommandes();
        initPaidCommandeTable();
    }

    private void initProductsTable() {

        idProdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProdCol.setCellValueFactory(new PropertyValueFactory<>("intitule"));
//        categorieCol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        priceProdCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qteProdCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
//        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Populate the products table with data from the database
        productsList.addAll(AccessDB.getInstance().getProduits());
        productsTable.setItems(productsList);

        productsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedProduct = newValue;
            }
        });
    }

    private void initOrdersTable() {

//        idOrdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//        productOrdCol.setCellValueFactory(new PropertyValueFactory<>("intitule"));

        idOrdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getProduit().getId()).asObject());
        productOrdCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduit().getIntitule()));
        qteOrdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantite()).asObject());
        totalOrdCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTotal()).asObject());


        ordersTable.setItems(productOrderList);

        ordersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedProductOrder = newValue;
            }
        });
    }

    private void initTableCommandes(){

            produitsComCol.setCellValueFactory(cellData -> {
                List<ProductOrder> commandesProduit = cellData.getValue().getProductOrdersItems();
                String commandeNames = commandesProduit.stream().map(pc -> pc.getProduit().getIntitule()).collect(Collectors.joining(", "));
                return new SimpleStringProperty(commandeNames);
            });
//            dateHeureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdateCommande()))

        idComCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        montantComCol.setCellValueFactory(new PropertyValueFactory<>("montantTotal"));
        dateComCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        paymentComCol.setCellValueFactory(new PropertyValueFactory<>("etatPaiement"));

//        paiementComCol.setCellValueFactory(new PropertyValueFactory<>("paiement"));

        listeCommande.addAll(AccessDB.getInstance().getCommandes());
        tableCommandes.setItems(listeCommande);

        tableCommandes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedCommande = newValue;
//                updateOrderedProductsTable(newValue);
            }
        });
    }

    private void initPaidCommandeTable(){
        idPaidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        refPaidCol.setCellValueFactory(new PropertyValueFactory<>("paymentRef"));
        amountPaidCol.setCellValueFactory(new PropertyValueFactory<>("montantPaye"));
        reliquatPaidCol.setCellValueFactory(new PropertyValueFactory<>("quittance"));
        datePaidCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        listePaiments.addAll(AccessDB.getInstance().getPaiements());
        paymentsTable.setItems(listePaiments);

    }

//    private void updateOrderedProductsTable(Commande commande) {
//        // Clear the existing items in orderedProductsTable
//        orderedProductsTable.getItems().clear();
//
//        List<ProductOrder> produits = commande.getProductOrdersItems();
//
//        orderedProductsTable.getItems().addAll(produits);
//    }

//    private void




    @FXML
    private void addItem() {
        if (selectedProduct != null) {
            // Afficher une boîte de dialogue pour saisir la quantité
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Saisir la quantité");
            dialog.setHeaderText("Entrez la quantité souhaitée pour " + selectedProduct.getIntitule());
            dialog.setContentText("Quantité:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && !result.get().isEmpty()) {
                try {
                    int qte = Integer.parseInt(result.get());

                    if (qte > 0 && qte <= selectedProduct.getQte()) {
                        ProductOrder productOrder = new ProductOrder(selectedProduct, commande, qte);

//                        productOrder.setQuantite(qte);
//                        productOrder.setProduit(selectedProduct);
                        productOrderList.add(productOrder);

                        for(Produit produit : productsList){
                            if(produit.equals(productOrder.getProduit())){
                                produit.setQte(produit.getQte() - productOrder.getQuantite());
                                break;
                            }
                        }

                        productsTable.refresh();

                    } else {
                        displayErrorMessage("Quantité insuffisante ou invalide.");
                    }
                } catch (NumberFormatException e) {
                    displayErrorMessage("Quantité invalide.");
                }
            }
        }
    }


    private void refreshProductsTable(){
         productsTable.getItems().setAll(productsList);
    }

    private void updateOrdersTable() {
        Date currentDate = new Date();
        double totalAmount = calculateTotalAmount();
        Commande newCommande = new Commande(selectedProductsList, currentDate,  totalAmount);
        ordersList.add(newCommande);
        ordersTable.refresh();
    }


    @FXML
    private void removeItem(ActionEvent event) {

//        ProductOrder selectedCommande = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedProductOrder != null) {
            for (Produit produit : productsList) {
                if (produit.equals(selectedProductOrder.getProduit())) {
                    produit.setQte(produit.getQte() + selectedProductOrder.getQuantite());
                    break;
                }
            }
            productOrderList.remove(selectedProductOrder);

            ordersTable.refresh();
            productsTable.refresh();

        }
        else {
                displayErrorMessage("Aucun produit sélectionné pour être retiré de la commande.");
            }
        }



    @FXML
    private void confirmOrder(ActionEvent event) {
        if (productOrderList.isEmpty()) {
            displayErrorMessage("La liste des produits sélectionnés est vide.");
            return;
        }

        double totalAmount = calculateTotalAmount();

        Date currentDate = new Date();
//        commande = new Commande(selectedProductsList, currentDate.toString(), totalAmount);
        List<ProductOrder> productOrders = new ArrayList<>();

        for(ProductOrder prod : productOrderList){
            productOrders.add(prod);
        }

        commande.setMontantTotal(totalAmount);
        commande.setDate(currentDate);
        commande.setProductOrdersItems(productOrders);
        commande.setEtatPaiement("NON PAYE");

        AccessDB.getInstance().addCommande(commande);

        productOrderList.clear();
        productsTable.refresh();

        listeCommande.clear();
        listeCommande.addAll(AccessDB.getInstance().getCommandes());
        tableCommandes.setItems(listeCommande);
        tableCommandes.refresh();
    }

    private double calculateTotalAmount() {
        double total = 0;
        for (ProductOrder po : productOrderList) {
            total += po.getTotal();
        }
        return total;
    }

    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public ObservableList<Commande> getListeCommande() {
        return listeCommande;
    }

    public void setListeCommande(ObservableList<Commande> listeCommande) {
        this.listeCommande = listeCommande;
    }
    public Commande getSelectedCommande() {
        return selectedCommande;
    }

    public void setSelectedCommande(Commande selectedCommande) {
        this.selectedCommande = selectedCommande;
    }

    @FXML
    void openPayment(ActionEvent event) throws IOException {
        if(selectedCommande != null){
            AppLauncher.getInstance().initPaimentLayout(selectedCommande);
            Stage stage = (Stage) manageComBtn.getScene().getWindow();
            stage.close();
        }else{
            displayErrorMessage("Aucune commande sélectionnée");
        }
    }

    @FXML
    void switchTab(ActionEvent event) throws IOException {
        if(event.getSource() == manageComBtn){
            baseAnchorPane.setVisible(true);
            paymentListAnchor.setVisible(false);
        }else if(event.getSource() == paymentListBtn){
            paymentsTable.refresh();
            baseAnchorPane.setVisible(false);
            paymentListAnchor.setVisible(true);
        }else if(event.getSource() == recettesBtn){
            try {
                AppLauncher.getInstance().initRecetteLayout();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    private void logout(ActionEvent event) throws IOException {
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

}
