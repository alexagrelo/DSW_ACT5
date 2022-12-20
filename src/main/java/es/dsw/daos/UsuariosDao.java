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
					objUsuario.setPassword_usf(Result.getString("PASSWORD_USF"));
					objUsuario.setFirstsurname_usf(Result.getString("FIRSTSURNAME_USF"));
					objUsuario.setSecoundsurname_usf(Result.getString("SECOUNDSURNAME_USF"));
					objUsuario.setName_usf(Result.getString("NAME_USF"));
					
					//Traer los ids de rol de cada usuario
					UsuarioRolDao objUsuarioRolDao = new UsuarioRolDao();
					ArrayList<UsuarioRol> objTablaUsuarioRol = objUsuarioRolDao.getById(Result.getInt("IDUSER_USF"));
					Iterator<UsuarioRol> iterator = objTablaUsuarioRol.iterator();
					
					
					RolesDao objRolDao = new RolesDao();
					ArrayList<Rol> objTablaRol = new ArrayList<Rol>();
					ArrayList<String> Roles = new ArrayList<String>();
					while(iterator.hasNext()) {
						UsuarioRol objUsuarioRol = new UsuarioRol();
						objUsuarioRol.setIdrol_urf(iterator.next().getIdrol_urf());
						
						objTablaRol = objRolDao.getbyId(objUsuarioRol.getIdrol_urf());
						Iterator<Rol> iter = objTablaRol.iterator();
						
						while(iter.hasNext()) {
							Roles.add(iter.next().getRolname_rf());						
						}
					}	
					objUsuario.setRol(Roles);
					objTablaUsuario.add(objUsuario);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConection.close();
		}
		return objTablaUsuario;
	}
	
	
	public Usuario getUserbyUsername(String username) {
		System.out.println(11);
		System.out.println("usuario " + username);
		MySqlConnection objConection = new MySqlConnection();
		Usuario objUsuario = null;
		
		
		try {
			objConection.open();
			System.out.println(12);
			if(!objConection.isError()) {
				String sql = "SELECT * FROM user_film WHERE USERNAME_USF = " + "'" + username + "'";
				System.out.println(sql);
				ResultSet Result = objConection.executeSelect(sql);
				System.out.println(13);
				if(Result == null) {
					System.out.println("No hay Result");
				}else {
					System.out.println("Sí hay Result");
				}
				while (Result.next()) {
					
					System.out.println(14);
					objUsuario = new Usuario();
					System.out.println(15);
					objUsuario.setIduser_usf(Result.getInt("IDUSER_USF"));
					objUsuario.setUsername_usf(Result.getString("USERNAME_USF"));
					objUsuario.setPassword_usf(Result.getString("PASSWORD_USF"));
					objUsuario.setName_usf(Result.getString("NAME_USF"));
					objUsuario.setFirstsurname_usf(Result.getString("FIRSTSURNAME_USF"));
					objUsuario.setSecoundsurname_usf(Result.getString("SECOUNDSURNAME_USF"));
					//Traer los ids de rol de cada usuario
					UsuarioRolDao objUsuarioRolDao = new UsuarioRolDao();
					ArrayList<UsuarioRol> objTablaUsuarioRol = objUsuarioRolDao.getById(Result.getInt("IDUSER_USF"));
					Iterator<UsuarioRol> iterator = objTablaUsuarioRol.iterator();
					
					
					RolesDao objRolDao = new RolesDao();
					ArrayList<Rol> objTablaRol = new ArrayList<Rol>();
					ArrayList<String> Roles = new ArrayList<String>();
					while(iterator.hasNext()) {
						UsuarioRol objUsuarioRol = new UsuarioRol();
						objUsuarioRol.setIdrol_urf(iterator.next().getIdrol_urf());
						
						objTablaRol = objRolDao.getbyId(objUsuarioRol.getIdrol_urf());
						Iterator<Rol> iter = objTablaRol.iterator();
						
						while(iter.hasNext()) {
							Roles.add(iter.next().getRolname_rf());						
						}
					}	
					objUsuario.setRol(Roles);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConection.close();
		}
		System.out.println(16);
		return objUsuario;
	}
	

}
