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

import mx.consultoria.juridica.especializada.dto.JuzgadoDTO;
import mx.consultoria.juridica.especializada.service.ServiceJuzgadoInterface;





@Controller
public class JuzgadoController {
	
	
	@Autowired
	ServiceJuzgadoInterface service; // Se llama la inetrfaz del servicio donde tenemos SOLO declarados los metodos a utilizar

//	Este metodo nos redirecciona solo a la vista .jsp SOLO ES PARA ESO SE UTILIZA
	@RequestMapping(value="/Juzgado", method = RequestMethod.GET)
	public  String vista_Juzgado(ModelMap modelo) {
					
	return "viewJuzgado";  // <---- Esta es la vista en la carpeta WEB-INF/views
	}

	
//	Este metodo se usa para hacer la consulta de la informacion a la BD y almacenarla en en la tabla
//  Agregar registris ala base de datos..
	@ResponseBody 
	@RequestMapping(value="/ObtenerJuzgado", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity <List<JuzgadoDTO>> obtenerJuzgados(){
		
		final HttpHeaders headers =new HttpHeaders();
		
		                               //10 REGISTROS DE BASE DE DATOS
	List<JuzgadoDTO> listadeJuzgados = service.listadoInformacion();
	//listadeRamos VA A TENER TODOS ESOS 10 REGISTROS

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <List<JuzgadoDTO>> (listadeJuzgados, headers, HttpStatus.OK);
	}
	
	
	@ResponseBody 
	@RequestMapping(value="/EliminarJuzgado", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity <Integer> eliminarJuzgado(@ModelAttribute JuzgadoDTO identificador){
		
		final HttpHeaders headers =new HttpHeaders();
		int respuesta = 0;
				                              
		respuesta = service.eliminarInformacion(identificador); // identificador -> Es el parametro sobre el cua vamos a ejecutar la consulta
	

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
	
	
	
	
// Agregar registris ala base de datos..
	@ResponseBody 
	@RequestMapping(value="/AgregarNewJuzgado", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity <Integer> agregarJuzgado(@ModelAttribute JuzgadoDTO newInformacion){
		
	System.out.println(newInformacion);
		
		final HttpHeaders headers =new HttpHeaders();
		int respuesta = 0;
				                              
		respuesta = service.insertarInformacion(newInformacion);
	

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
	
}