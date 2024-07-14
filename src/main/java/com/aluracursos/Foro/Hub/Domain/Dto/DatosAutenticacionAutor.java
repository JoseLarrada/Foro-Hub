package com.aluracursos.Foro.Hub.Domain.Dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionAutor(
        @NotBlank
        String usuario,
        @NotBlank
        String contraseña) {
}
