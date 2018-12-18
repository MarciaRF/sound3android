package models;

import java.util.Date;
import java.io.Serializable;

public class Album implements Serializable{
    private long IdAlbum;
    private String Nome;
    private Integer Ano;
    private Integer Imagem;
    private Integer IdGenero_Autor;
    private Integer IdGenero_Album;


    public Album(Long idalbum, String nome, Integer ano, Integer imagem, Integer idGenero_Autor, Integer idGenero_Album) {
        IdAlbum = idalbum;
        Nome = nome;
        Ano = ano;
        Imagem = imagem;
        IdGenero_Autor = idGenero_Autor;
        IdGenero_Album = idGenero_Album;
    }

    public long getIdAlbum() { return IdAlbum; }

    public void setIdAlbum(long idAlbum) { IdAlbum = idAlbum; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public Integer getAno() { return Ano; }

    public void setAno(Integer ano) { Ano = ano; }

    public Integer getImagem() { return Imagem; }

    public void setImagem(Integer imagem) { Imagem = imagem; }

    public Integer getIdGenero_Autor() { return IdGenero_Autor; }

    public void setIdGenero_Autor(Integer idGenero_Autor) { IdGenero_Autor = idGenero_Autor; }

    public Integer getIdGenero_Album() { return IdGenero_Album; }

    public void setIdGenero_Album(Integer idGenero_Album) { IdGenero_Album = idGenero_Album; }

}
