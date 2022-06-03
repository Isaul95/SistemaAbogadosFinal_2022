package mx.consultoria.juridica.especializada.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.consultoria.juridica.especializada.dto.CatalogoJuzgadoDTO;

public class CatalogoJuzgadoMapper<T> implements RowMapper<CatalogoJuzgadoDTO>{
	@Override
	public CatalogoJuzgadoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CatalogoJuzgadoDTO asignarvalores = new CatalogoJuzgadoDTO();
		asignarvalores.setNombre(rs.getString("NOMBRE"));
		asignarvalores.setIdJuzgado(rs.getLong("ID_JUZGADO"));
		return asignarvalores;
	}

}