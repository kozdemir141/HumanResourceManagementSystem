package kodlamaio.hrms.core.utilities.security.encryption;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.Data;

@Data
public class Token {

	private String token;
	
	private Algorithm algorithm;
	
	private DecodedJWT decodedJWT;
}
