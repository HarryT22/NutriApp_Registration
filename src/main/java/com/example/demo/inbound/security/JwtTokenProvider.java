package com.example.demo.inbound.security;

import com.example.demo.model.appuser.AppUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.RS256;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityTime;

    @Autowired
    private SigningKeyProvider keys;

    @Autowired
    private AppUserService appUserService;

    public String createJwt(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", role);

        Date now = new Date();
        io.jsonwebtoken.SignatureAlgorithm signatureAlgorithm =RS256 ;
        Date validity = new Date(now.getTime() + validityTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity).signWith(signatureAlgorithm,keys.getPrivateKey())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = appUserService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(keys.getPublicKey()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValidJWT(String token) {
        try {
            Jwts.parser().setSigningKey(keys.getPublicKey()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new IllegalStateException("JWT invalid");
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
