package models;

import java.util.Date;

public class Album {
    private int IdAlbum;
    private String Nome;
    private String NomeAutor;
    private int Ano;
    private Integer Capa;

    public Album(int idAlbum, String nome, String nomeAutor, int ano, Integer capa) {
        IdAlbum = idAlbum;
        Nome = nome;
        NomeAutor = nomeAutor;
        Ano = ano;
        Capa = capa;
    }

    public int getIdAlbum() {
        return IdAlbum;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getNomeAutor() {
        return NomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        NomeAutor = nomeAutor;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }

    public Integer getCapa() {
        return Capa;
    }

    public void setCapa(Integer capa) {
        Capa = capa;
    }
}
