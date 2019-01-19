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
import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.HomeListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class HomeFragment extends Fragment implements HomeListener {

    View view;

    private RecyclerView recyclerViewTopAlbuns;
    private RecyclerView recyclerViewArtistas;
    private RecyclerView recyclerViewGeneros;
    private RecyclerView recyclerViewAlbunsRecentes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SingletonGestorConteudo.getInstance(getContext()).setConteudoListener(this);

        SingletonGestorConteudo.getInstance(getContext()).getTopAlbunsAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

        SingletonGestorConteudo.getInstance(getContext()).getAllGenerosAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

        SingletonGestorConteudo.getInstance(getContext()).getArtistasMaisVendidosAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

        SingletonGestorConteudo.getInstance(getContext()).getAlbunsMaisRecentesAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onRefreshAlbuns(ArrayList<Album> listaAlbuns) {
    }

    @Override
    public void onRefreshArtistas(ArrayList<Artista> listaArtistas) {
    }

    @Override
    public void onRefreshMusicas(ArrayList<Musica> listaMusicas) {
    }




    @Override
    public void onRefreshTopAlbuns(ArrayList<Album> listaTopAlbuns) {
        if(!listaTopAlbuns.isEmpty()){
            recyclerViewTopAlbuns = view.findViewById(R.id.rV_home_topAlbuns);
            recyclerViewTopAlbuns.setHasFixedSize(true);//Otimização
            recyclerViewTopAlbuns.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            AlbumAdapter albunsRecentesAdapter = new AlbumAdapter(getContext(), listaTopAlbuns);
            recyclerViewTopAlbuns.setAdapter(albunsRecentesAdapter);
        }
    }

    @Override
    public void onRefreshGeneros(ArrayList<Genero> listaGeneros) {
        if(!listaGeneros.isEmpty()){
            recyclerViewGeneros = view.findViewById(R.id.rV_home_Generos);
            recyclerViewGeneros.setHasFixedSize(true);
            recyclerViewGeneros.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            GeneroAdapter generoAdapter = new GeneroAdapter(getContext(), listaGeneros);
            recyclerViewGeneros.setAdapter(generoAdapter);
        }
    }

    @Override
    public void onRefreshArtistasMaisVendidos(ArrayList<Artista> listArtistasMaisVendidos) {
        if(listArtistasMaisVendidos != null){
            recyclerViewArtistas = view.findViewById(R.id.rV_home_artistas);
            recyclerViewArtistas.setHasFixedSize(true);
            recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            ArtistaAdapter artistaAdapter = new ArtistaAdapter(getContext(), listArtistasMaisVendidos);
            recyclerViewArtistas.setAdapter(artistaAdapter);
        }
    }

    @Override
    public void onRefreshAlbunsMaisRecentes(ArrayList<Album> listaAlbunsMaisVendidos) {
        if(listaAlbunsMaisVendidos != null)
        recyclerViewAlbunsRecentes = view.findViewById(R.id.rV_home_albunsRecentes);
        recyclerViewAlbunsRecentes.setHasFixedSize(true);//Otimização
        recyclerViewAlbunsRecentes.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        AlbumAdapter albunsRecentesAdapter = new AlbumAdapter(getContext(), listaAlbunsMaisVendidos);
        recyclerViewAlbunsRecentes.setAdapter(albunsRecentesAdapter);
    }


    /*@Override
    public void onResume() {
        SingletonGestorConteudo.getInstance(getContext()).getTopAlbunsAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

        SingletonGestorConteudo.getInstance(getContext()).getAllGenerosAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

        SingletonGestorConteudo.getInstance(getContext()).getArtistasMaisVendidosAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

        SingletonGestorConteudo.getInstance(getContext()).getAlbunsMaisRecentesAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()));

        super.onResume();
    }*/
}
