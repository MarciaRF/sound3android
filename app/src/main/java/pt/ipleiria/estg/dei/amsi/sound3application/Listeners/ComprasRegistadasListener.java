package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Compra;

public interface ComprasRegistadasListener {
    void onResponseGetCompras(ArrayList<Compra> compras);
}
