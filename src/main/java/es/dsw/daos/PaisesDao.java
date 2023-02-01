package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Distribuidor;
import es.dsw.models.Pais;

public class PaisesDao {
	
	@SuppressWarnings("unused")
	private boolean flagError;
	@SuppressWarnings("unused")
	private String msgError;
	
	
	public PaisesDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	public ArrayList<Pais> getAll() {

		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Pais> objTablaPais = new ArrayList<Pais>();

		try {
			objConection.open();

			if (!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM country_film");
				while (Result.next()) {
					Pais objPais = new Pais();
					objPais.setIdcountry_cf(Result.getInt("IDCOUNTRY_CF"));
					objPais.setCountry_cf(Result.getString("COUNTRY_CF"));
					objTablaPais.add(objPais);
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
		return objTablaPais;
	}
	
	public String getNombrePaisByIdPais(Integer id) {
		MySqlConnection objConection = new MySqlConnection();
		try {
			objConection.open();

			if (!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT COUNTRY_CF FROM country_film WHERE IDCOUNTRY_CF = " + id);
				while (Result.next()) {
					return Result.getString("COUNTRY_CF");
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
