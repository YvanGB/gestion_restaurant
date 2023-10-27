package com.rms.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "t_commande")
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private int id;
    @OneToMany(mappedBy = "commande", cascade= {CascadeType.PERSIST})
    private List<Produit> listeDeProduit = new ArrayList<>();
    private double montantTotal;

    @ManyToOne
    @JoinColumn(name = "id_paiement")
    private Paiement paiement = null;

    @OneToMany(mappedBy = "commande", cascade = {CascadeType.ALL})
    private List<ProductOrder> productOrdersItems = new ArrayList<>();

    @Column(name = "Etat_paiement")
    private String etatPaiement;


    private Date date;


    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Commande(){}

    public Commande(int id, List<Produit> listeDeProduit, double montantTotal) {
        this.id = id;
        this.listeDeProduit = listeDeProduit;
        this.montantTotal = montantTotal;
    }

    public Commande(List<Produit> listeDeProduit, Date date, double montantTotal) {
        this.listeDeProduit = listeDeProduit;
        this.montantTotal = montantTotal;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(String etatPaiement) {
        this.etatPaiement = etatPaiement;
    }


    public List<Produit> getListeDeProduit() {
        return listeDeProduit;
    }

    public void setListeDeProduit(List<Produit> listeDeProduit) {
        this.listeDeProduit = listeDeProduit;
    }

    public List<ProductOrder> getProductOrdersItems() {
        return productOrdersItems;
    }

    public void setProductOrdersItems(List<ProductOrder> productOrdersItems) {
        this.productOrdersItems = productOrdersItems;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void ajouterProduit(Produit produit) {
        produit.setCommande(this);
        listeDeProduit.add(produit);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof Commande)) return false;

        if(this.id == ((Commande)obj).getId())
            return true;

        return false;
    }
}
