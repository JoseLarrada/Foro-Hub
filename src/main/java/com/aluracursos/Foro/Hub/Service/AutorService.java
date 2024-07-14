package com.aluracursos.Foro.Hub.Service;

import com.aluracursos.Foro.Hub.Domain.Entity.Autor;
import com.aluracursos.Foro.Hub.Domain.Dto.DatosAutenticacionAutor;
import com.aluracursos.Foro.Hub.Domain.Dto.RespuestaToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AuthenticationManager authenticationManager;
    private final TokenServices tokenServices;
    public ResponseEntity autenticarAutor(DatosAutenticacionAutor autenticacionAutor){
        Authentication AuthToken=new UsernamePasswordAuthenticationToken(autenticacionAutor.usuario(),
                autenticacionAutor.contraseña());
        var usuarioAutenticado=authenticationManager.authenticate(AuthToken);
        var jwtToken=tokenServices.generarToken((Autor) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new RespuestaToken(jwtToken));
    }
}
