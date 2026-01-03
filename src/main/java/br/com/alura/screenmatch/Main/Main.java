package br.com.alura.screenmatch.Main;

import br.com.alura.screenmatch.Service.ConsumerAPI;
import br.com.alura.screenmatch.Service.ConvertData;
import br.com.alura.screenmatch.communication.RecordSeasons;
import br.com.alura.screenmatch.communication.RecordSerie;
import br.com.alura.screenmatch.domain.Serie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=abd3a916";
    private List<RecordSerie> seriesSearches = new ArrayList<>();

    private static final Scanner input = new Scanner(System.in);
    private static final ConsumerAPI consumer = new ConsumerAPI();
    private static final ConvertData cvtData = new ConvertData();

    public void showMenu() {
        int option = 1;

        while (option != 0) {
            var menu = """
                  \s
                  ================ Menu ================
                  1 - Search series
                  2 - Search episodes
                  3 - List searched series
                  0 - Exit
                  Enter your option:\s""";

            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();
                switch (option) {
                    case 1:
                        searchSerieWeb();
                        break;
                    case 2:
                        searchEpPutSerie();
                        break;
                    case 3:
                        listSearchedSeries();
                        break;
                    case 0:
                        logger.info("Exit...");
                        break;
                    default:
                        logger.warn("Invalid option");
            }
        }
    }

    private RecordSerie getDataSerie() {
        System.out.println("Enter the series name to search: ");
        var serieName = input.nextLine()
                .replace(" ", "+")
                .toLowerCase();

        var response = consumer.getDataOfAPI(ADDRESS + serieName + APIKEY);

        return cvtData.convertData(response, RecordSerie.class);
    }

    private void searchSerieWeb() {
        RecordSerie dados = getDataSerie();
        seriesSearches.add(dados);
        System.out.println(dados);
    }

    private void listSearchedSeries() {
        List<Serie> series;

        series = seriesSearches.stream()
                .map(Serie::new)
                .toList();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(serie -> logger.warn(serie.toString()));
    }

    private void searchEpPutSerie() {
        var dataSerie = getDataSerie();
        List<RecordSeasons> seasons = new ArrayList<>();

        for (int i = 1; i <= dataSerie.totalSeasons(); i++) {
            var json = consumer.getDataOfAPI(ADDRESS + dataSerie.title()
                    .replace(" ", "+")
                    .toLowerCase() +
                    "&season=" + i + APIKEY);

            seasons.add(cvtData.convertData(json, RecordSeasons.class));
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
