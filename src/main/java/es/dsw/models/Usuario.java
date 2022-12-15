package es.dsw.models;

import java.util.Date;
import java.util.ArrayList;

public class Usuario  {
	
	private int iduser_usf;
	private String username_usf;
	private String password_usf;
	private String name_usf;
	private String firstsurname_usf;
	private String secoundsurname_usf;
	private String email_usf;
	private boolean s_activerow_usf;
	private Date s_insertdate_usf;
	private Date s_updatedate_usf;
	private ArrayList<String> Rol;
	
	
	public Usuario() {
	}


	public Usuario(int iduser_usf, String username_usf, String password_usf, String name_usf, String firstsurname_usf,
			String secoundsurname_usf, String email_usf, boolean s_activerow_usf, Date s_insertdate_usf,
			Date s_updatedate_usf,ArrayList<String> _Rol) {
		this.iduser_usf = iduser_usf;
		this.username_usf = username_usf;
		this.password_usf = password_usf;
		this.name_usf = name_usf;
		this.firstsurname_usf = firstsurname_usf;
		this.secoundsurname_usf = secoundsurname_usf;
		this.email_usf = email_usf;
		this.s_activerow_usf = s_activerow_usf;
		this.s_insertdate_usf = s_insertdate_usf;
		this.s_updatedate_usf = s_updatedate_usf;
		this.Rol = _Rol;
		
	}


	public int getIduser_usf() {
		return iduser_usf;
	}


	public void setIduser_usf(int iduser_usf) {
		this.iduser_usf = iduser_usf;
	}


	public String getUsername_usf() {
		return username_usf;
	}


	public void setUsername_usf(String username_usf) {
		this.username_usf = username_usf;
	}


	public String getPassword_usf() {
		return password_usf;
	}


	public void setPassword_usf(String password_usf) {
		this.password_usf = password_usf;
	}


	public String getName_usf() {
		return name_usf;
	}


	public void setName_usf(String name_usf) {
		this.name_usf = name_usf;
	}


	public String getFirstsurname_usf() {
		return firstsurname_usf;
	}


	public void setFirstsurname_usf(String firstsurname_usf) {
		this.firstsurname_usf = firstsurname_usf;
	}


	public String getSecoundsurname_usf() {
		return secoundsurname_usf;
	}


	public void setSecoundsurname_usf(String secoundsurname_usf) {
		this.secoundsurname_usf = secoundsurname_usf;
	}


	public String getEmail_usf() {
		return email_usf;
	}


	public void setEmail_usf(String email_usf) {
		this.email_usf = email_usf;
	}


	public boolean isS_activerow_usf() {
		return s_activerow_usf;
	}


	public void setS_activerow_usf(boolean s_activerow_usf) {
		this.s_activerow_usf = s_activerow_usf;
	}


	public Date getS_insertdate_usf() {
		return s_insertdate_usf;
	}


	public void setS_insertdate_usf(Date s_insertdate_usf) {
		this.s_insertdate_usf = s_insertdate_usf;
	}


	public Date getS_updatedate_usf() {
		return s_updatedate_usf;
	}


	public void setS_updatedate_usf(Date s_updatedate_usf) {
		this.s_updatedate_usf = s_updatedate_usf;
	}




	public ArrayList<String> getRol() {
		return Rol;
	}


	public void setRol(ArrayList<String> rol) {
		Rol = rol;
	}
	
	
	
	
	
	
	
	

}
