package pl.slaska.it.exchange.model;

public class Wallet {

    String idWallet;
    int quantity;
    String idUser;

    public Wallet(String idWallet, int quantity, String idUser) {
        this.idWallet = idWallet;
        this.quantity = quantity;
        this.idUser = idUser;
    }

    public String getIdWallet() {
        return idWallet;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdWallet(String idWallet) {
        this.idWallet = idWallet;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
