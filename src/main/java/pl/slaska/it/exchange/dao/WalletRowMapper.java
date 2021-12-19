package pl.slaska.it.exchange.dao;

import org.springframework.jdbc.core.RowMapper;
import pl.slaska.it.exchange.model.Wallet;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class WalletRowMapper implements RowMapper<Wallet> {
    public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException{

        Wallet wallet = new Wallet();
        wallet.setIdWallet(rs.getString("idWallet"));
        wallet.setQuantity(rs.getFloat("quantity"));
        wallet.setIdUser(rs.getString("idUser"));
        return wallet;
    }
}