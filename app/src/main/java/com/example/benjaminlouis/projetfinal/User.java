package com.example.benjaminlouis.projetfinal;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class User {
    private Integer id;
    private String nom;
    private String prenom;
    private String sexe;
    private String metier;
    private String service;
    private String mail;
    private String telephone;
    private String cv;


    public User(){
        this.nom=null;
        this.prenom=null;
        this.sexe=null;
        this.metier=null;
        this.service=null;
        this.mail=null;
        this.telephone=null;
        this.cv=null;
        this.id=null;
    }

    public User(Integer id,String nom, String prenom, String sexe, String metier, String service, String mail, String telephone, String cv) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.metier = metier;
        this.service = service;
        this.mail = mail;
        this.telephone = telephone;
        this.cv = cv;
        this.id=id;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        service = service;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
