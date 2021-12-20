package pl.slaska.it.exchange.model;

import java.time.LocalDate;

public class Offers {

    int idOffer;
    float quantity;
    float price;
    float fee;
    float total;
    LocalDate dateOffer;
    String idUser;

    public Offers(int idOffer, float quantity, float price, float fee ,float total ,LocalDate dateOffer, String idUser) {
        this.idOffer = idOffer;
        this.quantity = quantity;
        this.price = price;
        this.fee = fee;
        this.total = total;
        this.dateOffer = dateOffer;
        this.idUser = idUser;
    }

    public Offers(){}

    public int getIdOffer() {
        return idOffer;
    }

    public float getTotal() {
        return total;
    }

    public float getFee() {
        return fee;
    }

    public float getPrice() {
        return price;
    }

    public float getQuantity() {
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

    public void setTotal(float total) {
        this.total = total;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setDateOffer(LocalDate dateOffer) {
        this.dateOffer = dateOffer;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
