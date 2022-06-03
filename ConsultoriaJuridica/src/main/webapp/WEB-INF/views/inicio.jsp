<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="es">

 <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Consultoría Juridica</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/Icons/resources_app/favicon.png">       
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/bootstrap.min.css"/>"type="text/css"><link>        
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/font-awesome.min.css"/>"type="text/css"><link>
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/themify-icons.css"/>"type="text/css"><link>
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/metisMenu.css"/>"type="text/css"><link>                
        <!-- others css -->        
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/typography.css"/>"type="text/css"><link>        
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/default-css.css"/>"type="text/css"><link>               
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/styles.css"/>"type="text/css"><link>        
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/responsive.css"/>"type="text/css"><link>        
        <!-- modernizr css -->
        <link rel="stylesheet" href="<c:url value="/resources/assets/js/vendor/modernizr-2.8.3.min.js"/>"type="text/css"><link>
        
        <script lenguaje="JavaScript" type="text/javascript"
    src="<c:url value="/resources/javascript/asuntos.js"/>"></script>
    
    <!-- LIGAS PARA FUNCIONALIDAD DE LOS DataTanbles  -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<script type="text/javascript" lenguaje="javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" lenguaje="javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    
    </head>

<body>
        <!-- preloader area start -->
        <div id="preloader">
            <div class="loader"></div>
        </div>
        
                <!-- preloader area end -->
        <!-- page container area start -->
        <div class="page-container">
            <!-- sidebar menu area start -->
            <div class="sidebar-menu">
                <div class="sidebar-header">
                    <div class="logo">
                        <a href="index"><img src="<%=request.getContextPath()%>/resources/assets/images/icon/logo.png" alt="logo"></a>
                    </div>
                    <br>
                    <%-- <h6 class="text-center" style="color: aliceblue"><i class="fa fa-user"></i> <strong><%out.print(((com.jcode.myapp.model.session.Usuario) request.getSession().getAttribute("usuario")).getName_user());%></strong></h6> --%>
				<h6 class="text-center" style="color: aliceblue">
					<i class="fa fa-user"></i> 
					<strong> 
					Isaul
					</strong>
				</h6>
			</div>
                <jsp:include page="menu.jsp"/>
            </div>
            <!-- sidebar menu area end -->
            <!-- main content area start -->
            <div class="main-content">
            
                <!-- header area start  MENU ARRIBA-->               
                <div class="header-area"> <!-- color negro menu arriba ->bg-dark  -->
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
                <!-- header area end MENU ARRIBA-->
                
                <div class="main-content-inner">
<h1>here</h1>

<br><br>

<!--   AKI ESTAMOS LOS 3 DataTable()  INICIAMOSSSSSSSSSSSSSSSSSSS **********************************************************  -->

				
                  	
        <!-- main content area start -->
        <div class="main-content">
            <!-- header area start -->
            <div class="header-area">
                <div class="row align-items-center">
                   
                    <!-- profile info & task notification -->
                    <div class="col-md-6 col-sm-4 clearfix">
                        <ul class="notification-area pull-right">
                            <li id="full-view"><i class="ti-fullscreen"></i></li>
                            <li id="full-view-exit"><i class="ti-zoom-out"></i></li>
                            <li class="dropdown">
                                <i class="ti-bell dropdown-toggle" data-toggle="dropdown">
                                    <span>2</span>
                                </i>
                                <div class="dropdown-menu bell-notify-box notify-box">
                                    <span class="notify-title">You have 3 new notifications <a href="#">view all</a></span>
                                    <div class="nofity-list">
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-key btn-danger"></i></div>
                                            <div class="notify-text">
                                                <p>You have Changed Your Password</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-comments-smiley btn-info"></i></div>
                                            <div class="notify-text">
                                                <p>New Commetns On Post</p>
                                                <span>30 Seconds ago</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-key btn-primary"></i></div>
                                            <div class="notify-text">
                                                <p>Some special like you</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-comments-smiley btn-info"></i></div>
                                            <div class="notify-text">
                                                <p>New Commetns On Post</p>
                                                <span>30 Seconds ago</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-key btn-primary"></i></div>
                                            <div class="notify-text">
                                                <p>Some special like you</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-key btn-danger"></i></div>
                                            <div class="notify-text">
                                                <p>You have Changed Your Password</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-key btn-danger"></i></div>
                                            <div class="notify-text">
                                                <p>You have Changed Your Password</p>
                                                <span>Just Now</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                            
                                                       
                          
                        </ul>
                    </div>
                </div>
            </div>
            
            <!-- page title area end -->
            <div class="main-content-inner">
                <div class="row">
                    <!-- data table start -->
                    <div class="col-12 mt-5">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="header-title">Data Table Default</h4>
                                <div class="data-tables">
                                    <table id="dataTable1" class="text-center">
                                        <thead class="bg-light text-capitalize">
                                            <tr>
                                                <th>Name</th>
                                                <th>Position</th>
                                                <th>Office</th>
                                                <th>Age</th>
                                                <th>Start Date</th>
                                                <th>salary</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                            <tr>
                                                <td>Ashton Cox</td>
                                                <td>Junior Technical Author</td>
                                                <td>San Francisco</td>
                                                <td>66</td>
                                                <td>2009/01/12</td>
                                                <td>$86,000</td>
                                            </tr>
                                            <tr>
                                                <td>Bradley Greer</td>
                                                <td>Software Engineer</td>
                                                <td>London</td>
                                                <td>41</td>
                                                <td>2012/10/13</td>
                                                <td>$132,000</td>
                                            </tr>
                                            <tr>
                                                <td>Brenden Wagner</td>
                                                <td>Software Engineer</td>
                                                <td>San Francisco</td>
                                                <td>28</td>
                                                <td>2011/06/07</td>
                                                <td>$206,850</td>
                                            </tr>                                                                                      
                                            <tr>
                                                <td>Bruno Nash</td>
                                                <td>Software Engineer</td>
                                                <td>Edinburgh</td>
                                                <td>21</td>
                                                <td>2012/03/29</td>
                                                <td>$433,060</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- data table end -->
                    <!-- Primary table start -->
                    <div class="col-12 mt-5">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="header-title">Data Table Primary</h4>
                                <div class="data-tables datatable-primary">
                                    <table id="dataTable2" class="text-center">
                                        <thead class="text-capitalize">
                                            <tr>
                                                <th>Name</th>
                                                <th>Position</th>
                                                <th>Office</th>
                                                <th>Age</th>
                                                <th>Start Date</th>
                                                <th>salary</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Airi Satou</td>
                                                <td>Accountant</td>
                                                <td>Tokyo</td>
                                                <td>33</td>
                                                <td>2008/11/28</td>
                                                <td>$162,700</td>
                                            </tr>
                                            <tr>
                                                <td>Angelica Ramos</td>
                                                <td>Chief Executive Officer (CEO)</td>
                                                <td>London</td>
                                                <td>47</td>
                                                <td>2009/10/09</td>
                                                <td>$1,200,000</td>
                                            </tr>
                                            <tr>
                                                <td>Ashton Cox</td>
                                                <td>Junior Technical Author</td>
                                                <td>San Francisco</td>
                                                <td>66</td>
                                                <td>2009/01/12</td>
                                                <td>$86,000</td>
                                            </tr>                                          
                                            <tr>
                                                <td>Caesar Vance</td>
                                                <td>Pre-Sales Support</td>
                                                <td>New York</td>
                                                <td>29</td>
                                                <td>2011/12/12</td>
                                                <td>$106,450</td>
                                            </tr>
                                            <tr>
                                                <td>Bruno Nash</td>
                                                <td>Software Engineer</td>
                                                <td>Edinburgh</td>
                                                <td>21</td>
                                                <td>2012/03/29</td>
                                                <td>$433,060</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Primary table end -->
					
                    <!-- Dark table start -->
                    <div class="col-12 mt-5">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="header-title">Data Table Dark</h4>
                                <div class="data-tables datatable-dark">
                                    <table id="dataTable3" class="text-center">
                                        <thead class="text-capitalize">
                                            <tr>
                                                <th>Name</th>
                                                <th>Position</th>
                                                <th>Office</th>
                                                <th>Age</th>
                                                <th>Start Date</th>
                                                <th>salary</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Airi Satou</td>
                                                <td>Accountant</td>
                                                <td>Tokyo</td>
                                                <td>33</td>
                                                <td>2008/11/28</td>
                                                <td>$162,700</td>
                                            </tr>
                                            <tr>
                                                <td>Angelica Ramos</td>
                                                <td>Chief Executive Officer (CEO)</td>
                                                <td>London</td>
                                                <td>47</td>
                                                <td>2009/10/09</td>
                                                <td>$1,200,000</td>
                                            </tr>                                                                                     
                                            <tr>
                                                <td>Bradley Greer</td>
                                                <td>Software Engineer</td>
                                                <td>London</td>
                                                <td>41</td>
                                                <td>2012/10/13</td>
                                                <td>$132,000</td>
                                            </tr>
                                            <tr>
                                                <td>Brenden Wagner</td>
                                                <td>Software Engineer</td>
                                                <td>San Francisco</td>
                                                <td>28</td>
                                                <td>2011/06/07</td>
                                                <td>$206,850</td>
                                            </tr>
                                            
                                            <tr>
                                                <td>Bruno Nash</td>
                                                <td>Software Engineer</td>
                                                <td>Edinburgh</td>
                                                <td>21</td>
                                                <td>2012/03/29</td>
                                                <td>$433,060</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Dark table end -->
					
                </div>
            </div>
        </div>
        <!-- main content area end -->
		
	
	

<!--   AKI ESTAMOS DataTable TerminAMOSSSSSSSSSSSSSSSSSSS **********************************************************  -->

                </div>
            </div>
            <!-- main content area end -->
            
            <!-- footer area start-->
            <jsp:include page="footer.jsp"/>
            <!-- footer area end-->
            
        </div>
        
        <!-- page container area end -->
        <!-- jquery latest version -->
		<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/vendor/jquery-2.2.4.min.js"/>"></script>
        <!-- bootstrap 4 js -->        
		<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/popper.min.js"/>"></script>           
		<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/bootstrap.min.js"/>"></script>                 
		<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/metisMenu.min.js"/>"></script>           
		<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/jquery.slimscroll.min.js"/>"></script>       
		<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/jquery.slicknav.min.js"/>"></script>
		<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/scripts.js"/>"></script>




   <!-- jquery latest version -->
<script lenguaje="JavaScript" type="text/javascript" src="<c:url value="/resources/assets/js/vendor/jquery-2.2.4.min.js"/>"></script>
    <!-- <script src="/resources/assets/js/vendor/jquery-2.2.4.min.js"></script> -->
    <!-- bootstrap 4 js -->
    <script src="<c:url value="/resources/assets/js/popper.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/owl.carousel.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/metisMenu.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/jquery.slimscroll.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/jquery.slicknav.min.js"/>"></script>

    <!-- Start datatable js -->
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
    <!-- others plugins -->
    
    <script src="<c:url value="/resources/assets/js/plugins.js"/>"></script>
    <!-- <script src="/resources/assets/js/plugins.js"></script> -->
    
    <script src="<c:url value="/resources/assets/js/scripts.js"/>"></script>
    <!-- <script src="/resources/assets/js/scripts.js"></script> -->
    
</body>
</html>
