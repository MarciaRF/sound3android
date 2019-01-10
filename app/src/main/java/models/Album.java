package models;

import java.util.Date;
import java.io.Serializable;

public class Album implements Serializable{
    private long IdAlbum;
    private String Nome;
    private int Ano;
    private int Preco;
    private String Imagem;
    private int Id_Autor;
    private int Id_Genero;


    public Album(long idalbum, String nome, int ano, int preco, String imagem, int id_autor, int id_genero) {
        IdAlbum = idalbum;
        Nome = nome;
        Ano = ano;
        Preco = preco;
        Imagem = imagem;
        Id_Autor = id_autor;
        Id_Genero = id_genero;
    }

    public long getIdAlbum() { return IdAlbum; }

    public void setIdAlbum(long idAlbum) { IdAlbum = idAlbum; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }

    public String getImagem() { return Imagem; }

    public int getPreco() { return Preco; }

    public void setPreco(int preco) { Preco = preco; }

    public void setImagem(String imagem) { Imagem = imagem; }

    public int getId_Autor() {
        return Id_Autor;
    }

    public void setId_Autor(int id_Autor) {
        Id_Autor = id_Autor;
    }

    public int getId_Genero() {
        return Id_Genero;
    }

    public void setId_Genero(int id_Genero) {
        Id_Genero = id_Genero;
    }
}
