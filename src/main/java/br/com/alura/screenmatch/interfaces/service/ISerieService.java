package br.com.alura.screenmatch.interfaces.service;

import br.com.alura.screenmatch.communication.SerieResponse;

import java.util.List;

public interface ISerieService {

    List<SerieResponse> getAll();
    List<SerieResponse> getTopFive();

}
