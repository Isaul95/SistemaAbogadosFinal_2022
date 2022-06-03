package mx.consultoria.juridica.especializada.service;

import java.util.List;

import mx.consultoria.juridica.especializada.dto.CatalogoJuzgadoDTO;


public interface ServiceCatalogoJuzgadoInterface {

	public List<CatalogoJuzgadoDTO> listadoInformacion();
	CatalogoJuzgadoDTO obtenerInformacion(CatalogoJuzgadoDTO CatalogoJuzgadoDTO);	
	public int eliminarInformacion(CatalogoJuzgadoDTO identificador);
	public int insertarInformacion(CatalogoJuzgadoDTO newInformacion);
	public int actualizarInformacion(CatalogoJuzgadoDTO nuevo);
	
}
