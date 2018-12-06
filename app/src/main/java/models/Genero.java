package models;

import java.io.Serializable;

public class Genero implements Serializable{
    private long IdGenero;
    private String Nome;
    private String Descricao;
    private Integer Imagem;

    private static int autoIncrementId = 0;

    public Genero(String nome, String descricao, Integer imagem){
        IdGenero = autoIncrementId++;
        Nome = nome;
        Descricao = descricao;
        Imagem = imagem;
    }

    public void setIdGenero(long idGenero) { IdGenero = idGenero; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public String getDescricao() { return Descricao; }

    public void setDescricao(String descricao) { Descricao = descricao; }

    public Integer getImagem() { return Imagem; }

    public void setImagem(Integer imagem) { Imagem = imagem; }
}
