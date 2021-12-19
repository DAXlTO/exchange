package pl.slaska.it.exchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.slaska.it.exchange.model.Transactions;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionsDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addTransaction(String walletBuyer, String walletSeller, float quantity){
        jdbcTemplate.update("INSERT INTO Transactions VALUES(?,?,?,?,?)", obtenerID(),walletBuyer,walletSeller,quantity, LocalDate.now());
    }

    public int obtenerID(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(idTransactions) AS idTransactions FROM Transactions", String.class);
        if (consulta == null){
            return 1;
        }

        int r = Integer.parseInt(consulta) + 1;
        return r;
    }

    public List<Transactions> getTransactions(String wallet){
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Transactions WHERE walletBuyer=? OR walletSeller=?",
                    new TransactionsRowMapper(),wallet,wallet);
        }catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
