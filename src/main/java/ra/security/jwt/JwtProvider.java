package ra.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ra.security.userpincipal.UserPrincipel;

import java.util.Date;
@Component
public class JwtProvider {
    // mục đích tạo ra
    @Value("${expired}")
    private Long EXPIRED;
    @Value("${secret_key}")
    private String SECRET_KEY;
    private final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    // tao ra token
    public String generateToken(UserPrincipel userPrincipel){
        return Jwts.builder().setSubject(userPrincipel.getUsername())
                // thoi gian song
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+EXPIRED))
                // chu ky bi mat
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }
    // kiem tra token
    public Boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException expiredJwtException)
        {
            logger.error("Thời gian hết hạn {}",expiredJwtException.getMessage());
        }catch (SignatureException signatureException)
        {
            logger.error("Sai chữ ký {}",signatureException.getMessage());
        }catch (MalformedJwtException malformedJwtException)
        {
            logger.error("Lỗi định dạng {}",malformedJwtException.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
