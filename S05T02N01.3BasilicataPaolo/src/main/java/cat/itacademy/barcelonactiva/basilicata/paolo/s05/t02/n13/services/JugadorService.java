package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services;

import java.util.List;
import java.util.Optional;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Jugador;


public interface JugadorService {
	
	public Jugador addJugador(Jugador jugador);
	public void updateJugador(Jugador jugador);
	public Optional<Jugador> getJugadorById(long id);
	public List<Jugador> getAll();

}
