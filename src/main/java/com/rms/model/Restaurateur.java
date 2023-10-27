package com.rms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity(name = "t_restaurateur")
//@Table(name = "t_restaurateur")
public class Restaurateur extends User implements Serializable {
    public Restaurateur(int id, String nom, String prenom, String login, String password, String role) {
        super(id, nom, prenom, login, password, role);
    }

    public Restaurateur(String nom, String prenom, String login, String password, String role) {
        super(nom, prenom, login, password, role);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof Restaurateur)) return false;

        if(this.id == ((Restaurateur)obj).getId())
            return true;

        return false;
    }
}
