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
        System.out.print("Digite o nome da série para buscar uma Serie: ");

        var nameSerie = input.nextLine()
                .replace(" ", "+")
                .toLowerCase();

        var response = consumer.getDataOfAPI(ADDRESS + nameSerie + APIKEY);

        var dataSerie = cvtData.convertData(response, Serie.class);
//        System.out.println(dataSerie.toString());

        List<Seasons> seasons = new ArrayList<>();
        for (int i = 1; i <= dataSerie.totalSeasons(); i++) {
            response = ConsumerAPI.getDataOfAPI(ADDRESS + nameSerie +
                    "&season=" + i + APIKEY);

            seasons.add(cvtData.convertData(response, Seasons.class));
        }

//        seasons.forEach(System.out::println);

//        System.out.println(ADDRESS + nameSerie + APIKEY);

//        seasons.forEach(t -> { t.episodes().forEach(e -> {
//                System.out.println(e.title());
//            });
//        });

        List<DataEpisode> dataEpisodes = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

//        System.out.println("\nTop ten episodes");
//        dataEpisodes.stream()
//                .filter(ep -> !ep.assessment().equalsIgnoreCase("N/A"))
//                .peek(System.out::println)
//                .sorted(Comparator.comparing(DataEpisode::assessment).reversed())
//                .peek(e -> System.out.println("Ordenação" + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite de 10" + e))
//                .map(e -> e.title().toUpperCase())
//                .peek(e -> System.out.println("Transformando titles em maiúsculo" + e))
//                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(dte -> new Episode(s.number(), dte))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);


        System.out.print("\nSearch title: ");
        var titleSearch = input.nextLine();
        Optional<Episode> search = episodes.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(titleSearch.toUpperCase()))
                .findFirst();

        if (search.isPresent()) {
            System.out.println("Episode " + search.get().getTitle());
        } else {
            System.out.println("Episode not found");
        }

//        System.out.println("A partir de que ano você desea ver os episódios? ");
//        var year = input.nextInt();
//        input.nextLine();
//
//        LocalDate dateSearch = LocalDate.of(year, 1, 1);
//
//        episodes.stream()
//                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(dateSearch))
////                .filter(e -> e.getTitle().startsWith("T") && e.getTitle().contains("and"))
//                .forEach(e -> {
//                    System.out.println("\nSeason: " + e.getSeason() +
//                            "\nEpisode: " + e.getTitle() +
//                            "\nData de lançamento: " + e.getReleaseDate().format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy")));
//                });

        Map<Integer, Double> assessmentPutSeason = episodes.stream()
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getAssessment)));

        assessmentPutSeason.forEach((i,as) -> System.out.printf("Assessment from the season %d: %.2f\n", i, as));
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
