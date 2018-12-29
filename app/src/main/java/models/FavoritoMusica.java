package models;

import java.io.Serializable;

public class FavoritoMusica implements Serializable {

    private long IdFavMusica;
    private long IdUtilizador;
    private long IdMusica;

    public FavoritoMusica(long idfav_musica, long id_Utilizador, long id_Musica){
        IdFavMusica = idfav_musica;
        IdUtilizador = id_Utilizador;
        IdMusica = id_Musica;
    }


    public long getIdFavMusica() {
        return IdFavMusica;
    }

    public void setIdFavMusica(long idFavMusica) {
        IdFavMusica = idFavMusica;
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
