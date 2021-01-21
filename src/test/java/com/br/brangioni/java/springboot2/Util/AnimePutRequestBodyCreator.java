package com.br.brangioni.java.springboot2.Util;

import com.br.brangioni.java.springboot2.request.AnimePutRequestBody;

public class AnimePutRequestBodyCreator {
    public static AnimePutRequestBody createAnimePutRequestBody(){
        return AnimePutRequestBody.builder()
                .id(Animecreator.createValidUpdateAnime().getId())
                .name(Animecreator.createValidUpdateAnime().getName())
                .build();
    }
}
