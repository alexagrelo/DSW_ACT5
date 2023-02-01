package es.dsw.models;

public class Pais {
	
	private int idcountry_cf;
	private String country_cf;
	
	public Pais() {
	}

	public Pais(int idcountry_cf, String country_cf) {
		this.idcountry_cf = idcountry_cf;
		this.country_cf = country_cf;
	}

	public int getIdcountry_cf() {
		return idcountry_cf;
	}

	public void setIdcountry_cf(int idcountry_cf) {
		this.idcountry_cf = idcountry_cf;
	}

	public String getCountry_cf() {
		return country_cf;
	}

	public void setCountry_cf(String country_cf) {
		this.country_cf = country_cf;
	}

}
