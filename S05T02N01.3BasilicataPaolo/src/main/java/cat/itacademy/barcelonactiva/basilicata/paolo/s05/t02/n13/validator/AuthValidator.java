package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.exception.Apiunauthorized;


@Component
public class AuthValidator {
	
	private static final String CLIENT_CREDENTIAL_STRING = "client_credentials";
	
	public void validate(MultiValueMap<String,String> paramMap,String grantType) throws Apiunauthorized {
		
		if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIAL_STRING)) {
				message("El campo grant_type es invalido");
		}
		if(Objects.isNull(paramMap) || 
				paramMap.getFirst("client_id").isEmpty() || 
				paramMap.getFirst("client_secret").isEmpty()) {

				message("client_id o client_secret es invalido");
			
		}
		
	}
	private void message(String message) throws Apiunauthorized{
		throw new Apiunauthorized(message);
		
	}
}
