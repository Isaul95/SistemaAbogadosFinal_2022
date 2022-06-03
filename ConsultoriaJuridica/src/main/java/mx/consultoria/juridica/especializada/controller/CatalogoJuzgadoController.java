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

import mx.consultoria.juridica.especializada.dto.CatalogoJuzgadoDTO;
import mx.consultoria.juridica.especializada.service.ServiceCatalogoJuzgadoInterface;

@Controller
public class CatalogoJuzgadoController {

	@Autowired
	ServiceCatalogoJuzgadoInterface service;
	
	@RequestMapping(value="/CatalogoJuzgado", method = RequestMethod.GET)// URL
	public  String vista(ModelMap modelo) { // NOMBRE DEL METOD
					
	return "viewCatalogosJuzgado";  // <---- Esta es la vista en la carpeta src/main/webapp/WEB-INF/views
	}
	
//	Este metodo se usa para hacer la consulta de la informacion a la BD y almacenarla en en la tabla
	@ResponseBody 
	@RequestMapping(value="/ObtenerCatalogoJuzgados", method = RequestMethod.GET, produces="application/json") //URL
	public ResponseEntity <List<CatalogoJuzgadoDTO>> obtenerJuzgados(){//METHOD NAME
		
		final HttpHeaders headers =new HttpHeaders();
		
		                               //10 REGISTROS DE BASE DE DATOS
	List<CatalogoJuzgadoDTO> listadeJuzgados = service.listadoInformacion();
	//listadeRamos VA A TENER TODOS ESOS 10 REGISTROS

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <List<CatalogoJuzgadoDTO>> (listadeJuzgados, headers, HttpStatus.OK);// SE RETORNA LISTA Y ESTADO 202
	}
	
	
	
//	
	@ResponseBody 
	@RequestMapping(value="/EliminarCatalogoJuzagado", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity <Integer> eliminarRamo(@ModelAttribute CatalogoJuzgadoDTO identificador){
		
		final HttpHeaders headers =new HttpHeaders();
		int respuesta = 0;
				                              
		respuesta = service.eliminarInformacion(identificador); // identificador -> Es el parametro sobre el cua vamos a ejecutar la consulta
	

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
	
	
	
	
// Agregar registris ala base de datos..
	@ResponseBody 
	@RequestMapping(value="/AgregarNewCatalogoJuzgado", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity <Integer> agregarRamo(@ModelAttribute CatalogoJuzgadoDTO newInformacion){
		
	System.out.println(newInformacion);
		
		final HttpHeaders headers =new HttpHeaders();
		int respuesta = 0;
				                              
		respuesta = service.insertarInformacion(newInformacion);
	

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
	
	
	
//	 Obtener el registro del catalogo de ramo donde especificamente corresponda el id que estamos seleccionando.
	@ResponseBody 
	@RequestMapping(value = "/ObtenerCatalogoJuzgadoPorId", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity <CatalogoJuzgadoDTO> obtenerRamoPorId(@ModelAttribute CatalogoJuzgadoDTO CatalogoJuzgadoDTO){  // idramo = idramo.getIdRamo();
		final HttpHeaders headers =new HttpHeaders();  // idramo.getIdRamo(); = 5
		CatalogoJuzgadoDTO ramo = new CatalogoJuzgadoDTO();
		ramo = service.obtenerInformacion(CatalogoJuzgadoDTO);		
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <CatalogoJuzgadoDTO> (ramo, headers, HttpStatus.OK);
	}	
	
	
	
//	En esta parate una vez cosultado la informacion del ramo por el id el metodo de la linea 96, ahora si procedemos a realizar 
//	el update al registro
	
	@ResponseBody 
	@RequestMapping(value = "/ActualizarCatalogoJuzgado", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity <Integer> actualizarRamoPorId(@ModelAttribute CatalogoJuzgadoDTO nuevo){
		final HttpHeaders headers =new HttpHeaders();
//		System.out.println("UpdateDates ->" + nuevo.getIdRamo() + nuevo.getNombre());
		int respuesta=0;
		respuesta=service.actualizarInformacion(nuevo);		
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}		
	
}
