package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.Users;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public void addCiudadano(Users users) {
        jdbcTemplate.update("INSERT INTO \"User\" VALUES(?,?,?,?,?,?,?,?)",
                users.getId(), users.getBalance(), users.getCredit_card(), users.getAge(), users.getEmail(), users.getNombre(), LocalDate.now(),passwordEncryptor.encryptPassword(users.getPassword()), users.getPhone());
    }

    public void updateCiudadano(Users users) {
        jdbcTemplate.update("UPDATE \"User\" SET card =?, age =?, password =? WHERE id =?",
                users.getCredit_card(), users.getAge(), users.getPassword(), users.getId());
    }

    public void deleteCiudadano(String id) {
        jdbcTemplate.update("DELETE FROM \"User\" WHERE id =?", id);
    }

    public Users getCiudadano(String id) {
        try {
            /*List<Ciudadano> mun = jdbcTemplate.query("SELECT * FROM Ciudadano",
                    new CiudadanoRowMapper());

            for (int i = 0; i < mun.size(); i++){
                System.out.println(mun.get(i).getNif());
            }
             */
            return jdbcTemplate.queryForObject("SELECT * FROM \"User\" WHERE id =?",
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
