package models;

import java.io.Serializable;

public class Musica implements Serializable{
    private long IdMusica;
    private String Nome;
    private String Duracao;
    private int Preco;
    private int IdAlbum;
    private String CaminhoMusica;
    private Integer Posicao;


    public Musica(long idMusica, String nome, String duracao, int preco, int idAlbum, String caminhoMusica, Integer posicao){
        IdMusica = idMusica;
        Nome = nome;
        Duracao = duracao;
        Preco = preco;
        IdAlbum = idAlbum;
        CaminhoMusica = caminhoMusica;
        Posicao = posicao;
    }

    public long getIdMusica() { return IdMusica; }

    public void setIdMusica(long idMusica) { IdMusica = idMusica; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public String getDuracao() { return Duracao; }

    public void setDuracao(String duracao) { Duracao = duracao; }

    public int getPreco() { return Preco; }

    public void setPreco(int preco) { Preco = preco; }

    public int getIdAlbum() { return IdAlbum; }

    public void setIdAlbum(int idAlbum) { IdAlbum = idAlbum; }

    public String getCaminhoMusica() { return CaminhoMusica; }

    public void setCaminhoMusica(String caminhoMusica) { CaminhoMusica = caminhoMusica; }

    public Integer getPosicao() { return Posicao; }

    public void setPosicao(Integer posicao) { Posicao = posicao; }
}
