package com.rms.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "t_paiement")
public class Paiement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    private int id;
    private double montantPaye;
    private double quittance;
    private String paymentRef;

    @OneToOne(mappedBy = "paiement")
    Commande commande = null;
    @OneToMany(mappedBy = "paiement", cascade= {CascadeType.PERSIST})
    private List<Commande> listeCommande = new ArrayList<>();
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_recette")
    private Recette recette = null;

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public Paiement(){}

    public Paiement(int id, double montantPaye, double quittance, List<Commande> listeCommande, Date date, String paymentRef) {
        this.id = id;
        this.montantPaye = montantPaye;
        this.quittance = quittance;
        this.listeCommande = listeCommande;
        this.date = date;
        this.paymentRef = paymentRef;
    }

    public Paiement(double montantPaye, double quittance, List<Commande> listeCommande, Date date, String paymentRef) {
        this.montantPaye = montantPaye;
        this.quittance = quittance;
        this.listeCommande = listeCommande;
        this.date = date;
        this.paymentRef = paymentRef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(int montantPaye) {
        this.montantPaye = montantPaye;
    }

    public double getQuittance() {
        return quittance;
    }

    public void setQuittance(int quittance) {
        this.quittance = quittance;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public List<Commande> getListeCommande() {
        return listeCommande;
    }

    public void setListeCommande(List<Commande> listeCommande) {
        this.listeCommande = listeCommande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void ajouterCommande(Commande commande) {
        commande.setPaiement(this);
        listeCommande.add(commande);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof Paiement)) return false;

        if(this.id == ((Paiement)obj).getId())
            return true;

        return false;
    }
}
