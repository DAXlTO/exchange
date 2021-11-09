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
        user.setNombre(rs.getString("nombre"));
        user.setNif(rs.getString("nif"));
        user.setEmail(rs.getString("email"));
        user.setResidencia(rs.getString("residencia"));
        user.setFechaRegistro(rs.getObject("fechaRegistro", LocalDate.class));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
