package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.communication.SerieResponse;
import br.com.alura.screenmatch.interfaces.service.ISerieService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class SerieController {

    private final ISerieService service;

    public SerieController(ISerieService service) {
        this.service = service;
    }

    @GetMapping("/series")
    public ResponseEntity<@NonNull List<SerieResponse>> getSeries() {
        var response = service.getAll();

        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/series/topfive")
    public ResponseEntity<@NonNull List<SerieResponse>> getTopFiveSeries() {
        var response = service.getTopFive();

        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(response);
    }

}
