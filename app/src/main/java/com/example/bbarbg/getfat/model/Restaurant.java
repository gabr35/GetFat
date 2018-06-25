package com.example.bbarbg.getfat.model;

public class Restaurant {



    String name;
    String type;
    double x;
    double y;
    boolean opennow;
    String foto;
    String adresse;
    double rating;

    public Restaurant(String name, String type, double x, double y, boolean opennow, String foto, String adresse, double rating) {

        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
        this.opennow = opennow;
        this.foto = foto;
        this.adresse = adresse;
        this.rating = rating;
    }
    public Restaurant(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public String getAdresse() {
        return adresse;
    }

    public double getRating() {
        return rating;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isOpennow() {
        return opennow;
    }

    public void setOpennow(boolean opennow) {
        this.opennow = opennow;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return name;
    }
}
