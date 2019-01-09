package models;

import java.io.Serializable;

public class FavoritoAlbum implements Serializable {

    private long IdUtilizador;
    private long IdAlbum;

    public FavoritoAlbum (long id_Utilizador, long id_Album){
        IdUtilizador = id_Utilizador;
        IdAlbum = id_Album;
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
