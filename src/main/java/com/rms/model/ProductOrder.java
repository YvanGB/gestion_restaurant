package com.rms.model;

import java.util.Objects;

import jakarta.persistence.*;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;

@Entity(name="t_productOrder")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;
    private int quantite;
    private double total;

    public ProductOrder() {}

    public ProductOrder(Produit produit, int qte) {
        this.setProduit(produit);
        this.setQuantite(qte);
        this.setTotal(produit.getPrix() * qte);
    }

    public ProductOrder(Produit produit, Commande commande, int qte) {
        this.setProduit(produit);
        this.setCommande(commande);
        this.setQuantite(qte);
        this.setTotal(produit.getPrix() * qte);
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int qte) {
        this.quantite = qte;
        updateTotal();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }


    public void updateTotal() {
        this.setTotal(this.quantite * this.produit.getPrix());
    }

    // Implémentation de equals() et hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return Objects.equals(produit, that.produit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produit);
    }
}