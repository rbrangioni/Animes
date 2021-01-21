package com.br.brangioni.java.springboot2.mapper;

import com.br.brangioni.java.springboot2.domain.Anime;
import com.br.brangioni.java.springboot2.request.AnimePostRequestBody;
import com.br.brangioni.java.springboot2.request.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePotRequestBody);
}
