package models;

import java.io.Serializable;

public class FavoritoGenero implements Serializable {

    private long IdFavGenero;
    private long IdUtilizador;
    private long IdGenero;

    public FavoritoGenero(long idfav_genero, long id_Utilizador, long id_Genero){
        IdFavGenero = idfav_genero;
        IdUtilizador = id_Utilizador;
        IdGenero = id_Genero;
    }


    public long getIdFavGenero() {
        return IdFavGenero;
    }

    public void setIdFavGenero(long idFavGenero) {
        IdFavGenero = idFavGenero;
    }

    public long getIdUtilizador() {
        return IdUtilizador;
    }

    public void setIdUtilizador(long idUtilizador) {
        IdUtilizador = idUtilizador;
    }

    public long getIdGenero() {
        return IdGenero;
    }

    public void setIdGenero(long idGenero) {
        IdGenero = idGenero;
    }
}
