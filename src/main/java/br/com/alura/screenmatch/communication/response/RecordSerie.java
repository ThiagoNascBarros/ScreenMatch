package br.com.alura.screenmatch.communication.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;

/*
* @Document - JsonProperty and JsonAlias
* JsonPropertiy It's better because it deserializes,
* and when you convert it back to JSON, it will send it as
* the JSON that's written in the JsonProperty.
*
* JsonAlias don't do this
* */

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordSerie(@JsonProperty("Title") String title,
                          @JsonProperty("imdbRating") String assessment,
                          @JsonProperty("totalSeasons") Integer totalSeasons,
                          @JsonProperty("Genre") String genre,
                          @JsonProperty("Plot") String plot,
                          @JsonProperty("Actors") String actors,
                          @JsonProperty("Poster") String poster)
{
    @Override
    @Nonnull
    public String toString() {
        return String.format(
                "Serie {%n" +
                        "  title        = '%s'%n" +
                        "  assessment   = '%s'%n" +
                        "  totalSeasons = %d%n" +
                        "  genre        = '%s'%n" +
                        "  plot         = '%s'%n" +
                        "  actors        = '%s'%n" +
                        "  poster       = '%s'%n" +
                        "}",
                title,
                assessment,
                totalSeasons,
                genre,
                plot,
                actors,
                poster
        );
    }

}
