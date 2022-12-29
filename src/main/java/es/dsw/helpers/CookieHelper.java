package es.dsw.helpers;

import java.net.URLEncoder;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
	
	public static void saveCookie(String cookieName, String value, int maxAge, HttpServletResponse response) {
		
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	
	
	public static String getCookieValue(String cookieName, HttpServletRequest request) {
		
		String value = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			int i = 0;
			boolean cookieExists = false;
			while(!cookieExists && i<cookies.length) {
				if(cookies[i].getName().equals(cookieName)) {
				cookieExists = true;
				value = cookies[i].getValue();
			}else {
				i++;
			}
		  }
		}
		return value;				
	}
	
	
	public static void saveDateTimeCookie(HttpServletRequest request, HttpServletResponse response) {
		String timestamp = ZonedDateTime.now(ZoneId.of("Europe/Lisbon")).toString();

		Cookie newCookie = new Cookie("ultimoAcceso", timestamp);
		newCookie.setMaxAge(24*60*60);
		response.addCookie(newCookie);
	}

}
