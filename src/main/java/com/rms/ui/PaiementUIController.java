package com.rms.ui;

import com.rms.AppLauncher;
import com.rms.db.AccessDB;
import com.rms.model.BoiteDialog;
import com.rms.model.Commande;
import com.rms.model.Paiement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class PaiementUIController {

    @FXML
    private Label idCom;

    @FXML
    private Button cancelPaiementBtn;

    @FXML
    private Label datePaiement;

    @FXML
    private Label montantCom;

    @FXML
    private TextField montantEnc;

    @FXML
    private Label montantRest;

    @FXML
    private Label refPaiement;

    @FXML
    private Button validPaiementBtn;

    private Alert alert;

    private double reliquat = 0;
    Commande commande = null;

    List<Commande> commandeList = null;
    Paiement paiement = null;

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
        initInformation();
    }

    public PaiementUIController(){}

    public void initialize(){
        AppLauncher.getInstance().setPaiementUIController(this);
        commandeList = AccessDB.getInstance().getCommandes();
        initInformation();
    }

    private String genererRefPayment() {
        return "payref##" + new Date().toString().replaceAll(" ", "-");
    }

    private void initInformation(){
        montantRest.textProperty().set("0");
        if(commande != null){
            idCom.textProperty().set(" "+commande.getId());
            montantCom.textProperty().set(commande.getMontantTotal() + "");

            if(commande.getPaiement() != null) {
                paiement = commande.getPaiement();

                montantEnc.textProperty().set(commande.getPaiement().getMontantPaye() + "");
                montantRest.textProperty().set(commande.getPaiement().getQuittance() + "");
                refPaiement.textProperty().set(commande.getPaiement().getPaymentRef());
                datePaiement.textProperty().set(commande.getPaiement().getDate().toString());
            }else {
                paiement = new Paiement();
                paiement.setDate(new Date());
                paiement.setPaymentRef(genererRefPayment());
                commande.setPaiement(paiement);

                refPaiement.textProperty().set(paiement.getPaymentRef());
                datePaiement.textProperty().set(paiement.getDate().toString());
            }
        }

        montantEnc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calculerMontantRestant(newValue);
            }
        });
    }

    @FXML
    private void calculerMontantRestant(String value) {
        reliquat = Double.parseDouble(value) - commande.getMontantTotal();
        montantRest.textProperty().set(reliquat + "");
    }


    @FXML
    void addPaiement(ActionEvent event) throws IOException {

            if(montantRest.textProperty().get().isEmpty()) {
                montantRest.textProperty().set("0");
            }

            if(commande != null) {
                if(montantEnc.textProperty().get().isEmpty()) {
                    BoiteDialog.showError("Erreur", "Le champ du montant encaissé est vide !");
                } else {
                    if(commande.getMontantTotal() > Double.parseDouble(montantEnc.textProperty().get())) {
                        BoiteDialog.showError("Erreur", "Le prix de la commande est superieur au montant de paiement. \n" +
                                "Veuillez vérifiez le montant de paiement !!!");
                    } else {
                        if(reliquat > Double.parseDouble(montantRest.textProperty().get())) {
                            BoiteDialog.showWarning("Erreur", "Le montant rendu est inferieur au montant dû». \n" +
                                    "Verifiez le montant de paiement et celui rendu !!!");
                        } else if(reliquat < Double.parseDouble(montantRest.textProperty().get())) {
                            BoiteDialog.showWarning("Attention", "Le montant rendu est superieur au montant dû» !");
                        }

//                        if(commande.getPaiement() != null) {
//                            paiement.setMontantPaye((int) Double.parseDouble(montantEnc.textProperty().get()));
//                            paiement.setQuittance((int) Double.parseDouble(montantRest.textProperty().get()));
//
//                            paiement.setDate(new Date());
//
//                            if(AccessDB.getInstance().updatePaiement(paiement) != null) {
//                                BoiteDialog.showInformation("Information", "Paiement rÃ©ussi !");
//                                quit();
//                            } else {
//                                BoiteDialog.showError("Erreur", "Echec de paiement !");
//                            }
//                        }
//                        else {
//                            paiement = new Paiement();
//                            paiement.setCommande(commande);
//                            paiement.setMontantPaye((int) Double.parseDouble(montantEnc.textProperty().get()));
//                            paiement.setQuittance((int) Double.parseDouble(montantRest.textProperty().get()));
//                            paiement.setDate(new Date());
//                            String paymentReference = "#p#" + new Date().toString();
//                            paiement.setPaymentRef(paymentReference);
//
////                            BoiteDialog.showInformation("Information", "Paiement réussi !");
////                            quit();
//                            commande.setPaiement(paiement);
//
//                            if(AccessDB.getInstance().addPaiement(paiement) != null) {
//                                BoiteDialog.showInformation("Information", "Paiement effectué avec succès !");
//                                commande.setEtatPaiement("PAYE");
//
//                                commandeList.clear();
//                                commandeList.addAll(AccessDB.getInstance().getCommandes());
//                                quit();
//                            } else {
//                                BoiteDialog.showError("Erreur", "Echec de paiement !");
//                            }
//                        }
                        if (commande != null) {
                            paiement = new Paiement();
                            paiement.setCommande(commande);
                            commande.setPaiement(paiement);
                            paiement.setMontantPaye((int) Double.parseDouble(montantEnc.textProperty().get()));
                            paiement.setQuittance((int) Double.parseDouble(montantRest.textProperty().get()));
                            paiement.setDate(new Date());
                            String paymentReference = "#p#" + new Date().toString();
                            paiement.setPaymentRef(paymentReference);

//                            System.out.println(paiement.getQuittance());

                            Paiement persistedPaiement = AccessDB.getInstance().addPaiement(paiement);

                            if (persistedPaiement != null) {
                                commande.setPaiement(persistedPaiement);
                                commande.setEtatPaiement("PAYE");
                                // Mettre à jour la commande dans la base de données pour associer le paiement
                                AccessDB.getInstance().updateCommande(commande);
                                BoiteDialog.showInformation("Information", "Paiement effectué avec succès !");

                                commandeList.clear();
                                commandeList.addAll(AccessDB.getInstance().getCommandes());
                                quit();
                            } else {
                                BoiteDialog.showError("Erreur", "Echec de paiement !");
                            }
                        }
                    }
                }
            }
        }

    @FXML
    void cancelPaiement(ActionEvent event) {
        try{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Annuler");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sûr de vouloir annuler?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                Stage stage = (Stage) cancelPaiementBtn.getScene().getWindow();
                stage.close();
                AppLauncher.getInstance().initRestaurateurLayout();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }


    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }


    @FXML
    private void quit() throws IOException {
        Stage stage = (Stage) montantEnc.getScene().getWindow();
        AppLauncher.getInstance().initRestaurateurLayout();
        stage.close();
    }

}


/***********************************************************/

//
//    @FXML
//    private void supprimerPaiement() {
//        if(commande.getPaiement() != null) {
//            paiement = commande.getPaiement();
//            commande.setPaiement(null); // Dissocier la commande du paiement
//            AccessDB.getInstance().updateCommande(commande);
//
//            if(AccessDB.getInstance().deletePaiement(paiement) != null) {
//                BoiteDialog.showInformation("Information", "Suppression de paiement rÃ©ussie !");
//                quit();
//            } else {
//                BoiteDialog.showError("Erreur", "Echec de suppression du paiement !");
//            }
//        } else {
//            BoiteDialog.showError("Erreur", "Le paiement n'existe pas car vous ne l'avez pas encore validÃ© !");
//        }
//    }
//

