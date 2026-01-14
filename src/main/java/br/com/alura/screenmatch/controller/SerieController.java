package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.communication.SerieResponse;
import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.repository.ISerieRepository;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class SerieController {

    @Autowired
    private ISerieRepository serieRepository;

    @GetMapping("/serie")
    public ResponseEntity<@NonNull List<SerieResponse>> GetSerie() {
        var response = serieRepository.findAll().stream()
                .map(SerieResponse::new)
                .toList();

        return ResponseEntity.ok(response);
    }

}
