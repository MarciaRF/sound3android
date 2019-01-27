package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.Musica;

public interface MusicasListener {
    void onRefreshMusicas(ArrayList<Musica> listaMusicas, Album listaMusicasArtistas);
}
