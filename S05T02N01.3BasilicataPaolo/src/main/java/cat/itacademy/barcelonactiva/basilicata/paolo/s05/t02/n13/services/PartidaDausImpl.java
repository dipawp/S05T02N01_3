package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Dau;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain.Partida;


@Service
public class PartidaDausImpl implements PartidaDaus{
	
	private Dau dau1;
	public Dau getDau1() {
		return dau1;
	}

	public void setDau1(Dau dau1) {
		this.dau1 = dau1;
	}

	public Dau getDau2() {
		return dau2;
	}

	public void setDau2(Dau dau2) {
		this.dau2 = dau2;
	}

	public int getPuntuacioTotal() {
		return puntuacioTotal;
	}

	public void setPuntuacioTotal(int puntuacioTotal) {
		this.puntuacioTotal = puntuacioTotal;
	}

	public boolean isGuanyada() {
		return guanyada;
	}

	public void setGuanyada(boolean guanyada) {
		this.guanyada = guanyada;
	}

	private Dau dau2;
	
	private int puntuacioTotal;
	private boolean guanyada;
	
	

	public PartidaDausImpl() {
		super();
		
		this.dau1 = new Dau();
		this.dau2 = new Dau();
	}

	@Override
	public Partida llencarDaus() {
		// TODO Auto-generated method stub
		
		Partida partida = new Partida();
		
		
		int result = this.dau1.llencarDau() + this.dau2.llencarDau();
		this.setPuntuacioTotal(result);
		if(this.puntuacioTotal >= 7) {
			this.setGuanyada(true);
		}
		else {
			this.setGuanyada(false);
		}
		
		partida.setDau1(this.dau1.getPuntuacio());
		partida.setDau2(this.dau2.getPuntuacio());
		partida.setGuanyada(this.isGuanyada());
		
		return partida;
	}
	

}
