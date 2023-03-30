package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Jugador;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Partida;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services.JugadorServiceImpl;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services.PartidaServiceImpl;


@RestController
public class JugadorController {
	
	@Autowired
	JugadorServiceImpl jugadorServiceImpl;
	
	@Autowired
	PartidaServiceImpl partidaServiceImpl;
	
	@PostMapping(path = "/players")
	public ResponseEntity<Jugador> addJugador(@RequestBody Jugador jugador){
		try {
		 Jugador jug = jugadorServiceImpl.addJugador(jugador);
		 return new ResponseEntity<>(jug,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(path = "/players")
	public ResponseEntity<Jugador> updateJugador(@RequestBody Jugador jugador){
		try {
			Optional<Jugador> jugOptional = jugadorServiceImpl.getJugadorById(jugador.getId());
			if(jugOptional.isPresent()) {
				Jugador jug = new Jugador(jugOptional.get().getId(),jugador.getNom(), jugOptional.get().getDataRegistre());
				jugadorServiceImpl.addJugador(jug);
				return new ResponseEntity<>(jug,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@GetMapping(path = "/players/{id}/games")
	public ResponseEntity<Jugador> llistaPartidas(@PathVariable("id") long id){
		List<Partida> part = null;
		Jugador jugador = new Jugador();
		try {
			Optional<Jugador> jugadorData = jugadorServiceImpl.getJugadorById(id);
			if(jugadorData.isPresent()) {
			   jugador.setId(jugadorData.get().getId());
			   jugador.setNom(jugadorData.get().getNom());
			   jugador.setDataRegistre(jugadorData.get().getDataRegistre());
			   part = partidaServiceImpl.findAllByJugadorId(jugador.getId());
			   jugador.setPartidas(part);
			   return new ResponseEntity<>(jugador,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/players/ranking")
	public ResponseEntity<String> getMidPlayersRanking(){
		List<Jugador> jugadors = jugadorServiceImpl.getAll();
		float ranking = 0f;
		if(jugadors.size() > 0) {
			for(Jugador jugador: jugadors) {
				List<Partida> partidas = partidaServiceImpl.findAllByJugadorId(jugador.getId());
				int win = 0;
				for(Partida partida: partidas) {
					if(partida.isGuanyada()) {
						win += 1;
					}
				}
				ranking += ((float)(partidas.size() * win) / 100);
			}
		}
		return new ResponseEntity<String>("El ranking mig es: " +  Float.toString(((float)ranking) / jugadors.size()) + "%",HttpStatus.OK);
	}
	
	@GetMapping(path = "/players/ranking/loser")
	public ResponseEntity<JugadorDTO> getLoser(){
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
			Collections.sort(jugadorsDTO, new Comparator<JugadorDTO>() {
				@Override
				public int compare(JugadorDTO o1, JugadorDTO o2) {
					// TODO Auto-generated method stub
					return Float.compare(o1.getExit(), o1.getExit());
				}
			});
			return new ResponseEntity<>(jugadorsDTO.get(0),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@GetMapping(path = "/players/ranking/winner")
	public ResponseEntity<JugadorDTO> getWinner(){
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
			Collections.sort(jugadorsDTO, new Comparator<JugadorDTO>() {
				@Override
				public int compare(JugadorDTO o1, JugadorDTO o2) {
					// TODO Auto-generated method stub
					return Float.compare(o2.getExit(), o1.getExit());
				}
			});
			return new ResponseEntity<>(jugadorsDTO.get(0),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		
	}
	

}
