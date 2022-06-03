package mx.consultoria.juridica.especializada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.consultoria.juridica.especializada.service.ServiceInterface;


//******************************************************************************
//Este Controller solo se usa para redireccionar a la vista de Login
//*******************************************************************************


@Controller
public class LoginController {
	
	@Autowired
	ServiceInterface service;

	@RequestMapping("/Login") // Esta de Login es el index se usa para redireccionar a la cista del Login
	public String Login() {
		System.out.println("Intentando iniciar sesión");		
		return "Login"; // Este Login es el Login.jsp
	}
		
	@RequestMapping("/inicio")
	public String inicio() {
		System.out.println("Entro al inicio");
		return "inicio";
	} 		
	
	
	@RequestMapping(value="/viewPruebaTemplate", method = RequestMethod.GET)
	public  String vista_PruebaTemplate(ModelMap modelo) {
		System.out.println("Esta entrando en la vista de prueba.....");
	return "viewPruebaTemplateFinal";  // viewPruebaTemplateFinal.jsp
	}
		

}
