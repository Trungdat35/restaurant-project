package com.example.restaurant_spring.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtUtil {
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) // thời gian tạo token
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24 )) // 24 p hết hạn
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignKey(){
        String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
        byte[] key = Decoders.BASE64.decode(secretKey); // giải mã khóa bí mật
        return Keys.hmacShaKeyFor(key);
    }
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        return  extractClaim(token, Claims::getExpiration).before(new Date()); // kiểm tra ngày hết hạn có trước ngày hiện tại k
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    private  Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }
//    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails){
//        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis())) // thời gian tạo token
//                .setExpiration(new Date(System.currentTimeMillis() +  timeExprition)) // 7 ngày hết hạn
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }

}
