package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.communication.RecordSeasons;
import br.com.alura.screenmatch.communication.RecordSerie;
import br.com.alura.screenmatch.domain.Episode;
import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import br.com.alura.screenmatch.exception.DuplicateDataError;
import br.com.alura.screenmatch.repository.ISerieRepository;
import br.com.alura.screenmatch.service.ConsumerAPI;
import br.com.alura.screenmatch.service.ConvertData;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=abd3a916";
    private final ISerieRepository serieRepository;
    private List<Serie> series = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Scanner input = new Scanner(System.in);
    private static final ConsumerAPI consumer = new ConsumerAPI();
    private static final ConvertData cvtData = new ConvertData();

    public Main(ISerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void showMenu() {
        int option = 1;

        while (option != 0) {
            var menu = """
                  \s
                  ================ Menu ================
                  1 - Save serie
                  2 - Search serie and save episodes
                  3 - List searched series
                  4 - Search serie in database
                  5 - Search series of one actor
                  6 - Top five series
                  7 - Search series by category
                  8 - Search series with assessment limit
                  0 - Exit
                  Enter your option:""";

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
                    case 4:
                        searchSerieDatabase();
                        break;
                    case 5:
                        searchActorsOfSerieDatabase();
                        break;
                    case 6:
                        searchTopFiveSeries();
                        break;
                    case 7:
                        searchSerieByCategory();
                        break;
                    case 8:
                        searchSerieByTitleWithAssesmentLimit();
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
        RecordSerie data = getDataSerie();
        Serie serie = new Serie(data);

        try {
            serieRepository.save(serie);
        } catch (ConstraintViolationException ex) {
            throw new DuplicateDataError("Error of datas duplicates");
        }

        System.out.println(data);
    }

    private void searchSerieDatabase() {
        System.out.println("Enter title of your serie the database: ");
        var serieName = input.nextLine();

        var serie = serieRepository.findByTitleContainingIgnoreCase(serieName);
        if (serie.isPresent()) {
            System.out.println(serie);
        } else {
            logger.info("Serie not found");
        }
    }

    private void searchActorsOfSerieDatabase() {
        System.out.println("Enter actors of your serie the database: ");
        String nameActor = input.nextLine();

        System.out.println("Assessment from what value? ");
        var assessment = input.nextDouble();

        var listOfSerieWithActor = serieRepository.findByActorsContainingIgnoreCaseAndAssessmentGreaterThanEqual(nameActor, assessment);
        listOfSerieWithActor
                .forEach(s -> System.out.println("Participated in: " + s.getTitle() +
                        " with assessment: " + s.getAssessment()));
    }

    private void listSearchedSeries() {
        series = serieRepository.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(serie -> logger.info(serie.toString()));
    }

    private void searchEpPutSerie() {
        listSearchedSeries();

        System.out.println("Enter the serie name to search: ");
        var nameSerie = input.nextLine();

        var dataSerie = series.stream()
                .filter(s -> s.getTitle().toLowerCase()
                        .contains(nameSerie.toLowerCase()))
                .findFirst();

        if (dataSerie.isPresent()) {
            List<RecordSeasons> seasons = new ArrayList<>();
            var serie = dataSerie.get();

            for (int i = 1; i <= serie.getTotalSeasons(); i++) {
                var json = consumer.getDataOfAPI(ADDRESS + serie.getTitle()
                        .replace(" ", "+")
                        .toLowerCase() +
                        "&season=" + i + APIKEY);

                seasons.add(cvtData.convertData(json, RecordSeasons.class));
            }

            seasons.forEach(System.out::println);

            var episodes = seasons.stream()
                    .flatMap(s -> s.episodes().stream()
                            .map(e -> new Episode(s.number(), e)))
                    .collect(Collectors.toList());

            serie.setEpisodes(episodes);

            serieRepository.save(serie);
        } else {
            logger.error("Serie not found");
        }
    }

    private void searchTopFiveSeries() {
        List<Serie> topSeries = serieRepository.findTop5ByOrderByAssessmentDesc();
        topSeries.forEach(serie -> logger.info(serie.getTitle() + " | assessment: " + serie.getAssessment()));
    }

    private void searchSerieByCategory() {
        System.out.println("Enter category of serie you want to search: ");
        var nameCategory = input.nextLine();

        var category = ECategory.fromPortuguese(nameCategory);

        List<Serie> seriesByCategory = serieRepository.findByGenre(category);

        seriesByCategory.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }

    private void searchSerieByTitleWithAssesmentLimit() {
        System.out.println("Enter title of serie you want to search: ");
        String title = input.nextLine();

        System.out.println("Enter limit of assessment: ");
        Double assessment = input.nextDouble();

        var series = serieRepository.findByTitleContainingIgnoreCaseAndAssessmentGreaterThan(title, assessment);

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(s -> logger.info(s.toString()));
    }

}
