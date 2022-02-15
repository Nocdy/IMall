package utils;

import constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 17:54
 */
public class JwtTokenUtils {
    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static final Properties props=new Properties();
    private static final byte[] API_KEY_SECRET_BYTES;
    private static final SecretKey SECRET_KEY;

    static {
        InputStream in = JwtTokenUtils.class.getClassLoader().getResourceAsStream("keys/secret_key.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        API_KEY_SECRET_BYTES= DatatypeConverter.parseBase64Binary(props.getProperty(SecurityConstants.JWT_SECRET_KEY));
        SECRET_KEY=Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);
    }




    public static String createToken(String username, String id, List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .claim(SecurityConstants.ROLE_CLAIMS, String.join(",", roles))
                .setId(id)
                .setIssuer("SnailClimb")
                .setIssuedAt(createdDate)
                .setSubject(username)
                .setExpiration(expirationDate)
                .compact();
        // 添加 token 前缀 "Bearer ";
        return SecurityConstants.TOKEN_PREFIX + tokenPrefix;
    }

    public static String getId(String token) {
        Claims claims = getClaims(token);
        return claims.getId();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = getClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        String userName = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(userName, token, authorities);
    }

    private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        String role = (String) claims.get(SecurityConstants.ROLE_CLAIMS);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build().
                parseClaimsJws(token).
                getBody();
    }
}
