package es.dsw.models;

import java.util.Date;


public class Rol {
	
	private int idrol_rf;
	private String rolcode_rf;
	private String rolname_rf;
	private int s_activerow_rf;
	private Date s_insertdate_rf;
	private Date s_updatedate_rf;
	
	
	public Rol() {
		
	}


	public Rol(int idrol_rf, String rolcode_rf, String rolname_rf, int s_activerow_rf, Date s_insertdate_rf,
			Date s_updatedate_rf) {
		this.idrol_rf = idrol_rf;
		this.rolcode_rf = rolcode_rf;
		this.rolname_rf = rolname_rf;
		this.s_activerow_rf = s_activerow_rf;
		this.s_insertdate_rf = s_insertdate_rf;
		this.s_updatedate_rf = s_updatedate_rf;
	}


	public int getIdrol_rf() {
		return idrol_rf;
	}


	public void setIdrol_rf(int idrol_rf) {
		this.idrol_rf = idrol_rf;
	}


	public String getRolcode_rf() {
		return rolcode_rf;
	}


	public void setRolcode_rf(String rolcode_rf) {
		this.rolcode_rf = rolcode_rf;
	}


	public String getRolname_rf() {
		return rolname_rf;
	}


	public void setRolname_rf(String rolname_rf) {
		this.rolname_rf = rolname_rf;
	}


	public int getS_activerow_rf() {
		return s_activerow_rf;
	}


	public void setS_activerow_rf(int s_activerow_rf) {
		this.s_activerow_rf = s_activerow_rf;
	}


	public Date getS_insertdate_rf() {
		return s_insertdate_rf;
	}


	public void setS_insertdate_rf(Date s_insertdate_rf) {
		this.s_insertdate_rf = s_insertdate_rf;
	}


	public Date getS_updatedate_rf() {
		return s_updatedate_rf;
	}


	public void setS_updatedate_rf(Date s_updatedate_rf) {
		this.s_updatedate_rf = s_updatedate_rf;
	}
	
	
	
	
	

}
