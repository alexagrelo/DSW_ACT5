package es.dsw.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.dsw.daos.UsuariosDao;
import es.dsw.models.Usuario;

@Controller
public class MainController {
	
	@GetMapping(value = {"/","/home"})
	public String home(Model objModel, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		String Roles ="";
		
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
		ArrayList<Usuario> objListaUsuario = objUsuario.getAll();
		
		objModel.addAttribute("Usuarios", objListaUsuario);
		
		
		/*String timestamp = ZonedDateTime.now(ZoneId.of("Europe/Lisbon"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyy, hh.mm.ss a"));
		
		Cookie newCookie = new Cookie("fechaAcceso","Fecha#del#último#acceso#" + timestamp);
		newCookie.setMaxAge(24*60*60);
		response.addCookie(newCookie);*/
		
		return "home";
	}
	
	@GetMapping(value = {"/login"})
	public String login() {		
		return "login";
	}

}


