package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Partidas")
public class Partida implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "dau_1")
	private int dau1;
	@Column(name = "dau_2")
	private int dau2;
	@Column(name = "guanyada")
	private Boolean guanyada;
	
	@JoinColumn(name = "jugador_id")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JsonBackReference
	private Jugador jugador;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDau1() {
		return dau1;
	}

	public void setDau1(int dau1) {
		this.dau1 = dau1;
	}

	public int getDau2() {
		return dau2;
	}

	public void setDau2(int dau2) {
		this.dau2 = dau2;
	}

	public boolean isGuanyada() {
		return guanyada;
	}

	public void setGuanyada(boolean guanyada) {
		this.guanyada = guanyada;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Partida() {
		super();
	}

	public Partida(long id, int dau1, int dau2, boolean guanyada, Jugador jugador) {
		super();
		this.id = id;
		this.dau1 = dau1;
		this.dau2 = dau2;
		this.guanyada = guanyada;
		this.jugador = jugador;
	}
	
	
	

}
