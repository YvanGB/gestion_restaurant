package com.rms.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "t_recette")
public class Recette implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recette")
    private int id;
    @OneToMany(mappedBy = "recette", cascade= {CascadeType.PERSIST})
    private List<Paiement> listeDePaiement = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date date;
    private double montantTotal;

    public Recette(){}

    public Recette(List<Paiement> listeDePaiement) {
        this.listeDePaiement = listeDePaiement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Paiement> getListeDePaiement() {
        return listeDePaiement;
    }

    public void setListeDePaiement(List<Paiement> listeDePaiement) {
        this.listeDePaiement = listeDePaiement;
    }

    public void ajouterPaiement(Paiement paiement) {
        paiement.setRecette(this);
        listeDePaiement.add(paiement);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof Recette)) return false;

        if(this.id == ((Recette)obj).getId())
            return true;

        return false;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
}
