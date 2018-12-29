package models;

import java.io.Serializable;

public class FavoritoArtista implements Serializable {

    private long IdFavArtista;
    private long IdUtilizador;
    private long IdArtista;

    public FavoritoArtista(long idfav_artista, long id_Utilizador, long id_Artista){
        IdFavArtista = idfav_artista;
        IdUtilizador = id_Utilizador;
        IdArtista = id_Artista;
    }


    public long getIdFavArtista() {
        return IdFavArtista;
    }

    public void setIdFavArtista(long idFavArtista) {
        IdFavArtista = idFavArtista;
    }

    public long getIdUtilizador() {
        return IdUtilizador;
    }

    public void setIdUtilizador(long idUtilizador) {
        IdUtilizador = idUtilizador;
    }

    public long getIdArtista() {
        return IdArtista;
    }

    public void setIdArtista(long idArtista) {
        IdArtista = idArtista;
    }
}
