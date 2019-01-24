package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import adaptadores.AlbumAdapter;
import adaptadores.AlbumPesquisaAdapter;
import adaptadores.ArtistaAdapter;
import adaptadores.ArtistaPesquisaAdapter;
import adaptadores.GeneroAdapter;
import adaptadores.GeneroPesquisaAdapter;
import adaptadores.MusicaAdapter;
import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.PesquisaListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;

public class PesquisaActivity extends AppCompatActivity implements PesquisaListener {

    public static final String PESQUISA = "PESQUISA";

    private ArrayList<Album> lstAlbum;
    private ArrayList<Musica> lstMusica;
    private ArrayList<Artista> lstArtista;
    private ArrayList<Genero> lstGenero;

    private RecyclerView recyclerViewAlbuns;
    private RecyclerView recyclerViewArtistas;
    private RecyclerView recyclerViewGeneros;
    private RecyclerView recyclerViewMusicas;

    private String pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pesquisa = getIntent().getStringExtra(PESQUISA);

        this.setTitle("Resultados de Pesquisa " + "(" + pesquisa + ")" );

        lstMusica = new ArrayList<>();
        lstAlbum = new ArrayList<>();
        lstArtista = new ArrayList<>();
        lstGenero = new ArrayList<>();

        for (Album temp : SingletonGestorConteudo.getInstance(getApplicationContext()).getAlbunsBD()) {
            if (temp.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                lstAlbum.add(temp);
            }
        }

        for (Musica temp: SingletonGestorConteudo.getInstance(getApplicationContext()).getMusicasBD()) {
            if (temp.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                System.out.println("---->Temp : " + temp.getNome());
                lstMusica.add(temp);
            }
        }

        for (Artista temp: SingletonGestorConteudo.getInstance(getApplicationContext()).getArtistasBD()) {
            if (temp.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                lstArtista.add(temp);
            }
        }

        for (Genero temp: SingletonGestorConteudo.getInstance(getApplicationContext()).getGenerosBD()) {
            if (temp.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                lstGenero.add(temp);
            }
        }


        SingletonGestorDados.getInstance(this).setPesquisaListener(this);

        SingletonGestorDados.getInstance(this).getPesquisaAlbunsAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), pesquisa);

        SingletonGestorDados.getInstance(this).getPesquisaGenerosAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), pesquisa);

        SingletonGestorDados.getInstance(this).getPesquisaArtistasAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), pesquisa);

        SingletonGestorDados.getInstance(this).getPesquisaMusicasAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), pesquisa);

    }

    @Override
    public void onRefreshAlbunsPesquisa(ArrayList<Album> pesquisaAlbuns) {
        if(pesquisaAlbuns != null){
            recyclerViewAlbuns = findViewById(R.id.rV_pesquisa_albuns);
            recyclerViewAlbuns.setHasFixedSize(true);//Otimização
            recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(this, pesquisaAlbuns);
            recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);
        }
    }

    @Override
    public void onRefreshGenerosPesquisa(ArrayList<Genero> pesquisaGeneros) {
        if(pesquisaGeneros != null){
            recyclerViewGeneros = findViewById(R.id.rV_pesquisa_generos);
            recyclerViewGeneros.setHasFixedSize(true);//Otimização
            recyclerViewGeneros.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            GeneroPesquisaAdapter generoPesquisaAdapter = new GeneroPesquisaAdapter(this, pesquisaGeneros);
            recyclerViewGeneros.setAdapter(generoPesquisaAdapter);
        }
    }

    @Override
    public void onRefreshArtistasPesquisa(ArrayList<Artista> pesquisaArtistas) {
        if(pesquisaArtistas != null){
            recyclerViewArtistas = findViewById(R.id.rV_pesquisa_artistas);
            recyclerViewArtistas.setHasFixedSize(true);//Otimização
            recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            ArtistaPesquisaAdapter artistaPesquisaAdapter = new ArtistaPesquisaAdapter(this, pesquisaArtistas);
            recyclerViewArtistas.setAdapter(artistaPesquisaAdapter);
        }
    }

    @Override
    public void onRefreshAMusicasPesquisa(ArrayList<Musica> pesquisaMusicas) {
        if(pesquisaMusicas != null){
            recyclerViewMusicas = findViewById(R.id.rV_pesquisa_musicas);
            recyclerViewMusicas.setHasFixedSize(true);//Otimização
            recyclerViewMusicas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            MusicaAdapter musicasAdapter = new MusicaAdapter(this, pesquisaMusicas);
            recyclerViewMusicas.setAdapter(musicasAdapter);
        }
    }
}
