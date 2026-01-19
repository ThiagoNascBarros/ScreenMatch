package br.com.alura.screenmatch.interfaces.service;

import br.com.alura.screenmatch.communication.request.RequestRegisterSerie;
import br.com.alura.screenmatch.communication.request.ResponseRegisterSerie;
import br.com.alura.screenmatch.communication.response.ResponseEpisode;
import br.com.alura.screenmatch.communication.response.ResponsePost;
import br.com.alura.screenmatch.communication.response.ResponseSerie;

import java.util.List;
import java.util.UUID;

public interface ISerieService {

    List<ResponseSerie> getAll();
    List<ResponseSerie> getTopFive();
    ResponseRegisterSerie register(RequestRegisterSerie request);
    List<ResponseSerie> getReleasesSeries();
    ResponseSerie getById(UUID id);
    List<ResponseEpisode> getAllSeason(UUID id);
    List<ResponseEpisode> getEpisodesOfSeason(UUID id, Integer numberSeason);

    List<ResponseSerie> getSeriesByCategory(String categoryEnum);

    ResponsePost getPosts();
}
