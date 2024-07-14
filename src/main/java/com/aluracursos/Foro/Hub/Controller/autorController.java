package com.aluracursos.Foro.Hub.Controller;

import com.aluracursos.Foro.Hub.Domain.Dto.DatosAutenticacionAutor;
import com.aluracursos.Foro.Hub.Service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class autorController {
    private final AutorService autorService;
    @PostMapping
    public ResponseEntity login(@RequestBody DatosAutenticacionAutor datos){
        return autorService.autenticarAutor(datos);
    }
}
