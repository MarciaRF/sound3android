package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.FavoritoMusica;
import models.Musica;

public interface CarrinhoListener {
    void onRefreshCarrinho(ArrayList<Musica> musicas, ArrayList<Album> album , ArrayList<Musica> musicasFavoritas);
    void onRefreshMusicasFavoritos(ArrayList<Musica> favoritoMusicas);
}
