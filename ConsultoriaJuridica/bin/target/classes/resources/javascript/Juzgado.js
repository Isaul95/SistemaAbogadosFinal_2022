$(document).ready(function(){ // Esta parte es para realizar la carga de la pagina *DESDE EL INICIO*
		
	llenarTablaJuzgado();
	 // Llamamos la function de llenar tabla al inicio de cargar la pagina
	dataTable();
	

     // Al precionar el Boton "Agregar Nuevo Juzgado" x medio se javascrip abriremos el modal
	 
	
});  // Termina  - $(document).ready


$(document).on("click","#btn_AbrirModalJuzgado",function(e){
	e.preventDefault();
	$('#ventanaModalJuzgado').modal('show'); // <-- Este -> ventanaModalJuzgado es el Ide del modal Juzgado le estamos diciendo cuando damos click en el boton de la vista ->"Agregar Nuevo Juzgado" con el -> .modal-show va a mostrarnos el modal
	    	$('#nombre_Juzgado').val(""); // Cuando nos muestre/presente el modal para agregar new Juzgado nos va limpar el campo de texto
	    	 //llenar_combo_lista_de_juzgados ();//lista de juzgados
	    	 llenar_combo_lista_de_ramos('#listaDeRamos');

});

//3
// Se consulta la lista de Juzgados
function llenarTablaJuzgado(){
	var table = $('#tbl_Juzgados').DataTable();
	table.destroy(); // Destruir la informacion
	$.ajax({
		type: "get",
		url: '/ConsultoriaJuridica/ObtenerJuzgado',
		dataType: "json",
		success: function(response){  // response =  listadeJuzgados
			$("#tbl_Juzgados").DataTable({
				data: response,
				resposive: true,
				columns:[					
					{
						data: "idJuzgado",
						"searchable" : false,
						"visible": false,						
					},
					{
						data: "juzgado",
					},
					{
						data: "ramo",
					},
					{
						className: "text-center",
						data: function(row, type, set){
							var a;
							a = `<a href="#" id="eliminar_Juzgado" class="btn btn-danger btn-remove" value="${row.idJuzgado}"><i class="fa fa-trash"></i></a>`;
							
							return a;
						}						
					},
					{
						className: "text-center",
						data: function(row, type, set){			// fa-2x
							var a;
							a = `<a href="#" id="editar_Juzgado" class="btn btn-success" value="${row.idJuzgado}"><i class="fa fa-edit"></i></a>`;
							
							return a;
						}						
					}
					]
					 
				});
			},					
		});
}

function llenar_combo_lista_de_juzgados(combo) {
$(combo).empty();
    $.ajax({
        type: "get",
        url: "/ConsultoriaJuridica/ObtenerJuzgados",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (key, registro) {
                $(combo).append('<option value=' + registro.idJuzgados + '>' + registro.nombre + '</option>');
             });
        },
    });
}

function llenar_combo_lista_de_ramos(combo) {
$(combo).empty();
    $.ajax({
        type: "get",
        url: "/ConsultoriaJuridica/ObtenerDatosRamo",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (key, registro) {
                $(combo).append('<option value=' + registro.idRamo + '>' + registro.nombre + '</option>');
             });
        },
    });
}

	

// Al momento de dar click sobre el icono se ejecuta esta funcion para ejecitar la peticion y se elimne el registro.

$(document).on("click","#eliminar_Juzgado",function(e){
	e.preventDefault();
	var idTraidoDesdeElBotonEliminar = $(this).attr("value"); // Asignado el id a la variable -> idTraidoDesdeElBotonEliminar 
	$.ajax({												 // Todo esto contiene el valor del id desde la tabla -> $(this).attr("value");
		type: "post",
		url: "/ConsultoriaJuridica/EliminarJuzgado",
		data: {
			idJuzgado: idTraidoDesdeElBotonEliminar,  //idJuzgado -> el valor de la izq hace referencia a mi modelo o clase JuzgadoDTO *DEBE ESTAR TAL CUAL
		}, 				    
		dataType: "json",
		success: function(respuestadelcontrolador){
			if (respuestadelcontrolador==1){  // En caso de que si se elimino el registro (CONDICION)
				alert("Fue eliminado un registro.!"); // Mensaje a mostrar
				llenarTablaJuzgado();  // Aki se llama la funcion para poder recargar nuevamente los registros nuevos o los que se hayan eliminado
				
			}
			else{
				alert("No se pudo eliminar el registro.!");
			}
		}
	});

});




// function para insertar informacion ala base de datos

$(document).on("click","#boton_guardarJuzgado",function(e){
	e.preventDefault();
	var nameJuzgadoSeExtraeDesdeCajaDeTexto = $('#nombre_juzgado').val(); // Asi estamos recojiendo informacion de la ACAJA DE TEXTO DESDE LA VISTA
//	var nombre = $('#nombre').val();
//	var sexo = $('#sexo').val();
//	var edad = $('#edad').val();
//	var telefono = $('#telefono').val();
	$.ajax({
		type: "post",
		url: "/ConsultoriaJuridica/AgregarNewJuzgado",
		data: {
			nombre: nameJuzgadoSeExtraeDesdeCajaDeTexto,
//			nombre : nombre,
//			sexo : sexo,
//			edad: edad,
//			telefono : telefono,
		},
		dataType: "json",
		success: function(respuestadelcontrolador){
			if (respuestadelcontrolador==1){
				alert("Nuevo Juzgado agregado...");
				$('#nombre_juzgado').val("");           // Una vez que se inserto registro en la DB el campo de texto lo va a limpiar, esto para que cuando volvamos a abrir el modal ya no tenga informacion anterior
				$("#ventanaModalJuzgado").modal("hide");  // Una vez que se inserto registro en la DB se oculta el modal para que nos muestre la tabla.
				llenarTablaJuzgado();	         // Una vez que se inserto registro en la DB se manda a llamar la function de llenar tabla para que refresque la tabla y aparesca el registro nuevo.			
			}
			else{alert("Juzgado no agregado.!");}
		}
});

});









// Para pruebas
function dataTable(){
	var table = $('#dataTable3').DataTable();
}
/**
 * 
 */