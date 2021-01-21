package com.br.brangioni.java.springboot2.Util;

import com.br.brangioni.java.springboot2.request.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody.builder()
                .name(Animecreator.createAnimeToBeSaved().getName())
                .build();
    }
}
