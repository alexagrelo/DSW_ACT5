package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Distribuidor;

public class DistribuidoresDao {
	
	private boolean flagError;
	private String msgError;
	
	
	public DistribuidoresDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	public ArrayList<Distribuidor> getAll() {

		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Distribuidor> objTablaDistribuidor = new ArrayList<Distribuidor>();

		try {
			objConection.open();

			if (!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM producer_film");
				while (Result.next()) {
					Distribuidor objDistribuidor = new Distribuidor();
					objDistribuidor.setIdproducer_pf(Result.getInt("IDPRODUCER_PF"));
					objDistribuidor.setProducer_pf(Result.getString("PRODUCER_PF"));
					objTablaDistribuidor.add(objDistribuidor);
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
		return objTablaDistribuidor;
	}
	

}
