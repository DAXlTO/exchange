package pl.slaska.it.exchange.model;

import java.time.LocalDate;

public class Transactions {

    int idTransactions;
    String walletBuyer;
    String walletSeller;
    int quantity;
    LocalDate trans_date;

    public Transactions(int idTransactions, String walletBuyer, String walletSeller, int quantity, LocalDate trans_date) {
        this.idTransactions = idTransactions;
        this.walletBuyer = walletBuyer;
        this.walletSeller = walletSeller;
        this.quantity = quantity;
        this.trans_date = trans_date;
    }

    public Transactions(){}

    public int getIdTransactions() {
        return idTransactions;
    }

    public String getWalletBuyer() {
        return walletBuyer;
    }

    public String getWalletSeller() {
        return walletSeller;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getTrans_date() {
        return trans_date;
    }

    public void setIdTransactions(int idTransactions) {
        this.idTransactions = idTransactions;
    }

    public void setWalletBuyer(String walletBuyer) {
        this.walletBuyer = walletBuyer;
    }

    public void setWalletSeller(String walletSeller) {
        this.walletSeller = walletSeller;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTrans_date(LocalDate trans_date) {
        this.trans_date = trans_date;
    }
}
