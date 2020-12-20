package com.br.brangioni.java.springboot2.service;

import com.br.brangioni.java.springboot2.domain.Anime;
import com.br.brangioni.java.springboot2.exception.BadRequestException;
import com.br.brangioni.java.springboot2.mapper.AnimeMapper;
import com.br.brangioni.java.springboot2.repository.AnimeRepository;
import com.br.brangioni.java.springboot2.request.AnimePostRequestBody;
import com.br.brangioni.java.springboot2.request.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();

    }

    public Anime findByIdOrTrowbadRequestException(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime Not Found!"));
//                animes.stream()
//                .filter(anime -> anime.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));

    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        //System.out.println("Genero: "+animePostRequestBody.getGenero());
        //System.out.println("Tipo: "+animePostRequestBody.getTipo());
        System.out.println(animePostRequestBody.toString());
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));

    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrTrowbadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        System.out.println(animePutRequestBody.toString());
        Anime savedAnime = findByIdOrTrowbadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
//        Anime anime = Anime.builder()
//                .id(savedAnime.getId())
//                .name(animePutRequestBody.getName()).
//                build();
        animeRepository.save(anime);

    }
}
