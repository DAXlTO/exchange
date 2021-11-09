package pl.slaska.it.exchange.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.jdbc.core.RowMapper;
import pl.slaska.it.exchange.model.Ciudadano;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class UserRowMapper implements RowMapper<Ciudadano> {
    public Ciudadano mapRow(ResultSet rs, int rowNum) throws SQLException {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        Ciudadano ciudadano= new Ciudadano();
        ciudadano.setNombre(rs.getString("nombre"));
        ciudadano.setNif(rs.getString("nif"));
        ciudadano.setEmail(rs.getString("email"));
        ciudadano.setResidencia(rs.getString("residencia"));
        ciudadano.setFechaRegistro(rs.getObject("fechaRegistro", LocalDate.class));
        ciudadano.setPassword(rs.getString("password"));
        return ciudadano;
    }
}
