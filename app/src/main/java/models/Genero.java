package models;

import java.io.Serializable;

public class Genero implements Serializable{
    private long IdGenero;
    private String Nome;
    private String Descricao;
    private String Imagem;


    public Genero(long idGenero, String nome, String descricao, String caminhoImagem){
        IdGenero = idGenero;
        Nome = nome;
        Descricao = descricao;
        Imagem = caminhoImagem;
    }

    public long getIdGenero() { return IdGenero; }

    public void setIdGenero(long idGenero) { IdGenero = idGenero; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public String getDescricao() { return Descricao; }

    public void setDescricao(String descricao) { Descricao = descricao; }

    public String getImagem() { return Imagem; }

    public void setImagem(String imagem) { Imagem = imagem; }
}
