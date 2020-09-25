package uk.co.corasoftware.security.jwt.util;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtTokenDecoder {

	public static boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("TEST_KEY")).parseClaimsJws(token)
					.getBody();
		} catch (SignatureException ex) {
			return false;
		}
		return true;
	}

	public static Claims decodeJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("TEST_KEY"))
				.parseClaimsJws(token).getBody();
		return claims;
	}
}
