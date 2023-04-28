package me.zeeeeeeek.backend.services.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    //todo use env variables
    private static final String SECRETKEY = "YnR2cHgxczFnbWpza205ZGxnbHppajd0MDlkNTM4YmlrZ3c3anhoZDhmemg1azNxenQyeHdvZHhybmk5aWJtYmlieTZmeWJrN3Fna2FpcWgxeGdkNXNsOTI0ejA5OWN1N3c5cGpmdjR6bG5qaWE4dHo5dDFid2Uwb2VvbzNveHI=";
    private static final Integer EXPIRATION = 1000 * 60 * 10; // 10 minutes

    public String extractUsername(String jwtToken) {
        return extractClaim(
                jwtToken,
                Claims::getSubject
        );
    }

    public String extractFirstName(String jwtToken) {
        return extractClaim(
                jwtToken,
                claims -> (String) claims.get("firstName")
        );
    }

    public String extractLastName(String jwtToken) {
        return extractClaim(
                jwtToken,
                claims -> (String) claims.get("lastName")
        );
    }

    public String extractEmail(String jwtToken) {
        return extractClaim(
                jwtToken,
                claims -> (String) claims.get("email")
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
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
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
     * @return true, if the token is valid, false otherwise
     */
    public boolean isTokenValid(String jwtToken) {
        final String username = extractUsername(jwtToken);
        return username!= null && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpirationDate(jwtToken).before(new Date());
    }

    private Key getSigningKey() {
        byte[] secretKeyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}
