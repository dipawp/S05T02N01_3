package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Partida;


public interface PartidaRepository extends CrudRepository<Partida, Long>{

	List<Partida> findAllByJugadorId(long id);
	void deleteByJugadorId(int id);
}
