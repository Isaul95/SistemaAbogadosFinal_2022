package mx.consultoria.juridica.especializada.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.consultoria.juridica.especializada.dto.HistoricoDocumentosDTO;
import mx.consultoria.juridica.especializada.entities.HistoricoCasos;
import mx.consultoria.juridica.especializada.repository.RepositorioDaoHistoricoDocumentos;


@Service
public class ServiceImplHistoricoDocumentos implements ServiceInterfaceHistoricoDocumentos{
	
	@Autowired
	RepositorioDaoHistoricoDocumentos dao;

	
	@Override
	public List<HistoricoDocumentosDTO> listadoDeHistoricoCasos() { //Lista de casos la tabla de historico
		return dao.listHistoricoDeCasosAntiguos();
	}


	
//	Este metodo para insertar en la DB se realizo con hibernate
	@Override
	public int insertarInformacionCaso(HistoricoDocumentosDTO histDocumentosDTO) { // Realiza el registro del documento.

		HistoricoCasos documento = new HistoricoCasos(); // Objeto ->documento es de la clase Entity

		//	Objeto de la Entity asignarle el valor que extraemos del DTO a los campos que se van a insertar en la tabla
		documento.setDocumentoBy(histDocumentosDTO.getDocByte());
		documento.setFechaRegistro(dao.obtenerMomentoActual()); // Se hizo un metodo en el DAO para consultar la fecha actual y ese insertarlo en el campo de fecha registro.
		documento.setNombreArchivo(histDocumentosDTO.getNombreDocumento());
		documento.setUsuarioAlta(histDocumentosDTO.getUsuarioAlta());
		documento.setIdHistoricoCasos(dao.obtenValorSecuenciaTableHistorico()); //Metodo para consultar la secuencia de la tabla, de esta forma con hibernate ya no hacemos uso de triggers.
		
		return dao.insertarDocumentosCaso(documento);
	}

//	NOTAS; HIBERNATE TE OBLIGA A LLENAR TODOS LOS CAMPOS

	
	
// Metido para consultar el nombre y el dcumento binario desde DB
	@Override
	public HistoricoCasos descargarArchivoDeHistoricoUser(HistoricoDocumentosDTO datosDelDTO) {		
			
		HistoricoDocumentosDTO historicoDocumentosDTO = new HistoricoDocumentosDTO();
		historicoDocumentosDTO.setIdHistoricoCasos(datosDelDTO.getIdHistoricoCasos());
		
		HistoricoCasos llevaDocumentoPorId = dao.obtenerDocumentoPorIdTablaHistoricoEntity(historicoDocumentosDTO); // Consulta		

		return llevaDocumentoPorId;
	}

	

	@Override
	public int eliminarInformacion(HistoricoDocumentosDTO identificador) {
		return dao.eliminarInformacion(identificador);
	}
	
	

}
