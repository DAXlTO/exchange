package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.Users;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;

@Repository
public class UsersDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public void addCiudadano(Users users) {
        jdbcTemplate.update("INSERT INTO Users VALUES(?,?,?,?,?,?,?,?)",
                users.getId(), users.getBalance(), users.getCredit_card(), users.getAge(), users.getEmail(), users.getName(),passwordEncryptor.encryptPassword(users.getPassword()), users.getPhone());

    }

    public void updateCiudadano(Users users) {
        jdbcTemplate.update("UPDATE Users SET credit_card =?, age =?, email =?, name =?, password =?, phone =? WHERE id =?",
                users.getCredit_card(), users.getAge(), users.getEmail(), users.getName(), users.getPassword(), users.getPhone(), users.getId());
    }

    public void deleteCiudadano(String id) {
        jdbcTemplate.update("DELETE FROM Users WHERE id =?", id);
    }

    public Users getCiudadano(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE id =?",
                    new UsersRowMapper(), id);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Users> getUsers(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Users",
                    new UsersRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}

public class GeneratePrivateKey {
    public static void main(String[] args) {
        for (int i=0; i<10000; i++) {
            ECKey key = new ECKey();
            DumpedPrivateKey privKey = key.getPrivateKeyEncoded(NetworkParameters.prodNet());
            System.out.println("private key: " + privKey.toBase58());
            Address pubAddress = new Address(NetworkParameters.prodNet(), key.getPubKeyHash());
            System.out.println("public key: " + pubAddress.toBase58());
            System.out.println("--------------------------------"); } } }
