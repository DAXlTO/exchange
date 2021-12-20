package pl.slaska.it.exchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.Offers;
import pl.slaska.it.exchange.model.Transactions;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OffersDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addOffer(float quantity, float price, float total ,String idUser){
        jdbcTemplate.update("INSERT INTO Offers VALUES(?,?,?,?,?,?)", obtenerID(),quantity,price, total, LocalDate.now(),idUser);
    }

    public int obtenerID(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(idOffer) AS idOffer FROM Offers", String.class);
        if (consulta == null){
            return 1;
        }

        int r = Integer.parseInt(consulta) + 1;
        return r;
    }

    public List<Offers> getOffers(String id){
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Offers WHERE idUser != ?",
                    new OffersRowMapper(),id);
        }catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
