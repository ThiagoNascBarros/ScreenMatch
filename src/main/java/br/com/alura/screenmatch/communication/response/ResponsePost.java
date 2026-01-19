package br.com.alura.screenmatch.communication.response;

import br.com.alura.screenmatch.Post;

public record ResponsePost(String frase,
                           String personagem,
                           String poster,
                           String titulo) {
    public ResponsePost(Post post) {
        this(
                post.getFrase(),
                post.getPersonagem(),
                post.getPoster(),
                post.getTitulo()
        );
    }
}
