package mx.consultoria.juridica.especializada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.consultoria.juridica.especializada.dto.CatalogoJuzgadoDTO;
import mx.consultoria.juridica.especializada.repository.RepositorioDaoCatalogoJuzgadoInterface;

@Service
public class ServiceCatalogoJuzgadoImpl implements ServiceCatalogoJuzgadoInterface{

	@Autowired
	RepositorioDaoCatalogoJuzgadoInterface dao;
	
	
	@Override
	public List<CatalogoJuzgadoDTO> listadoInformacion() {
		return dao.listadoInformacion();
	}

	@Override
	public CatalogoJuzgadoDTO obtenerInformacion(CatalogoJuzgadoDTO CatalogoJuzgadoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int eliminarInformacion(CatalogoJuzgadoDTO identificador) {
		return dao.eliminarInformacion(identificador);
	}

	@Override
	public int insertarInformacion(CatalogoJuzgadoDTO newInformacion) {
	     return dao.insertarInformacion(newInformacion);
	}

	@Override
	public int actualizarInformacion(CatalogoJuzgadoDTO nuevo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
