package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.FavoritoAlbum;

public interface AlbumFavoritosListener {
    void onRefreshFavoritosAlbum(ArrayList<FavoritoAlbum> favoritoAlbums);
    void checkAlbumInFavoritos(String check);
    void onRefreshAlbum(Album album);
    void onUpdateFavoritosAlbumBD (FavoritoAlbum favoritoAlbum);
}
