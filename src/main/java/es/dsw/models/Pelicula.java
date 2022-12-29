package es.dsw.models;

import java.util.Date;

public class Pelicula {
	
	private String idfilm_rf;
	private String title_rf;
	private String synopsis_rf;
	private String genero;
	private String director_rf;
	private String reparto_rf;
	private String anio_rf;
	private Date datepremiere_rf;
	private String distribuidor;
	private Pais pais;
	
	
	public Pelicula() {
		
	}


	public Pelicula(String idfilm_rf, String title_rf, String synopsis_rf, String genero, String director_rf,
			String reparto_rf, String anio_rf, Date datepremiere_rf, String distribuidor, Pais pais) {
		this.idfilm_rf = idfilm_rf;
		this.title_rf = title_rf;
		this.synopsis_rf = synopsis_rf;
		this.genero = genero;
		this.director_rf = director_rf;
		this.reparto_rf = reparto_rf;
		this.anio_rf = anio_rf;
		this.datepremiere_rf = datepremiere_rf;
		this.distribuidor = distribuidor;
		this.pais = pais;
	}


	public String getIdfilm_rf() {
		return idfilm_rf;
	}


	public void setIdfilm_rf(String idfilm_rf) {
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


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
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


	public String getAnio_rf() {
		return anio_rf;
	}


	public void setAnio_rf(String anio_rf) {
		this.anio_rf = anio_rf;
	}


	public Date getDatepremiere_rf() {
		return datepremiere_rf;
	}


	public void setDatepremiere_rf(Date datepremiere_rf) {
		this.datepremiere_rf = datepremiere_rf;
	}


	public String getDistribuidor() {
		return distribuidor;
	}


	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}


	public Pais getPais() {
		return pais;
	}


	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
	

}
