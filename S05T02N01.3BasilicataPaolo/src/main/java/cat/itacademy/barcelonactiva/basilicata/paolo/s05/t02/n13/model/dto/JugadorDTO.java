package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.dto;

public class JugadorDTO {
	
	private String nom;
	private float exit;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getExit() {
		return exit;
	}
	public void setExit(float exit) {
		this.exit = exit;
	}
	public JugadorDTO(String nom, float exit) {
		super();
		this.nom = nom;
		this.exit = exit;
	}
	
	

}
