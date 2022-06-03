package mx.consultoria.juridica.especializada.dto;

import java.sql.Blob;
import java.util.Date;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class HistoricoDocumentosDTO {
	private int idHistoricoCasos;
	private String usuarioAlta;
	private String fechaRegistro;
	private String nombreDocumento;
	private transient Blob documento;
	
//	Estos atributos no son de la base de datos, son extras xq los necesitamos para extraer las propiedades del archivo byte
	private byte[] docByte;    // este atributo es para almacenar el documento en bytes
	private String contentType;
	private CommonsMultipartFile archivo;
	
	
	public int getIdHistoricoCasos() {
		return idHistoricoCasos;
	}
	public void setIdHistoricoCasos(int idHistoricoCasos) {
		this.idHistoricoCasos = idHistoricoCasos;
	}
	public String getUsuarioAlta() {
		return usuarioAlta;
	}
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public Blob getDocumento() {
		return documento;
	}
	public void setDocumento(Blob documento) {
		this.documento = documento;
	}
	public byte[] getDocByte() {
		return docByte;
	}
	public void setDocByte(byte[] docByte) {
		this.docByte = docByte;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public CommonsMultipartFile getArchivo() {
		return archivo;
	}
	public void setArchivo(CommonsMultipartFile archivo) {
		this.archivo = archivo;
	}
	
}
