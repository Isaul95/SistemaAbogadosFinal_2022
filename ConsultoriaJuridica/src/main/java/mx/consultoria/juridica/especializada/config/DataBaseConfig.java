package mx.consultoria.juridica.especializada.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import mx.consultoria.juridica.especializada.repository.RepositorioDao;
import mx.consultoria.juridica.especializada.repository.RepositorioImpl;

@Configuration
@PropertySource(
value= {
	"classpath:/properties/CONEXIONES.properties"	
})
public class DataBaseConfig {
	
	@Autowired
	private Environment enviroment;
	

	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
	    data.setDriverClassName(enviroment.getRequiredProperty("DRIVER"));
	    data.setUrl(enviroment.getRequiredProperty("IP"));
	    data.setUsername(enviroment.getRequiredProperty("USUARIO"));
	    data.setPassword(enviroment.getRequiredProperty("PASSWORD"));
	return data;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(dataSource());
		return jdbc;
	}
	
	@Bean(name="Servicio")
	public RepositorioDao servicio() {
		RepositorioImpl servicio = new RepositorioImpl();
		servicio.setJdbcTemplate(jdbcTemplate());
		return servicio;
	}
	

}
