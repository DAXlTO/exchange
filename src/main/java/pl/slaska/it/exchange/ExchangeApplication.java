package pl.slaska.it.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class ExchangeApplication {

	public static void main(String[] args) {

		//BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		//System.out.println(passwordEncryptor.encryptPassword("admin"));

		SpringApplication.run(ExchangeApplication.class, args);
	}

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
