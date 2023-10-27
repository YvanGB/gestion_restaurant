package com.rms.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "t_produit")
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private int id;
    private String intitule;
    private String description;
    private double prix;
    private int qte;
    private String categorie;
    private String image;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande = null;

    @OneToMany(mappedBy = "produit")
    private List<ProductOrder> productOrdersItems = new ArrayList<>();

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }


    public Produit(){}

    public Produit(int id, String intitule, String description, double prix, String categorie, int qte, Date date) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.qte = qte;
        this.date = date;
    }

    public Produit(String intitule, String description, double prix, String categorie, int qte, Date date) {
        this.intitule = intitule;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.qte = qte;
        this.date = date;
    }

    public Produit(int id, String intitule, String description, double prix, String categorie, String image, int qte, Date date) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.image = image;
        this.qte = qte;
        this.date = date;
    }

    public Produit(String intitule, String description, double prix, String categorie, String image, int qte, Date date) {
        this.intitule = intitule;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.image = image;
        this.qte = qte;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof Produit)) return false;

        if(this.id == ((Produit)obj).getId())
            return true;

        return false;
    }
}
