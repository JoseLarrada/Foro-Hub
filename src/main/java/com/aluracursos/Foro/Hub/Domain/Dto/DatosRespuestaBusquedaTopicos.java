package com.aluracursos.Foro.Hub.Domain.Dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DatosRespuestaBusquedaTopicos(
        String titulo,
        String mensaje,
        EstadoTopico topico,
        Categoria categoria,
        LocalDate fechaCreacion
) {
}
