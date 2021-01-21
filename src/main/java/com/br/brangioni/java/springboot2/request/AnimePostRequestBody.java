package com.br.brangioni.java.springboot2.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AnimePostRequestBody {
    @NotEmpty(message = "O nome não pode ser vazio")
    @NotNull(message = "O nome não pode ser Nulo")
    private String name;
    private String genero;
    private String tipo;

    @NotNull(message = "Quantidade de episódios não pode ser Null")
    private Integer episodios;
    private Double avaliacao;
    private Integer membros;

}
