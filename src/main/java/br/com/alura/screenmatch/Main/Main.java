package br.com.alura.screenmatch.Main;

import br.com.alura.screenmatch.Domain.DataEpisode;
import br.com.alura.screenmatch.Domain.Episode;
import br.com.alura.screenmatch.Domain.Seasons;
import br.com.alura.screenmatch.Domain.Serie;
import br.com.alura.screenmatch.Service.ConsumerAPI;
import br.com.alura.screenmatch.Service.ConvertData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=abd3a916";

    private Scanner input = new Scanner(System.in);
    private ConsumerAPI consumer = new ConsumerAPI();
    private ConvertData cvtData = new ConvertData();

    public void showMenu() {
        var menu = """
                1 - Search series
                2 - Search episodes
               \s
                0 - Exit                                \s
               \s""";

        System.out.println(menu);
        var option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                searchSerieWeb();
                break;
            case 2:
                searchEpPutSerie();
                break;
            case 0:
                System.out.println("Exit...");
                break;
            default:
                System.out.println("Invalid option");
        }
    }


    private Serie getDataSerie() {
        System.out.println("Enter the series name to search: ");
        var serieName = input.nextLine()
                .replace(" ", "+")
                .toLowerCase();

        var response = consumer.getDataOfAPI(ADDRESS + serieName + APIKEY);

        return cvtData.convertData(response, Serie.class);
    }

    private void searchSerieWeb() {
        Serie dados = getDataSerie();
        System.out.println(dados);
    }

    private void searchEpPutSerie() {
        var dataSerie = getDataSerie();
        List<Seasons> seasons = new ArrayList<>();

        for (int i = 1; i <= dataSerie.totalSeasons(); i++) {
            var json = ConsumerAPI.getDataOfAPI(ADDRESS + dataSerie.title()
                    .replace(" ", "+")
                    .toLowerCase() +
                    "&season=" + i + APIKEY);

            seasons.add(cvtData.convertData(json, Seasons.class));
        }

        seasons.forEach(System.out::println);
    }

    private void learningLambdas() {
        List<String> names = Arrays.asList("Iasmin", "Thiago", "Isabela", "Laura");

        names.stream()
                .sorted()
                .limit(3)
                .filter(n -> n.startsWith("I"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

}
