package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;

public interface HomeListener {
    void onRefreshGeneros(ArrayList<Genero> listaGeneros);
    void onRefreshAlbuns(ArrayList<Album> listaAlbuns);
    void onRefreshArtistas(ArrayList<Artista> listaArtistas);
    void onRefreshMusicas(ArrayList<Musica> listaMusicas);


    void onRefreshTopAlbuns(ArrayList<Album> listaTopAlbuns, ArrayList<Artista> listaArtistasTopAlbuns);
    void onRefreshArtistasMaisVendidos(ArrayList<Artista> listArtistasMaisVendidos);
    void onRefreshAlbunsMaisRecentes(ArrayList<Album> listaAlbunsMaisVendidos, ArrayList<Artista> listaArtistasTopAlbuns);

    //void onRefreshTopAlbunsArtistas(ArrayList<Artista> listaArtistasTopAlbuns);

}
