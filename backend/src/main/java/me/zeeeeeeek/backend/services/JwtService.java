package me.zeeeeeeek.backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @NonNull
    private static final String SECRETKEY = System.getenv("SECRET");

    public String extractUsername(String jwtToken) {
        return extractClaim(
                jwtToken,
                Claims::getSubject
        );
    }

    public Date extractExpirationDate(String jwtToken) {
        return extractClaim(
                jwtToken,
                Claims::getExpiration
        );
    }

    public Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver
                .apply(claims);
    }

    public String generateJwtTokenWithCustomClaims(
            Map<String, Object> claims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateJwtTokenWithoutExtraClaims(UserDetails userDetails) {
        return generateJwtTokenWithCustomClaims(
                Map.of(),
                userDetails);
    }

    /**
     * Check if the token is valid.
     * If the token is valid, then the user is authenticated.
     * If the user is authenticated, will not be authenticated again.
     * @param jwtToken the token to be checked
     * @param userDetails the user details
     * @return true if the token is valid, false otherwise
     */
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            return true;
        }
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpirationDate(jwtToken).before(new Date());
    }

    private Key getSigningKey() {
        byte[] secretKeyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}
