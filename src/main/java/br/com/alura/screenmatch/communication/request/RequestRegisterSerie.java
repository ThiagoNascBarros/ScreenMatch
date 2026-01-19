package br.com.alura.screenmatch.communication.request;

import br.com.alura.screenmatch.domain.Serie;
import jakarta.annotation.Nonnull;

public record RequestRegisterSerie(String title,
                                   Double assessment,
                                   Integer totalSeasons,
                                   String genre,
                                   String plot,
                                   String actors,
                                   String poster) {

    public RequestRegisterSerie(@Nonnull Serie serie) {
        this(
                serie.getTitle(),
                serie.getAssessment(),
                serie.getTotalSeasons(),
                String.valueOf(serie.getGenre()),
                serie.getPlot(),
                serie.getActors(),
                serie.getPoster()
        );
    }

}
