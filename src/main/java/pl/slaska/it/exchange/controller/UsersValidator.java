package pl.slaska.it.exchange.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.slaska.it.exchange.model.Users;

public class UsersValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Users.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Users users=(Users) o;

        if(users.getId().length() != 9)errors.rejectValue("id","invalid",
                "ID invalid");
        if(users.getCredit_card() < 100000000)errors.rejectValue("credit_card","invalid",
                "Credit Card invalid");
        if(users.getAge()<18) errors.rejectValue("age","invalid",
                "You are not of age");


    }
}
