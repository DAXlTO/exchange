package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.jdbc.core.RowMapper;
import pl.slaska.it.exchange.model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UsersRowMapper implements RowMapper<Users> {
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        Users users = new Users();
        users.setId(rs.getString("id"));
        users.setBalance(0);
        users.setCredit_card(rs.getInt("credit_card"));
        users.setAge(rs.getInt("age"));
        users.setEmail(rs.getString("email"));
        users.setName(rs.getString("name"));
        users.setPassword(rs.getString("password"));
        users.setCredit_card(rs.getInt("phone"));
        return users;
    }
}
