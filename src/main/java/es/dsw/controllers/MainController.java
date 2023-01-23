package es.dsw.controllers;


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
	public String home(Model objModel, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		// Se resetea la variable Roles
		String Roles ="";
		
		//Se comprueba si hay un usuario logueado y se recogen sus roles (hay 4 en la BD)
		if(authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()){
			GrantedAuthority auxRol;
			
			Collection<? extends GrantedAuthority> objRoles = authentication.getAuthorities();
			
			Iterator<? extends GrantedAuthority> iterator = objRoles.iterator();
			
			while(iterator.hasNext()) {
				auxRol = iterator.next();
				Roles = Roles + auxRol.getAuthority() + ", ";
			}
		}
		
		objModel.addAttribute("Nombre", authentication.getName());
		objModel.addAttribute("Roles", Roles);
		
		
		
		UsuariosDao objUsuario = new UsuariosDao();
		//ArrayList<Usuario> objListaUsuario = objUsuario.getAll();
		Usuario user = objUsuario.getUserbyUsername(authentication.getName());
		ArrayList<String> userRoles = user.getRol();
		String userRol="";
		
		for (int i = 0; i < userRoles.size(); i++) {
			userRol = userRol + userRoles.get(i) + ", ";
		}
		
		//objModel.addAttribute("Usuarios", objListaUsuario);
		objModel.addAttribute("User", user);
		objModel.addAttribute("Rol", userRol);
			
		CookieHelper.saveDateTimeCookie(request, response);
		
		
		return "home";
	}
	
	@GetMapping(value = {"/login"})
	public String login(Model objModel, HttpServletRequest request, HttpServletResponse response) {		
		
		
		
		
		String objCookieData = CookieHelper.getCookieValue("ultimoAcceso", request);
		if(objCookieData != null) {
			
			String Date = objCookieData.substring(0,10);
			String day = Date.substring(8);
			String month = Date.substring(5,7);
			String year = Date.substring(0,4);
			String formattedDate = day+"/"+month+"/"+year;
			
			String Time = objCookieData.substring(11,19).replace("/", ":");
			
			
			String DateTime = "Fecha del último acceso: " + formattedDate + " " + Time;
			objModel.addAttribute("DateTime",DateTime);
		}
		
		
		return "login";
		
		
	}

}


