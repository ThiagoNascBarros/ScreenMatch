package br.com.alura.screenmatch.communication;

import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import jakarta.annotation.Nonnull;

public record SerieResponse(String title,
                            Double assessment,
                            Integer totalSeasons,
                            ECategory genre,
                            String plot,
                            String actors,
                            String poster) {
    public SerieResponse(@Nonnull Serie serie) {
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
