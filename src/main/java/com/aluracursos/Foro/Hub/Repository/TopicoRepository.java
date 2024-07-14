package com.aluracursos.Foro.Hub.Repository;

import com.aluracursos.Foro.Hub.Domain.Entity.Topico;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {
    List<Topico> findAllByTitulo(String titulo);
    @Query("""
            SELECT t FROM Topico t
            ORDER BY t.fechaCreacion
            ASC
            LIMIT 10
            """)
    List<Topico> MostrarTopicosRecientes();
}
