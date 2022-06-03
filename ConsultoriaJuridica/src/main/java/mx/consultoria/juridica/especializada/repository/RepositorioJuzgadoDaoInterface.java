package mx.consultoria.juridica.especializada.repository;

import java.util.List;

import mx.consultoria.juridica.especializada.dto.JuzgadoDTO;

public interface RepositorioJuzgadoDaoInterface {
	
	public List<JuzgadoDTO> listadoInformacion();
	JuzgadoDTO obtenerInformacion(JuzgadoDTO JuzgadoDTO);
	public int eliminarInformacion(JuzgadoDTO identificador);
	public int insertarInformacion(JuzgadoDTO newInformacion);
	public int actualizarInformacionDB(JuzgadoDTO nuevo);

}
