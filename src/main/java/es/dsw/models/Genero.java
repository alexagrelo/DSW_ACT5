package es.dsw.models;

public class Genero {
	
	private int idgenre_gf;
	private String genre_gf;
	
	public Genero() {
	}

	public Genero(int idgenre_gf, String genre_gf) {
		this.idgenre_gf = idgenre_gf;
		this.genre_gf = genre_gf;
	}

	public int getIdgenre_gf() {
		return idgenre_gf;
	}

	public void setIdgenre_gf(int idgenre_gf) {
		this.idgenre_gf = idgenre_gf;
	}

	public String getGenre_gf() {
		return genre_gf;
	}

	public void setGenre_gf(String genre_gf) {
		this.genre_gf = genre_gf;
	}

}
