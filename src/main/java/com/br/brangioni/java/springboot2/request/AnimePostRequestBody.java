package com.br.brangioni.java.springboot2.request;

import lombok.Data;

@Data
public class AnimePostRequestBody {
    private String name;
    private String genero;
    private String tipo;
    private Integer episodios;
    private Double avaliacao;
    private Integer membros;

}
