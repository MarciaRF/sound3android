package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;

public interface FavoritosListener {
    void onRefreshAlbunsFavoritos(ArrayList<Album> albuns);
    void onRefreshArtistasFavoritos(ArrayList<Artista> artistas);
    void onRefreshGenerosFavoritos(ArrayList<Genero> generos);
    void onRefreshMusicasFavoritos(ArrayList<Musica> musicas);
}
