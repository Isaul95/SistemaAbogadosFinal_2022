package mx.consultoria.juridica.especializada.service;

import java.util.List;
import mx.consultoria.juridica.especializada.dto.HistoricoDocumentosDTO;
import mx.consultoria.juridica.especializada.entities.HistoricoCasos;


public interface ServiceInterfaceHistoricoDocumentos {
	
	public List<HistoricoDocumentosDTO> listadoDeHistoricoCasos();
	public int eliminarInformacion(HistoricoDocumentosDTO identificador);
	public int insertarInformacionCaso(HistoricoDocumentosDTO histDocumentosDTO);
	public HistoricoCasos descargarArchivoDeHistoricoUser(HistoricoDocumentosDTO histDocumentoDTO);
	
}
