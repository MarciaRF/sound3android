package models;

import java.io.Serializable;

public class LinhaCompra implements Serializable {

    private long IdCompra;
    private long IdMusica;


    public LinhaCompra(long id_compra, long id_musica){
        IdCompra = id_compra;
        IdMusica = id_musica;
    }


    public long getIdCompra() { return IdCompra; }

    public void setIdCompra(long idCompra) {
        IdCompra = idCompra;
    }

    public long getIdMusica() {
        return IdMusica;
    }

    public void setIdMusica(long idMusica) {
        IdMusica = idMusica;
    }
}
