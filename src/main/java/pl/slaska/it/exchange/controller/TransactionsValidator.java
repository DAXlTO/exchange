package pl.slaska.it.exchange.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.slaska.it.exchange.model.Transactions;

public class TransactionsValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Transactions.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors){}

    public void validate1(float have, Object o, Errors errors) {
        Transactions transactions = (Transactions) o;
        if(transactions.getQuantity()>have)
            errors.rejectValue("quantity","quantity", "You don't have enough BTC!!!");
        if(transactions.getWalletBuyer().length() != 40)
            errors.rejectValue("walletBuyer","walletBuyer","Incorrect wallet");
    }
}
