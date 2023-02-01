//Se sobrecarga el botón toggle
$(function() {
	// Sidebar toggle behavior
	$('#sidebarCollapse').on('click', function() {
		$('#sidebar, #content').toggleClass('active');
	});

});



//Se sobrecarga botón de Guardar de ventana modal y carga de sección del menú
$(document).ready(function() {
	
	// Guardar películas
	$('body').on('click', '#GuardarPelicula', function() {
		//En esta zona debería ubicarse la llamada asíncrona al servidor enviando los datos de la nueva película
		let titulo = $("#f_title_rf").val().trim();
		let sinopsis = $("#f_synopsis_rf").val().trim();
		let id_genero = $("#f_idgenre_rf").val();
		let director = $("#f_director_rf").val().trim();
		let reparto = $("#f_reparto_rf").val().trim();
		let anio = $("#f_anio_rf").val();
		let premiere = $("#f_datepremiere_rf").val();
		let id_distribuidor = $("#f_idproducer_rf").val();
		let id_pais = $("#f_idcountry_rf").val();
		
		$('resultadoGuardar').html("");
		
		let header = $("meta[name='_csrf_header']").attr("content");
		let token = $("meta[name='_csrf']").attr("content");
		

		$.ajax({
			url     : './procesarDatos',
			method  : 'POST',
			data    : {titulo:titulo, sinopsis:sinopsis, id_genero:id_genero, director:director, reparto:reparto, 
						anio:anio, premiere:premiere, id_distribuidor:id_distribuidor, id_pais:id_pais},
			beforeSend: request => request.setRequestHeader(header, token),
			success    : function(resultText){
				$('#resultadoGuardar').html('<p style="color:red; text-align:center;">'+resultText+'</p>');
			        
				if(titulo != "" && id_genero != "" && anio != "" && id_pais != ""){
					setTimeout(() => {
						$("#btnCerrarForm").trigger("click");
						$("#nuevaPeliculaForm").trigger("reset");
						$('#resultadoGuardar').html("");
						
						$("#NuevaPeliculaCenter").modal('hide');
						$('body').removeClass('modal-open'); //eliminamos la clase del body para poder hacer scroll
						$('.modal-backdrop').remove();//eliminamos el backdrop del modal
						$("#filmsListDiv").load("./listadoPeliculas")
					},"1500")
						
				}
				
			},
			error : function(jqXHR, exception){
				console.error("exception: ", exception)
				console.info("jqXHR", jqXHR)
				
			   $('#resultadoGuardar').html('<p style="color:red; text-align:center;">Error Fatal en el Servidor</p>');
			}
			
		});

		
	})

	// Muestra los datos del perfil al pulsar el enlace en la barra lateral
	$('body').on('click', '#btnShowPerfil', function() {
		$('#perfilDiv').toggleClass('hidden');
		$('#perfilDiv').toggleClass('shown');
		
		$('#filmsListDiv').html("");
		
	})
	
	
	// Mostrar listado de películas
	$('body').on('click', '#IdListarPeliculas', function(){
		$('#perfilDiv').removeClass('shown');
		$('#perfilDiv').addClass('hidden');
		
		$('#filmsListDiv').removeClass('hidden');
		$('#filmsListDiv').addClass('shown');
		
		$('#filmsListDiv').load("./listadoPeliculas");
	})
	
})