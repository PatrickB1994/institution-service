package com.mdsl.institutionservice.security;

import com.mdsl.institutionservice.exception.AccessDeniedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtHelper
{

	private static final String SECRET = "ThisIsASecretKeyForTestingOnlyThisIsASecretKeyForTestingOnly";
	static SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
	static byte[] keyBytes = SECRET_KEY.getEncoded();
	private static final int TOKEN_TTL = 3;
	private static final int REFRESH_TOKEN_TTL = 120;

	private static SecretKey getSignInKey()
	{
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public static String generateToken(String name)
	{
		var now = Instant.now();
		return Jwts.builder()
				   .subject(name)
				   .issuedAt(Date.from(now))
				   .expiration(Date.from(now.plus(TOKEN_TTL, ChronoUnit.MINUTES)))
				   .signWith(getSignInKey())
				   .compact();
	}

	public static String generateRefreshToken()
	{
		return Jwts.builder().expiration(Date.from(Instant.now().plus(REFRESH_TOKEN_TTL, ChronoUnit.MINUTES))).signWith(getSignInKey()).compact();
	}

	public static String extractUsername(String token)
	{
		return getTokenBody(token).getSubject();
	}

	public static Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private static Claims getTokenBody(String token)
	{
		try
		{
			return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
		}
		catch(SignatureException | ExpiredJwtException e)
		{ // Invalid signature or expired token
			throw new AccessDeniedException("Access denied: " + e.getMessage());
		}
	}

	private static boolean isTokenExpired(String token)
	{
		Claims claims = getTokenBody(token);
		return claims.getExpiration().before(new Date());
	}
}
