package com.aluracursos.Foro.Hub.Controller;

import com.aluracursos.Foro.Hub.Domain.Dto.DatosActualizarTopico;
import com.aluracursos.Foro.Hub.Domain.Dto.DatosRegistroTopico;
import com.aluracursos.Foro.Hub.Service.TopicsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topico")
public class TopicoController {
    private final TopicsService topicsService;
    @PostMapping
    public ResponseEntity crear(@RequestBody @Valid DatosRegistroTopico datos, HttpServletRequest http){
        return topicsService.registrarTopico(datos,http);
    }
    @GetMapping
    public ResponseEntity obtenerTopicos(){
        return topicsService.mostrarTopicos();
    }
    @GetMapping("/{titulo}")
    public ResponseEntity obtenerTopicosPorTitulo(@PathVariable String titulo){
        return topicsService.mostrarTopicosPorTitulo(titulo);
    }
    @GetMapping("/ordernado")
    public ResponseEntity obtenerTopicosOrdenados(){
        return topicsService.mostrarTopicosPorFechaAscendente();
    }
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody DatosActualizarTopico topico,HttpServletRequest http){
        return topicsService.actualizarTopico(topico,http);
    }
    @PutMapping("/desactivar/{id}")
    public ResponseEntity desactivarTopico(@PathVariable Long id){
        return topicsService.cambiarEstadoTopico(id);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        return topicsService.eliminartopico(id);
    }
}
