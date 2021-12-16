package pl.slaska.it.exchange.model;

import java.time.LocalDate;

public class Offers {

    int idOffer;
    int quantity;
    float price;
    LocalDate dateOffer;
    String idUser;

    public Offers(int idOffer, int quantity, float price, LocalDate dateOffer, String idUser) {
        this.idOffer = idOffer;
        this.quantity = quantity;
        this.price = price;
        this.dateOffer = dateOffer;
        this.idUser = idUser;
    }

    public Offers(){}

    public int getIdOffer() {
        return idOffer;
    }


    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDateOffer() {
        return dateOffer;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDateOffer(LocalDate dateOffer) {
        this.dateOffer = dateOffer;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
