package com.rms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity(name = "t_chefCuisinier")
//@Table(name = "t_chefCuisinier")
public class ChefCuisinier extends User implements Serializable {
    public ChefCuisinier(int id, String nom, String prenom, String login, String password, String role) {
        super(id, nom, prenom, login, password, role);
    }

    public ChefCuisinier(String nom, String prenom, String login, String password, String role) {
        super(nom, prenom, login, password, role);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof ChefCuisinier)) return false;

        if(this.id == ((ChefCuisinier)obj).getId())
            return true;

        return false;
    }
}
