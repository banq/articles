package com.doublexman.common.auth;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.Map;

public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String secretKey;

    private long tokenValidityInSeconds;

    public TokenProvider(String secretKey, long tokenValidityInSeconds) {
        this.secretKey = secretKey;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }


    public String createToken(String subjectId, Map<String, String> subjectInfo) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + 1000*tokenValidityInSeconds);
        JwtBuilder jwtBuilder = Jwts.builder().setSubject(subjectId);
        for(Map.Entry<String, String> item : subjectInfo.entrySet()) {
            jwtBuilder = jwtBuilder.claim(item.getKey(), item.getValue());
        }
        return jwtBuilder
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.info("Invalid JWT signature");
            logger.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            logger.info("Invalid JWT signature");
            logger.trace("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            logger.info("Expired JWT token");
            logger.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.info("Unsupported JWT token");
            logger.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            logger.info("JWT token compact of handler are invalid");
            logger.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    public ClaimParser parse(String token) {
        return new ClaimParser(secretKey, token);
    }

    public static class ClaimParser {
        private Claims claims;
        private String secretKey;
        private String token;

        public ClaimParser(String secretKey, String token) {
            this.secretKey = secretKey;
            this.token = token;
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }

        public Map<String, Object> getClaims() {
            return claims;
        }

        public String getSubject() {
            return claims.getSubject();
        }

        public String getValue(String key) {
            return (String) claims.get(key);
        }
    }



}
