package es.dsw.models;

import java.util.Date;

import es.dsw.daos.GenerosDao;
import es.dsw.daos.PaisesDao;

public class Pelicula {
	
	private Integer idfilm_rf;
	private String title_rf;
	private String synopsis_rf;
	private Integer id_genero_rf;
	private String director_rf;
	private String reparto_rf;
	private Integer anio_rf;
	private Date datepremiere_rf;
	private Integer id_producer_rf;
	private Integer id_pais_rf;
	private Integer s_iduser_cf;
	
	//atributos de utilidad
	private String genero = "";
	private String pais = "";
	
	
	public Pelicula() {
		
	}


	public Pelicula(Integer idfilm_rf, String title_rf, String synopsis_rf, Integer id_genero_rf, String director_rf,
			String reparto_rf, Integer anio_rf, Date datepremiere_rf, Integer idproducer_rf, Integer id_pais_rf, Integer s_iduser_cf) {
		this.idfilm_rf = idfilm_rf;
		this.title_rf = title_rf;
		this.synopsis_rf = synopsis_rf;
		this.id_genero_rf = id_genero_rf;
		this.director_rf = director_rf;
		this.reparto_rf = reparto_rf;
		this.anio_rf = anio_rf;
		this.datepremiere_rf = datepremiere_rf;
		this.id_producer_rf = idproducer_rf;
		this.id_pais_rf = id_pais_rf;
		this.s_iduser_cf = s_iduser_cf;
		
		
	}


	public Integer getIdfilm_rf() {
		return idfilm_rf;
	}


	public void setIdfilm_rf(Integer idfilm_rf) {
		this.idfilm_rf = idfilm_rf;
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
	

	public Integer getS_iduser_cf() {
		return s_iduser_cf;
	}


	public void setS_iduser_cf(Integer s_iduser_cf) {
		this.s_iduser_cf = s_iduser_cf;
	}
	

	public String getGenero() {
		GenerosDao generoDao = new GenerosDao();
		this.genero = generoDao.getNombreGeneroByIdGenero(id_genero_rf);
		return genero;
	}

	public String getPais() {
		PaisesDao paisDao = new PaisesDao();
		this.pais = paisDao.getNombrePaisByIdPais(id_pais_rf);
		return pais;
	}


	@Override
	public String toString() {
		return "idfilm_rf=" + idfilm_rf + "\n title_rf=" + title_rf + "\n synopsis_rf=" + synopsis_rf
				+ "\n id_genero_rf=" + id_genero_rf + "\n director_rf=" + director_rf + "\n reparto_rf=" + reparto_rf
				+ "\n anio_rf=" + anio_rf + "\n datepremiere_rf=" + datepremiere_rf + "\n id_producer_rf=" + id_producer_rf
				+ "\n id_pais_rf=" + id_pais_rf;
	}
	
	
	

}
