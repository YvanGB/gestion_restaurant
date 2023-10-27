package com.rms.model;

public class CommandeItem {
    private Produit produit;
    private int quantite;
    private double prixTotal;

    public CommandeItem(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        this.prixTotal = produit.getPrix() * quantite;
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

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    // Ajoutez les méthodes getters pour les attributs
}
