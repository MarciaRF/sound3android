package models;

import java.io.Serializable;

public class FavoritoGenero implements Serializable {

    private long IdUtilizador;
    private long IdGenero;

    public FavoritoGenero(long id_Utilizador, long id_Genero){
        IdUtilizador = id_Utilizador;
        IdGenero = id_Genero;
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
