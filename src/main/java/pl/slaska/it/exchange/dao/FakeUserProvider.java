package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.Ciudadano;
import pl.slaska.it.exchange.model.UserDetails;

import java.util.*;

@Repository
public class FakeUserProvider implements UserDao {

    final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();

    public FakeUserProvider() {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        UserDetails userei102720jamdd = new UserDetails();
        userei102720jamdd.setEmail("ei102720jamdd");
        userei102720jamdd.setPassword(passwordEncryptor.encryptPassword("ei102720jamdd"));
        knownUsers.put("ei102720jamdd", userei102720jamdd);
    }

    @Override
    public UserDetails loadUserByUsername(String nif, String password, UserDAO userDAO) {
        List<Ciudadano> ciudadanos = userDAO.getCiudadanos();
        for (int i = 0; i < ciudadanos.size(); i++){
            UserDetails usuario = new UserDetails();
            usuario.setNif(ciudadanos.get(i).getNif());
            usuario.setPassword(ciudadanos.get(i).getPassword());
            knownUsers.put(ciudadanos.get(i).getNif(),usuario);
        }
        UserDetails user = knownUsers.get(nif.trim());
        if (user == null)
            return null;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            return user;
        }
        else {
            return null;
        }
    }




}
