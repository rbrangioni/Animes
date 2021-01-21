package com.br.brangioni.java.springboot2.service;


import com.br.brangioni.java.springboot2.Util.AnimePostRequestBodyCreator;
import com.br.brangioni.java.springboot2.Util.AnimePutRequestBodyCreator;
import com.br.brangioni.java.springboot2.Util.Animecreator;
import com.br.brangioni.java.springboot2.domain.Anime;
import com.br.brangioni.java.springboot2.exception.BadRequestException;
import com.br.brangioni.java.springboot2.repository.AnimeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;
    @Mock
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(Animecreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepositoryMock.findAll())
                .thenReturn(List.of(Animecreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(Animecreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(Animecreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(Animecreator.createValidAnime());

        BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));
    }
    @Test
    @DisplayName("listAll returns list of anime inside page object when successful")
    void listAll_ReturnsListOfAnimesInsidePageObject_WhenSuccessful(){
        String expectedName = Animecreator.createValidAnime().getName();

        Page<Anime> animePage = animeService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAllNonPageable returns list of anime when successful")
    void listAllNonPageable_ReturnsListOfAnimes_WhenSuccessful(){
        String expectedName = Animecreator.createValidAnime().getName();

        List<Anime> animes = animeService.listAllNonPageable();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns anime when successful")
    void findByIdOrThrowBadRequestException_ReturnsAnime_WhenSuccessful(){
        Long expectedId = Animecreator.createValidAnime().getId();

        Anime anime = animeService.findByIdOrThrowBadRequestException(1);

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when anime is not found")
    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenAnimeIsNotFound(){
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> animeService.findByIdOrThrowBadRequestException(1));
    }

    @Test
    @DisplayName("findByName returns a list of anime when successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful(){
        String expectedName = Animecreator.createValidAnime().getName();

        List<Anime> animes = animeService.findByName("anime");

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of anime when anime is not found")
    void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound(){
        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> animes = animeService.findByName("anime");

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("save returns anime when successful")
    void save_ReturnsAnime_WhenSuccessful(){

        Anime anime = animeService.save(AnimePostRequestBodyCreator.createAnimePostRequestBody());

        Assertions.assertThat(anime).isNotNull().isEqualTo(Animecreator.createValidAnime());

    }

    @Test
    @DisplayName("replace updates anime when successful")
    void replace_UpdatesAnime_WhenSuccessful(){

        Assertions.assertThatCode(() ->animeService.replace(AnimePutRequestBodyCreator.createAnimePutRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful(){

        Assertions.assertThatCode(() ->animeService.delete(1))
                .doesNotThrowAnyException();

    }
}
