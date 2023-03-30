package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String uid;
	private String  name;
	private String lastName;
	private String role;
	private String country;
	
	

}
