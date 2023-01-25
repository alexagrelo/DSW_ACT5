//Se sobrecarga el botón toggle
$(function() {
    // Sidebar toggle behavior
    $('#sidebarCollapse').on('click', function() {
   	   $('#sidebar, #content').toggleClass('active');
    });

  });
  
 

  //Se sobrecarga botón de Guardar de ventana modal y carga de sección del menú
  $(document).ready(function(){
    	$('body').on('click', '#GuardarPelicula', function(){
      	//En esta zona debería ubicarse la llamada asíncrona al servidor enviando los datos de la nueva película

      	$("#NuevaPeliculaCenter").modal('hide');
      	$('body').removeClass('modal-open'); //eliminamos la clase del body para poder hacer scroll
      	$('.modal-backdrop').remove();//eliminamos el backdrop del modal
    })
    
     // Muestra los datos del perfil al pulsar el enlace en la barra lateral
    $('body').on('click', '#btnShowPerfil', function(){
		console.info('Capturado evento click en enlace perfil de barra lateral');
    	$('#perfilDiv').toggleClass('hidden');
		$('#perfilDiv').toggleClass('shown');
    	
    	
    })
    console.info('documento ready');
  
  })