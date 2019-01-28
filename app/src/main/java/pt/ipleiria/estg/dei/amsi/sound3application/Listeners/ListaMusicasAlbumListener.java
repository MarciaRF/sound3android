package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.List;

import models.Musica;

public interface ListaMusicasAlbumListener {
    void onDeleteFavMusica(List<Musica> mFav, Musica musica);
    void onCreateFavMusica(List<Musica>mFav, Musica musica);
    void onDeleteCartMusica(List<Musica> mCart, Musica musica);
    void onCreateCartMusica(List<Musica>mCart, Musica musica);
}
