package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.Artista;

public interface DetalhesArtistaListener {
    void onRefreshArtista(Artista artista);
    void onRefreshAbunsArtista(ArrayList<Album> albunsArtista, ArrayList<Artista> artistas);
    void checkArtistaInFavoritos(String check);
}
