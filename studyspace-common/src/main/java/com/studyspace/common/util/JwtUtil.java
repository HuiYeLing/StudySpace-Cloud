package com.studyspace.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET = "studyspace-jwt-secret-key";
    private static final long EXPIRE = 7 * 24 * 60 * 60 * 1000L; // 7天

    /**
     * 签发 token
     */
    public static String generate(Long userId, String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * 校验并解析 token
     */
    public static Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从 token 取 userId
     */
    public static Long getUserId(String token) {
        return parse(token).get("userId", Long.class);
    }

    /**
     * 从 token 取 role
     */
    public static String getRole(String token) {
        return parse(token).get("role", String.class);
    }

    /**
     * 从 token 取 username
     */
    public static String getUsername(String token) {
        return parse(token).get("username", String.class);
    }
}