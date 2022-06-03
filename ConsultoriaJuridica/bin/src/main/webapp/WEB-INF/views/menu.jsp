<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
<div class="main-menu">
    <div class="menu-inner">
        <nav>
            <ul class="metismenu" id="menu">
                <li><a class="item" href="index"><i class="fa fa-home"></i> <span>Inicio</span></a></li>
                <li>
                    <a href="javascript:void(0)"><i class="fa fa-tag"></i><span>Catalogos</span></a>
                    <ul class="collapse">
                        <li><a class="item" href="<c:url value='CatalogoRamoJuzgado'/>"><i class="fa fa-cubes" ></i> Ramos</a></li>
                        <li><a class="item" href="<c:url value='CatalogoJuzgado'/>"><i class="fa fa-cubes" ></i> Juzgado</a></li>
                        <li><a class="item" href="<c:url value='viewHistoricoDocumentos'/>"><i class="fa fa-cubes" ></i> Historico de casos</a></li>
                    </ul>
                </li>
                 <li><a class="item" href="<c:url value='Aplicativos'/>"><i class="fa fa-home"></i> <span>Cliente</span></a></li>
                  <li><a class="item" href="<c:url value='Aplicativos'/>"><i class="fa fa-home"></i> <span>Abogado</span></a></li>
                  <li><a class="item" href="<c:url value='Aplicativos'/>"><i class="fa fa-home"></i> <span>Asunto</span></a></li>
                  <li><a class="item" href="<c:url value='Aplicativos'/>"><i class="fa fa-home"></i> <span>Juzgado</span></a></li>
                  <li><a class="item" href="<c:url value='Aplicativos'/>"><i class="fa fa-home"></i> <span>Secretaria</span></a></li>
                  <li><a class="item" href="<c:url value='Aplicativos'/>"><i class="fa fa-home"></i> <span>Asunto - abogado - cliente</span></a></li>
            </ul>
        </nav>
    </div>
</div>
</html>
