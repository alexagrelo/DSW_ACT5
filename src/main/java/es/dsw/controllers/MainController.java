package es.dsw.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.dsw.daos.DistribuidoresDao;
import es.dsw.daos.GenerosDao;
import es.dsw.daos.PaisesDao;
import es.dsw.daos.PeliculasDao;
import es.dsw.daos.UsuariosDao;
import es.dsw.helpers.CookieHelper;
import es.dsw.models.Distribuidor;
import es.dsw.models.Genero;
import es.dsw.models.Pais;
import es.dsw.models.Pelicula;
import es.dsw.models.Usuario;

@Controller
public class MainController {

	@GetMapping(value = { "/", "/home" })
	public String home(Model objModel, Authentication authenticatedUser, HttpServletRequest request,
			HttpServletResponse response) {
		// Se resetea la variable Roles
		String roles = "";

		// Se comprueba si hay un usuario logueado y se recogen sus roles (hay 4 en la
		// BD)
		if (authenticatedUser.getAuthorities() != null && !authenticatedUser.getAuthorities().isEmpty()) {
			GrantedAuthority rolItem;

			Collection<? extends GrantedAuthority> objRoles = authenticatedUser.getAuthorities();

			Iterator<? extends GrantedAuthority> rolesIterator = objRoles.iterator();

			while (rolesIterator.hasNext()) {
				rolItem = rolesIterator.next();
				roles += rolItem.getAuthority() + ", ";
			}
		}

		// Guardamos la info del usuario introducido en en la memoria de la aplicación,
		// que por tanto tiene @ApplicationScope.
		objModel.addAttribute("Nombre", authenticatedUser.getName());
		objModel.addAttribute("Roles", roles);

		UsuariosDao objUsuarioDao = new UsuariosDao();
		Usuario usuario = objUsuarioDao.getUserbyUsername(authenticatedUser.getName());
		ArrayList<String> userRoles = usuario.getRol();
		String userRol = "";
		for (String rol : userRoles) {
			userRol += (rol + ", ");
		}

		userRol += "end";
		userRol = userRol.replace(", end", "");

		objModel.addAttribute("User", usuario);
		objModel.addAttribute("Rol", userRol);

		CookieHelper.saveDateTimeCookie(request, response);

		GenerosDao objGenerosDao = new GenerosDao();
		ArrayList<Genero> generos = objGenerosDao.getAll();
		objModel.addAttribute("generos", generos);

		DistribuidoresDao objDistribuidoresDao = new DistribuidoresDao();
		ArrayList<Distribuidor> distribuidores = objDistribuidoresDao.getAll();
		objModel.addAttribute("distribuidores", distribuidores);

		PaisesDao objPaisesDao = new PaisesDao();
		ArrayList<Pais> paises = objPaisesDao.getAll();
		objModel.addAttribute("paises", paises);

		return "home";
	}

	@GetMapping(value = { "/login" })
	public String login(Model objModel, HttpServletRequest request, HttpServletResponse response) {

		String objCookieData = CookieHelper.getCookieValue("ultimoAcceso", request);
		if (objCookieData != null) {

			String output = objCookieData.replace("_", " ");
			output = "Fecha del último acceso: " + output;
			objModel.addAttribute("DateTime", output);
		}

		return "login";
	}

	@PostMapping(value = { "/", "/procesarDatos" }, produces = "application/json")
	public String procesarDatos(Authentication authentication, Model objModel,
			@RequestParam(name = "titulo", defaultValue = "") String Titulo,
			@RequestParam(name = "sinopsis", defaultValue = "", required=false) String Sinopsis,
			@RequestParam(name = "id_genero", defaultValue = "-1") Integer IdGenero,
			@RequestParam(name = "director", defaultValue = "", required=false) String Director,
			@RequestParam(name = "reparto", defaultValue = "", required=false) String Reparto,
			@RequestParam(name = "anio", defaultValue = "-1") Integer Anio,
			@RequestParam(name = "premiere", defaultValue = "0000-00-00", required=false) String Premiere,
			@RequestParam(name = "id_distribuidor", defaultValue = "-1", required=false) Integer id_distribuidor,
			@RequestParam(name = "id_pais", defaultValue = "-1") Integer id_pais) {

		PeliculasDao objPeliculasDao = new PeliculasDao();
		
		UsuariosDao objUsuario = new UsuariosDao();
		Usuario user = objUsuario.getUserbyUsername(authentication.getName());
		int idUser = user.getIduser_usf();

		List<String> errores = new ArrayList<>();
		
		// Mensaje que transmitirá éxito al crear la película
		// o errores en los campos, según corresponda.
	 	String message = "";
		boolean error = false;
		
		// Pasamos la fecha de estreno a formato java.sql.Date
		java.sql.Date dateSQL = null;
		if(Premiere.equals("0000-00-00")) {
			dateSQL = null;
		} else {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date datePremiere = sdf.parse(Premiere);
				dateSQL = new java.sql.Date(datePremiere.getTime());
			} catch (ParseException e) {
				errores.add("La fecha debe tener un formato válido");
				System.out.println("FECHA PARSING ERROR" + e.getMessage());
				e.printStackTrace();
			}
		}
		

		if (Titulo.isBlank()) {
			errores.add("El Título no puede quedar vacío");
		}

		if (IdGenero < 1) {
			errores.add("El Género no puede quedar vacío");
		}

		if (Anio < 0) {
			errores.add("El Año no puede quedar vacío");
		}

		if (id_pais < 0) {
			errores.add("El País no puede quedar vacío");
		}
		
		if (errores.isEmpty()) {
			Pelicula nuevaPelicula = new Pelicula();
			nuevaPelicula.setTitle_rf(Titulo);
			nuevaPelicula.setSynopsis_rf(Sinopsis);
			nuevaPelicula.setId_genero_rf(IdGenero);
			nuevaPelicula.setDirector_rf(Director);
			nuevaPelicula.setReparto_rf(Reparto);
			nuevaPelicula.setAnio_rf(Anio);
			nuevaPelicula.setDatepremiere_rf(dateSQL);
			nuevaPelicula.setId_producer_rf(id_distribuidor);
			nuevaPelicula.setId_pais_rf(id_pais);
			nuevaPelicula.setS_iduser_cf(idUser);
			
			objPeliculasDao.setPelicula(nuevaPelicula);
			
			error = false;
			message = "La película ha sido guardada con éxito";
			
		} else {
			error = true;
			for (String errorItem : errores) {
			message += (errorItem + ".\n");
			}
		}

		
		objModel.addAttribute("mensaje", message);
		objModel.addAttribute("error", errores.size() > 0);
		
		return "procesarDatos";

	}
	
	@GetMapping(value= {"/listadoPeliculas"})
	public String listadoPeliculas(Model objModel) {
		PeliculasDao objPeliculasDao = new PeliculasDao();
		ArrayList<Pelicula> objTablaPelicula = objPeliculasDao.getAll();
		
		objModel.addAttribute("ListadoPeliculas", objTablaPelicula);
		return "listadoPeliculas";
	}
	
	 @PostMapping(value= {"/", "/eliminarPelicula"}, produces="application/json")
	 private void eliminarPelicula(HttpServletRequest request, @RequestParam("idPelicula") String idPelicula) {
		 Integer idPeliculaInteger = Integer.parseInt(idPelicula);
		 PeliculasDao objPeliculasDao = new PeliculasDao();
		 objPeliculasDao.deleteById(idPeliculaInteger);
	 }

}
