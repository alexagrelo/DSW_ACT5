<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>EL RINCÓN DEL CINE - Listado de Películas</title>
<link href="./styles/home.css" rel="stylesheet">
<script src="./js/jquery-3.6.1.min.js"></script>
<script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<script src="./js/listadoPeliculas.js"></script>
</head>


<table class="table table-light table-striped table-hover">
	<thead>
		<tr class="p-3">
			<th scope="col">#</th>
			<th scope="col">Nombre</th>
			<th scope="col">Género</th>
			<th scope="col">Director</th>
			<th scope="col">País</th>
			<th scope="col">Fecha de estreno</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="0" step="1" items="${ListadoPeliculas}" var="item">
			<tr class="p-3">
				<th scope="row">${item.getIdfilm_rf()}</th>
				<td>${item.getTitle_rf()}</td>
				<td>${item.getGenero()}</td>
				<td>${item.getDirector_rf()}</td>
				<td>${item.getPais()}</td>
				<td>${item.getDatepremiere_rf()}</td>
				<td><form:form>
						<input type="button" class="btnDelete btn btn-danger"
							target="${item.getIdfilm_rf()}" value="Eliminar" />
					</form:form></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</html>
