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

import adaptadores.MusicaAdapter;
import adaptadores.MusicaAdapterAlbum;
import models.Album;
import models.Musica;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DetalhesAlbumActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.MusicaFavoritosCarrinhoListenner;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.MusicasListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class MusicaFragment extends Fragment implements MusicasListener, MusicaFavoritosCarrinhoListenner {

    View view;

    private RecyclerView myrecyclerview;

    private long idAlbum ;
    private long idUtilizador;

    private ArrayList<Musica> musicasFavoritos;
    private boolean existeMusicasFav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_musicas, container, false);

        DetalhesAlbumActivity atividade = (DetalhesAlbumActivity)getActivity();
        idAlbum = atividade.idAlbum;

        idUtilizador = GestorSharedPref.getInstance(getContext()).getIdUtilizador();

        SingletonGestorConteudo.getInstance(getContext()).setMusicasListener(this);
        SingletonGestorDados.getInstance(getContext()).setMusicaFavoritosCarrinhoListenner(this);

        SingletonGestorConteudo.getInstance(getContext()).getMusicasAlbumAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idAlbum);

        SingletonGestorDados.getInstance(getContext()).checkMusicasAlbumNosFavoritodAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idUtilizador, idAlbum);

        /*SingletonGestorConteudo.getInstance(getContext()).getMusicasAlbumCarrinho(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idAlbum);*/

        return view;
    }


    @Override
    public void onRefreshMusicas(ArrayList<Musica> listaMusicas, Album listaMusicasArtistas) {
        myrecyclerview = view.findViewById(R.id.rv_Musicas);
        MusicaAdapterAlbum musicaAdapter = new MusicaAdapterAlbum(getContext(), listaMusicas, listaMusicasArtistas, idUtilizador, musicasFavoritos);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(musicaAdapter);
    }

    @Override
    public void onMusicasNosFavoritos(ArrayList<Musica> listaMusicasFavoritos) {
        musicasFavoritos = listaMusicasFavoritos;
    }

    @Override
    public void onMusicasNosCarrinho(ArrayList<Musica> listaMusicasCarrinho) {

    }


}
