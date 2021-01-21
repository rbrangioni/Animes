package com.br.brangioni.java.springboot2.Util;

import com.br.brangioni.java.springboot2.domain.Anime;

public class Animecreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder().name("Padrinhos Mágicos").genero("Anime").tipo("TV").episodios(50).avaliacao(10.0).membros(650).build();
    }

    public static Anime createValidAnime(){
        return Anime.builder().id(15010L).name("Padrinhos Mágicos").genero("Anime").tipo("TV").episodios(50).avaliacao(10.0).membros(650).build();
    }

    public static Anime createValidUpdateAnime(){
        return Anime.builder().id(15010L).name("Padrinhos Mágicos - Zero").genero("Anime").tipo("TV").episodios(50).avaliacao(10.0).membros(650).build();
    }
}
