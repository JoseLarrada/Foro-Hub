package com.aluracursos.Foro.Hub.Repository;

import com.aluracursos.Foro.Hub.Domain.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    UserDetails findByNombreUsuario(String nombreUsuario);
}
