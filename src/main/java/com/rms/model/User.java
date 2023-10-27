package com.rms.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "t_user")
//@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    protected int id;
    protected String nom;
    protected String prenom;
    protected String login;
    protected String password;
    protected String role;

    public User() {
        // Constructeur par défaut sans arguments
    }
    public User(int id, String nom, String prenom, String login, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String nom, String prenom, String login, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(!(obj instanceof User)) return false;

        if(this.id == ((User)obj).getId())
            return true;

        return false;
    }


}
