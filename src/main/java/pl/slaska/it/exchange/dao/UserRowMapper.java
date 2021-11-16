package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.jdbc.core.RowMapper;
import pl.slaska.it.exchange.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setBalance(rs.getInt("balance"));
        user.setCredit_card(rs.getInt("card"));
        user.setAge(rs.getInt("age"));
        user.setEmail(rs.getString("email"));
        user.setNombre(rs.getString("nombre"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
