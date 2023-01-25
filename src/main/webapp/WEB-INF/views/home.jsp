<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EL RINC�N DEL CINE - Gesti�n</title>
<link href="./styles/home.css" rel="stylesheet" id="bootstrap-css">
<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="./js/jquery-3.6.1.min.js"></script>
<script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

<script src="./js/home.js"></script>
</head>
<body>
	<!-- La maquetaci�n base de esta p�gina ha sido obtenida desde: https://bootstrapious.com/p/bootstrap-vertical-navbar-->
	<!-- Vertical navbar -->
	<div class="vertical-nav bg-white" id="sidebar">
		<div class="py-4 px-3 mb-4 bg-light">
			<div class="media d-flex align-items-center">
				<img src="./img/usuario.gif" alt="..." width="65"
					class="mr-3 rounded-circle img-thumbnail shadow-sm">
				<div class="media-body">
					<h4 class="m-0">${User.getName_usf()}
						${User.getFirstsurname_usf()} ${User.getSecoundsurname_usf()}</h4>
					<p class="font-weight-light text-muted mb-0">${User.getUsername_usf()}</p>
				</div>
			</div>
		</div>

		<!--Area del men� -->
		<p
			class="text-gray font-weight-bold text-uppercase px-3 small pb-4 mb-0">Usuario</p>

		<ul class="nav flex-column bg-white mb-0">
			<li class="nav-item"><a href="#" id="btnShowPerfil"
				class="nav-link text-dark font-italic bg-light"> 
				<i class="fa fa-th-large mr-3 text-primary fa-fw">
					</i> Mi Perfil
			</a></li>
			<security:authorize access="hasRole('admin')">
				<li class="nav-item"><a href="#"
					class="nav-link text-dark font-italic"> <i
						class="fa fa-th-large mr-3 text-primary fa-fw"></i> Nuevo usuario
				</a></li>
			</security:authorize>
			<security:authorize access="hasRole('admin')">
				<li class="nav-item"><a href="#"
					class="nav-link text-dark font-italic"> <i
						class="fa fa-th-large mr-3 text-primary fa-fw"></i> Nuevo rol
				</a></li>
			</security:authorize>
			<security:authorize access="hasRole('admin')">
				<li class="nav-item"><a href="#"
					class="nav-link text-dark font-italic"> <i
						class="fa fa-th-large mr-3 text-primary fa-fw"></i> Listar
						usuarios
				</a></li>
			</security:authorize>
			<security:authorize access="hasRole('admin')">
				<li class="nav-item"><a href="#"
					class="nav-link text-dark font-italic"> <i
						class="fa fa-th-large mr-3 text-primary fa-fw"></i> Listar roles
				</a></li>
			</security:authorize>
			<li class="nav-item">
				<form id="logout" action="./logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form> <a href="javascript:document.getElementById('logout').submit()"
				class="nav-link text-dark font-italic"> <i
					class="fa fa-th-large mr-3 text-primary fa-fw"></i> Salir
			</a>

			</li>
		</ul>
		<br />
		<p
			class="text-gray font-weight-bold text-uppercase px-3 small pb-4 mb-0">Repositorio</p>

		<ul class="nav flex-column bg-white mb-0">
			<security:authorize
				access="hasRole('admin') || hasRole('commercial')">
				<li class="nav-item"><a href="#"
					class="nav-link text-dark font-italic" data-toggle="modal"
					data-target="#NuevaPeliculaCenter"> <i
						class="fa fa-th-large mr-3 text-primary fa-fw"></i> Nueva pel�cula
				</a></li>
			</security:authorize>
			<security:authorize
				access="hasRole('admin') || hasRole('commercial') || hasRole('basicuser')">
				<li class="nav-item"><a id="IdListarPeliculas" href="#"
					class="nav-link text-dark font-italic"> <i
						class="fa fa-address-card mr-3 text-primary fa-fw"></i> Listar
						pel�culas
				</a></li>
			</security:authorize>
		</ul>



	</div>


	<!--Area del home page -->

	<!-- Page content holder -->
	<div class="page-content p-5" id="content">
		<!-- Toggle button -->
		<button id="sidebarCollapse" type="button"
			class="btn btn-light bg-white rounded-pill shadow-sm px-4 mb-4">
			<i class="fa fa-bars mr-2"></i><small
				class="text-uppercase font-weight-bold"></small>
		</button>

		<!-- Capa seccion01 donde se cargar�n las secciones-->
		<div id="seccion01">
			<div id="perfilDiv" class="hidden">
				<h2 class="display-4 text-white">
					<b>Mi Perfil</b>
				</h2>
				<p class="lead text-white mb-0">
					<b>Nombre:</b> ${User.getName_usf()}
				</p>
				<p class="lead text-white mb-0">
					<b>Apellidos:</b> ${User.getFirstsurname_usf()}
				</p>
				<p class="lead text-white mb-0">
					<b>Nombre de usuario:</b> ${User.getUsername_usf()}
				</p>
				<p class="lead text-white mb-0">
					<b>Roles:</b> ${Rol}
				</p>
				<br />
				<p class="lead text-white">
					<a href="javascript:document.getElementById('logout').submit()"
						class="text-white"> Salir</a>
				</p>
			</div>
			<!--  
			<div class="separator"></div>
			-->
		</div>
	</div>
	<!-- End demo content -->




	<!-- Ventana Modal en bootstrap para Nueva Pelicula -->
	<div class="modal fade" id="NuevaPeliculaCenter" tabindex="-1"
		role="dialog" aria-labelledby="NuevaPeliculaCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="NuevaPeliculaLongTitle">Nueva
						pel�cula</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">[Formulario de recogida de datos]</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button id="GuardarPelicula" type="button" class="btn btn-dark">Guardar</button>
				</div>
			</div>
		</div>
	</div>



</body>
</html>