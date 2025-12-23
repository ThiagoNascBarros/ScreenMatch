package br.com.alura.screenmatch.Domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer number,
                          @JsonAlias("imdbRating") String assessment,
                          @JsonAlias("Released") String releaseDate) {
}
