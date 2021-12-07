package pl.slaska.it.exchange.dao;

import pl.slaska.it.exchange.model.Offers;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class OffersRowMapper implements RowMapper<Offers> {
    public Offers mapRow(ResultSet rs, int rowNum) throws SQLException{

        Offers offers = new Offers();
        offers.setIdOffer(rs.getInt("idOffer"));
        offers.setWalletSeller(rs.getString("walletSeller"));
        offers.setQuantity(rs.getInt("quantity"));
        offers.setDateOffer(rs.getObject("dateOffer", LocalDate.class));
        offers.setIdUser(rs.getString("idUser"));
        return offers;
    }
}
