package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.UserDetails;
import pl.slaska.it.exchange.model.Users;

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
    public UserDetails loadUserByUsername(String email, String password, UsersDAO usersDAO) {
        List<Users> users = usersDAO.getUsers();
        for(int i = 0; i < users.size(); i++){
            UserDetails user = new UserDetails();
            user.setEmail(users.get(i).getEmail());
            user.setPassword(users.get(i).getPassword());
            knownUsers.put(users.get(i).getEmail(),user);
        }
        UserDetails user = knownUsers.get(email.trim());
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
