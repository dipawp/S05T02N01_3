package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse {
	@JsonProperty(value = "token_type")
	private String tokenType;
	@JsonProperty(value = "access_token")
	private String accessToken;
	@JsonProperty(value = "expires_in")
	private int expiresIn;
	@JsonProperty(value = "issued_at")
	private String issuedAt;
	@JsonProperty(value = "client_id")
	private String clientId;
	

}
