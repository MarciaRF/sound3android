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

import java.util.ArrayList;

import adaptadores.AlbumAdapter;
import adaptadores.ArtistaAdapter;
import adaptadores.GeneroAdapter;
import adaptadores.MusicaAdapter;
import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;


public class HomeFragment extends Fragment{

    View view;

    private ArrayList<Album> lstAlbuns;
    private ArrayList<Musica> lstMusicas;
    private ArrayList<Artista> lstArtistas;
    private ArrayList<Genero> lstGeneros;

    private RecyclerView recyclerViewAlbuns;
    private RecyclerView recyclerViewArtistas;
    private RecyclerView recyclerViewGeneros;
    private RecyclerView recyclerViewAlbunsRecentes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstAlbuns = SingletonGestorConteudo.getInstance(getContext()).getAlbunsBD();
        lstArtistas = SingletonGestorConteudo.getInstance(getContext()).getArtistasBD();
        lstMusicas = SingletonGestorConteudo.getInstance(getContext()).getMusicasBD();
        SingletonGestorConteudo.getInstance(getContext()).getAllGenerosAPI(getContext(),ConteudoJsonParser.isConnectionInternet(getContext()));
        lstGeneros = SingletonGestorConteudo.getInstance(getContext()).getGenerosBD();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        //RV TOP ALBUNS
        recyclerViewAlbuns = view.findViewById(R.id.rV_home_topAlbuns);
        recyclerViewAlbuns.setHasFixedSize(true);//Otimização
        recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        AlbumAdapter albumAdapter = new AlbumAdapter(getContext(), lstAlbuns);
        recyclerViewAlbuns.setAdapter(albumAdapter);

        //RV Generos
        recyclerViewGeneros = view.findViewById(R.id.rV_home_Generos);
        recyclerViewGeneros.setHasFixedSize(true);
        recyclerViewGeneros.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        GeneroAdapter generoAdapter = new GeneroAdapter(getContext(), lstGeneros);
        recyclerViewGeneros.setAdapter(generoAdapter);

        //RV ARTISTAS DO MOMENTO
        recyclerViewArtistas = view.findViewById(R.id.rV_home_artistas);
        recyclerViewArtistas.setHasFixedSize(true);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ArtistaAdapter artistaAdapter = new ArtistaAdapter(getContext(), lstArtistas);
        recyclerViewArtistas.setAdapter(artistaAdapter);

        //RV ALBUNS MAIS RECENTES
        recyclerViewAlbunsRecentes = view.findViewById(R.id.rV_home_albunsRecentes);
        recyclerViewAlbunsRecentes.setHasFixedSize(true);//Otimização
        recyclerViewAlbunsRecentes.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        AlbumAdapter albunsRecentesAdapter = new AlbumAdapter(getContext(), lstAlbuns);
        recyclerViewAlbunsRecentes.setAdapter(albunsRecentesAdapter);


        return view;
    }



}
