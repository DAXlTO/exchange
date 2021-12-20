package pl.slaska.it.exchange.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.slaska.it.exchange.model.Offers;
import pl.slaska.it.exchange.model.Transactions;

public class OffersValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Transactions.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors){}

    public void validate1(float have, Object o, Errors errors) {
        Offers offers = (Offers) o;
        if(offers.getQuantity()+offers.getQuantity()*0.005 >have)
            errors.rejectValue("quantity","quantity", "You don't have enough BTC!!!");
    }
}
