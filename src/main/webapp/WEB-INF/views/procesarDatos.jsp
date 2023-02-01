<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>EL RINCÓN DEL CINE - Datos</title>
	<link href="./styles/home.css" rel="stylesheet" id="bootstrap-css">
<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="./js/jquery-3.6.1.min.js"></script>
<script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</head>
	<body>
		<c:if test="${error}">
			<p style="color:red;">${mensaje}</p>
		</c:if>
		<c:if test="${!error}">
			<p style="color:green;">${mensaje}</p>
		</c:if>
	</body>
</html>

