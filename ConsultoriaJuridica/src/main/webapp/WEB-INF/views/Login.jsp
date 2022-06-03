<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="<c:url value="/resources/Css/Procesos.css"/>" type="text/css"><link>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/Icons/resources_app/favicon.png">
<script lenguaje="JavaScript" type="text/javascript"src="<c:url value="/resources/javascript/jquery-3.2.1.js" />"></script>
<script lenguaje="JavaScript" type="text/javascript"src="<c:url value="/resources/javascript/Script_alumnos.js" />"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">		
</head>


<body>

<div class="bg-dark"> <br>
	<%-- <img src="<%=request.getContextPath()%>/resources/Images/logo.jpg"></img> --%>
		<center>	<h1 color="white">..: Sistema Consultoria Juridica :..</h1> </center>
 <br>
</div>
<div id = "Contenido" class="p-3 mb-2 bg-light text-dark">

 <div class="container">
         
    <div class="row">
    
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
     
        <div class="card card-signin my-5">
          <img src="<%=request.getContextPath()%>/resources/Images/profile.png" class="rounded" ></img> <!--   height="220"   width="240" --> 
       
          <div class="p-3 mb-2 bg-light text-dark"> <!-- FONDO DEL CONTENEDOR DEL LOGIN -->
            <form name="form" action="<c:url value='/Login'/>"
                    method="POST">
            <form class="form-signin">
              <div class="form-label-group">
              
                <input type="text" id="Username" class="form-control" placeholder="Usuario" name='username'>
                <br>
              </div>
              <div class="form-label-group">
                <input type="password" id="Pass" class="form-control" placeholder="Contraseña" name='password'>
             <br>
              </div>
		    <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" id="main">Iniciar sesión</button>
		   </form>
              <hr class="my-8">
               </form>
               <h5 id=respuestalogin"></h5>
          </div>
        </div>
      </div>
    </div>
  </div>
<!-- <footer>
<div id="Pie_de_pagina" align=center  class="navbar navbar-fixed-bottom">
<td><h4>©2020 Radiomovil Dipsa S.A. de C.V. Todos los derechos reservados</h4></td>
</div>
</footer> -->
</body>
</html>