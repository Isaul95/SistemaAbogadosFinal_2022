
$(document).ready(function(){ // Esta parte es para realizar la carga de la pagina *DESDE EL INICIO*
		
	llenarTablaHistDocumentos(); // Llamamos la function de llenar tabla al inicio de cargar la pagina

	
     // Al precionar el Boton "Agregar Nuevo Ramo" x medio se javascrip abriremos el modal
	 $('#btn_AbrirModalRamo').click(function () {
		 debugger;
//		 alert("INICIAAAA...");
//		 $('#modalCargandoCategoria').modal('show');
	    	$('#ventanaModalRamo').modal('show'); // <-- Este -> ventanaModalRamo es el Ide del modal Ramo le estamos diciendo cuando damos click en el boton de la vista ->"Agregar Nuevo Ramo" con el -> .modal-show va a mostrarnos el modal
	    	$('#nombre_ramo').val("");     // Cuando nos muestre/presente el modal para agregar new ramo nos va limpar el campo de texto
//	    	$('#modalCargandoCategoria').modal('hide');
	   });
	 
	 
});  // Termina  - $(document).ready



// Lista de documentos el histrorico de los casos que se llevaron con fechas pasadas.
function llenarTablaHistDocumentos(){

	var table = $('#tbl_histDocumentos').DataTable();
	table.destroy(); 
	$.ajax({
		type: "get",
		url: '/ConsultoriaJuridica/ObtenerHistoricoAllDocumentos',
		dataType: "json",
		success: function(response){  // response =  listadeRamos
			$("#tbl_histDocumentos").DataTable({
				data: response,
				resposive: true,
				columns:[					
					{
						data: "idHistoricoCasos",
						"searchable" : false,
						"visible": false,						
					},
					{
						data: "fechaRegistro",
					},
					{
						data: "nombreDocumento",
					},
					{
						className: "text-center",
						data: function(row, type, set){
							var a;
							a = `<a href="#" title="Elimina todo el registro" onclick="eliminar_ramo('${row.idHistoricoCasos}')" class="btn btn-danger btn-remove"><i class="fa fa-trash"></i></a>`;
							
							return a;
						}						
					},
					{
						className: "text-center",
						data: function(row, type, set){			// fa-2x
							var a;
							a = `<a href="#" title="Ver archivo adjunto" target="_blank" onclick="descargarComprobantePDF('${row.idHistoricoCasos}')"><i class="far fa-file-pdf fa-3x"></i></a>`;
							
							return a;
						}						
					}
					]
					 
				});
			},					
		});
}


// Esta parte es para consultar la informacion

$(document).on("click","#editar_ramo",function(e){
	e.preventDefault();
	var idTraidoDesdeElBotonActualizar = $(this).attr("value");
	$.ajax({
		type: "post",
		url: "/ConsultoriaJuridica/ObtenerRamoPorId",
		data: {
			idRamo: idTraidoDesdeElBotonActualizar,
		},
		dataType: "json",
		success: function(data){
//		 document.getElementById('modalActualizarRamo').style.display='block'
			$('#modalActualizarRamo').modal('show');
	       $('#idRamo_actualizar').val(data.idRamo);  // 11 
           $('#nombre_ramo_actualizar').val(data.nombre); // New ramo
		}
});

});



// Esta function es la principal donde ejecutamos el update a la base de datos.

$(document).on("click","#BotonActualizarRamo",function(e){
	e.preventDefault();
	/*debugger;*/
	var idTraidoDesdeElBotonActualizar = $('#idRamo_actualizar').val();
	var nombreksevaActualizar = $('#nombre_ramo_actualizar').val();	
	
	$.ajax({
		type: "post",
		url: "/ConsultoriaJuridica/ActualizarRamo",
		data: {
			idRamo: idTraidoDesdeElBotonActualizar,
			nombre: nombreksevaActualizar,			
		},
		dataType: "json",
		success: function(respuestadelcontrolador){
			if (respuestadelcontrolador==1){
				alert("El catalogo de ramos fue actualizado...");
				$("#modalActualizarRamo").modal("hide");
				llenarTablaHistDocumentos();
				
			}
			else{alert("Catalogo de ramos no actualizado");}
		}
});

});




// Al momento de dar click sobre el icono se ejecuta esta funcion para ejecitar la peticion y se elimne el registro. USANDO EL TOASTER PARA MOASTRAR MENSAJE
function eliminar_ramo(idHistoricoCasos){
    
    debugger;
    var idTraidoDesdeElBotonEliminar = idHistoricoCasos;
    Swal.fire({
        title: "¿Estás seguro de borrar?",
        text: "¡Es irreversile!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "¡Si, borrar!",
        cancelButtonText: "¡No, cancelar!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "post",
                url: '/ConsultoriaJuridica/EliminarRegistroHistorico',
                data: {
                	idHistoricoCasos: idTraidoDesdeElBotonEliminar,
        		},
                dataType: "json",
                success: function (data) {
                    if (data == 1) {
                        Swal.fire(
                            '¡Eliminado!',
                            'El parametro fue eliminado',
                            'success'
                        );
                        llenarTablaHistDocumentos();
                    }
                },
            });
        }
    });
}



// Boton de eliminar pero sin el prediseño de boostrap

//$(document).on("click","#eliminar_ramo",function(e){
//	e.preventDefault();
//	var idTraidoDesdeElBotonEliminar = $(this).attr("value"); // Asignado el id a la variable -> idTraidoDesdeElBotonEliminar 
//															  //  	Todo esto contiene el valor del id desde la tabla -> $(this).attr("value");
//	$.ajax({												 
//		type: "post",
//		url: "/ConsultoriaJuridica/EliminarCatRamo",
//		data: {
//			idRamo: idTraidoDesdeElBotonEliminar,  //idRamo -> el valor de la izq hace referencia a mi modelo o clase RamoDTO *DEBE ESTAR TAL CUAL
//		}, 				    
//		dataType: "json",
//		success: function(respuestadelcontrolador){
//			if (respuestadelcontrolador==1){  // En caso de que si se elimino el registro (CONDICION)
//				alert("El catalogo de Ramo, fue eliminado un registro.!"); // Mensaje a mostrar
//				llenarTablaHistDocumentos();  // Aki se llama la funcion para poder recargar nuevamente los registros nuevos o los que se hayan eliminado
//			}
//			else{
//				alert("No se pudo eliminar el registro.!");
//			}
//		}
//	});
//});




// function para insertar informacion ala base de datos

$(document).on("click","#boton_guardarDocumento",function(e){
	debugger;
	e.preventDefault();
	
  var formData  = new FormData(); // Cuando se trabaja con archivos adjuntos (pdf, word, imagenes, etc) se utiliza el -> formData para comprimir los datos binarios
  var usuarioQueRegistra = "Isaul_por_el_momento"; // -> $('#nombre_ramo').val(); // Extraer el usuario que realizar el registro del documento adjunto
	
  var archivo = $('#anexoUno input[type=file]')[0].files[0]; // -> archivo -> es el id del campo input-file del modal JSP vista
  
  if(archivo != undefined){
	  var fileName = archivo.name; 	 	
	  var fileSize = archivo.size;
	  	  var pdf="pdf";
		  var xml="xlsx";
  alert("File Name ->" +fileName );
			  
	  var ext = fileName.split('.').pop();
		  	if(ext == pdf){
		  		alert("****FILE-PDF==>" +fileName );
		  	}else if(ext == xml){
		  		alert("?-FILE-XML ---->" +fileName );
		  	}else{
		  		alert("Otro --->" +fileName );
		  	}
		  	
  }
	
	formData.append("archivo",archivo);		
//    formData.append("usuarioAlta", usuarioQueRegistra);
	
	$.ajax({
		type: 'POST',
		url: "/ConsultoriaJuridica/registrarDocumento",
		data: formData,
		dataType: "json",
		enctype: 'multipart/form-data',
		processData: false,
	    contentType: false,
	    cache: false,
		
		success: function(respuestadelcontrolador){
//			alert("linea 220 respuesta");
			if (respuestadelcontrolador==1){
				alert("Nuevo documento agregado...");
				$("#ventanaModalRamo").modal("hide");  // Una vez que se inserto registro en la DB se oculta el modal para que nos muestre la tabla.
				llenarTablaHistDocumentos();	         // Una vez que se inserto registro en la DB se manda a llamar la function de llenar tabla para que refresque la tabla y aparesca el registro nuevo.			
			}if (respuestadelcontrolador==2){
				alert("RESPUESTA (#2) - documento Excell...");
			}else{
				alert("Documento no agregado.!");
				}
		}
});

});





function descargarComprobantePDF(idHistoricoCasos){
	debugger;
	var url = "/ConsultoriaJuridica/getPDFArchivoAdjunto/"+idHistoricoCasos+".html";
	//console.log("url: " + url);
//	window.location.href = url;
//	window.open(url, "_blank", "documento adjunto");
	window.open(url,  idHistoricoCasos);
}



