package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;

public interface PesquisaListener {
    void onRefreshAlbunsPesquisa(ArrayList<Album> pesquisaAlbuns);
    void onRefreshGenerosPesquisa(ArrayList<Genero> pesquisaGeneros);
    void onRefreshArtistasPesquisa(ArrayList<Artista> pesquisaArtistas);
    void onRefreshAMusicasPesquisa(ArrayList<Musica> pesquisaMusicas);
}
