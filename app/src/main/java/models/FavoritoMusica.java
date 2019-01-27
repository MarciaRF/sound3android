package models;

import java.io.Serializable;

public class FavoritoMusica implements Serializable {

    private long IdUtilizador;
    private long IdMusica;

    public FavoritoMusica(long id_Utilizador, long id_Musica){
        IdUtilizador = id_Utilizador;
        IdMusica = id_Musica;
    }



    public long getIdUtilizador() {
        return IdUtilizador;
    }

    public void setIdUtilizador(long idUtilizador) {
        IdUtilizador = idUtilizador;
    }

    public long getIdMusica() {
        return IdMusica;
    }

    public void setIdMusica(long idMusica) {
        IdMusica = idMusica;
    }
}
