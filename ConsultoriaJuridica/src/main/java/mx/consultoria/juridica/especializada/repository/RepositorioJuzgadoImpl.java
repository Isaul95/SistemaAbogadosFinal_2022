package mx.consultoria.juridica.especializada.repository;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.consultoria.juridica.especializada.dto.JuzgadoDTO;
import mx.consultoria.juridica.especializada.dto.RamoDTO;
import mx.consultoria.juridica.especializada.mapper.JuzgadoMapper;
import mx.consultoria.juridica.especializada.mapper.RamoMapper;


@Repository
public class RepositorioJuzgadoImpl implements RepositorioJuzgadoDaoInterface{

	@Autowired
	private DataSource dataSource;  // ESTE TIENE LA CONEXIÓN A BASE DE DATOS
	private JdbcTemplate jdbcTemplate=new JdbcTemplate();
	
	@Override
	public List<JuzgadoDTO> listadoInformacion() {
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate.query("select JUZ.ID_JUZGADO_PRIMERA_INSTANCIA AS ID, J.Nombre AS JUZGADO, R.Nombre AS RAMO from JUZGADO JUZ\r\n"
				+ "inner join CATALOGO_JUZGADO J\r\n"
				+ "on JUZ.ID_JUZGADO = J.ID_JUZGADO\r\n"
				+ "inner join CATALOGO_RAMO R\r\n"
				+ "on R.ID_RAMO = JUZ.ID_RAMO", new JuzgadoMapper<JuzgadoDTO>());
		
	}
	@Override
	public JuzgadoDTO obtenerInformacion(JuzgadoDTO JuzgadoDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int eliminarInformacion(JuzgadoDTO identificador) {
		jdbcTemplate.setDataSource(dataSource);		         // Contiene la conexion a la DB
		Object [] idjuzgado = {identificador.getIdJuzgado()};      // Se crea un areglo de tipo object
		int[] types = {Types.INTEGER}; 						// 
		return jdbcTemplate.update("delete from juzgado where id_juzgado = ?",idjuzgado,types); // Query
		
	}
	@Override
	public int insertarInformacion(JuzgadoDTO newInformacion) {
		jdbcTemplate.setDataSource(dataSource);		         // Contiene la conexion a la DB
		return jdbcTemplate.update("INSERT INTO Juzgado(NOMBRE) VALUES(?)", newInformacion.getJuzgado());
		
	}
	@Override
	public int actualizarInformacionDB(JuzgadoDTO nuevo) {
		// TODO Auto-generated method stub
		return 0;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	


}
	

	
	
	