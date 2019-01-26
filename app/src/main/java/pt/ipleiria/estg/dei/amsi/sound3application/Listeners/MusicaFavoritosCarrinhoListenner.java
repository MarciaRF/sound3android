package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Musica;

public interface MusicaFavoritosCarrinhoListenner {
    void onMusicasNosFavoritos(ArrayList<Musica> listaMusicasFavoritos);
    void onMusicasNosCarrinho(ArrayList<Musica> listaMusicasCarrinho);
}
