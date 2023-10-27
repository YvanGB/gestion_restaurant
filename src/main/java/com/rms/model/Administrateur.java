package com.rms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity(name = "t_administrateur")
//@Table(name = "t_administrateur")
public class Administrateur extends User implements Serializable {

    public Administrateur(int id, String nom, String prenom, String login, String password, String role) {
        super(id, nom, prenom, login, password, "Administrateur");
    }

    public Administrateur(String nom, String prenom, String login, String password, String role) {
        super(nom, prenom, login, password, "Administrateur");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof Administrateur)) return false;

        if(this.id == ((Administrateur)obj).getId())
            return true;

        return false;
    }
}
