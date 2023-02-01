$(document).ready(function() {

	$(".btnDelete").click(function() {
		if (confirm("¿Está seguro de que desea eliminar esta película?")) {

			let token = $("meta[name='_csrf']").attr("content");
			let header = $("meta[name='_csrf_header']").attr("content");

			var idPelicula = $(this).attr("target");

			$.ajax({
				type: "POST",
				url: "./eliminarPelicula",
				data: "idPelicula=" + idPelicula,
				dataType: "json",
				beforeSend: request => request.setRequestHeader(header, token),

			})
			setTimeout(() => {
				$("#seccion01").load("./listadoPeliculas");
			}, "500")
		}



	})

})