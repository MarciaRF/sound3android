package models;

import java.io.Serializable;
import java.sql.Date;

public class Comentario implements Serializable {
    private long idComentario;
    private String Conteudo;
    private String Data_Criacao;
    private long Id_Utilizador;
    private long Id_Album;

    public Comentario(long id_Comentario, String conteudo, String data_Criacao, long id_Utilizador, long id_Album){
        idComentario = id_Comentario;
        Conteudo = conteudo;
        Data_Criacao = data_Criacao;
        Id_Utilizador = id_Utilizador;
        Id_Album = id_Album;
    }

    public long getIdComentario() { return idComentario; }

    public void setIdComentario(long idComentario) { this.idComentario = idComentario; }

    public String getConteudo() { return Conteudo; }

    public void setConteudo(String conteudo) { Conteudo = conteudo; }

    public String getData_Criacao() { return Data_Criacao; }

    public void setData_Criacao(String data_Criacao) { Data_Criacao = data_Criacao; }

    public long getId_Utilizador() { return Id_Utilizador; }

    public void setId_Utilizador(long id_Utilizador) { Id_Utilizador = id_Utilizador; }

    public long getId_Album() { return Id_Album; }

    public void setId_Album(long id_Album) { Id_Album = id_Album; }
}
