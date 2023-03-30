package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Jugador;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Partida;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services.JugadorServiceImpl;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services.PartidaDausImpl;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services.PartidaServiceImpl;


@RestController
public class PartidaController {
	
	@Autowired
	JugadorServiceImpl jugadorServiceImpl;

	@Autowired
	PartidaServiceImpl partidaServiceImpl;
	
	@Autowired
	PartidaDausImpl partidaDausImpl;
	
	
	@DeleteMapping(path = "/players/{id}")
	public ResponseEntity<Partida> tiraDaus(@PathVariable("id") long id){
		try {
			partidaServiceImpl.deleteAllByJugadorId((int)id);
			return new ResponseEntity<>(null,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}
	
	
	@PostMapping(path = "/players/{id}/games")
	public ResponseEntity<Partida> getPartidas(@PathVariable("id") long id){
		
		Partida partida = partidaDausImpl.llencarDaus();
		List<Partida> part = null;
		Jugador jugador = new Jugador();
		try {
			Optional<Jugador> jugadorData = jugadorServiceImpl.getJugadorById(id);
			if(jugadorData.isPresent()) {
			   
			   jugador.setId(jugadorData.get().getId());
			   jugador.setNom(jugadorData.get().getNom());
			   jugador.setDataRegistre(jugadorData.get().getDataRegistre());
			   part = partidaServiceImpl.findAllByJugadorId(jugador.getId());
			   //jugador.setPartidas(part);
			   
			   //jugador.setPartidas()
			   partida.setJugador(jugador);
			   partidaServiceImpl.addPartida(partida);
			   
			  // part = partidaServiceImpl.findAllByJugadorId(jugadorData.get().getId());
			   return new ResponseEntity<>(partida,HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
				//return new ResponseEntity<Partida>(partida);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(path = "/players")
	public ResponseEntity<List<JugadorDTO>> getPlayersStatistics(){
		List<JugadorDTO> jugadorsDTO = new ArrayList<>();
		List<Jugador> jugadors = jugadorServiceImpl.getAll();
		if(jugadors.size() > 0) {
			for(Jugador jugador: jugadors) {
				List<Partida> partidas = partidaServiceImpl.findAllByJugadorId(jugador.getId());
				int win = 0;
				for(Partida partida: partidas) {
					if(partida.isGuanyada()) {
						win += 1;
					}
				}
				JugadorDTO jugadorDTO = new JugadorDTO(jugador.getNom(), ((float)(partidas.size() * win) / 100));
				jugadorsDTO.add(jugadorDTO);
			}
		}
		
		
		return new ResponseEntity<List<JugadorDTO>>(jugadorsDTO,HttpStatus.OK);
	}
}




