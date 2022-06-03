package mx.consultoria.juridica.especializada.service;

import java.util.List;

import mx.consultoria.juridica.especializada.dto.JuzgadoDTO;
import mx.consultoria.juridica.especializada.dto.RamoDTO;

public interface ServiceJuzgadoInterface {

	public List<JuzgadoDTO> listadoInformacion();
	JuzgadoDTO obtenerInformacion(JuzgadoDTO JuzgadoDTO);
	public int eliminarInformacion(JuzgadoDTO identificador);
	public int insertarInformacion(JuzgadoDTO newInformacion);
	public int actualizarInformacionDB(JuzgadoDTO nuevo);
	
}
