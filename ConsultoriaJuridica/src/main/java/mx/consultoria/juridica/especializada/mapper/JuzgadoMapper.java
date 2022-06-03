package mx.consultoria.juridica.especializada.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.consultoria.juridica.especializada.dto.JuzgadoDTO;




public class JuzgadoMapper<T> implements RowMapper<JuzgadoDTO>{
	@Override
	public JuzgadoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JuzgadoDTO asignarvalores = new JuzgadoDTO();
		asignarvalores.setIdJuzgado(rs.getLong("ID"));
		asignarvalores.setJuzgado(rs.getString("JUZGADO"));
		asignarvalores.setRamo(rs.getString("RAMO"));
		return asignarvalores;
	}

}
