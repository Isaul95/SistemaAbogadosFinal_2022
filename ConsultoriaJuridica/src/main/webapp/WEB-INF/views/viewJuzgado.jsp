<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<!-- LIGAS PARA FUNCIONALIDAD DE LOS DataTanbles  -->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
	<script type="text/javascript" lenguaje="javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script type="text/javascript" lenguaje="javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>

<!-- Esstos scripts se ocupan  -->
	<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/javascript/jquery-3.2.1.js" />"></script>
	<script lenguaje="JavaScript" type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
	<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/javascript/Juzgado.js"/>"></script>
    	
	<meta charset="ISO-8859-1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<title>Consultoría Juridica</title>

	<link rel="shortcut icon" type="<%=request.getContextPath()%>/resources/Icons/resources_app/favicon.png">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/bootstrap.min.css"/>"type="text/css"><link>                  
 	<link rel="stylesheet" href="<c:url value="/resources/assets/css/font-awesome.min.css"/>"type="text/css"><link>                      
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/themify-icons.css"/>"type="text/css"><link>    
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/metisMenu.css"/>"type="text/css"><link>  
    <!-- amcharts css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />   
    <!-- Start datatable css -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.jqueryui.min.css">    
        <!-- others css -->
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/typography.css"/>"type="text/css"><link>
	<link rel="stylesheet" href="<c:url value="/resources/assets/css/default-css.css"/>"type="text/css"><link>
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/styles.css"/>"type="text/css"><link>
	<link rel="stylesheet" href="<c:url value="/resources/assets/css/responsive.css"/>"type="text/css"><link>	
	<link rel="stylesheet" href="<c:url value="/resources/view/sweetalert.css"/>"type="text/css"><link>    
    <!-- modernizr css -->
    <script src="<c:url value="/resources/assets/js/vendor/modernizr-2.8.3.min.js"/>"></script>
</head>



<body>

<!-- page container area start  INICIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ENGLOBA TODO-->
<div class="page-container">

  <!--  =====================    MENU LATERAL IZQ COLOR NEGRO     =================  -->
<!-- sidebar menu area start / Aki no toquen nada, solo copien tal cual el codigo y se lo lleven a su nueva vista-->
            <div class="sidebar-menu">
                <div class="sidebar-header">
                    <div class="logo">
                        <a href="index"><img src="<%=request.getContextPath()%>/resources/assets/images/icon/logo.png" alt="logo"></a>
                    </div>
                    <br>
                   <%--  <h6 class="text-center" style="color: aliceblue"><i class="fa fa-user"></i> <strong><%out.print(((com.jcode.myapp.model.session.Usuario) request.getSession().getAttribute("usuario")).getName_user());%></strong></h6> --%>
                    <h6 class="text-center" style="color: aliceblue">
					<i class="fa fa-user"></i> 
					<strong> 
					Isaul
					</strong>
				</h6>
                </div>
                <jsp:include page="menu.jsp"/>
            </div>
<!-- sidebar menu area end Aki TERMINA el sidebar-menu es el menu lateral izq de color negro*********************-->

<!--  AKI INICIA EL CUERPO (la parte blanca de la vista) DONDE VAN LOS COMPONENTES ******************************************* -->  
<!-- main content area start -->
<div class="main-content">
                        
            
<!-- header area start / NO SE TOCA NADA AKI - INICIA ENCABEZADO LA PARTE SUPERIOR DONDE ESTA -> Cerras Session **** -->
                <div class="header-area">
                    <div class="row align-items-center">
                        <!-- nav and search button -->
                        <div class="col-4 clearfix">
                            <div class="nav-btn pull-left" style="margin-top: 0px">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </div>
                        <!-- profile info & task notification -->
                        <div class="col-8 clearfix">
                            <ul class="notification-area pull-right">
                                <li>
                                    <a href="cerrarsession">
                                        Cerrar Sessión
                                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
<!-- header area end TERMINA ENCABEZADO LA PARTE SUPERIOR DONDE ESTA -> Cerrar Session ************* -->
            

                
 <!-- AKI INICIA EL CONTENEDOR DE LOS COMPONENTES  -->
			<div class="main-content-inner" style="padding-top: 30px">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title text-center">
									<strong>Juzgados</strong>
								</h5>
								<hr>
								<br>
								<!-- Button que se presenta en el body del html PARA ABRIR EL MODAL-->
								<button type="button" class="btn btn-primary" id="btn_AbrirModalJuzgado"><!-- Para abrir modal el boton se le agrego un ide vamos a abrir el modal por medio de JavaScript en el archivo  .js -->
									<i class="fa fa-plus-square fa-2x" aria-hidden="true"></i>
								</button>
								
								<br><br>
								<div width="50%">
									<!-- La tabla para listar informacion  -->
									<table class="table" id="tbl_Juzgados">
										<thead class="bg-dark">
											<tr class="text-white">
												<th scope="col"></th>
												<th scope="col">JUZGADO</th>	
										        <th scope="col">RAMO</th>	
												<th style="width: 15%">ELIMINAR</th>
												<th style="width: 15%">EDITAR</th>
											</tr>
										</thead>

									</table>
								</div>

								<br>
								<br>

								<!--------------------------- INICIA Modal para registrar el nueo ramo ------------------------------------------>
						
								<div class="modal fade" id="ventanaModalJuzgado" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="staticBackdropLabel">
													Agregar union Juzgado y Ramo</h5>
												<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body" id="contenido">
												<label for="">Nuevo juzgado:</label> 
												<select id="listaDeJuzgados" class="form-select" aria-label="Default select example">
                                                
                                                </select>
                                                <label for="">Nuevo ramo:</label> 
												<select id="listaDeRamos" class="form-select" aria-label="Default select example">
                                                
                                                </select>
												<!--  <label for="">Nombre:</label>
											        <input type="text" class="form-control" id="nombre" placeholder="Nombre del alumno">
											        <label for="">Sexo:</label>
											        <input type="text" class="form-control" id="sexo" placeholder="Masculino o Femenino">
											        <label for="">Edad:</label>
											        <input type="text" class="form-control" id="edad" placeholder="Edad del alumno">
											        <label for="">Telefono:</label>
											        <input type="text" class="form-control" id="telefono" placeholder="Numero del alumno">       -->
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
												<button type="button" class="btn btn-primary" id="boton_guardarJuzgado" onclick="click">Guardar cambios</button>
											</div>
										</div>
									</div>
								</div>
								<!--------------------------Ternina modal de ramo------------------------------------------------->






								<!-- Modal 2 ESTE MODAL ES PARA ACTUALIZAR INFORMACION DEL RAMO-->
								<div class="modal" tabindex="-1" role="dialog" id="modalactualizaralumno">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">Actualizar alumno</h5>
											</div>
											<input type="hidden" id="idalumno_actualizar">
											<div class="modal-body">
												<label for="">Apellido: </label> 
												<input type="text" class="form-control" id="apellido_actualizar" placeholder="Apellido del alumno"> <br> 
												<label for="">Nombre: </label> <input type="text" class="form-control" id="nombre_actualizar" placeholder="Nombre del alumno" /> <br> 
												<label for="">sexo: </label> 
												<input type="text" class="form-control" id="sexo_actualizar" placeholder="Sexo del alumno" /> <br>
												<label for="">Edad: </label> <input type="text" class="form-control" id="edad_actualizar" placeholder="Edad  del alumno" /> <br> 
												<label for="">Telefono: </label> 
												<input type="text" class="form-control" id="telefono_actualizar" placeholder="Telefono del alumno" />
												<br>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary" data-dismiss="modal" id="Boton_cerrar" onclick="document.getElementById('modalactualizaralumno').style.display='none'">Cerrar</button>
												<button type="submit" class="btn btn-primary" id="Botactualizar" onclick="document.getElementById('modalactualizaralumno').style.display='none'">Actualizar alumno</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- AKI TERMINA EL CONTENEDOR DE LOS COMPONENTES  -->
	            </div>
<!--  AKI TERMINA EL CUERPO (la parte blanca de la vista) DONDE VAN LOS COMPONENTES******************************************* -->          
            
       <!-- footer area start-->
            <jsp:include page="footer.jsp"/>
            <!-- footer area end-->
	
</div>
<!-- page container area end  TERMINAAAAAAAAAAAAAAAAAA englobar todoooooooooooooooo-->


    <!-- bootstrap 4 js -->
    <script src="<c:url value="/resources/assets/js/popper.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/metisMenu.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/jquery.slimscroll.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/jquery.slicknav.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/scripts.js"/>"></script>   
    <script src="<c:url value="/resources/js_app/app/utilities/lib-utilities.js"/>"></script>    
    <script src="<c:url value="/resources/js_app/view/jquery.Pagination.min.js"/>"></script>
    <script src="<c:url value="/resources/js_app/view/sweetalert.min.js"/>"></script>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>