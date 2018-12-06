package models;

import java.io.Serializable;

public class Artista implements Serializable{
    private long IdArtista;
    private String Nome;
    private String Nacionalidade;
    private Integer Ano;
    private Integer Imagem;

    private static int autoIncrementId = 0;

    public Artista(String nome, String nacionalidade, Integer ano, Integer imagem){
        IdArtista = autoIncrementId++;
        Nome = nome;
        Nacionalidade = nacionalidade;
        Ano = ano;
        Imagem = imagem;
    }

    public void setIdArtista(long idArtista) { IdArtista = idArtista; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public String getNacionalidade() { return Nacionalidade; }

    public void setNacionalidade(String nacionalidade) { Nacionalidade = nacionalidade; }

    public Integer getAno() { return Ano; }

    public void setAno(Integer ano) { Ano = ano; }

    public Integer getImagem() { return Imagem; }

    public void setImagem(Integer imagem) { Imagem = imagem; }
}
