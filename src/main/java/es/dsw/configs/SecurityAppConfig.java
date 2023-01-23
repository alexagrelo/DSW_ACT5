package es.dsw.configs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import es.dsw.daos.UsuariosDao;
import es.dsw.models.Usuario;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig {

	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
      
		// Los usuarios se cargan directamente de la BD -> Tabla "user_film"
				
		UsuariosDao Usuario = new UsuariosDao();
		ArrayList<Usuario> objListaUsuario = Usuario.getAll();	
		
        //Se crea un objeto InMemoryUserDetailsManager que nos permitirá cargar los usuarios en memoria de aplicación.
		// @ScopeApplication
        InMemoryUserDetailsManager InMemory = new InMemoryUserDetailsManager();
        
        // Se traen los roles de cada usuario (user_film -> userrol_film -> rol_film)
		for(Usuario usuario : objListaUsuario) {
			StringBuilder roles = new StringBuilder("");
			for(String eachstring: usuario.getRol()) {
				roles.append(eachstring).append(",");
			}
			
			// Para la actividad no se utilizan métodos de encriptación para los usuarios			
			@SuppressWarnings("deprecation")
			UserDetails user = User.withDefaultPasswordEncoder()
			.username(usuario.getUsername_usf())
			.password(usuario.getPassword_usf())
			.roles(roles.toString().split(","))
			.build();


			InMemory.createUser(user);
		}
       
        //Se devuelve a el modulo de Spring Security el descriptor del objeto InMemoryUserDetailsManager para que surta efecto las modificaciones.
        return InMemory;
    }
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
        // Añadimos los mapeos públicos que indica el enunciado
    	http.authorizeRequests()
    	 		.regexMatchers("/styles/*.*") 
    	 			.permitAll()
    	 		.regexMatchers("/img/*.*")
    	 			.permitAll()
    	 		.regexMatchers("/js/*.*")
    	 			.permitAll() 
    	 		.regexMatchers("/bootstrap/*.*")
    	 			.permitAll() 
        	 	.regexMatchers("/ayuda")
    	 			.permitAll() 
    	 		.antMatchers("/admin/**").hasRole("admin")
    	 		.antMatchers("/commercial/**").hasRole("commercial")
    	 		.antMatchers("/basicUser/**").hasRole("basicUser")
    	 		.anyRequest()
    	 			.authenticated() //Configuración para el proceso de autenticación de usuario
    	 				.and()
    	 					.formLogin() //Configuración para logeo basado en formulario de login.
    	 						.loginPage("/login") //Se indica la controladora que devuelve la vista de logeo. Debe estar implementada en una controladora.
    	 						.loginProcessingUrl("/loginprocess") //Se indica la controladora que procesará los datos del logeo. Este mapeo no es necesario implementarlo en ninguna controladora.
    	 						.permitAll() //Se indica que la controladora /loggin estará accesibles a todos los clientes (para que todo cliente tenga la oportunidad de logearse).
    	 				.and()
    	 					.logout()
    	 					//.logoutSuccessUrl("/login?logout")
    	 					.permitAll(); //Se habilita el logout. No es necesario implementar este mapeo en ninguna controladora, al invocar /logout, spring security anula la autenticación y además reinicia las variables de sesión.
    	
    	//Se devuelve el beans para que spring lo procese.
        return http.build();
    }
	

   @Bean
    public AuthenticationEventPublisher authenticationEventPublisher
    (ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
	

}

