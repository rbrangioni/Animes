package com.br.brangioni.java.springboot2.service;

import com.br.brangioni.java.springboot2.domain.Anime;
import com.br.brangioni.java.springboot2.exception.BadRequestException;
import com.br.brangioni.java.springboot2.mapper.AnimeMapper;
import com.br.brangioni.java.springboot2.repository.AnimeRepository;
import com.br.brangioni.java.springboot2.request.AnimePostRequestBody;
import com.br.brangioni.java.springboot2.request.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);

    }


    public List<Anime> listAllNoPageable() {
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
    @Transactional(rollbackFor = Exception.class)
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        //System.out.println("Genero: "+animePostRequestBody.getGenero());
        //System.out.println("Tipo: "+animePostRequestBody.getTipo());
        System.out.println(animePostRequestBody.toString());
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
//        if(true)
//            throw new RuntimeException("Código inválido");
//
//        return save;

    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));
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
