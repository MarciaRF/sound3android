package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import adaptadores.AlbumPesquisaAdapter;
import adaptadores.ArtistaPesquisaAdapter;
import adaptadores.GeneroAdapter;
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
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class FavoritosFragment extends Fragment {

    View view;

    private RecyclerView recyclerViewMusicas;
    private RecyclerView recyclerViewAlbuns;
    private RecyclerView recyclerViewArtistas;
    private RecyclerView recyclerViewGeneros;

    private ArrayList<FavoritoMusica> favMusicas;
    private ArrayList<FavoritoAlbum> favAlbums;
    private ArrayList<FavoritoArtista> favArtistas;
    private ArrayList<FavoritoGenero> favGeneros;

    private ArrayList<Musica> lstMusicas;
    private ArrayList<Album> lstAlbuns;
    private ArrayList<Artista> lstArtistas;
    private ArrayList<Genero> lstGeneros;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        favMusicas = SingletonGestorDados.getInstance(getContext()).getFavoritosMusicaBD();
        favAlbums = SingletonGestorDados.getInstance(getContext()).getFavoritosAlbumBD();
        favArtistas = SingletonGestorDados.getInstance(getContext()).getFavoritosArtistaBD();
        favGeneros = SingletonGestorDados.getInstance(getContext()).getFavoritosGeneroBD();

        if(favMusicas != null){
            for (FavoritoMusica musicas: favMusicas) {
                lstMusicas.add(SingletonGestorConteudo.getInstance(getContext()).getMusica(musicas.getIdMusica()));
            }
        }

        if(favAlbums != null){
            for (FavoritoAlbum albuns: favAlbums) {
                lstAlbuns.add(SingletonGestorConteudo.getInstance(getContext()).getAlbum(albuns.getIdAlbum()));
            }
        }

        if(favArtistas != null){
            for (FavoritoArtista artistas: favArtistas) {
                lstArtistas.add(SingletonGestorConteudo.getInstance(getContext()).getArtista(artistas.getIdArtista()));
            }
        }

        if(favGeneros != null){
            for (FavoritoGenero generos: favGeneros) {
                lstGeneros.add(SingletonGestorConteudo.getInstance(getContext()).getGenero(generos.getIdGenero()));
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favoritos, container, false);


        if(lstMusicas != null){
            recyclerViewMusicas = view.findViewById(R.id.rV_favoritos_musicas);
            recyclerViewMusicas.setHasFixedSize(true);
            recyclerViewMusicas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
            MusicaAdapter musicaAdapter = new MusicaAdapter(getContext(), lstMusicas);
            recyclerViewMusicas.setAdapter(musicaAdapter);
        }

        if(lstAlbuns != null){
            recyclerViewAlbuns = view.findViewById(R.id.rV_favoritos_albuns);
            recyclerViewAlbuns.setHasFixedSize(true);
            recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
            AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(getContext(), lstAlbuns);
            recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);
        }


        if(lstGeneros != null){
            recyclerViewGeneros = view.findViewById(R.id.rV_favoritos_generos);
            recyclerViewGeneros.setHasFixedSize(true);
            recyclerViewGeneros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
            GeneroAdapter generoAdapter = new GeneroAdapter(getContext(), lstGeneros);
            recyclerViewGeneros.setAdapter(generoAdapter);
        }

        if(lstArtistas != null){
            recyclerViewArtistas = view.findViewById(R.id.rV_favoritos_artistas);
            recyclerViewArtistas.setHasFixedSize(true);
            recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
            ArtistaPesquisaAdapter artistaPesquisaAdapter = new ArtistaPesquisaAdapter(getContext(), lstArtistas);
            recyclerViewArtistas.setAdapter(artistaPesquisaAdapter);
        }


        return view;
    }



}
