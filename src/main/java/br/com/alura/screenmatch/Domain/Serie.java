package br.com.alura.screenmatch.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
* @Document - JsonProperty and JsonAlias
* JsonPropertiy It's better because it deserializes,
* and when you convert it back to JSON, it will send it as
* the JSON that's written in the JsonProperty.
*
* JsonAlias don't do this
* */

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie (@JsonProperty("Title") String Title,
                     @JsonProperty("imdbRating") String Assessment,
                     @JsonProperty("totalSeasons") Integer totalSeasons) { }
