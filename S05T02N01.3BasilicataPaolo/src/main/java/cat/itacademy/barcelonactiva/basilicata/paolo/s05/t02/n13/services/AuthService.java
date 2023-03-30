package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.dto.JwtResponse;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.dto.UsuarioDTO;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.security.JwtIO;
import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.util.DateUtils;


@Service
public class AuthService {
	
	@Autowired
	private JwtIO jwtIO;
	
	@Autowired
	private DateUtils dateUtils;
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.token.expires-in}")
	private int EXPIRES_IN;
	public JwtResponse login(String clientId, String clientSecret) {
		
//		UUID uuid = UUID.randomUUID();
//		
//		UsuarioDTO user = UsuarioDTO.builder()
//				.name("")
//				.lastName("")
//				.role("")
//				.country("")
//				.uid(uuid.toString())
//				.build();
		
		JwtResponse jwt = JwtResponse.builder()
				.tokenType("bearer")
				.accessToken(jwtIO.generateToken("genera token para s05_t02_n01_3"))
				.issuedAt(dateUtils.getDateMillis()+"")
				.clientId(clientId)
				.expiresIn(EXPIRES_IN)
				.build();
		return jwt;
				
	}

}