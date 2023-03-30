package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Jugadors")
public class Jugador implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "nom_jugador", length = 20,nullable = false)
	private String nom;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registre", nullable = false)
	private Date dataRegistre;
	
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "jugador", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	//@JsonIgnoreProperties("id")
	private List<Partida> partidas = new ArrayList<>();
	
	
	
	
	public List<Partida> getPartidas() {
		return partidas;
	}
	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public Date getDataRegistre() {
		return dataRegistre;
	}
	public void setDataRegistre(Date dataRegistre) {
		this.dataRegistre = dataRegistre;
	}
	public Jugador() {
	}
	public Jugador(long id, String nom, Date dataRegistre) {
		this.id = id;
		this.nom = nom;
		this.dataRegistre = dataRegistre;
	}

	@PrePersist
	private void onCreate() {
		this.dataRegistre = new Date();
	}
	
	
	

}
