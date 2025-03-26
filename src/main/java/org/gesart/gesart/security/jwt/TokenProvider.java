package org.gesart.gesart.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author :  <A>BRAHIMA TRAORE</A>
 * @version : 1.0
 * Copyright (c) 2024.
 * @since : 2024/12/15 à 00:16
 */

@Data
@Component
@Slf4j
@SuppressWarnings("ALL")
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    @Value("${jwt.security.jwt-token}")
    private String jwtSecret;
    @Value("${jwt.security.jwt-expiraton-ms}")
    private int jwtExpirationMs;
    @Value("${jwt.security.token-validity-in-seconds-for-remember-me}")
    private int tokenValidityForRememberMe;
    private SecretKey key;


    private SecretKey getAuthoritiesKey() {
        this.jwtExpirationMs *= 1000;
        this.tokenValidityForRememberMe *= 1000;
        var decodedKey = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(decodedKey));
        return key;
    }

    /**
     * Generate a token from user info.
     *
     * @param userDetails
     * @return token
     */
    public String generateJwtToken(final UserDetails userDetails) {
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = System.currentTimeMillis();

        Date validity = new Date(now + this.jwtExpirationMs * 1000);

        return Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    /**
     * Validation du token.
     *
     * @param token
     * @param userDetails
     * @return boolean
     */
    public boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extraction du username.
     *
     * @param token
     * @return String
     */
    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Expiration.
     *
     * @param token
     * @return Date
     */
    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extraction.
     *
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return T
     */
    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Claims.
     *
     * @param token
     * @return Claims
     */
    private Claims extractAllClaims(final String token) {
        // return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    /**
     * check token.
     *
     * @param token
     * @return boolean
     */
    public boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }
}
