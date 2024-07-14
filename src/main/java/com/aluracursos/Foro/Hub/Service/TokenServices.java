package com.aluracursos.Foro.Hub.Service;

import com.aluracursos.Foro.Hub.Domain.Entity.Autor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServices {
    @Value("${api.security.secret}")
    private String jwtkey;
    public String generarToken(Autor autor){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtkey);
            String token = JWT.create()
                    .withIssuer("Voll Medic")
                    .withSubject(autor.getUsername())
                    .withClaim("id", autor.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token){
        DecodedJWT verifier=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtkey);
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Voll Medic")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            throw new RuntimeException();
        }
        if (verifier==null){
            throw new RuntimeException();
        }else{
            return verifier.getSubject();
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
