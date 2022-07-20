package com.transkript.reportcard.business.util;

import com.transkript.reportcard.config.model.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Random;

@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.token.prefix}") private String tokenPrefix;
    @Value("${jwt.secret.expiration}") private Long expiration;

    private final SecretKey secretKey;

    @Autowired
    public JwtUtil() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public JwtToken generateToken(String subject) {
        String tokenId = String.valueOf(new Random().nextInt());
        long creationTimeMillis = System.currentTimeMillis();
        String token = Jwts.builder().setId(tokenId)
                .setSubject(subject)
                .setIssuer("transkript.com")
                .setAudience("reportcard.transkript.com")
                .setIssuedAt(new Date(creationTimeMillis))
                .setExpiration(new Date(creationTimeMillis + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                // TODO use non-deprecated signature algorithm
                .compact();
        var claims = getClaims(token);
        return new JwtToken(token, claims);
    }

    public Claims getClaims(String token) {
        if (token == null || token.equals("")) {
            return null;
        }
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token).getBody();
    }

    public String addPrefixToken(String token) {
        return tokenPrefix +  " " + token;
    }

    public String removeTokenPrefix(String token) {
        return token.replace(tokenPrefix + " ", "");
    }
}

