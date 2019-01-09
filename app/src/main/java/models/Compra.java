package models;

import java.io.Serializable;
import java.util.Date;

public class Compra implements Serializable {

    private long IDCompra;
    private Date DataCompra;
    private int ValorTotal;
    private int Efetividade;
    private long IDUtilizador;


    public Compra(int id_compra, Date data_compra, int valor_total, int efetividade, long id_utilizador){
        IDCompra = id_compra;
        DataCompra = data_compra;
        ValorTotal = valor_total;
        Efetividade = efetividade;
        IDUtilizador = id_utilizador;
    }

    public long getIDCompra() { return IDCompra; }

    public void setIDCompra(long IDCompra) { this.IDCompra = IDCompra; }

    public Date getDataCompra() { return DataCompra; }

    public void setDataCompra(Date dataCompra) { DataCompra = dataCompra; }

    public int getValorTotal() { return ValorTotal; }

    public void setValorTotal(int valorTotal) { ValorTotal = valorTotal; }

    public int getEfetividade() { return Efetividade; }

    public void setEfetividade(int efetividade) { Efetividade = efetividade; }

    public long getIDUtilizador() { return IDUtilizador; }

    public void setIDUtilizador(long IDUtilizador) { this.IDUtilizador = IDUtilizador; }
}
