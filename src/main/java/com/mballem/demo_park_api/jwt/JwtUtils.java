package com.mballem.demo_park_api.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789-0123456789";
    public static final long EXPIRATION_DAYS = 0;
    public static final long EXPIRATION_HOURS = 0;
    public static final long EXPIRATION_MINUTES = 2;

    private JwtUtils() {
    }

    private static Key generateKey () {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start) {

        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRATION_DAYS).plusHours(EXPIRATION_HOURS).plusMinutes(EXPIRATION_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String userName, String role) {
        Date issuedAt = new Date();
        Date limit = toExpireDate(issuedAt);

        String token = Jwts.builder()
                .setHeaderParam("typ","JWT")
                        .setSubject(userName)
                        .setIssuedAt(issuedAt)
                        .setExpiration(limit)
                        .claim("role", role)
                        .signWith(generateKey(), SignatureAlgorithm.HS256)
                        .compact();

                return new JwtToken(token);
    }

    private static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(generateKey()).build()
                    .parseClaimsJws(refactorToken(token)).getBody();
        } catch (JwtException ex) {
            log.error(String.format("Token invalido %s ", ex.getMessage()));
        }
        return null;
    }

    public static String getUserNameFromToken(String token) {
            return getClaimsFromToken(token).getSubject();
        }

        public static boolean isValidToken(String token) {
            try {
                 Jwts.parserBuilder()
                        .setSigningKey(generateKey()).build()
                        .parseClaimsJws(refactorToken(token));
            } catch (JwtException ex) {
                log.error(String.format("Token invalido %s ", ex.getMessage()));
            }
            return false;
        }

    public static String refactorToken(String token) {
        if(token.contains(JWT_BEARER)){
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
}
