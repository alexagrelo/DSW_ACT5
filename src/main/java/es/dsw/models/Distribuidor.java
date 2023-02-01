package es.dsw.models;

public class Distribuidor {
	
	private Integer idproducer_pf;
	private String producer_pf;
	
	public Distribuidor() {
	}

	public Distribuidor(Integer idproducer_pf, String producer_pf) {
		this.idproducer_pf = idproducer_pf;
		this.producer_pf = producer_pf;
	}

	public int getIdproducer_pf() {
		return idproducer_pf;
	}

	public void setIdproducer_pf(Integer idproducer_pf) {
		this.idproducer_pf = idproducer_pf;
	}

	public String getProducer_pf() {
		return producer_pf;
	}

	public void setProducer_pf(String producer_pf) {
		this.producer_pf = producer_pf;
	}

}
