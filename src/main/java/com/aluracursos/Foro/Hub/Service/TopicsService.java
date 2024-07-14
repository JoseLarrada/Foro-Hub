package com.aluracursos.Foro.Hub.Service;

import com.aluracursos.Foro.Hub.Domain.Dto.DatosActualizarTopico;
import com.aluracursos.Foro.Hub.Domain.Dto.DatosRegistroTopico;
import com.aluracursos.Foro.Hub.Domain.Dto.DatosRespuestaBusquedaTopicos;
import com.aluracursos.Foro.Hub.Domain.Dto.EstadoTopico;
import com.aluracursos.Foro.Hub.Domain.Entity.Autor;
import com.aluracursos.Foro.Hub.Domain.Entity.Topico;
import com.aluracursos.Foro.Hub.Repository.AutorRepository;
import com.aluracursos.Foro.Hub.Repository.TopicoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicsService {
    private final TokenServices tokenServices;
    private final TopicoRepository topicoRepository;
    private final AutorRepository autorRepository;

    public ResponseEntity registrarTopico(DatosRegistroTopico datosRegistroTopico, HttpServletRequest request){
        UserDetails autor=obtenerIdAutor(request);
        if (autor!=null) {
            var topico=new Topico(datosRegistroTopico,(Autor) autor);
            topicoRepository.save(topico);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    public ResponseEntity mostrarTopicosPorFechaAscendente(){
        return ResponseEntity.ok(fromToDto(topicoRepository.MostrarTopicosRecientes()));
    }
    public ResponseEntity mostrarTopicosPorTitulo(String titulo){
        return ResponseEntity.ok(fromToDto(topicoRepository.findAllByTitulo(titulo)));
    }
    public ResponseEntity mostrarTopicos(){
        return ResponseEntity.ok(fromToDto(topicoRepository.findAll()));
    }

    public ResponseEntity actualizarTopico(DatosActualizarTopico datosActualizarTopico,HttpServletRequest request){
        if (topicoRepository.existsById(datosActualizarTopico.id())){
            var autor=obtenerIdAutor(request);
            topicoRepository.save(new Topico(datosActualizarTopico,(Autor) autor));
            return ResponseEntity.ok("Actuazlizado");
        }
        return ResponseEntity.badRequest().build();
    }
    public ResponseEntity eliminartopico(Long idTopico){
        topicoRepository.deleteById(idTopico);
        return ResponseEntity.ok("Eliminado Correctamente");
    }
    public ResponseEntity cambiarEstadoTopico(Long idTopico){
        var topico=topicoRepository.findById(idTopico);
        if (topico.isPresent()){
            topico.get().setTopico(EstadoTopico.valueOf("INACTIVO"));
            return ResponseEntity.ok(new DatosRespuestaBusquedaTopicos(topico.get().getTitulo(),
                    topico.get().getMensaje(),topico.get().getTopico(), topico.get().getCategoria(),topico.get().getFechaCreacion()));
        }
        return ResponseEntity.badRequest().body("No se pudo actualizar");
    }
    private List<DatosRespuestaBusquedaTopicos> fromToDto(List<Topico> lista){
        return lista.stream()
                .map(t->new DatosRespuestaBusquedaTopicos(
                        t.getTitulo(),t.getMensaje(),t.getTopico(),t.getCategoria(),t.getFechaCreacion()))
                .collect(Collectors.toList());
    }
    private UserDetails obtenerIdAutor(HttpServletRequest request){
        var authHeader=request.getHeader("Authorization");
        if (authHeader!=null) {
            var token = authHeader.replace("Bearer ", "");
            String subject = tokenServices.getSubject(token);
            var autor = autorRepository.findByNombreUsuario(subject);
            return autor;
        }
        return null;
    }
}
