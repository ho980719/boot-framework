package com.ho.bootframework.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

  private final String SECRET_KEY = "YourSecretKeyHere";
  private final long VALIDITY_MS = 1000 * 60 * 60; // 1시간
  private final long REFRESH_VALIDITY_MS = 1000L * 60 * 60 * 24 * 7; // 7일

  public String generateAccessToken(String subject) {
    return Jwts.builder()
      .setSubject(subject)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_MS))
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public String generateRefreshToken(String subject) {
    return Jwts.builder()
      .setSubject(subject)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + REFRESH_VALIDITY_MS))
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  public boolean validateRefreshToken(String token) {
    return validateToken(token);
  }

  public String getSubject(String token) {
    return Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public String getUserId(String token) {
    return Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

}