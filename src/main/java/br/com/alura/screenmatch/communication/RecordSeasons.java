package br.com.alura.screenmatch.communication;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordSeasons(@JsonAlias("Season") Integer number,
                            @JsonAlias("Episodes") List<RecordEpisode> episodes) {
}
