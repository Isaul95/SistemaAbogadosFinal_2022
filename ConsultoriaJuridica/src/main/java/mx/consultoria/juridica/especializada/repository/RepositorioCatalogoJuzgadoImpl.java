package mx.consultoria.juridica.especializada.repository;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.consultoria.juridica.especializada.dto.CatalogoJuzgadoDTO;
import mx.consultoria.juridica.especializada.mapper.CatalogoJuzgadoMapper;

@Repository
public class RepositorioCatalogoJuzgadoImpl implements RepositorioDaoCatalogoJuzgadoInterface{

	@Autowired
	private DataSource dataSource;  // ESTE TIENE LA CONEXIÓN A BASE DE DATOS
	private JdbcTemplate jdbcTemplate=new JdbcTemplate();//ME PERMITE CONECTAR A BASE DE DATOS Y EJECUTAR CONSULTAS
	
	
	@Override
	public List<CatalogoJuzgadoDTO> listadoInformacion() {
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate.query("SELECT * FROM CATALOGO_JUZGADO", new CatalogoJuzgadoMapper<CatalogoJuzgadoDTO>());
	}

	@Override
	public CatalogoJuzgadoDTO obtenerInformacion(CatalogoJuzgadoDTO CatalogoJuzgadoDTO) {
		return null;
	}

	@Override
	public int eliminarInformacion(CatalogoJuzgadoDTO identificador) {
		jdbcTemplate.setDataSource(dataSource);		         // Contiene la conexion a la DB
		Object [] idjuzgado = {identificador.getIdJuzgado()};      // Se crea un areglo de tipo object
		int[] types = {Types.INTEGER}; 						// 
		return jdbcTemplate.update("delete from catalogo_juzgado where id_juzgado = ?",idjuzgado,types); // Query
	}

	@Override
	public int insertarInformacion(CatalogoJuzgadoDTO newInformacion) {
		jdbcTemplate.setDataSource(dataSource);		         // Contiene la conexion a la DB
		return jdbcTemplate.update("INSERT INTO CATALOGO_JUZGADO(NOMBRE) VALUES(?)", newInformacion.getNombre());
	}

	@Override
	public int actualizarInformacion(CatalogoJuzgadoDTO nuevo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
