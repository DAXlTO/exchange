package pl.slaska.it.exchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;

@Repository
public class OffersDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addOffer(float quantity, float price, String idUser){
        jdbcTemplate.update("INSERT INTO Offers VALUES(?,?,?,?,?)", obtenerID(),quantity,price, LocalDate.now(),idUser);
    }

    public int obtenerID(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(idOffer) AS idOffer FROM Offers", String.class);
        if (consulta == null){
            return 1;
        }

        int r = Integer.parseInt(consulta) + 1;
        return r;
    }
}
