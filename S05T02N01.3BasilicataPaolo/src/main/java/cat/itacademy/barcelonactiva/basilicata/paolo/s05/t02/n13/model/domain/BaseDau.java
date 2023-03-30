package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.domain;

import java.util.Random;

public abstract class BaseDau {
	
	private int puntuacio;

	public int getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(int puntuacio) {
		this.puntuacio = puntuacio;
	}
	
	
	public int llencarDau() {
		Random rnd = new Random();
		int result = rnd.nextInt(6) + 1;
		this.puntuacio = result;
		return result;
	}

}
