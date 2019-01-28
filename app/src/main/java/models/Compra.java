package models;

import java.io.Serializable;
import java.util.Date;

public class Compra implements Serializable {

    private long IDCompra;
    private String DataCompra;
    private int ValorTotal;
    private boolean Efetivada;
    private long IDUtilizador;


    public Compra(int id_compra, String data_compra, int valor_total, boolean efetivada, long id_utilizador){
        IDCompra = id_compra;
        DataCompra = data_compra;
        ValorTotal = valor_total;
        Efetivada = efetivada;
        IDUtilizador = id_utilizador;
    }

    public long getIDCompra() { return IDCompra; }

    public void setIDCompra(long IDCompra) { this.IDCompra = IDCompra; }

    public String getDataCompra() {
        return DataCompra;
    }

    public void setDataCompra(String dataCompra) {
        DataCompra = dataCompra;
    }

    public int getValorTotal() { return ValorTotal; }

    public void setValorTotal(int valorTotal) { ValorTotal = valorTotal; }

    public boolean getEfetivada() {
        return Efetivada;
    }
    public void setEfetivada(boolean efetivada) {
        Efetivada = efetivada;
    }

    public long getIDUtilizador() { return IDUtilizador; }

    public void setIDUtilizador(long IDUtilizador) { this.IDUtilizador = IDUtilizador; }
}
