package br.com.alura.screenmatch.interfaces;

import br.com.alura.screenmatch.communication.SerieResponse;

import java.util.List;

public interface ISerieService {

    List<SerieResponse> getAll();

}
