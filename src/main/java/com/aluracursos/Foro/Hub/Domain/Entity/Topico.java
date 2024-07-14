package com.aluracursos.Foro.Hub.Domain.Entity;

import com.aluracursos.Foro.Hub.Domain.Dto.DatosActualizarTopico;
import com.aluracursos.Foro.Hub.Domain.Dto.DatosRegistroTopico;
import com.aluracursos.Foro.Hub.Domain.Dto.EstadoTopico;
import com.aluracursos.Foro.Hub.Domain.Dto.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "topicos")
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDate fechaCreacion;
    @Enumerated(EnumType.STRING)
    private EstadoTopico topico;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @ManyToOne
    private Autor autor;

    public Topico(DatosRegistroTopico datosRegistroTopico,Autor autor) {
        this.titulo=datosRegistroTopico.titulo();
        this.mensaje=datosRegistroTopico.mensaje();
        this.fechaCreacion=LocalDate.now();
        this.topico=datosRegistroTopico.topico();
        this.categoria=datosRegistroTopico.categoria();
        this.autor=autor;
    }

    public Topico(DatosActualizarTopico datosActualizarTopico,Autor autor) {
        this.id= datosActualizarTopico.id();
        if (datosActualizarTopico.titulo()!=null){
            this.titulo=datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje()!=null){
            this.mensaje=datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.topico()!=null){
            this.topico=datosActualizarTopico.topico();
        }
        if (datosActualizarTopico.categoria()!=null){
            this.categoria=datosActualizarTopico.categoria();
        }
        this.autor=autor;
    }

    public void setTopico(EstadoTopico topico) {
        this.topico = topico;
    }
}
