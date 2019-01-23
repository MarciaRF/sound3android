package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.Genero;

public interface DetalhesGeneroListener {
    void onRefreshGenero(Genero genero);
    void onRefreshAlbunsGeneros(ArrayList<Album> albunsGenero);
    void checkGeneroInFavoritos(String check);
}
