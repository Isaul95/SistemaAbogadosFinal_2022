package mx.consultoria.juridica.especializada.repository;

import java.util.List;

import mx.consultoria.juridica.especializada.dto.RamoDTO;

public interface RepositorioDao {
	public List<RamoDTO> listadoInformacion();
	 RamoDTO actualizarInformacion(RamoDTO ramoDto);	
	public int eliminarInformacion(RamoDTO identificador);
	public int insertarInformacion(RamoDTO newInformacion);
	public int UpdateCatRamosDB(RamoDTO nuevo);
}
