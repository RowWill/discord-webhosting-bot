package uk.co.corasoftware.util.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenEncoder {

	private static final Logger LOG = LoggerFactory.getLogger(JwtTokenEncoder.class);

	private JwtTokenEncoder() {
	}

	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(String.valueOf(nowMillis));
		final Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// @formatter:off
		JwtBuilder builder = Jwts.builder()
						.setId(id)
						.setIssuedAt(now)
						.setSubject(subject)
						.setIssuer(issuer)
						.signWith(signatureAlgorithm, signingKey);
		// @formatter:on

		if (ttlMillis > 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		String compact = builder.compact(); //TODO update to debug
		LOG.info("Dev token - {}", compact);
		return compact;
	}
}
