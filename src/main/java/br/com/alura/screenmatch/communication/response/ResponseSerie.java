package br.com.alura.screenmatch.communication.response;

import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import jakarta.annotation.Nonnull;

import java.util.Optional;
import java.util.UUID;

public record ResponseSerie(UUID id,
                            String title,
                            Double assessment,
                            Integer totalSeasons,
                            ECategory genre,
                            String plot,
                            String actors,
                            String poster) {
    public ResponseSerie(@Nonnull Serie serie) {
        this(
                serie.getId(),
                serie.getTitle(),
                serie.getAssessment(),
                serie.getTotalSeasons(),
                serie.getGenre(),
                serie.getPlot(),
                serie.getActors(),
                serie.getPoster()
        );
    }

    public ResponseSerie(@Nonnull Optional<Serie> serie) {
        this(
                serie.get().getId(),
                serie.get().getTitle(),
                serie.get().getAssessment(),
                serie.get().getTotalSeasons(),
                serie.get().getGenre(),
                serie.get().getPlot(),
                serie.get().getActors(),
                serie.get().getPoster()
        );
    }
}
