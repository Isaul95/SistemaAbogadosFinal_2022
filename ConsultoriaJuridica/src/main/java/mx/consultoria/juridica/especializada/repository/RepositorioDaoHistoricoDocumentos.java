package mx.consultoria.juridica.especializada.repository;

import java.util.Date;
import java.util.List;
import mx.consultoria.juridica.especializada.dto.HistoricoDocumentosDTO;
import mx.consultoria.juridica.especializada.entities.HistoricoCasos;


public interface RepositorioDaoHistoricoDocumentos {
	public List<HistoricoDocumentosDTO> listHistoricoDeCasosAntiguos();
	
	public int eliminarInformacion(HistoricoDocumentosDTO identificador);
	public int insertarDocumentosCaso(HistoricoCasos historicoCasos);
	public Date obtenerMomentoActual();
	public int obtenValorSecuenciaTableHistorico();
	public HistoricoCasos obtenerDocumentoPorIdTablaHistoricoEntity(HistoricoDocumentosDTO historicoDocumentosDTO);
	
	
}
