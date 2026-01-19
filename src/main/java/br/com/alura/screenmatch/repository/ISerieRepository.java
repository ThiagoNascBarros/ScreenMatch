package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.communication.response.ResponsePost;
import br.com.alura.screenmatch.communication.response.ResponseSerie;
import br.com.alura.screenmatch.domain.Episode;
import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISerieRepository extends JpaRepository<Serie, UUID> {
    Optional<Serie> findByTitleContainingIgnoreCase(String title);

    List<Serie> findTop5ByOrderByAssessmentDesc();

    List<Serie> findByActorsContainingIgnoreCaseAndAssessmentGreaterThanEqual(String actors, Double assessment);

    List<Serie> findByGenre(ECategory category);

    List<Serie> findByTitleContainingIgnoreCaseAndAssessmentGreaterThan(String title, Double assessment);

    @Query("select s from Serie s where s.totalSeasons <= :totalSeasons and s.assessment >= :assessment")
    List<Serie> seriesForSeasonsAndAssessment(int totalSeasons, Double assessment);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:segment%")
    List<Episode> episodesBySegment(String segment);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie " +
            "ORDER BY e.assessment DESC LIMIT 5")
    List<Episode> topEpisodesBySerie(Optional<Serie> serie);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie " +
            "AND YEAR(e.releaseDate) >= :year ")
    List<Episode> episodesBySerieAndYear(Optional<Serie> serie, int year);

    @Query("SELECT s FROM Serie s JOIN s.episodes e GROUP BY s ORDER BY MAX(e.releaseDate) DESC LIMIT 5")
    List<Serie> findTopFiveSeries();

    @Query("select e from Serie s join s.episodes e where s.id = :id and e.season = :numberSeason")
    List<Episode> getEpisodesBySeasons(UUID id, Integer numberSeason);



}