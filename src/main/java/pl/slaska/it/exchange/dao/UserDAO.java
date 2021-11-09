package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.Ciudadano;
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

    public void addCiudadano(Ciudadano ciudadano) {
        jdbcTemplate.update("INSERT INTO Ciudadano VALUES(?,?,?,?,?,?)",
                ciudadano.getNombre(),ciudadano.getNif(),ciudadano.getEmail(),ciudadano.getResidencia(), LocalDate.now(),passwordEncryptor.encryptPassword(ciudadano.getPassword()));
    }

    public void updateCiudadano(Ciudadano ciudadano) {
        jdbcTemplate.update("UPDATE Ciudadano SET nombre =?, email =?, residencia =?, fechaRegistro =?, password =? WHERE nif =?",
                ciudadano.getNombre(),ciudadano.getEmail(),ciudadano.getResidencia(),ciudadano.getFechaRegistro(),ciudadano.getPassword(),ciudadano.getNif());
    }

    public void deleteCiudadano(String Nif) {
        jdbcTemplate.update("DELETE FROM Ciudadano WHERE nif =?", Nif);
    }

    public Ciudadano getCiudadano(String Nif) {
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

    public List<Ciudadano> getCiudadanos(){
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
