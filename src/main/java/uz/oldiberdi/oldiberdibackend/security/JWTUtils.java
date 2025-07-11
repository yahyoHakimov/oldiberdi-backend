package uz.oldiberdi.oldiberdibackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import uz.oldiberdi.oldiberdibackend.entity.User;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {
    private static final String SECRET = "SuperSecretKeyForOldiberdiProject123456789";
    private static final long EXPIRATION = 100 * 60 * 60 *24;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean isTokenValid(String token) {
        try {
            extractUserId(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
