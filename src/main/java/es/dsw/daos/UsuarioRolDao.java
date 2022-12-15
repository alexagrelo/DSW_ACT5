package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.models.UsuarioRol;
import es.dsw.connections.MySqlConnection;

public class UsuarioRolDao {
	
	private boolean flagError;
	private String msgError;
	
	public UsuarioRolDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	public UsuarioRol getOnce(int idUsuario) {
		
		MySqlConnection objConection = new MySqlConnection();
		UsuarioRol objUsuarioRol = null;	
		
		try {
			  objConection.open();
		
		if (!objConection.isError()){
			   ResultSet Result = objConection.executeSelect("SELECT * FROM userrol_film WHERE iduser_urf = " + idUsuario);
			   while(Result.next()) {
				   objUsuarioRol = new UsuarioRol();
				   objUsuarioRol.setIduser_urf(Result.getInt("IDUSER_URF"));
				   objUsuarioRol.setIdrol_urf(Result.getInt("IDROL_URF"));
				   objUsuarioRol.setS_insertdate_urf(Result.getDate("S_INSERTDATE_URF"));
				   objUsuarioRol.setS_updatedate_urf(Result.getDate("S_UPDATEDATE_URF"));
			   }
			
			} else {
					this.flagError = true;
					this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConection.msgError();
				   }
	    } catch (Exception ex) {
				this.flagError = true;
				this.msgError = "Error en getOnce. +Info: " + ex.getMessage();
		} finally {
				objConection.close();
		}
		
		return objUsuarioRol;
	}

}
