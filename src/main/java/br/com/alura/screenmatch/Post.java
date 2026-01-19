package br.com.alura.screenmatch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "frase", nullable = false, length = Integer.MAX_VALUE)
    private String frase;

    @Column(name = "personagem", nullable = false, length = 100)
    private String personagem;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "poster", nullable = false, length = Integer.MAX_VALUE)
    private String poster;

}