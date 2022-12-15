package es.dsw.models;

import java.util.Date;

public class UsuarioRol {
	private int iduser_urf;
	private int idrol_urf;
	private Date s_insertdate_urf;
	private Date s_updatedate_urf;
	
	public UsuarioRol() {
	}

	public UsuarioRol(int iduser_urf, int idrol_urf, Date s_insertdate_urf, Date s_updatedate_urf) {
		
		this.iduser_urf = iduser_urf;
		this.idrol_urf = idrol_urf;
		this.s_insertdate_urf = s_insertdate_urf;
		this.s_updatedate_urf = s_updatedate_urf;
	}

	public int getIduser_urf() {
		return iduser_urf;
	}

	public void setIduser_urf(int iduser_urf) {
		this.iduser_urf = iduser_urf;
	}

	public int getIdrol_urf() {
		return idrol_urf;
	}

	public void setIdrol_urf(int idrol_urf) {
		this.idrol_urf = idrol_urf;
	}

	public Date getS_insertdate_urf() {
		return s_insertdate_urf;
	}

	public void setS_insertdate_urf(Date s_insertdate_urf) {
		this.s_insertdate_urf = s_insertdate_urf;
	}

	public Date getS_updatedate_urf() {
		return s_updatedate_urf;
	}

	public void setS_updatedate_urf(Date s_updatedate_urf) {
		this.s_updatedate_urf = s_updatedate_urf;
	}
	
	
	
	
	
	
	
}