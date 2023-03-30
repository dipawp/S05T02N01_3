package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Apiunauthorized extends Exception{

	private static final long serialVersionUID = 1L;

	public Apiunauthorized(String message) {
		super(message);
	}
}