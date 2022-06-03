package mx.consultoria.juridica.especializada.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

// 23 - 2:3 y algo.... 24 abril a las 12

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

// lib for file xml - of poi Version 4.1.2
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import mx.consultoria.juridica.especializada.dto.HistoricoDocumentosDTO;
import mx.consultoria.juridica.especializada.entities.HistoricoCasos;
import mx.consultoria.juridica.especializada.service.ServiceInterfaceHistoricoDocumentos;


// **************************************************************************************************************
//   Este Controller solo se usa para realizar los proceso de los documentos que se llevan como historico
//***************************************************************************************************************


@Controller
public class HistoricoCasosController {	

	@Autowired
	ServiceInterfaceHistoricoDocumentos service;
	
	private static Set holidays = null;
	//private final static ResourceBundle BUNDLE;
	
/*	
	static {

		BUNDLE = ResourceBundle.getBundle(HistoricoCasosController.class.getName());

		//log = Logger.getLogger(DateHelper.class.getName());

		// Rellenar el conjunto de días festivos
		holidays = new HashSet();
		for (String dateStr: BUNDLE.getString("com.kodegeek.blog.finance.DateHelper.holidays").split(";\\s*", -1)) {
			try {
				holidays.add(DateFormat.getDateInstance(DateFormat.DEFAULT).parse(dateStr));
				System.out.println("003_holidays"+holidays);
			} catch (ParseException e) {
				//log.warning(String.format(BUNDLE.getString("com.kodegeek.blog.finance.DateHelper.error.badDate"), dateStr));
				System.out.println("Error en la static: "+ String.format(BUNDLE.getString("com.kodegeek.blog.finance.DateHelper.error.badDate"), dateStr));
				continue;
			}
		}

	}
*/
	
	@RequestMapping(value="/viewHistoricoDocumentos", method = RequestMethod.GET)
	public  String vista_HistoricoDocumentos(ModelMap modelo) {
		
	return "viewHistoricoDocumentos";  // viewPruebaTemplateFinal.jsp
	}
	
	

//	Para este servicio solo se consulta los datos MAS NO EL DOCUMENTO BYTE
	@ResponseBody 
	@RequestMapping(value="/ObtenerHistoricoAllDocumentos", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity <List<HistoricoDocumentosDTO>> obtenerRamos(){
		
		final HttpHeaders headers =new HttpHeaders();
		
		List<HistoricoDocumentosDTO> listaHistoricoCasos = service.listadoDeHistoricoCasos();
			
	    headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <List<HistoricoDocumentosDTO>> (listaHistoricoCasos, headers, HttpStatus.OK);
	}
	
	


	// Se consulta el documento de los historicos y se hace la descarga del pdf
	@RequestMapping(value = "/getPDFArchivoAdjunto/{idHistoricoCasos}")  // Esta es otra forma de pasar los parametros para poder hacer la consulta a la DB
	@ResponseBody
	public ResponseEntity<byte[]> descargaPDFHistorico(@PathVariable Integer idHistoricoCasos) {
		
		System.out.println(" Empieza la compilacion del PDF!!! para el historico =>" + idHistoricoCasos); 
		
		HistoricoDocumentosDTO histDocumentoDTO = new HistoricoDocumentosDTO();
		histDocumentoDTO.setIdHistoricoCasos(idHistoricoCasos);
		
		HistoricoCasos documento = service.descargarArchivoDeHistoricoUser(histDocumentoDTO);  // Consulta
		
		byte[] docByte = documento.getDocumentoBy(); // del objeto de la Entity -> (HistoricoCasos) -> documento - extraemos el byte[] donde se consulta el tipo de dato BLOB de la DB
															
		HttpHeaders headers = new HttpHeaders();
		
/*
 * 	Hacemos una evaluacion (if()) con el nombre del documento -> documento.getNombreArchivo() para ver si (.contains = CONTIENE) el .pdf
 *  si es asi entonces le decimos que entre el if. De lo contrario entra en else es decir el archivo que se esta consultando desde la DB no es
 *  un PDF si no una imagen (png, jpg, etc.), ESTO XQ EL NAVEGADOR NECESITA SABER QUE TIPO ARCHIVO VA A MOSTRAR
 *  
 *  Con esta linea -> headers.setContentType(MediaType.parseMediaType("application/pdf")); -- le estamos diciendo que necesitamos ver el documento PDF
 *  que nos lo presente en el navegador y asi mismo poder descargarlo.
 *  
 *  SI USTEDES BUSCAN EN GOOGLE ESTO -> (application/pdf) les a mostrar una lista de opciones para poder mostrar archivos directamente en el navegador
 *  Content-type -> TIPO DE CONTENIDO (EXAMPLE; pdf, xml, word, png, jpg, etc....................)   
 */
		
			if(documento.getNombreArchivo().contains(".pdf")) {
				headers.setContentType(MediaType.parseMediaType("application/pdf")); // Necesitamos que muestre pdf (abre una venta donde los presenta)
			} else {
				headers.setContentType(MediaType.parseMediaType("image/webp")); // Necesitamos que muestre IMAGEN (abre una venta donde los presenta)
			}					

		headers.add("Content-Disposition", "inline;filename=" + documento.getNombreArchivo()); // Se le pasa el nombre dle file de la consulta para cuando se descargue el archivo con dicho nombre
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(docByte, headers, HttpStatus.OK);
	}

	

	
	
	@ResponseBody 
	@RequestMapping(value="/EliminarRegistroHistorico", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity <Integer> eliminarRamo(@ModelAttribute HistoricoDocumentosDTO datosDTO){
		
		final HttpHeaders headers =new HttpHeaders();
		int respuesta = 0;
				                              
		respuesta = service.eliminarInformacion(datosDTO); // Consulta 
	

	headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
	
		
//		
//	@RequestMapping(value="/registrarDocumento", method = RequestMethod.POST, produces="application/json")
//	@ResponseBody 
//	public ResponseEntity <Integer> agregarRamo(@RequestParam("archivo") MultipartFile file, HttpServletRequest request) {
//		final HttpHeaders headers =new HttpHeaders();
//		int respuesta = 0;
//		
//		SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//		UserDetails usuario =  (UserDetails) securityContext.getAuthentication().getPrincipal();
////		System.out.println("USER EN SESSION ->"+usuario.getUsername());
//		// 	Estas 3 lineas juntas sonse spring security es decir extraemos el usuario que esta logueado en el sistema
//		
//		try {	
///*------->>*/if(file.getOriginalFilename().contains(".pdf")) {
//	System.out.println("ES UN ARCHIVO PDF (.pdf)...");
//					if (file != null) { // Evaluamos si el objeto de file no venga vacion es decir k si haya seleccionado un archivo desde la interfaz.
//							
//						HistoricoDocumentosDTO histDocumentosDTO = new HistoricoDocumentosDTO(); // Un objeto de la clase DTO cada atributos se lo pasamos el valor del docimento para guardar en DB.		
//							histDocumentosDTO.setNombreDocumento(file.getOriginalFilename()); // file es el objeto que contiene las propiedades del documento pdf selecconado
//							histDocumentosDTO.setDocByte(file.getBytes());	 // getBytes() -> es la pripiedad del objeto file, getBytes()-es lo que contiene el archivo binario
//							histDocumentosDTO.setUsuarioAlta(usuario.getUsername());		
//				        
//						respuesta = service.insertarInformacionCaso(histDocumentosDTO); // Consulta
//					} /*else {
//						System.out.println("Debe adjuntar el archivo...");				
//					}*/
//			
///*------->>*/}else if(file.getOriginalFilename().contains(".xls") || file.getOriginalFilename().contains(".xlsx") ) { // .xls,.xlsx
////--------------------------------------------------------------------------------------------------------------------------------------
//	HistoricoDocumentosDTO histDocumenDTO = new HistoricoDocumentosDTO(); // Un objeto de la clase DTO cada atributos se lo pasamos el valor del docimento para guardar en DB.		
//	histDocumenDTO.setNombreDocumento(file.getOriginalFilename()); // file es el objeto que contiene las propiedades del documento pdf selecconado
//	histDocumenDTO.setDocByte(file.getBytes());
//	
//	System.out.println("file.getBytes() => " + ReflectionToStringBuilder.toString(file.getInputStream()));
//	System.out.println("histDocumenDTO: histDocumenDTO => " + ReflectionToStringBuilder.toString(histDocumenDTO));
//	
//	
//	
//// 1. Obtenga la secuencia de entrada del archivo de lectura
////			File f = new File("C:\\Users\\COMIMSA\\Downloads\\A\\test.xlsx"); // "PATH\FICHERO.xlsx"
////			File f = new File(file); // Pasandole el multipart NO FUNCIONA
////		    InputStream inp = new FileInputStream(f);
//			InputStream inp = file.getInputStream(); // se hace la conversion -> To convert Multipart file to Input Stream SE LLEVA LAS MISMAS PROPIEDADES
//   /*48*/   Workbook wb = WorkbookFactory.create(inp);
//// 3. Obtenga la hoja de acuerdo con el libro de trabajo
//   /*49*/   Sheet sheet = wb.getSheetAt(0); // la primera hoja = 0
//		    
//// 4. Obtener filas de acuerdo a la hoja Segun inicia a contar desde 0
//		    int iRow = 0; // Apartir de la linea 2 empieza la lectura
//		    Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
//		    
//		    int cel = 0;
//		    Cell cell1 = sheet.getRow(iRow).getCell(cel);
//System.out.println("***********=> " + cell1);
//// mientras haya mas datos NO ESTAN LAS EXCEPTCIONES AUN de cuando o en que momento romper el ciclo		    
//   /*52 ALL*/   while(row!=null) {		//  Leer COLUMNAS
//
//				// 5. Obtener columnas basadas en filas
//					        Cell cell = row.getCell(0);
//					        
//				// 6. Obtenga el valor en la celda
//					        String value = cell.getStringCellValue();
//				System.out.println("======================================== INICIA LECTURA XML  ================================================");
//					        System.out.println("XML-COLUMNA: VALOR_CELDA => " + value);		    			
//				
//				iRow++; // aumenta - next row
//				row = sheet.getRow(iRow);
//		    }
//
//
////		    int iCell = 0; // Apartir de la linea 2 empieza la lectura
////		    Cell cell1 = sheet.getRow(0).getCell(0);
////		    
////			//  Leer FILAS
////		    while(cell1!=null) {		    
////
////				// 5. Obtener columnas basadas en filas
////					        Cell cell = row.getCell(0);
////					        
////				// 6. Obtenga el valor en la celda
////					        String value = cell.getStringCellValue();
////				System.out.println("======================================== INICIA LECTURA XML  ================================================");
////					        System.out.println("XML-FILLAS: VALOR_CELDA => " + value);		    			
////				
////				iRow++; // aumenta - next row
////				row = sheet.getRow(iRow);				
////				
////		    }
//		    
//// Cerrar el recurso de transmisión
//	        inp.close();
//		    
//		    
////				System.out.println("ES UN ARCHIVO EXCELL (xml)...");
//				respuesta = 2;
//	
//				
//				
////--------------------------------------------------------------------------------------------------------------------------------------
///*------->>*/}else {
//				System.out.println("Debe adjuntar el archivo... VIENE VACIO EL -> file.getOriginalFilename");
//			}
//			
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			System.out.println("Error en la prueba: "+ e.getMessage());			
//			headers.setContentType(MediaType.APPLICATION_JSON);
//		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
//		}
//	}
// -----------------------------------------------------------------------------------------------------------------------------
// -------------------------------    ESTE ES LO QUE FUNCIONABA LEYENDO LAS COLUMNAS    ----------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
@RequestMapping(value="/registrarDocumento", method = RequestMethod.POST, produces="application/json")
@ResponseBody 
public ResponseEntity <Integer> agregarRamo(@RequestParam("archivo") MultipartFile file, HttpServletRequest request) {
	final HttpHeaders headers =new HttpHeaders();
	int respuesta = 0;
	
	SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
	UserDetails usuario =  (UserDetails) securityContext.getAuthentication().getPrincipal();

System.out.println("NAME-ARCHIVO-FILE -->" + file.getOriginalFilename());
	
// El nombre y extension del archivo de lote correspondiente a Complemento couta de Mercado es: COMPLEMENTOMERC(DDMMAA)001.xls
	String nameFile = "COMPLEMENTOMERC"; // 
	
	if (file != null) {
		
		if(file.getOriginalFilename().contains(nameFile)) {
			System.out.println("---011111-------------------NAME-ARCHIVO-FILE GOODDDDDD....");
			
			if(file.getOriginalFilename().contains(".xls") || file.getOriginalFilename().contains(".xlsx") ) { // .xls,.xlsx
				System.out.println("---002222-------------------EXT.-ARCHIVO-FILE GOODDDDDD....");			
			}
			
		}
	}
	
//1.- HACER UNA EXPRESION REGULAR PARA QUE VALIDE QUE LA EXTENSION DEL DOCUMENTO SEA (xsl | XLS | xlsx | XLSX)
//2.- Validar la curp
//3.- Validar RFC
//4.- Validar si es solo texto
//5.- Validar si es solo numeros
//6.- Agregar ceros (0) a la izquierda para complementar 11 posiciones si 
	
	
	
	try {	
/*------->>*/if(file.getOriginalFilename().contains(".pdf")) {
System.out.println("ES UN ARCHIVO PDF (.pdf)...");
				if (file != null) { // Evaluamos si el objeto de file no venga vacion es decir k si haya seleccionado un archivo desde la interfaz.
						
					HistoricoDocumentosDTO histDocumentosDTO = new HistoricoDocumentosDTO(); // Un objeto de la clase DTO cada atributos se lo pasamos el valor del docimento para guardar en DB.		
						histDocumentosDTO.setNombreDocumento(file.getOriginalFilename()); // file es el objeto que contiene las propiedades del documento pdf selecconado
						histDocumentosDTO.setDocByte(file.getBytes());	 // getBytes() -> es la pripiedad del objeto file, getBytes()-es lo que contiene el archivo binario
						histDocumentosDTO.setUsuarioAlta(usuario.getUsername());		
			        
					respuesta = service.insertarInformacionCaso(histDocumentosDTO); // Consulta
				}
		
/*------->>*/}else if(file.getOriginalFilename().contains(".xls") || file.getOriginalFilename().contains(".xlsx") ) { // .xls,.xlsx
//--------------------------------------------------------------------------------------------------------------------------------------

//	 try {
		 
//1. Obtenga la secuencia de entrada del archivo de lectura
//		File f = new File("C:\\Users\\COMIMSA\\Downloads\\A\\test.xlsx"); // "PATH\FICHERO.xlsx"
//		File f = new File(file); // Pasandole el multipart NO FUNCIONA
//	    InputStream inp = new FileInputStream(f);
		InputStream inp = file.getInputStream(); // se hace la conversion -> To convert Multipart file to Input Stream SE LLEVA LAS MISMAS PROPIEDADES
///*48*/   Workbook wb = WorkbookFactory.create(inp);
		Workbook wb = new XSSFWorkbook(inp);
//3. Obtenga la hoja de acuerdo con el libro de trabajo
/*49*/   Sheet sheet = wb.getSheetAt(0); // la primera hoja = 0



Row row = null;
int i=1;

while((row = sheet.getRow(i))!=null){
// int custId,pinCode;
 String curp,nombre,rfc;
		 try{
			 curp = row.getCell(0).getStringCellValue();
				System.out.println("curp-->: " + curp);
		 }catch(Exception e){
			 curp = null;
			 }
		 
// try{
//  curp = row.getCell(1).getStringCellValue();
// }
// catch(Exception e){curp = null;
// }
 
 
 try{
  nombre = row.getCell(1).getStringCellValue();
  System.out.println("nombre-->: " + nombre);
 }
 catch(Exception e){
	 nombre = null;
 }
 
 
// try{
//  pinCode = (int) row.getCell(3).getNumericCellValue();
// }
// catch(Exception e){pinCode = 0;
// }
 
 try{
  rfc = row.getCell(2).getStringCellValue();
  System.out.println("rfc-->: " + rfc);
 }
 catch(Exception e){
	 rfc = null;
 }
 
 
// Customer cust = new Customer(custId,curp,nombre,pinCode,rfc);
// listCust.add(cust);
  i++; 
  
}


// (custId,curp,nombre,pinCode,rfc) , (custId,curp,nombre,pinCode,rfc), (custId,curp,nombre,pinCode,rfc) 
inp.close();
	








//Iterator<Row> iterator = sheet.iterator();
//DataFormatter formatter = new DataFormatter();

//	while (iterator.hasNext()) {
//		Row nextRow = iterator.next();
//		Iterator<Cell> cellIterator = nextRow.cellIterator();
//		while(cellIterator.hasNext()) {
//			Cell cell = cellIterator.next();
//			String contenidoCelda = formatter.formatCellValue(cell);
////			System.out.println("celda-->: " + contenidoCelda);									
//		}
//		System.out.println("=================================================");
//	}


//	 inp.close();
//	 } catch (Exception e) {
//         e.printStackTrace();
//     }

String nss = "12354546548";
String resp = "";


System.out.println("-----------------------------------------------------------------");
//System.out.println("esNumerico ==>" +  esNumerico);
System.out.println("nss ==>" +  nss);

	 if(nss.trim().equalsIgnoreCase("") || nss == null) {
		 resp = "No puede venir vacio...";		 
	 }
	 boolean esNumerico = nss.chars().allMatch(Character :: isDigit);
	 if(esNumerico == false) {
		 resp = "INVALIDO, debe ser numerico";
	 }
System.out.println("Respuesta NSS Final ==>" +  resp);
	 

System.out.println("\n==============================================================");
String nssCerosIzq = "454 9 ";

String textoFormateado = String.format("%11s", nssCerosIzq).replace(' ','0');
System.out.println("[" + textoFormateado + "]");
System.out.println("==============================================================");
//1.- Cuando la variable contiene las 11 posiciones no hace nada el codigo, es decir no agrega mas Ceros
//2.- NSS 7 posiciones --> nss = 1235454   ->AGREGA CEROS Resultado =  00001235454
//3.- NSS 8 posiciones --> nss = 13246489  ->AGREGA CEROS Resultado =  00013246489
//4.- NSS 9 posiciones --> nss = 859393202 ->AGREGA CEROS Resultado =  00859393202
//5.- NSS 4 posiciones --> nss = 82024     ->AGREGA CEROS Resultado =  00000082024
//6.- NSS 2 posiciones --> nss = 29        ->AGREGA CEROS Resultado =  00000000029

//  Aplica tambien para los espacios en blanco los remplaza - Resultdo = 00000000029
//  Resultado = 00000454090



	Calendar c = Calendar.getInstance();
	c.add(Calendar.MONTH, -1);
	Date date = c.getTime();
	DateFormat dates = new SimpleDateFormat("yyyyMM");
	// Caso 2: obtener la fecha y salida por pantalla con formato:
	String AÑO_MES_TABLA_COLDVIEW_EN_TURNO=dates.format(date);//

	System.out.println("========MES-ANTERIOR=========>"+AÑO_MES_TABLA_COLDVIEW_EN_TURNO);

			respuesta = 2;				
			
			
							
			
			
			

			
			
			
			
//--------------------------------------------------------------------------------------------------------------------------------------
}else if(file.getOriginalFilename().contains(".PNG")) {
	//System.out.println("===========================================");
// ,2019-01-08,2019-01-09,2019-01-10,2019-04-05,2019-04-06,2019-04-07,2019-05-01,2019-06-07,2019-06-08,2019-06-09,2019-09-13,2019-09-14,2019-09-15,2019-10-01,2019-10-02,2019-10-03,2019-10-04,2019-10-05,2019-10-06,2019-10-07	
	// Días festivos
    String holidays = "20220502,"
    				+ "20220104,"
    				+ "20210901,"
    				+ "20220106,"
    				+ "20210301";
	
	//do {
	String currDateStr = null;
	SimpleDateFormat format = new SimpleDateFormat("yy-M-d");
	// MAL = abril - julio 
	try {  
	currDateStr = "2021-03-25"; // ok = enero , feb, marxo,  mayo, junio, agost, sept, oct, noviembre ,dic
	/*	Calendar fechaActual = Calendar.getInstance(); // Obtener la fecha actual-----------------01
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd"); //-----------------02
		System.err.println("mami ==>"+ fechaActual.getTime());
		String secondstDay = format2.format(fechaActual.getTime());   	 	 //-----------------03
		System.out.println("---ISAUL--3------secondstDay:=>"+secondstDay); //-----------------04 secondstDay:=>20220529
		String mesFechaActual = secondstDay.substring(4,2);
System.out.println("===============__MI_MES_MEYO_========="+mesFechaActual);
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	     System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
	     System.out.println("dtfdtf..ooo000 ==>"+ dtf);
	     System.out.println("========================yyaa===================");
		*/
	/*System.err.println("FESTIVOS__holidays__01==>"+ holidays);
	System.err.println("FESTIVOS__holidays__02==>"+ holidays.length());
	System.err.println("FESTIVOS__holidays__03==>"+ holidays.toString());*/
		
	String periodo = "422";
	String mesDijito = periodo.substring(0, 1);
	String anioDijito = periodo.substring(1, 3);
	//String mes = mesDijito;
	//System.err.println("=================================>>>>>>>>>>>AMOR_TU_mesDijito==>"+ mesDijito);
	//System.err.println("=================================>>>>>>>>>>>AMOR_TU_mesDijito==>"+ anioDijito);
	String newFechaSinRestarMes = procesaPerSacarMes(mesDijito, anioDijito);
	
	
	//currDateStr = newFechaSinRestarMes;
	//System.out.println("=======================>>>>>>newFechaSinRestarMes==>"+ newFechaSinRestarMes);
	//System.out.println("========================>>>>>currDateStr==>"+ currDateStr);
	
	Date date = format.parse(currDateStr);
		
	
	if (date != null) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
//System.out.println("20_calendar ==>"+ calendar.getTime());
//calendar.set(Calendar.MONTH, +1);// le sumamos 1 mes al mes actual del periodo para que los files salgan con el mes siguiente dia habil

		int month = calendar.get(Calendar.MONTH);
		int year =  calendar.get(Calendar.YEAR);
		//System.out.println("11_MES..AMOR->==> "+ month + " 22_ANIO-> "+year); 
		//System.out.println(String.format("SYSTEM__DIA_SALIDAAAAAA_OK: %d", getLastBusinessDayOfMonth(month, year)));
		int per = getLastBusinessDayOfMonth(month, year, holidays);
		System.err.println("PERIODO_FINALLY_ISAUL->"+0+per);
		System.err.println("PERIODO_FINALLY_ISAUL->"+ year+0+per);
//System.out.println(String.format("%s %s ???=>", currDateStr,isLastBusinessDayOfMonth(date) == true? "Es el último día hábil": "No es el último día hábil"));
	} 
	} catch (Exception pExp) {
		System.err.println(String.format("Ignorar la mala fecha: '%s'")+pExp);
	}	
	//} while (true);
		
	
	
	
//----------------------
/*
	// Obtener el primer día del mes anterior
    Calendar cal_1 = Calendar.getInstance (); // Obtener la fecha actual
    int daySec = 1;
    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

// 	
    cal_1.set (Calendar.DAY_OF_MONTH, 2); // Establecer en el 1, la fecha actual es el primer día del mes

String secondstDay = format2.format(cal_1.getTime());
System.err.println("---ISAUL--3------secondstDay:=>"+secondstDay);
*/
	
}







// De aki se saco lo del ejemplo de los dias habiles en java ejercicio de marci
// -> http://kodegeek.com/blog/2009/01/26/¿como-encontrar-el-ultimo-dia-habil-del-mes-en-java/
else {
			System.out.println("Debe adjuntar el archivo... VIENE VACIO EL -> file.getOriginalFilename");
		}
		 
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
		
	} catch (Exception e) {
		System.out.println("Error en la prueba: "+ e.getMessage());			
		headers.setContentType(MediaType.APPLICATION_JSON);
	return new ResponseEntity <Integer> (respuesta, headers, HttpStatus.OK);
	}
}


public static int getLastBusinessDayOfMonth(final int month, final int year, String holidays) {   //inicio-method------------------- 
		int day = 2;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 2);//Se agrega el 2 para k tome el segundo dia
	System.out.println("001_calendar.getTime() ==>"+ calendar.getTime()); // calendar.getTime() ==> Sat Feb 28 18:20:24 CST 2009

	SimpleDateFormat dtf2 = new SimpleDateFormat("yyyyMMdd");
	Date dateObj = calendar.getTime();
	String formattedDate = dtf2.format(dateObj);
	System.out.println("00250_formattedDate ASI SE PUEDE EVALUAR CON EL holidays.CONTARINS- ==>"+formattedDate);

//-------------------------------------------------------------------------------------------------
	Calendar calendarFes = Calendar.getInstance();
	calendarFes.set(Calendar.MONTH, month);
	calendarFes.set(Calendar.YEAR, year);
	calendarFes.set(Calendar.DAY_OF_MONTH, 1);//Se rest 1 dia para ver si el anterior fue festivo diay
	System.out.println("500_calendarFes.getTime() ==>"+ calendarFes.getTime());
	// ------------------
	Date dateObjFest = calendarFes.getTime();
	String formattedDateFest = dtf2.format(dateObjFest);
	System.out.println("00250_formattedDate ASI SE PUEDE EVALUAR CON EL holidays.CONTARINS- ==>"+formattedDateFest);
//--------------------------------------------------------------------------------------------------			
//cualkier dia del mes siempre y cuando sea festivo entrara y le sumara un 1 para:
	// xq de entrada ya toma el segundo dia del mes asi k 2 +1 = 3 
	
if(holidays.contains(formattedDateFest)) { // si el primer dia del mes es festivo OTRA PARAM EXTRAE DIAS
	System.out.println("------- Entro En el Primer dia del Mes es un dia festivo HEREEE ---");
	  //if (calendarFes.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			calendar.add(Calendar.DAY_OF_MONTH, 1); // suma dos dia y toma el tercero para k sea el segundo dia habil
	  //}
}else {
	System.out.println("---*****//===NO ES FESTIVO_EL_PRIME_DIA_DEL_MES==//*****--NORMAL---");
	// Sigue buscando hacia delante hasta que el día no sea un fin de semana o un día festivo
		while(true) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) { // satu = 7
				calendar.add(Calendar.DAY_OF_MONTH,2); // tenia 2
				//continue;
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				calendar.add(Calendar.DAY_OF_MONTH, 2);
				continue;
			}else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}else if (holidays.contains(formattedDate)) {
				System.err.println("SI entroooooooo0000000000==>"); 
					calendar.add(Calendar.DAY_OF_MONTH, 1);				
				//continue;
			} 
			break;
		} // Fin del While
}
		
		day = calendar.get(Calendar.DAY_OF_MONTH);
	System.out.println("00_ISAUL_1_DIA AKII --RESULTAD----YA----------==>"+ day);

		return day;
	}  // FIN-method-------------------

	



// *************    ESTOOOO SE RESPALDA XQ YA FUNCIONA SI EL SEGUNDO DIA ES FESTIVO LE SUMA 1 Y TOMA EL SIGUIENTE PERO NO FUNCIONA 
//***************************  SI EL PRIMER DIA DEL MES ES FESTIVO *******************************

/* 1.------------ Obtenga el último día hábil del mes para una combinación determinada de mes / año  -----------------------
* @param moth El mes
* @param year El año 
* @return El último día hábil =====  getLastBusinessDayOfMonth => obtener el último día hábil del mes    */
//public static int getLastBusinessDayOfMonth(final int month, final int year, String holidays) {   //inicio-method-------------------
////System.out.println("MES==> "+ month + " --ANIO-> "+year); 
//	int day = 2;
//	Calendar calendar = Calendar.getInstance();
//	calendar.set(Calendar.MONTH, month);
//	calendar.set(Calendar.YEAR, year);
////	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Ultimo dia
////	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); // Primero dia
//	calendar.set(Calendar.DAY_OF_MONTH, 2);//Se agrega el 2 para k tome el segundo dia
////System.out.println("00_RAFAEL_calendar.get(Calendar.DAY_OF_MONTH) ==>"+ calendar.get(Calendar.DAY_OF_MONTH));
//System.out.println("001_calendar.getTime() ==>"+ calendar.getTime()); // calendar.getTime() ==> Sat Feb 28 18:20:24 CST 2009
//
//SimpleDateFormat dtf2 = new SimpleDateFormat("yyyyMMdd");
////Calendar cal = Calendar.getInstance();
//Date dateObj = calendar.getTime();
//String formattedDate = dtf2.format(dateObj);
//System.out.println("00250_formattedDate ASI SE PUEDE EVALUAR CON EL holidays.CONTARINS- ==>"+formattedDate);
//
//
//	//
////System.err.println("FESTIVOS__holidays_(SIN_FOR_)__01==>"+ holidays);
//String[] dayArr = holidays.split(",");
////System.err.println("FESTIVOS__holidays__200==>"+ dayArr);
//List<String> holidayList = new ArrayList<String>(Arrays.asList(dayArr));
////System.err.println("FESTIVOS__holidays__300==>"+ holidayList);
//
////System.err.println("FESTIVOS__holidays__300==>"+ holidayList.size());
//
//if (holidayList.size() > 0) {
//    for (int i = 0; i < holidayList.size(); i++) {
//    	//System.err.println("FESTIVOS__holidays__500==>"+ holidayList.get(i));
//        /*if (sdate.equals(list.get(i))) {
//            return true;
//        }*/
//    }
//}
//
//
////-------------------------------------------------------------------------------------------------
//Calendar calendarFes = Calendar.getInstance();
//calendarFes.set(Calendar.MONTH, month);
//calendarFes.set(Calendar.YEAR, year);
//calendarFes.set(Calendar.DAY_OF_MONTH, 1);//Se rest 1 dia para ver si el anterior fue festivo diay
//System.out.println("500_calendarFes.getTime() ==>"+ calendarFes.getTime());
//
////--------------------------------------------------------------------------------------------------			
//
//
//
//
//// Sigue buscando hacia delante hasta que el día no sea un fin de semana o un día festivo
//	while(true) {
////System.err.println("===== ENTRA ==>"+calendar.get(Calendar.DAY_OF_WEEK));
//		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) { // satu = 7
//			calendar.add(Calendar.DAY_OF_MONTH,2); // tenia 2
//			//System.out.println("entro en el sabado-----"+calendar.get(Calendar.DAY_OF_MONTH));
//			//continue;
//		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//			calendar.add(Calendar.DAY_OF_MONTH, 2);
//			//System.out.println("entro en el domingo"+calendar.gset(Calendar.DAY_OF_MONTH));
//			continue;
//		}else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
//			calendar.add(Calendar.DAY_OF_MONTH, 1);
//			continue;
//		}else if (holidays.contains(formattedDate)) {
//			System.err.println("SI entroooooooo0000000000==>"); /*	
////-------------------------------------------------------------------------------------------------
//			Calendar calendarFes = Calendar.getInstance();
//			calendarFes.set(Calendar.MONTH, month);
//			calendarFes.set(Calendar.YEAR, year);
//			calendarFes.set(Calendar.DAY_OF_MONTH, 1);//Se rest 1 dia para ver si el anterior fue festivo diay
//		System.out.println("500_calendarFes.getTime() ==>"+ calendarFes.getTime());
//			
////--------------------------------------------------------------------------------------------------			
//			
//			
//			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) { // el 1er dia del mes fue lunes
//				calendar.add(Calendar.DAY_OF_MONTH, 2);
//			}else {*/
//				calendar.add(Calendar.DAY_OF_MONTH, 1);
//			//}
//				
//				
//			//continue;
//		} /*else if (holidays.contains(calendar.getTime())) {
//			calendar.add(Calendar.DAY_OF_MONTH, -1); 
//			continue;
//		}*/
//		break;
//	} // Fin del While
//	day = calendar.get(Calendar.DAY_OF_MONTH);
//System.out.println("00_ISAUL_1_DIA AKII --RESULTAD----YA----------==>"+ day);
//
////	int count = day+1;
////System.out.println("002_CONUTN_SUMA_DIAS ==>"+ count); // AKI SE RETORNARIA LA FECHA REPORTE 04-05-2022
//	return day;
//}  // FIN-method-------------------


public static String procesaPerSacarMes(String mes, String anio){
//System.out.println("MES_INTEGER PROCESA_NEW_MARCI------.... ==>"+ mes + "ANIO=>"+anio);
	String mesPeriodo;
	int mesInt = Integer.valueOf(mes);
//System.out.println("------------>mesInt.... ==>"+ mesInt);
	
	switch (mesInt){
        case 1:  mesPeriodo = "01"; // Enero
                 break;
        case 2:  mesPeriodo = "02"; // Febrero
                 break;
        case 3:  mesPeriodo = "03"; // Marzo
                 break;
        case 4:  mesPeriodo = "04"; // Abril
                 break;
        case 5:  mesPeriodo = "05"; // Mayo
                 break;
        case 6:  mesPeriodo = "06"; // Junio
                 break;
        case 7:  mesPeriodo = "07"; // Julio
                 break;
        case 8:  mesPeriodo = "08"; // Agosto
        		 break;
        case 9:  mesPeriodo = "09"; // ...
        		 break;
        case 10: mesPeriodo = "10"; // ...
        		 break;
        case 11: mesPeriodo = "11"; // ...
        		 break;
        case 12: mesPeriodo = "12"; // ...
        		 break;
        default: mesPeriodo = "0"; // ...
                 break;
    }	 // currDateStr = "2022-05-25";
    //System.out.println("MARICAAA --=> "+ mesPeriodo);	// mes => 01 
// 	 	 	 	 	 	 	20   22   -    01      + - + 25   ==> 2022-01-25
    String mesPerFinally = "20"+anio+"-"+mesPeriodo+"-"+"25";
//System.out.println("###########____FeCHA_FIN_DEL_PERIODO____############# =>"+ mesPerFinally);	// mes => 01
    return mesPerFinally;
}


/*   Métodos de fecha de ayuda adaptados a la industria financiera.    -- es el último día laboral del mes  */
/*
public final static boolean isLastBusinessDayOfMonth(final Date aDate) {
	boolean isLastBusinessDay = false;
	
	// ¿Se puede hacer una comparación?
	if (aDate == null) {
		return isLastBusinessDay;
	}
	
	Calendar calendar = Calendar.getInstance();
	
	// Fecha actual en un fin de semana
	calendar.setTime(aDate);
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	int month = calendar.get(Calendar.MONTH);
	int year =  calendar.get(Calendar.YEAR);
	
	int lastbusinessDayOfMonth = getLastBusinessDayOfMonth(month, year);
System.err.println("FINALLY_01 ==>"+ lastbusinessDayOfMonth);
System.err.println("FINALLY_02 ==>"+ day);


			if (lastbusinessDayOfMonth != Calendar.SATURDAY && lastbusinessDayOfMonth != Calendar.SUNDAY) {
				int count = day+1;
				System.out.println("_LA_OTRAAAAS ==>"+ count);
				
			} 
			
	
	if (lastbusinessDayOfMonth == day) {
		isLastBusinessDay = true;
	}
System.out.println("005_es el último día laboral del mes ==>"+ isLastBusinessDay);
	return isLastBusinessDay;
}
*/



	
	
	
//	**********************************************************************************************************************************	
}  // Fin de la clase RamoController  

//--------------------       DOCUMENYTACION            ------------------
//
//1.- Leer file xml excel  -> https://qavalidation.com/2016/04/rw-excel-poi-workbookfactory.html/
//2.- Leer file xml excel  -> http://decodigo.com/java-leer-archivo-excel
//3.- Leer file xml excel  -> https://www.codejava.net/coding/java-example-to-update-existing-excel-files-using-apache-poi
//4.- Leer file xml excel  -> https://ingenieriadesoftware.es/como-leer-documentos-excel-desde-java/
//5.- Convert file ruta to Multipart -> https://stackoverflow.com/questions/35479290/multipart-file-to-file-inputstream

