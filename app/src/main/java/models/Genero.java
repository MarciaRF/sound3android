package models;

import java.io.Serializable;

public class Genero implements Serializable{
    private long IdGenero;
    private String Nome;
    private String Descricao;
    private Integer Imagem;


    public Genero(long idGenero, String nome, String descricao, Integer imagem){
        IdGenero = idGenero;
        Nome = nome;
        Descricao = descricao;
        Imagem = imagem;
    }

    public long getIdGenero() { return IdGenero; }

    public void setIdGenero(long idGenero) { IdGenero = idGenero; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public String getDescricao() { return Descricao; }

    public void setDescricao(String descricao) { Descricao = descricao; }

    public Integer getImagem() { return Imagem; }

    public void setImagem(Integer imagem) { Imagem = imagem; }
}
