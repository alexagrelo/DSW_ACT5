package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Usuario;
import es.dsw.models.UsuarioRol;
import es.dsw.models.Rol;


public class UsuariosDao {
	
	private boolean flagError;
	private String msgError;
	
	public UsuariosDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	public ArrayList<Usuario> getAll(){
		System.out.println(0);
		
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Usuario> objTablaUsuario = new ArrayList<Usuario>();
		
		try {
			objConection.open();
			
			if(!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM user_film");
				while (Result.next()) {
					Usuario objUsuario = new Usuario();
					objUsuario.setIduser_usf(Result.getInt("IDUSER_USF"));
					objUsuario.setUsername_usf(Result.getString("USERNAME_USF"));
					objUsuario.setFirstsurname_usf(Result.getString("FIRSTSURNAME_USF"));
					objUsuario.setSecoundsurname_usf(Result.getString("SECOUNDSURNAME_USF"));
					objUsuario.setUsername_usf(Result.getString("USERNAME_USF"));
					UsuarioRolDao objUsuarioRolDao = new UsuarioRolDao();
					UsuarioRol objUsuarioRol = objUsuarioRolDao.getOnce(Result.getInt("IDUSER_USF"));
					
					RolesDao objRolDao = new RolesDao();
					ArrayList<Rol> objTablaRol = objRolDao.getbyId(objUsuarioRol.getIdrol_urf());
					Iterator<Rol> iter = objTablaRol.iterator();
					
					ArrayList<String> Roles = new ArrayList<String>();
					while(iter.hasNext()) {
						Roles.add(iter.next().getRolname_rf());						
					}
					objUsuario.setRol(Roles);
					
					objTablaUsuario.add(objUsuario);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getAll. El bojeto clsConectionMysql informa error al abrir conexión. +Info: " + objConection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConection.close();
		}
		return objTablaUsuario;
	}
	

}
