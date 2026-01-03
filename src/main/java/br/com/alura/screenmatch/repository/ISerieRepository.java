package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.domain.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISerieRepository extends JpaRepository<Serie, UUID> {
}