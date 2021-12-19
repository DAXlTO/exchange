package pl.slaska.it.exchange.model;

public class Wallet {

    String idWallet;
    float quantity;
    String idUser;

    public Wallet(String idWallet, float quantity, String idUser) {
        this.idWallet = idWallet;
        this.quantity = quantity;
        this.idUser = idUser;
    }

    public Wallet(){}

    public String getIdWallet() {
        return idWallet;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdWallet(String idWallet) {
        this.idWallet = idWallet;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
