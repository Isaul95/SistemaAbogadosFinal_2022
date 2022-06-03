package mx.consultoria.juridica.especializada.service;

import java.util.List;

import mx.consultoria.juridica.especializada.dto.RamoDTO;

public interface ServiceInterface {
	public List<RamoDTO> listadoInformacion();
	RamoDTO actualizarInformacion(RamoDTO ramoDto);	
	public int eliminarInformacion(RamoDTO identificador);
	public int insertarInformacion(RamoDTO newInformacion);
	public int ActualizarCatRamosDB(RamoDTO nuevo);
}
