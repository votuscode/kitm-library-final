package com.kitm.library.backend.spring.web.config.jwt;

import com.kitm.library.backend.domain.user.UserEntity;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.issuer}")
  private String jwtIssuer;

  @Value("${jwt.expiration}")
  private String jwtExpiration;

  public String generateAccessToken(UserEntity userEntity) {

    return Jwts.builder()
        .setSubject(String.format("%s,%s", userEntity.getId(), userEntity.getUsername()))
        .setIssuer(jwtIssuer)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserId(String token) {

    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject().split(",")[0];
  }

  public String getUsername(String token) {

    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject().split(",")[1];
  }

  public Date getExpirationDate(String token) {

    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getExpiration();
  }

  public boolean validate(String token) {

    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature - {}", ex.getMessage());
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token - {}", ex.getMessage());
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token - {}", ex.getMessage());
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token - {}", ex.getMessage());
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty - {}", ex.getMessage());
    }

    return false;
  }
}
