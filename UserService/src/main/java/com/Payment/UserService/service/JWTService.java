package com.Payment.UserService.service;



import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.KEY;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	@Value("${secretKey}")
	String secretKey;
	
//	static {
//		try {
//			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//			SecretKey sk = keyGen.generateKey();
//			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//			
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	public String generateToken(String username) {
		
		Map<String , Object> claims = new HashMap<String, Object>();
		
		return Jwts
				.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60*60*300))
				.and()
				.signWith(getKey())
				.compact();
	}
	
	public SecretKey getKey() {
		System.out.println("secretKey : "+secretKey);
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	 public String extractUserName(String token) {
	        // extract the username from jwt token
		 	System.out.println("extractUserName : "+token);
	        return extractClaim(token, Claims::getSubject);
	    }

	    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        System.out.println("claims : "+claims);
	        return claimResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	    	
	    	 System.out.println("extractAllClaims : "+token);
	        return Jwts.parser()
	                .verifyWith(getKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	    public boolean validateToken(String token, UserDetails userDetails) {
	        final String userName = extractUserName(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	    	System.out.println("isTokenExpired : "+token);
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	    	System.out.println("extractExpiration : "+token);
	        return extractClaim(token, Claims::getExpiration);
	    }

}

