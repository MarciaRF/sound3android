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
import models.Genero;
import models.Musica;

import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class FavoritosFragment extends Fragment {

    View view;

    private RecyclerView recyclerViewMusicas;
    private RecyclerView recyclerViewAlbuns;
    private RecyclerView recyclerViewArtistas;
    private RecyclerView recyclerViewGeneros;

    private ArrayList<Musica> lstMusicas;
    private ArrayList<Album> lstAlbuns;
    private ArrayList<Artista> lstArtistas;
    private ArrayList<Genero> lstGeneros;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favoritos, container, false);


        recyclerViewMusicas = view.findViewById(R.id.rV_favoritos_musicas);
        recyclerViewMusicas.setHasFixedSize(true);
        recyclerViewMusicas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        MusicaAdapter musicaAdapter = new MusicaAdapter(getContext(), lstMusicas);
        recyclerViewMusicas.setAdapter(musicaAdapter);

        recyclerViewAlbuns = view.findViewById(R.id.rV_favoritos_albuns);
        recyclerViewAlbuns.setHasFixedSize(true);
        recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(getContext(), lstAlbuns);
        recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);

        recyclerViewGeneros = view.findViewById(R.id.rV_favoritos_generos);
        recyclerViewGeneros.setHasFixedSize(true);
        recyclerViewGeneros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        GeneroAdapter generoAdapter = new GeneroAdapter(getContext(), lstGeneros);
        recyclerViewGeneros.setAdapter(generoAdapter);

        recyclerViewArtistas = view.findViewById(R.id.rV_favoritos_artistas);
        recyclerViewArtistas.setHasFixedSize(true);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        ArtistaPesquisaAdapter artistaPesquisaAdapter = new ArtistaPesquisaAdapter(getContext(), lstArtistas);
        recyclerViewArtistas.setAdapter(artistaPesquisaAdapter);

        return view;
    }



}
