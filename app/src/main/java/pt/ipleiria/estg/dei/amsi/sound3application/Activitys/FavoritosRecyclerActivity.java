package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import adaptadores.AlbumPesquisaAdapter;
import adaptadores.ArtistaPesquisaAdapter;
import adaptadores.GeneroPesquisaAdapter;
import adaptadores.MusicaAdapter;
import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.FavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class FavoritosRecyclerActivity extends AppCompatActivity implements FavoritosListener {

    public final static String MOSTRAS_DADOS = "TIPO DE DADOS";
    private int opcao;

    private long idUtilizador;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_recycler);

        // Vai Buscar Id do Utilizador as Shared
        idUtilizador = GestorSharedPref.getInstance(this).getIdUtilizador();

        //Receber Intent com a chave para saber o que mostrar
        opcao = getIntent().getIntExtra(MOSTRAS_DADOS, 0);

        SingletonGestorDados.getInstance(this).setFavoritosListener(this);

        //Fazer reuqest consoate a chave recebida
        switch (opcao){
            case 1:
                SingletonGestorDados.getInstance(this).getAllFavoritosAlbumAPI(this,
                        ConteudoJsonParser.isConnectionInternet(this),idUtilizador);
                break;
            case 2:
                SingletonGestorDados.getInstance(this).getAllFavoritosMusicaAPI(this,
                        ConteudoJsonParser.isConnectionInternet(this),idUtilizador);
                break;
            case 3:
                SingletonGestorDados.getInstance(this).getAllFavoritosGeneroAPI(this,
                        ConteudoJsonParser.isConnectionInternet(this),idUtilizador);
                break;
            case 4:
                SingletonGestorDados.getInstance(this).getAllFavoritosArtistaAPI(this,
                        ConteudoJsonParser.isConnectionInternet(this),idUtilizador);
                break;
        }

        // Código de Inicialização da Recycler View
        recyclerView = findViewById(R.id.rV_favoritos_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
    }

    @Override
    public void onRefreshAlbunsFavoritos(ArrayList<Album> albuns, ArrayList<Artista> artistas) {
        if (opcao == 1){
            AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(this, albuns, artistas);
            recyclerView.setAdapter(albumPesquisaAdapter);
        }
    }

    @Override
    public void onRefreshMusicasFavoritos(ArrayList<Musica> musicas, ArrayList<Album> album, ArrayList<Musica> carrinho) {
        if (opcao == 2){
            System.out.println("musicas"+musicas);
            MusicaAdapter musicaAdapter = new MusicaAdapter(this, musicas, album, idUtilizador, musicas, carrinho);
            recyclerView.setAdapter(musicaAdapter);
        }
    }

    @Override
    public void onRefreshGenerosFavoritos(ArrayList<Genero> generos) {
        if (opcao == 3){
            GeneroPesquisaAdapter generoPesquisaAdapter = new GeneroPesquisaAdapter(this, generos);
            recyclerView.setAdapter(generoPesquisaAdapter);
        }
    }

    @Override
    public void onRefreshArtistasFavoritos(ArrayList<Artista> artistas) {
        if (opcao == 4){
            ArtistaPesquisaAdapter artistaPesquisaAdapter = new ArtistaPesquisaAdapter(this, artistas);
            recyclerView.setAdapter(artistaPesquisaAdapter);
        }
    }
}
