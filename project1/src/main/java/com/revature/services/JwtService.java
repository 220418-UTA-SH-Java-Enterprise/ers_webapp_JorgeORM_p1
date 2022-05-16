package com.revature.services;
import java.io.IOException;
import java.security.Key;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;

public class JwtService {

	private Key key;
	
	public JwtService() {
		byte[] secret = "my_secret_passwordafdsalkj;lkvjasd;lkfoijeowiru324u02938098134lkhj;ldjfa;sldkjfDSFSLDKJFLSKJF".getBytes();
        key = Keys.hmacShaKeyFor(secret);
	}
	
	public String createJwt(User user) throws InvalidKeyException, JsonProcessingException {
		UserJwtDTO dto = new UserJwtDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getRole());
		String jwt = Jwts.builder()
                .claim("user_dto", new ObjectMapper().writeValueAsString(dto))
                .signWith(key)
                .compact();
		return jwt;
	}
	
	public UserJwtDTO parseJwt(String jwt) throws IOException, JsonProcessingException{
		Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        String dtoString = (String) token.getBody().get("user_dto");
        return (new ObjectMapper()).readValue(dtoString, UserJwtDTO.class);
	}
}