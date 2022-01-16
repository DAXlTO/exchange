package pl.slaska.it.exchange.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.slaska.it.exchange.dao.UsersDAO;
import pl.slaska.it.exchange.model.Users;

public class UsersValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Users.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Users users=(Users) o;

        UsersDAO usersDAO = new UsersDAO();
        System.out.println(users.getEmail());
        System.out.println(usersDAO.getUserByEmail(users.getEmail()));
        if(!usersDAO.getUserByEmail(users.getEmail()).equals(""))
            errors.rejectValue("email","invalid",
                    "email duplicated");
        if(users.getId().length() != 9)errors.rejectValue("id","invalid",
                "ID invalid");

        String credit = String.valueOf(users.getCredit_card());
       if(credit.length() != 16)errors.rejectValue("credit_card","invalid",
             "Credit Card invalid");

        if(users.getAge()<18) errors.rejectValue("age","invalid",
                "You are not of age");
    }
    public void invalidID(Object o, Errors errors){

    }
}
