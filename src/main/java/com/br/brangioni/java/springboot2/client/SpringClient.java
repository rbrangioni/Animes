package com.br.brangioni.java.springboot2.client;

import com.br.brangioni.java.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        //ResponseEntity<Anime> entity =  new RestTemplate().getForEntity("http://localhost:8080/animes/15010", Anime.class);

        //Anime anime = new RestTemplate().getForObject("http://localhost:8080/animes/15010",Anime.class);

        //Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all",Anime[].class);

        //Object object = new RestTemplate().getForObject("http://viacep.com.br/ws/91755210/json", Object.class);

        //log.info(entity);
        //log.info(Arrays.toString(animes));
        //log.info(object);

//        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all", HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Anime>>() {
//                });
//
//        log.info(exchange.getBody());

//        Anime cdz = Anime.builder().name("Tom e Jerry").genero("Desenho").tipo("TV").episodios(50).avaliacao(10.0).membros(500).build();
//        System.out.println("teste CDZ: "+cdz.toString());
//
//        Anime cdzsaved = new RestTemplate().postForObject("http://localhost:8080/animes/", cdz, Anime.class);
//        log.info("Anime Salvo {} ", cdzsaved);

//        Anime kingDom = Anime.builder().name("kingDom").genero("Anime").tipo("TV").episodios(50).avaliacao(10.0).membros(650).build();
//        Anime kingDomSaved =  new RestTemplate().postForObject("http://localhost:8080/animes/", kingDom, Anime.class );
//
//        log.info("Saved anime {}", kingDomSaved);

        Anime samurai = Anime.builder().name("Samurai Champloo").genero("Anime").tipo("TV").episodios(50).avaliacao(10.0).membros(650).build();
        ResponseEntity<Anime> samuraiSaved =  new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(samurai, createJsonHeaders()),
                Anime.class);

        log.info("Saved anime {}", samuraiSaved);

        Anime animeToBeUpdate = samuraiSaved.getBody();
        animeToBeUpdate.setName("Samurai Champloo 2");
        ResponseEntity<Void> samuraiUpdate =  new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdate, createJsonHeaders()),
                Void.class);

        log.info("Update anime {}", samuraiUpdate);

        ResponseEntity<Void> samuraiDelete =  new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
               null,
                Void.class,
                animeToBeUpdate.getId());

        log.info("Delete anime {}", samuraiDelete);

    }

    private static HttpHeaders createJsonHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return httpHeaders;
    }


}
