
$(document).ready(function(){ // Esta parte es para realizar la carga de la pagina *DESDE EL INICIO*
		
	llenarTablaJuzgados(); // Llamamos la function de llenar tabla al inicio de cargar la pagina
	dataTable();
	
     // Al precionar el Boton "Agregar Nuevo Ramo" x medio se javascrip abriremos el modal
	 $('#btn_AbrirModalJuzgado').click(function () {
//		 alert("INICIAAAA...");
//		 $('#modalCargandoCategoria').modal('show');
	    	$('#ventanaModalJuzgado').modal('show'); // <-- Este -> ventanaModalRamo es el Ide del modal Ramo le estamos diciendo cuando damos click en el boton de la vista ->"Agregar Nuevo Ramo" con el -> .modal-show va a mostrarnos el modal
	    	$('#nombre_juzgado').val("");     // Cuando nos muestre/presente el modal para agregar new ramo nos va limpar el campo de texto
//	    	$('#modalCargandoCategoria').modal('hide');
	   });
	 
	 
});  // Termina  - $(document).ready



// Se consulta la lista de ramos
function llenarTablaJuzgados(){
//	
	var table = $('#tbl_juzgados').DataTable();
	table.destroy(); // Destruir la informacion
	$.ajax({
		type: "get",
		url: '/ConsultoriaJuridica/ObtenerCatalogoJuzgados',
		dataType: "json",
		success: function(response){  // response =  listadeRamos
			$("#tbl_juzgados").DataTable({
				data: response,
				resposive: true,
				columns:[					
					{
						data: "idJuzgado",
						"searchable" : false,
						"visible": false,						
					},
					{
						data: "nombre",
					},
					{
						className: "text-center",
						data: function(row, type, set){
							var a;
							a = `<a href="#" id="eliminar_juzgado" class="btn btn-danger btn-remove" value="${row.idJuzgado}"><i class="fa fa-trash"></i></a>`;
							
							return a;
						}						
					},
					{
						className: "text-center",
						data: function(row, type, set){			// fa-2x
							var a;
							a = `<a href="#" id="editar_ramo" class="btn btn-success" value="${row.idJuzgado}"><i class="fa fa-edit"></i></a>`;
							
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
				llenarTablaJuzgados();
				
			}
			else{alert("Catalogo de ramos no actualizado");}
		}
});

});









// Al momento de dar click sobre el icono se ejecuta esta funcion para ejecitar la peticion y se elimne el registro.

$(document).on("click","#eliminar_juzgado",function(e){
	e.preventDefault();
	var idTraidoDesdeElBotonEliminar = $(this).attr("value"); // Asignado el id a la variable -> idTraidoDesdeElBotonEliminar 
	$.ajax({												 // Todo esto contiene el valor del id desde la tabla -> $(this).attr("value");
		type: "post",
		url: "/ConsultoriaJuridica/EliminarCatalogoJuzagado",
		data: {
			idJuzgado: idTraidoDesdeElBotonEliminar,  //idRamo -> el valor de la izq hace referencia a mi modelo o clase RamoDTO *DEBE ESTAR TAL CUAL
		}, 				    
		dataType: "json",
		success: function(respuestadelcontrolador){
			if (respuestadelcontrolador==1){  // En caso de que si se elimino el registro (CONDICION)
				alert("El catalogo juzgado fue eliminado"); // Mensaje a mostrar
				llenarTablaJuzgados();  // Aki se llama la funcion para poder recargar nuevamente los registros nuevos o los que se hayan eliminado
				
			}
			else{
				alert("No se pudo eliminar el registro.!");
			}
		}
	});

});




// function para insertar informacion ala base de datos

$(document).on("click","#boton_guardarJuzgado",function(e){
	debugger;
	e.preventDefault();
//	$('#modalCargandoCategoria').modal('show');
	
	var nameRamoSeExtraeDesdeCajaDeTexto = $('#nombre_juzgado').val(); // Asi estamos recojiendo informacion de la ACAJA DE TEXTO DESDE LA VISTA
//	var nombre = $('#nombre').val();
//	var sexo = $('#sexo').val();
//	var edad = $('#edad').val();
//	var telefono = $('#telefono').val();
	$.ajax({
		type: "post",
		url: "/ConsultoriaJuridica/AgregarNewCatalogoJuzgado",
		data: {
			nombre: nameRamoSeExtraeDesdeCajaDeTexto,
		},
		dataType: "json",
		success: function(respuestadelcontrolador){
		
//			$('#modalCargandoCategoria').modal("hide");
			if (respuestadelcontrolador==1){
				alert("Nuevo juzagdo agregado...");
				$('#nombre_juzgado').val("");           // Una vez que se inserto registro en la DB el campo de texto lo va a limpiar, esto para que cuando volvamos a abrir el modal ya no tenga informacion anterior
				$("#ventanaModalJuzgado").modal("hide");  // Una vez que se inserto registro en la DB se oculta el modal para que nos muestre la tabla.
				llenarTablaJuzgados();	         // Una vez que se inserto registro en la DB se manda a llamar la function de llenar tabla para que refresque la tabla y aparesca el registro nuevo.			
			}
			else{alert("Juzgado no agregado.!");}
		}
});

});









// Para pruebas
function dataTable(){
	var table = $('#dataTable3').DataTable();
}
