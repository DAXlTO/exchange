package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.User;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public void addCiudadano(User user) {
        jdbcTemplate.update("INSERT INTO \"User\" VALUES(?,?,?,?,?,?,?,?)",
                user.getId(), user.getBalance(), user.getCredit_card(), user.getAge(),user.getEmail(), user.getNombre(), LocalDate.now(),passwordEncryptor.encryptPassword(user.getPassword()),user.getPhone());
    }

    public void updateCiudadano(User user) {
        jdbcTemplate.update("UPDATE \"User\" SET card =?, age =?, password =? WHERE id =?",
                user.getCredit_card(), user.getAge(),user.getPassword(), user.getId());
    }

    public void deleteCiudadano(String id) {
        jdbcTemplate.update("DELETE FROM \"User\" WHERE id =?", id);
    }

    public User getCiudadano(String id) {
        try {
            /*List<Ciudadano> mun = jdbcTemplate.query("SELECT * FROM Ciudadano",
                    new CiudadanoRowMapper());

            for (int i = 0; i < mun.size(); i++){
                System.out.println(mun.get(i).getNif());
            }
             */
            return jdbcTemplate.queryForObject("SELECT * FROM \"User\" WHERE id =?",
                    new UserRowMapper(), id);

        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> getCiudadanos(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM \"User\"",
                    new UserRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
