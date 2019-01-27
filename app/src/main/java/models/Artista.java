package models;

import java.io.Serializable;

public class Artista implements Serializable{
    private long IdArtista;
    private String Nome;
    private String Nacionalidade;
    private Integer Ano;
    private String Imagem;


    public Artista(long idArtista, String nome, String nacionalidade, Integer ano, String imagem){
        IdArtista = idArtista;
        Nome = nome;
        Nacionalidade = nacionalidade;
        Ano = ano;
        Imagem = imagem;
    }


    public long getIdArtista() { return IdArtista; }

    public void setIdArtista(long idArtista) { IdArtista = idArtista; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public String getNacionalidade() { return Nacionalidade; }

    public void setNacionalidade(String nacionalidade) { Nacionalidade = nacionalidade; }

    public Integer getAno() { return Ano; }

    public void setAno(Integer ano) { Ano = ano; }

    public String getImagem() { return Imagem; }

    public void setImagem(String imagem) { Imagem = imagem; }
}
