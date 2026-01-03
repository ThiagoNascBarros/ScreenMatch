package br.com.alura.screenmatch.domain;

import br.com.alura.screenmatch.communication.RecordEpisode;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer season;
    private String title;
    private Integer number;
    private Double assessment;
    private LocalDate releaseDate;
    @ManyToOne
    private Serie serie;

    public Episode(Integer numberSeason, RecordEpisode dte) {
        this.season = numberSeason;
        this.title = dte.title();
        this.number = dte.number();

        try {
            this.assessment = Double.valueOf(dte.assessment());
        } catch (NumberFormatException e) {
            this.assessment = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(dte.releaseDate());
        } catch (DateTimeParseException e) {
            this.releaseDate = null;
        }
    }

    public Episode() { }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return """
            Episode {
              season      = %s
              title       = '%s'
              number      = %d
              assessment  = %s
              releaseDate = %s
            }
            """.formatted(season, title, number, assessment, releaseDate);
    }
}
