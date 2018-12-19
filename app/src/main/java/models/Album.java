package models;

import java.util.Date;
import java.io.Serializable;

public class Album implements Serializable{
    private long IdAlbum;
    private String Nome;
    private Integer Ano;
    private Integer Imagem;
    private Integer Id_Autor;
    private Integer Id_Genero;


    public Album(long idalbum, String nome, Integer ano, Integer imagem, Integer id_autor, Integer id_genero) {
        IdAlbum = idalbum;
        Nome = nome;
        Ano = ano;
        Imagem = imagem;
        Id_Autor = id_autor;
        Id_Genero = id_genero;
    }

    public long getIdAlbum() { return IdAlbum; }

    public void setIdAlbum(long idAlbum) { IdAlbum = idAlbum; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public Integer getAno() { return Ano; }

    public void setAno(Integer ano) { Ano = ano; }

    public Integer getImagem() { return Imagem; }

    public void setImagem(Integer imagem) { Imagem = imagem; }

    public Integer getId_Autor() { return Id_Autor; }

    public void setId_Autor(Integer id_Autor) { Id_Autor = id_Autor; }

    public Integer getId_Genero() { return Id_Genero; }

    public void setId_Genero(Integer id_Genero) { Id_Genero = id_Genero;}
}
