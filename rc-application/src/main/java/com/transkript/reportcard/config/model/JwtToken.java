package com.transkript.reportcard.config.model;

import com.transkript.reportcard.exception.ReportCardException;
import com.transkript.reportcard.model.ReportCard;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Slf4j
public class JwtToken {
    @Getter @Setter private String value;
    private final Claims claims;

    public JwtToken(String value, Claims claims) {
        this.value = value;
        this.claims = claims;
    }

    public Date getExpirationDate() {
        return this.claims.getExpiration();
    }

    public String getSubject() {
        return this.claims.getSubject();
    }

    public boolean isExpired() {
        return getExpirationDate().before(new Date(System.currentTimeMillis()));
    }

    public boolean isValid(String username) {
        String subject = getSubject();
        return username.equals(subject) && !isExpired();
    }
}
