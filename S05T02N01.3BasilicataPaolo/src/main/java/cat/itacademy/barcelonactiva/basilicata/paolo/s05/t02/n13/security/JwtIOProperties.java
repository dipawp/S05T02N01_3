package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13")
public class JwtIOProperties {
	
	private Security security;
	private String timezone;
	private String issuer;
	private Token token;
	private Excluded excluded;
	
	@Data
	public static class Security{
		private boolean enabled;
	}
	
	@Data
	public static class Token{
		private Auth auth;
		private String secret;
		private int expiresIn;
	}
	
	@Data
	public static class Auth{
		private String path;
	}
	
	@Data
	private static class Excluded {
		private String path;
	}

}