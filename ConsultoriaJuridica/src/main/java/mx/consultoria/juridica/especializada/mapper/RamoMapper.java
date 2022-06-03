package mx.consultoria.juridica.especializada.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.consultoria.juridica.especializada.dto.RamoDTO;

public class RamoMapper<T> implements RowMapper<RamoDTO>{
	@Override
	public RamoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RamoDTO asignarvalores = new RamoDTO();
		asignarvalores.setNombre(rs.getString("NOMBRE"));
		asignarvalores.setIdRamo(rs.getInt("ID_RAMO"));
		return asignarvalores;
	}

}