package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.security;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.util.GsonUtils;
import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;

@Component
public class JwtIO {
	
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.token.secret}")
	private String SECRET;
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.timezone:UTC}")
	private String TIMEZONE;
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.token.expires-in:720000}")
	private int EXPIRES_IN;
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.issuer:none}")
	private String ISSUER;
	
	public String generateToken(Object src) {
		
		String subject = GsonUtils.serialize(src);
		
		Signer signer = HMACSigner.newSHA256Signer(SECRET);
		TimeZone tz = TimeZone.getTimeZone(TIMEZONE);
		
		ZonedDateTime zdt = ZonedDateTime.now(tz.toZoneId()).plusSeconds(EXPIRES_IN);
		JWT jwt = new JWT()
				.setIssuer(ISSUER)
				.setIssuedAt(ZonedDateTime.now(tz.toZoneId()))
				.setSubject(subject)
				.setExpiration(zdt);
		
		return JWT.getEncoder().encode(jwt, signer);
	}
	
	public boolean validateToken(String encodedJWT) {
		JWT jwt = jwt(encodedJWT);
		return jwt.isExpired();
	}
	public String getPayload(String encodedJWT) {
		JWT jwt = jwt(encodedJWT);
		
		return jwt.subject;
		
	}
	private JWT jwt(String encodedJWT) {
		JWT jwt = JWTUtils.decodePayload(encodedJWT);
		return jwt;	
	}

}
