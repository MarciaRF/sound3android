package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import models.Album;
import models.Artista;

public interface DetalhesAlbumListener {
    void onRefreshAlbum(Album album);
    void onRefreshArtistaAlbum(Artista artista);
    void checkAlbumInFavoritos(String check);
    void checkAlbumInCarrinho(String check);
}
