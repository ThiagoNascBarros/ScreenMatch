package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.domain.Serie;
import br.com.alura.screenmatch.domain.enums.ECategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISerieRepository extends JpaRepository<Serie, UUID> {
    Optional<Serie> findByTitleContainingIgnoreCase(String title);
    List<Serie> findTop5ByOrderByAssessmentDesc();
    List<Serie> findByActorsContainingIgnoreCaseAndAssessmentGreaterThanEqual(String actors, Double assessment);
    List<Serie> findByGenre(ECategory category);
    List<Serie> findByTitleContainingIgnoreCaseAndAssessmentGreaterThan(String title, Double assessment);

}