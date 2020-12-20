package com.br.brangioni.java.springboot2.repository;

import com.br.brangioni.java.springboot2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
