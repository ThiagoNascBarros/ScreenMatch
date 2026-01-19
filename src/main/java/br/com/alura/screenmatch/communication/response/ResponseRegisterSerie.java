package br.com.alura.screenmatch.communication.request;

import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import jakarta.annotation.Nonnull;

public record ResponseRegisterSerie(String title,
                                   Double assessment,
                                   Integer totalSeasons,
                                   ECategory genre,
                                   String plot,
                                   String actors,
                                   String poster) {

    public ResponseRegisterSerie(@Nonnull Serie serie) {
        this(
                serie.getTitle(),
                serie.getAssessment(),
                serie.getTotalSeasons(),
                serie.getGenre(),
                serie.getPlot(),
                serie.getActors(),
                serie.getPoster()
        );
    }

}
