package br.com.alura.screenmatch.communication.response;

import br.com.alura.screenmatch.domain.Episode;

public record ResponseEpisode(
        Integer temporada,
        Integer number,
        String title) {

    public ResponseEpisode(Episode e) {
        this(
                e.getSeason(),
                e.getNumber(),
                e.getTitle()
        );
    }
}
