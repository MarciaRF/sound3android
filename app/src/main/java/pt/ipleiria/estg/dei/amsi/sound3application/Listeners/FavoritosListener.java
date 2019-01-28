package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;
import java.util.List;

import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;

public interface FavoritosListener {
    void onRefreshAlbunsFavoritos(ArrayList<Album> albuns, ArrayList<Artista> artistas);
    void onRefreshArtistasFavoritos(ArrayList<Artista> artistas);
    void onRefreshGenerosFavoritos(ArrayList<Genero> generos);
    void onRefreshMusicasFavoritos(ArrayList<Musica> musicas, ArrayList<Album> album, ArrayList<Musica> carrinho);
}
