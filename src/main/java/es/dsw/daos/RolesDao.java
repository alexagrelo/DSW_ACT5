package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;

import es.dsw.models.Rol;

public class RolesDao {
	
	private boolean flagError;
	private String msgError;
	
	
	public RolesDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	public ArrayList<Rol> getAll(){
		
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Rol> objTablaRol = new ArrayList<Rol>();
		
		
		try {
			objConection.open();
			
			if(!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM rol_film");
				while(Result.next()) {
					Rol objRol = new Rol();
					
					objRol.setIdrol_rf(Result.getInt("IDROL_RF"));
					objRol.setRolcode_rf(Result.getString("ROLCODE_RF"));
					objRol.setRolname_rf(Result.getString("ROLNAME_RF"));
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMysql informa error al abrir conexión, +Info: " + objConection.msgError();
			}
			
		}catch(Exception ex){
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConection.close();
		}
		return objTablaRol;
	}
	
	
	public ArrayList<Rol> getbyId(int idRol) {
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Rol> objTablaRol = new ArrayList<Rol>();
		
		try {
			objConection.open();
			
			if(!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM rol_film WHERE idrol_rf = " + idRol);
				while(Result.next()) {
					Rol objRol = new Rol();				
					objRol.setIdrol_rf(Result.getInt("IDROL_RF"));
					objRol.setRolcode_rf(Result.getString("ROLCODE_RF"));
					objRol.setRolname_rf(Result.getString("ROLNAME_RF"));
					objTablaRol.add(objRol);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConection.msgError();
			   }
			
		} catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getOnce. +Info: " + ex.getMessage();
		} finally {
			objConection.close();
		}
		
		return objTablaRol;
	}

}
