package com.aluracursos.Foro.Hub.Domain.Dto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico (
        @NotBlank
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        EstadoTopico topico,
        Categoria categoria
){
}
