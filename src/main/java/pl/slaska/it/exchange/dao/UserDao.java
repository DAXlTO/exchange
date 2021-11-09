package pl.slaska.it.exchange.dao;

import pl.slaska.it.exchange.model.UserDetails;

public interface UserDao {
    UserDetails loadUserByUsername(String email, String contraseña, UserDAO userDAO);
}
