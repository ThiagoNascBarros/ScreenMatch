package br.com.alura.screenmatch.domain;

import br.com.alura.screenmatch.service.ConsumerGeminiAI;
import br.com.alura.screenmatch.communication.RecordSerie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import jakarta.annotation.Nonnull;

import java.util.OptionalDouble;

public class Serie {

    private String title;
    private Double assessment;
    private Integer totalSeasons;
    private ECategory genre;
    private String plot;
    private String actors;
    private final String poster;

    public Serie(RecordSerie req) {
        this.title = req.title();
        this.assessment = OptionalDouble.of(Double.parseDouble(req.assessment()))
                .orElse(0);
        this.totalSeasons = req.totalSeasons();
        this.genre = ECategory.fromString(req.genre().split(",")[0].trim());
        this.plot = ConsumerGeminiAI.getTranslate(req.plot().trim());
        this.actors = req.actors();
        this.poster = req.poster();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public ECategory getGenre() {
        return genre;
    }

    public void setGenre(ECategory genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    @Nonnull
    public String toString() {
        return String.format(
                        "\n" +
                        "  genre        = '%s'%n" +
                        "  title        = '%s'%n" +
                        "  assessment   = '%s'%n" +
                        "  totalSeasons = %d%n" +
                        "  plot         = '%s'%n" +
                        "  actors        = '%s'%n" +
                        "  poster       = '%s'%n" ,
                genre.getCategoryOMDb(),
                title,
                assessment,
                totalSeasons,
                plot,
                actors,
                poster
        );
    }

}
