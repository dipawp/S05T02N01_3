package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Partida;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.repository.PartidaRepository;
import jakarta.transaction.Transactional;

@Service
public class PartidaServiceImpl implements PartidaService{

	@Autowired
	PartidaRepository partidaRepository;
	
	@Override
	public Partida addPartida(Partida partida) {
		// TODO Auto-generated method stub
		
		return partidaRepository.save(partida);
	}

	@Override
	public void deleteAllById(long id) {
		// TODO Auto-generated method stub
		//partidaRepository.deleteAllByJugadorId((int)id);
		
	}

	@Override
	public List<Partida> findAllByJugadorId(long id) {
		// TODO Auto-generated method stub
		
		return partidaRepository.findAllByJugadorId(id);
	}

	@Override
	@Transactional
	public void deleteAllByJugadorId(int id) {
		// TODO Auto-generated method stub
		partidaRepository.deleteByJugadorId(id);
	}

}