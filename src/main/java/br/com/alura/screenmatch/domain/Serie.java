package br.com.alura.screenmatch.domain;

import br.com.alura.screenmatch.service.ConsumerGeminiAI;
import br.com.alura.screenmatch.communication.RecordSerie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity()
@Table(name = "series")
@Data
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String title;
    private Double assessment;
    private Integer totalSeasons;
    @Enumerated(EnumType.STRING)
    private ECategory genre;
    private String plot;
    private String actors;
    private String poster;
    @OneToMany(mappedBy = "serie",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Episode> episodes = new ArrayList<>();

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

    public Serie() { }

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
                        "  poster       = '%s'%n" +
                        "  episodes       = '%s'%n",
                genre.getCategoryOMDb(),
                title,
                assessment,
                totalSeasons,
                plot,
                actors,
                poster,
                Arrays.toString(episodes.stream()
                        .map(Episode::getTitle)
                        .toArray(String[]::new))
        );
    }

}
