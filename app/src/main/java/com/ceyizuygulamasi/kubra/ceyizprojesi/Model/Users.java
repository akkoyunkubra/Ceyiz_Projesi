package com.ceyizuygulamasi.kubra.ceyizprojesi.Model;

public class Users {
    private String email;
    private String sifre;

    public Users() {
    }

    public Users(String email, String sifre) {
        this.email = email;
        this.sifre = sifre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
