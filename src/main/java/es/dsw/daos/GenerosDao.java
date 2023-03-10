package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Genero;

public class GenerosDao {

	private boolean flagError;
	private String msgError;

	public GenerosDao() {
		this.flagError = false;
		this.msgError = "";
	}

	public ArrayList<Genero> getAll() {

		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Genero> objTablaGenero = new ArrayList<Genero>();

		try {
			objConection.open();

			if (!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM genre_film");
				while (Result.next()) {
					Genero objGenero = new Genero();
					objGenero.setIdgenre_gf(Result.getInt("IDGENRE_GF"));
					objGenero.setGenre_gf(Result.getString("GENRE_GF"));
					objTablaGenero.add(objGenero);
				}
			} else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMysql informa error al abrir conexión. +Info: "
						+ objConection.msgError();
			}
		} catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		} finally {
			objConection.close();
		}
		return objTablaGenero;
	}
	
	
	public String getNombreGeneroByIdGenero(Integer id) {
		MySqlConnection objConection = new MySqlConnection();
		try {
			objConection.open();

			if (!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT GENRE_GF FROM genre_film WHERE IDGENRE_GF = " + id);
				while (Result.next()) {
					String genero = Result.getString("GENRE_GF");
					return genero;
				}
			} else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMysql informa error al abrir conexión. +Info: "
						+ objConection.msgError();
			}
		} catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		} finally {
			objConection.close();
		}
		return null;
	}

}
