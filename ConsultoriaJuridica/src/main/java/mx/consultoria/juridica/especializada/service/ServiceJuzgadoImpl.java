package mx.consultoria.juridica.especializada.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.consultoria.juridica.especializada.dto.JuzgadoDTO;
import mx.consultoria.juridica.especializada.repository.RepositorioJuzgadoDaoInterface;

@Service
public class ServiceJuzgadoImpl implements ServiceJuzgadoInterface{
	
	@Autowired
	RepositorioJuzgadoDaoInterface dao;

	@Override
	public List<JuzgadoDTO> listadoInformacion() {
		return dao.listadoInformacion();
	}

	@Override
	public JuzgadoDTO obtenerInformacion(JuzgadoDTO JuzgadoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int eliminarInformacion(JuzgadoDTO identificador) {
		return dao.eliminarInformacion(identificador);
	}

	@Override
	public int insertarInformacion(JuzgadoDTO newInformacion) {
		return dao.insertarInformacion(newInformacion);
		
	}

	@Override
	public int actualizarInformacionDB(JuzgadoDTO nuevo) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
