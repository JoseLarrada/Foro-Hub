package com.aluracursos.Foro.Hub.Domain.Dto;

import com.aluracursos.Foro.Hub.Domain.Entity.Autor;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        EstadoTopico topico,
        Categoria categoria
) {
}
