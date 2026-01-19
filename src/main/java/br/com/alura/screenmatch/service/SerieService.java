package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.communication.request.RequestRegisterSerie;
import br.com.alura.screenmatch.communication.request.ResponseRegisterSerie;
import br.com.alura.screenmatch.communication.response.ResponseEpisode;
import br.com.alura.screenmatch.communication.response.ResponsePost;
import br.com.alura.screenmatch.communication.response.ResponseSerie;
import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import br.com.alura.screenmatch.interfaces.service.ISerieService;
import br.com.alura.screenmatch.repository.IPostRepository;
import br.com.alura.screenmatch.repository.ISerieRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SerieService implements ISerieService {

    private ISerieRepository repository;
    private IPostRepository postRepository;

    public SerieService(ISerieRepository repository, IPostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @Override
    public List<ResponseSerie> getAll(){
        var response = repository.findAll().stream()
                .map(ResponseSerie::new)
                .toList();

        if (response.isEmpty()) {
            return new ArrayList<>();
        }

        return response;
    }

    @Override
    public List<ResponseSerie> getTopFive() {
        var response = repository.findTop5ByOrderByAssessmentDesc().stream()
                .map(ResponseSerie::new)
                .toList();

        if (response.isEmpty()) {
            return new ArrayList<>();
        }

        return response;
    }

    @Override
    public ResponseRegisterSerie register(RequestRegisterSerie request) {
        var serie = new Serie(request);

        var entity = this.repository.save(serie);

        return new ResponseRegisterSerie(entity);
    }

    @Override
    public List<ResponseSerie> getReleasesSeries() {
        return this.repository.findTopFiveSeries()
                .stream()
                .map(ResponseSerie::new)
                .sorted(Comparator.comparing(ResponseSerie::assessment))
                .toList();
    }

    @Override
    public ResponseSerie getById(UUID id) {
        Optional<Serie> serie = this.repository.findById(id);

        if (serie.isPresent()) {
            return new ResponseSerie(serie);
        }

        return null;
    }

    @Override
    public List<ResponseEpisode> getAllSeason(UUID id) {
        Optional<Serie> serie = this.repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodes().stream()
                    .map(ResponseEpisode::new)
                    .toList();
        }

        return null;
    }

//    @Override // Forma que eu fiz para apresentar os episodios por temporada
//    public List<ResponseEpisode> getEpisodesOfSeason(UUID id, Integer numberSeason) {
//        Optional<Serie> serie = this.repository.findById(id);
//
//        if (serie.isPresent()) {
//            Serie s = serie.get();
//            return s.getEpisodes().stream()
//                    .filter(e -> Objects.equals(e.getSeason(), numberSeason))
//                    .map(ResponseEpisode::new)
//                    .toList();
//        }
//
//        return null;
//    }

    @Override // Forma que eu fiz para apresentar os episodios por temporada
    public List<ResponseEpisode> getEpisodesOfSeason(UUID id, Integer numberSeason) {
        return this.repository.getEpisodesBySeasons(id, numberSeason).stream()
                .map(ResponseEpisode::new)
                .toList();
    }

    @Override
    public List<ResponseSerie> getSeriesByCategory(String categoryEnum) {
        var catergory = ECategory.fromPortuguese(categoryEnum);
        return this.repository.findByGenre(catergory).stream()
                .map(ResponseSerie::new)
                .toList();
    }

    @Override
    public ResponsePost getPosts() {
        var searching = this.postRepository.getPostRandom();

        return new ResponsePost(searching);
    }


}
