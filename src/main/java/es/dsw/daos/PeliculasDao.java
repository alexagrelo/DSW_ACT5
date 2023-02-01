package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Pelicula;

public class PeliculasDao {

	private boolean flagError;
	private String msgError;

	private String title_rf;
	private String synopsis_rf;
	private Integer id_genero_rf;
	private String director_rf;
	private String reparto_rf;
	private Integer anio_rf;
	private Date datepremiere_rf;
	private Integer id_producer_rf;
	private Integer id_pais_rf;

	public PeliculasDao() {
		this.flagError = false;
		this.msgError = "";
	}

	public boolean isFlagError() {
		return flagError;
	}

	public void setFlagError(boolean flagError) {
		this.flagError = flagError;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getTitle_rf() {
		return title_rf;
	}

	public void setTitle_rf(String title_rf) {
		this.title_rf = title_rf;
	}

	public String getSynopsis_rf() {
		return synopsis_rf;
	}

	public void setSynopsis_rf(String synopsis_rf) {
		this.synopsis_rf = synopsis_rf;
	}

	public Integer getId_genero_rf() {
		return id_genero_rf;
	}

	public void setId_genero_rf(Integer id_genero_rf) {
		this.id_genero_rf = id_genero_rf;
	}

	public String getDirector_rf() {
		return director_rf;
	}

	public void setDirector_rf(String director_rf) {
		this.director_rf = director_rf;
	}

	public String getReparto_rf() {
		return reparto_rf;
	}

	public void setReparto_rf(String reparto_rf) {
		this.reparto_rf = reparto_rf;
	}

	public Integer getAnio_rf() {
		return anio_rf;
	}

	public void setAnio_rf(Integer anio_rf) {
		this.anio_rf = anio_rf;
	}

	public Date getDatepremiere_rf() {
		return datepremiere_rf;
	}

	public void setDatepremiere_rf(Date datepremiere_rf) {
		this.datepremiere_rf = datepremiere_rf;
	}

	public Integer getId_producer_rf() {
		return id_producer_rf;
	}

	public void setId_producer_rf(Integer id_producer_rf) {
		this.id_producer_rf = id_producer_rf;
	}

	public Integer getId_pais_rf() {
		return id_pais_rf;
	}

	public void setId_pais_rf(Integer id_pais_rf) {
		this.id_pais_rf = id_pais_rf;
	}

	@Override
	public String toString() {
		return "PeliculasDao \n flagError=" + flagError + "\n msgError=" + msgError + "\n  title_rf=" + title_rf
				+ "\n  synopsis_rf=" + synopsis_rf + "\n  id_genero_rf=" + id_genero_rf + "\n  director_rf="
				+ director_rf + "\n  reparto_rf=" + reparto_rf + "\n  anio_rf=" + anio_rf + "\n  datepremiere_rf="
				+ datepremiere_rf + "\n  id_producer_rf=" + id_producer_rf + "\n  id_pais_rf=" + id_pais_rf;
	}

	

	public ArrayList<Pelicula> getAll() {
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Pelicula> objTablaPelicula = new ArrayList<>();

		try {
			objConection.open();

			if (!objConection.isError()) {
				String sql = "SELECT * FROM repository_film";
				ResultSet Result = objConection.executeSelect(sql);

				while (Result.next()) {
					Pelicula objPelicula = new Pelicula();
					objPelicula.setIdfilm_rf(Result.getInt("IDFILM_RF"));
					objPelicula.setTitle_rf(Result.getString("TITLE_RF"));
					objPelicula.setId_genero_rf(Result.getInt("IDGENRE_RF"));
					objPelicula.setDirector_rf(Result.getString("DIRECTOR_RF"));
					objPelicula.setReparto_rf(Result.getString("REPARTO_RF"));
					objPelicula.setAnio_rf(Result.getInt("ANIO_RF"));
					objPelicula.setDatepremiere_rf(Result.getDate("DATEPREMIERE_RF"));
					objPelicula.setId_producer_rf(Result.getInt("IDPRODUCER_RF"));
					objPelicula.setId_pais_rf(Result.getInt("IDCOUNTRY_RF"));

					objTablaPelicula.add(objPelicula);

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
		
		return objTablaPelicula;
	}

	
	
	public void setPelicula(Pelicula nuevaPelicula) {
		MySqlConnection objConection = new MySqlConnection();
		try {
			objConection.open();
			if(!objConection.isError()) {
				String premiereString = (nuevaPelicula.getDatepremiere_rf() == null) ? null + "," : "'" + nuevaPelicula.getDatepremiere_rf() + "',";
				
				String sql = "INSERT INTO repository_film "
						+ "(TITLE_RF,"
						+ "SYNOPSIS_RF,"
						+ "IDGENRE_RF,"
						+ "DIRECTOR_RF,"
						+ "REPARTO_RF,"
						+ "ANIO_RF,"
						+ "DATEPREMIERE_RF,"
						+ "IDPRODUCER_RF,"
						+ "IDCOUNTRY_RF,"
						+ "S_INSERTDATE_RF,"
						+ "S_UPDATEDATE_RF,"
						+ "S_IDUSER_CF)"
						
						+ "VALUES "
						+ "("
						+ "'" + nuevaPelicula.getTitle_rf() +"',"
						+ "'" + nuevaPelicula.getSynopsis_rf() +"',"
						+ "'" + nuevaPelicula.getId_genero_rf() +"',"
						+ "'" + nuevaPelicula.getDirector_rf() +"',"
						+ "'" + nuevaPelicula.getReparto_rf() +"',"
						+ "'" + nuevaPelicula.getAnio_rf() +"',"
						+ premiereString
						+ "'" + nuevaPelicula.getId_producer_rf() +"',"
						+ "'" + nuevaPelicula.getId_pais_rf() +"',"
						+ "CURRENT_TIMESTAMP,"
						+ "CURRENT_TIMESTAMP,"
						+ "'" + nuevaPelicula.getS_iduser_cf() +"'"
						+")";
				System.out.println("sql insert nueva película = " + sql);
				objConection.executeInsert(sql);
				
			} else {
				this.flagError = true;
				this.msgError = "Error en setPelicula. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConection.msgError();
			}
		} catch (Exception e){
			this.flagError = true;
			this.msgError = "Error en setPelicula. +Info: " + e.getMessage();
			
		} finally {
			objConection.close();
			
		}
	}
	
	
	
	public void deleteById(Integer idPelicula) {
		
		MySqlConnection objConection = new MySqlConnection();
		
		try {
			  objConection.open();
			  if (!objConection.isError()){
				  
				  // Se borran las sesiones donde aparezca la película, para evitar el error (fk)
				  String sql = "DELETE FROM session_film WHERE idfilm_ssf = " + idPelicula;
				  objConection.executeUpdateOrDelete(sql);
				  // Si no hay error, se borra finalmente la película
				  sql = "DELETE FROM repository_film WHERE idfilm_rf = " + idPelicula;
				  objConection.executeUpdateOrDelete(sql);
			  } else {
				    this.flagError = true;
				    this.msgError = "Error en deleteAll. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConection.msgError();
			   }
		    } catch (Exception ex) {
			       this.flagError = true;
			       this.msgError = "Error en deleteById. +Info: " + ex.getMessage();
  	    } finally {
			       objConection.close();
  	    }
		
	}

}
