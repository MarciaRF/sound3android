package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import adaptadores.CompraAdapter;
import adaptadores.DownloadAdapter;
import models.Album;
import models.Musica;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DownloadListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class DownloadActivity extends AppCompatActivity implements DownloadListener {

    private RecyclerView reyclerViewDownload;
    private long idUtilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);


        ArrayList utilizador = GestorSharedPref.getInstance(this).getUser();
        idUtilizador = Integer.parseInt(utilizador.get(0).toString());

        SingletonGestorDados.getInstance(this).setDownloadListener(this);

        SingletonGestorDados.getInstance(this).getMusicasDownloadAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idUtilizador);


        reyclerViewDownload = findViewById(R.id.rv_download);
        reyclerViewDownload.setHasFixedSize(true);
        reyclerViewDownload.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onRefreshMusicasDownload(ArrayList<Musica> musicas, ArrayList<Album> albumMusicas) {
        DownloadAdapter downloadAdapter = new DownloadAdapter(this, musicas, albumMusicas);
        reyclerViewDownload.setAdapter(downloadAdapter);
    }
}
