package com.example.jwt;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

  // Secret Key is used to encrypt and decrypt the JWT
  private static final String SECRET_KEY = "68e656b251e67e8358bef8483ab0d51c6619f3e7a1a9f0e75838d41ff368f728";

  public String issueJwtToken(String subject) {
    return issueJwtToken(subject, Map.of());
  }

  public String issueJwtToken(String subject, String... scopes) {
    // Scopes represent the Roles of the User
    return issueJwtToken(subject, Map.of("scopes", scopes));
  }

  public String issueJwtToken(String subject, Map<String, Object> claims) {
    return Jwts.builder()
        // setClaims has to be before setSubject, otherwise it will wipe out the Subject
        .setClaims(claims)
        .setSubject(subject)
        .setIssuer("https://url.com")
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plus(14, ChronoUnit.DAYS)))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String getSubject(String jwt) {
    return getClaims(jwt).getSubject();
  }

  public boolean isJwtValid(String jwt, String username) {
    return getSubject(jwt).equals(username) && !isJwtExpired(jwt);
  }

  private boolean isJwtExpired(String jwt) {
    return getClaims(jwt).getExpiration().before(Date.from(Instant.now()));
  }

  private Claims getClaims(String jwt) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(jwt)
        .getBody();
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }
}
