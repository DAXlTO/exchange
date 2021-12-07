package pl.slaska.it.exchange.dao;

import pl.slaska.it.exchange.model.Offers;
import org.springframework.jdbc.core.RowMapper;
import pl.slaska.it.exchange.model.Transactions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class TransactionsRowMapper implements RowMapper<Transactions> {
    public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException{

        Transactions transactions = new Transactions();
        transactions.setIdTransactions(rs.getInt("idTransactions"));
        transactions.setWalletBuyer(rs.getString("walletBuyer"));
        transactions.setWalletSeller(rs.getString("walletSeller"));
        transactions.setQuantity(rs.getInt("quantity"));
        transactions.setTrans_date(rs.getObject("trans_date", LocalDate.class));
        return transactions;
    }
}