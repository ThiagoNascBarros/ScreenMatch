package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.communication.request.RequestRegisterSerie;
import br.com.alura.screenmatch.communication.request.ResponseRegisterSerie;
import br.com.alura.screenmatch.communication.response.ResponseEpisode;
import br.com.alura.screenmatch.communication.response.ResponsePost;
import br.com.alura.screenmatch.communication.response.ResponseSerie;
import br.com.alura.screenmatch.interfaces.service.ISerieService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/series")
public class SerieController {

    private final ISerieService service;

    public SerieController(ISerieService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<@NonNull List<ResponseSerie>> getSeries() {
        var response = service.getAll();

        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/lancamentos")
    public ResponseEntity<@NonNull List<ResponseSerie>> getSeriesLanc() {
        var response = service.getReleasesSeries();

        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/top5")
    public ResponseEntity<@NonNull List<ResponseSerie>> getTopFiveSeries() {
        var response = service.getTopFive();

        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull ResponseSerie> getDetaisSerie(@PathVariable UUID id) {
        var serie = service.getById(id);

        if (serie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(serie);
    }

    @GetMapping("/{id}/temporadas/todas")
    public ResponseEntity<@NonNull List<ResponseEpisode>> getAllSeasonsOfSerie(@PathVariable UUID id) {
        var seasonWithEpisodes = service.getAllSeason(id);
        return ResponseEntity.ok(seasonWithEpisodes);
    }

    @GetMapping("/{id}/temporadas/{numberSeason}")
    public ResponseEntity<@NonNull List<ResponseEpisode>> getSeasonOfSerie(@PathVariable UUID id,
                                                                           @PathVariable Integer numberSeason) {
        var seasonWithEpisodes = service.getEpisodesOfSeason(id, numberSeason);
        return ResponseEntity.ok(seasonWithEpisodes);
    }

    @GetMapping("/categoria/{categoryEnum}")
    public ResponseEntity<@NonNull List<ResponseSerie>> getByCategory(@PathVariable String categoryEnum) {
        var series = this.service.getSeriesByCategory(categoryEnum);
        return ResponseEntity.ok(series);
    }

    @GetMapping("/posts")
    public ResponseEntity<@NonNull ResponsePost> getByCategory(){
        return ResponseEntity.ok(this.service.getPosts());
    }


}
