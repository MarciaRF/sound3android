package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Album;
import models.Musica;

public interface DownloadListener {
    void onRefreshMusicasDownload(ArrayList<Musica> musicas, ArrayList<Album> albumMusicas);
}
