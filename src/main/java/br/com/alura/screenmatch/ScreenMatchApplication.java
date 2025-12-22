package br.com.alura.screenmatch;

import br.com.alura.screenmatch.Domain.Episode;
import br.com.alura.screenmatch.Domain.Serie;
import br.com.alura.screenmatch.Service.ConsumerAPI;
import br.com.alura.screenmatch.Service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var address = "https://www.omdbapi.com/?t=gilmore+girls&apikey=abd3a916";
        ConvertData cvtData = new ConvertData();

        var response = ConsumerAPI.getDataOfAPI(address);
        var dataSerie = cvtData.convertData(response, Serie.class);
        System.out.println(dataSerie.toString());

        address = "https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=1&apikey=abd3a916";
        response = ConsumerAPI.getDataOfAPI(address);
        var dataEpisode = cvtData.convertData(response, Episode.class);
        System.out.println(dataEpisode.toString());

    }

}
