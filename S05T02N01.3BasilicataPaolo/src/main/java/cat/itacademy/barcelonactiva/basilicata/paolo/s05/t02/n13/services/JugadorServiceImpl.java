package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Jugador;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.repository.JugadorRepository;


@Service
public class JugadorServiceImpl implements JugadorService{

	@Autowired
	JugadorRepository jugadorRepository;
	
	
	@Override
	public Jugador addJugador(Jugador jugador) {
		// TODO Auto-generated method stub
		jugadorRepository.save(jugador);
		return jugador;
	}

	@Override
	public void updateJugador(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Jugador> getAll() {
		// TODO Auto-generated method stub
		return (List<Jugador>) jugadorRepository.findAll();
	}

	@Override
	public Optional<Jugador> getJugadorById(long id) {
		// TODO Auto-generated method stub
		Optional<Jugador> jugador = jugadorRepository.findById(id);
		return jugador;
	}

}

