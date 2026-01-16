package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.communication.SerieResponse;
import br.com.alura.screenmatch.interfaces.service.ISerieService;
import br.com.alura.screenmatch.repository.ISerieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SerieService implements ISerieService {

    private ISerieRepository repository;

    public SerieService(ISerieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SerieResponse> getAll(){
        var response = repository.findAll().stream()
                .map(SerieResponse::new)
                .toList();

        if (response.isEmpty()) {
            return new ArrayList<>();
        }

        return response;
    }

    @Override
    public List<SerieResponse> getTopFive() {
        var response = repository.findTop5ByOrderByAssessmentDesc().stream()
                .map(SerieResponse::new)
                .toList();

        if (response.isEmpty()) {
            return new ArrayList<>();
        }

        return response;
    }

}
