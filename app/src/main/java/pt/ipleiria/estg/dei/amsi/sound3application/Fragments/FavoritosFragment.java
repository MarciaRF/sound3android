package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

import adaptadores.AlbumPesquisaAdapter;
import adaptadores.ArtistaPesquisaAdapter;
import adaptadores.GeneroAdapter;
import adaptadores.GeneroPesquisaAdapter;
import adaptadores.MusicaAdapter;

import models.Album;
import models.Artista;
import models.FavoritoAlbum;
import models.FavoritoArtista;
import models.FavoritoGenero;
import models.FavoritoMusica;
import models.Genero;
import models.Musica;

import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.FavoritosRecyclerActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.FavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.MusicaFavoritosCarrinhoListenner;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class FavoritosFragment extends Fragment implements FavoritosListener, MusicaFavoritosCarrinhoListenner {

    View view;

    private long idUtilizador;

    private RecyclerView recyclerViewMusicas;
    private RecyclerView recyclerViewAlbuns;
    private RecyclerView recyclerViewArtistas;
    private RecyclerView recyclerViewGeneros;

    private Button BtnVerAlbuns;
    private Button BtnVerMusicas;
    private Button BtnVerGeneros;
    private Button BtnVerArtistas;

    private ArrayList<Musica> musicasFavoritos;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idUtilizador = GestorSharedPref.getInstance(getContext()).getIdUtilizador();

        SingletonGestorDados.getInstance(getContext()).setFavoritosListener(this);
        SingletonGestorDados.getInstance(getContext()).setMusicaFavoritosCarrinhoListenner(this);

        SingletonGestorDados.getInstance(getContext()).getFavoritosArtistaAtividadeAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idUtilizador);

        SingletonGestorDados.getInstance(getContext()).getFavoritosAlbumAtividadeAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idUtilizador);

        SingletonGestorDados.getInstance(getContext()).getFavoritosGeneroAtividadeAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idUtilizador);

        SingletonGestorDados.getInstance(getContext()).getFavoritosMusicaAtividadeAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idUtilizador);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        BtnVerAlbuns =  view.findViewById(R.id.btn_favoritos_albuns);
        BtnVerMusicas =  view.findViewById(R.id.btn_favoritos_musicas);
        BtnVerGeneros =  view.findViewById(R.id.btn_favoritos_generos);
        BtnVerArtistas =  view.findViewById(R.id.btn_favoritos_artistas);

        BtnVerAlbuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoritosRecyclerActivity.class);
                intent.putExtra(FavoritosRecyclerActivity.MOSTRAS_DADOS, 1);
                startActivity(intent);
            }
        });

        BtnVerMusicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoritosRecyclerActivity.class);
                intent.putExtra(FavoritosRecyclerActivity.MOSTRAS_DADOS, 2);
                startActivity(intent);
            }
        });

        BtnVerGeneros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoritosRecyclerActivity.class);
                intent.putExtra(FavoritosRecyclerActivity.MOSTRAS_DADOS, 3);
                startActivity(intent);
            }
        });

        BtnVerArtistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoritosRecyclerActivity.class);
                intent.putExtra(FavoritosRecyclerActivity.MOSTRAS_DADOS, 4);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onRefreshAlbunsFavoritos(ArrayList<Album> albuns, ArrayList<Artista> artistas) {
        if(albuns != null){
            recyclerViewAlbuns = view.findViewById(R.id.rV_favoritos_albuns);
            recyclerViewAlbuns.setHasFixedSize(true);
            recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
            AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(getContext(), albuns, artistas);
            recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);

            if(albuns.size() < 5){
                BtnVerAlbuns.setEnabled(false);
                BtnVerAlbuns.setVisibility(view.INVISIBLE);
            }
        }
    }

    @Override
    public void onRefreshArtistasFavoritos(ArrayList<Artista> artistas) {
        if(artistas != null){
            recyclerViewArtistas = view.findViewById(R.id.rV_favoritos_artistas);
            recyclerViewArtistas.setHasFixedSize(true);
            recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
            ArtistaPesquisaAdapter artistaPesquisaAdapter = new ArtistaPesquisaAdapter(getContext(), artistas);
            recyclerViewArtistas.setAdapter(artistaPesquisaAdapter);

            if(artistas.size() < 5){
                BtnVerArtistas.setEnabled(false);
                BtnVerArtistas.setVisibility(view.INVISIBLE);
            }
        }
    }

    @Override
    public void onRefreshGenerosFavoritos(ArrayList<Genero> generos) {
        if(generos != null){
            recyclerViewGeneros = view.findViewById(R.id.rV_favoritos_generos);
            recyclerViewGeneros.setHasFixedSize(true);
            recyclerViewGeneros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
            GeneroPesquisaAdapter generoPesquisaAdapter = new GeneroPesquisaAdapter(getContext(), generos);
            recyclerViewGeneros.setAdapter(generoPesquisaAdapter);

            if(generos.size() < 5){
                BtnVerGeneros.setEnabled(false);
                BtnVerGeneros.setVisibility(view.INVISIBLE);
            }
        }
    }

    @Override
    public void onRefreshMusicasFavoritos(ArrayList<Musica> musicas, ArrayList<Album> albuns) {
        if (musicas != null){
            recyclerViewMusicas = view.findViewById(R.id.rV_favoritos_musicas);
            recyclerViewMusicas.setHasFixedSize(true);
            recyclerViewMusicas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
            MusicaAdapter musicaAdapter = new MusicaAdapter(getContext(), musicas, albuns, idUtilizador,musicasFavoritos);
            recyclerViewMusicas.setAdapter(musicaAdapter);

            if(musicas.size() < 5){
                BtnVerMusicas.setEnabled(false);
                BtnVerMusicas.setVisibility(view.INVISIBLE);
            }
        }
    }


    @Override
    public void onMusicasNosFavoritos(ArrayList<Musica> listaMusicasFavoritos) {
        musicasFavoritos = listaMusicasFavoritos;
    }

    @Override
    public void onMusicasNosCarrinho(ArrayList<Musica> listaMusicasCarrinho) {

    }
}
