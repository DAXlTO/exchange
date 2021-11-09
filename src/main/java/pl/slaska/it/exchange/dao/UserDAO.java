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
        jdbcTemplate.update("INSERT INTO Ciudadano VALUES(?,?,?,?,?,?)",
                user.getNombre(), user.getNif(), user.getEmail(), user.getResidencia(), LocalDate.now(),passwordEncryptor.encryptPassword(user.getPassword()));
    }

    public void updateCiudadano(User user) {
        jdbcTemplate.update("UPDATE Ciudadano SET nombre =?, email =?, residencia =?, fechaRegistro =?, password =? WHERE nif =?",
                user.getNombre(), user.getEmail(), user.getResidencia(), user.getFechaRegistro(), user.getPassword(), user.getNif());
    }

    public void deleteCiudadano(String Nif) {
        jdbcTemplate.update("DELETE FROM Ciudadano WHERE nif =?", Nif);
    }

    public User getCiudadano(String Nif) {
        try {
            /*List<Ciudadano> mun = jdbcTemplate.query("SELECT * FROM Ciudadano",
                    new CiudadanoRowMapper());

            for (int i = 0; i < mun.size(); i++){
                System.out.println(mun.get(i).getNif());
            }
             */
            return jdbcTemplate.queryForObject("SELECT * FROM Ciudadano WHERE nif =?",
                    new UserRowMapper(), Nif);

        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> getCiudadanos(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Ciudadano",
                    new UserRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
