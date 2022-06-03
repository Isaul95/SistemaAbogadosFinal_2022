package mx.consultoria.juridica.especializada.dto;

public class RamoDTO {
	private int idRamo;
	private String nombre;
	
	//ESTE OBTIENE EL VALOR ACTUAL DE NUESTRA VARIABLE idRamo
	public int getIdRamo() {
		return idRamo;
	}
	
	//SIRVE PARA ASIGNAR INFORMACIÓN A NUESTRA VARIABLE idRamo
	public void setIdRamo(int idRamo) {
		this.idRamo = idRamo;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
