package com.aluracursos.Foro.Hub.configs;

import com.aluracursos.Foro.Hub.Repository.AutorRepository;
import com.aluracursos.Foro.Hub.Service.TokenServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final AutorRepository autorRepository;
    private final TokenServices tokenServices;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener token de los header
        var authHeader=request.getHeader("Authorization");
        if (authHeader!=null){
            var token=authHeader.replace("Bearer ","");
            var subject=tokenServices.getSubject(token);
            if (subject!=null){
                //Token valido
                var autor=autorRepository.findByNombreUsuario(subject);
                var authentication= new UsernamePasswordAuthenticationToken(autor,null,
                        autor.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
