package es.dsw.controllers;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.dsw.daos.UsuariosDao;
import es.dsw.helpers.CookieHelper;
import es.dsw.models.Usuario;

@Controller
public class MainController {
	
	@GetMapping(value = {"/","/home"})
	public String home(Model objModel, Authentication authenticatedUser, HttpServletRequest request, HttpServletResponse response) {
		// Se resetea la variable Roles
		String roles ="";
		
		//Se comprueba si hay un usuario logueado y se recogen sus roles (hay 4 en la BD)
		if(authenticatedUser.getAuthorities() != null && !authenticatedUser.getAuthorities().isEmpty()){
			GrantedAuthority rolItem;
			
			Collection<? extends GrantedAuthority> objRoles = authenticatedUser.getAuthorities();
			
			Iterator<? extends GrantedAuthority> rolesIterator = objRoles.iterator();
			
			while(rolesIterator.hasNext()) {
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
		String userRol="";
		for (String rol : userRoles) {
			userRol += (rol + ", ");
		}
		
		userRol += "end";
		userRol = userRol.replace(", end", "");
		
		objModel.addAttribute("User", usuario);
		objModel.addAttribute("Rol", userRol);
			
		CookieHelper.saveDateTimeCookie(request, response);
		
		
		return "home";
	}
	
	@GetMapping(value = {"/login"})
	public String login(Model objModel, HttpServletRequest request, HttpServletResponse response) {
		
		String objCookieData = CookieHelper.getCookieValue("ultimoAcceso", request);
		if(objCookieData != null) {
			
			String output = objCookieData.replace("_", " ");
			output = "Fecha del último acceso: " + output;
			objModel.addAttribute("DateTime",output);
		}
		
		return "login";
	}

}


