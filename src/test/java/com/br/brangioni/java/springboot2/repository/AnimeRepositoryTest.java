package com.br.brangioni.java.springboot2.repository;

import com.br.brangioni.java.springboot2.Util.Animecreator;
import com.br.brangioni.java.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when succesful")
    void save_PersistAnime_WhenSuccesful(){
        Anime animeToBeSaved = Animecreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isNotBlank();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Save update anime when succesful")
    void save_UpdatesAnime_WhenSuccesful(){
        Anime animeToBeSaved = Animecreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("Macaco Loco");
        Anime animeUpdated = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isNotBlank();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete remove anime when succesful")
    void delte_UpdatesAnime_WhenSuccesful(){
        Anime animeToBeSaved = Animecreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);


       this.animeRepository.delete(animeToBeSaved);
        Optional<Anime> animeOptional =  this.animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(animeOptional.isEmpty());


    }

    @Test
    @DisplayName("Find By Name returns list of anime when succesful")
    void fundByName_ReturnsLitsOfAnime_WhenSuccesful(){
        Anime animeToBeSaved = Animecreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        System.out.println("Name: "+name);

        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty().contains(animeSaved);


    }

    @Test
    @DisplayName("Find By Name returns empty list when no anime is found")
    void fundByName_ReturnsLit_WhenAnimeIsNotFound(){
        List<Anime> animes = this.animeRepository.findByName("ZZZZZZZZ");

        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty(){
        Anime anime = new Anime();

//        Assertions.assertThatThrownBy(()-> this.animeRepository.save(anime))
//                .isInstanceOf(ConstraintViolationException.class);
          Assertions.assertThatExceptionOfType( ConstraintViolationException.class)
                  .isThrownBy(()-> this.animeRepository.save(anime))
                  .withMessageContaining("O nome não pode ser vazio");
    }


}