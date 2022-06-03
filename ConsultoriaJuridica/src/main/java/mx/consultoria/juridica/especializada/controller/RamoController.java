package mx.consultoria.juridica.especializada.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import mx.consultoria.juridica.especializada.dto.RamoDTO;
import mx.consultoria.juridica.especializada.service.ServiceInterface;


// ******************************************************************************
// Este Controller solo se usara para realizar los proceso de RAMO y JUZGADO
//*******************************************************************************


@Controller
public class RamoController {
	
// Se hace la inyeccion de independencia para usar la anotacion de la linea #12
	@Autowired
	ServiceInterface service; // Se llama la inetrfaz del servicio donde tenemos SOLO declarados los metodos a utilizar

//	Este metodo nos redirecciona solo a la vista .jsp SOLO ES PARA ESO SE UTILIZA
	@RequestMapping(value="/CatalogoRamoJuzgado", method = RequestMethod.GET)
	public  String vista_Asuntos(ModelMap modelo) {
					
	return "viewCatalogosRamoJusgado";  // <---- Esta es la vista en la carpeta WEB-INF/views
	}
	
	

//	Este metodo se usa para hacer la consulta de la informacion a la BD y almacenarla en en la tabla
	@ResponseBody 
	@RequestMapping(value="/ObtenerDatosRamo", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity <List<RamoDTO>> obtenerRamos(){
		
		final HttpHeaders headers =new HttpHeaders();
		
		                               //10 REGISTROS DE BASE DE DATOS
	List<RamoDTO> listadeRamos = service.listadoInformacion();
	//listadeRamos VA A TENER TODOS ESOS 10 REGISTROS

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <List<RamoDTO>> (listadeRamos, headers, HttpStatus.OK);
	}
	
	
	
//	
	@ResponseBody 
	@RequestMapping(value="/EliminarCatRamo", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity <Integer> eliminarRamo(@ModelAttribute RamoDTO identificador){
		
		final HttpHeaders headers =new HttpHeaders();
		int respuesta = 0;
				                              
		respuesta = service.eliminarInformacion(identificador); // identificador -> Es el parametro sobre el cua vamos a ejecutar la consulta
	

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
	
	
	
	
// Agregar registris ala base de datos..
	@ResponseBody 
	@RequestMapping(value="/AgregarNewRamo", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity <Integer> agregarRamo(@ModelAttribute RamoDTO newInformacion){
		
	System.out.println(newInformacion);
		
		final HttpHeaders headers =new HttpHeaders();
		int respuesta = 0;
				                              
		respuesta = service.insertarInformacion(newInformacion);
	

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
	
	
	
//	 Obtener el registro del catalogo de ramo donde especificamente corresponda el id que estamos seleccionando.
	@ResponseBody 
	@RequestMapping(value = "/ObtenerRamoPorId", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity <RamoDTO> obtenerRamoPorId(@ModelAttribute RamoDTO ramoDto){  // idramo = idramo.getIdRamo();
		final HttpHeaders headers =new HttpHeaders();  // idramo.getIdRamo(); = 5
		RamoDTO ramo = new RamoDTO();
		ramo = service.actualizarInformacion(ramoDto);		
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <RamoDTO> (ramo, headers, HttpStatus.OK);
	}	
	
	
	
//	En esta parate una vez cosultado la informacion del ramo por el id el metodo de la linea 96, ahora si procedemos a realizar 
//	el update al registro
	
	@ResponseBody 
	@RequestMapping(value = "/ActualizarRamo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity <Integer> actualizarRamoPorId(@ModelAttribute RamoDTO nuevo){
		final HttpHeaders headers =new HttpHeaders();
		System.out.println("UpdateDates ->" + nuevo.getIdRamo() + nuevo.getNombre());
		int respuesta=0;
		respuesta=service.ActualizarCatRamosDB(nuevo);		
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}		

	
	

	
}  // Fin de la clase RamoController  
