package models;

import java.io.Serializable;

public class FavoritoAlbum implements Serializable {

    private long IdFavAlbum;
    private long IdUtilizador;
    private long IdAlbum;

    public FavoritoAlbum (long idfav_album, long id_Utilizador, long id_Album){
        IdFavAlbum = idfav_album;
        IdUtilizador = id_Utilizador;
        IdAlbum = id_Album;
    }

    public long getIdFavAlbum() {
        return IdFavAlbum;
    }

    public void setIdFavAlbum(long idFavAlbum) {
        IdFavAlbum = idFavAlbum;
    }

    public long getIdUtilizador() {
        return IdUtilizador;
    }

    public void setIdUtilizador(long idUtilizador) {
        IdUtilizador = idUtilizador;
    }

    public long getIdAlbum() {
        return IdAlbum;
    }

    public void setIdAlbum(long idAlbum) {
        IdAlbum = idAlbum;
    }
}
