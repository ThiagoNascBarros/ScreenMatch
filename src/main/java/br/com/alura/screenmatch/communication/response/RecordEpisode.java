package br.com.alura.screenmatch.communication.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordEpisode(@JsonAlias("Title") String title,
                            @JsonAlias("Episode") Integer number,
                            @JsonAlias("imdbRating") String assessment,
                            @JsonAlias("Released") String releaseDate) {

    @Override
    @Nonnull
    public String toString() {
        return "Episode{" +
                "title='" + title + '\'' +
                ", number=" + number +
                ", assessment='" + assessment + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
