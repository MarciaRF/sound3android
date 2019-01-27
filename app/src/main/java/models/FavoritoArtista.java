package models;

import java.io.Serializable;

public class FavoritoArtista implements Serializable {

    private long IdUtilizador;
    private long IdArtista;

    public FavoritoArtista(long id_Utilizador, long id_Artista){
        IdUtilizador = id_Utilizador;
        IdArtista = id_Artista;
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
