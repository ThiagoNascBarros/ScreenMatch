package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p order by function('RANDOM') LIMIT 1")
    Post getPostRandom();
}
