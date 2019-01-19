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
import models.Musica;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.MusicasListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class MusicaFragment extends Fragment implements MusicasListener {

    View view;

    private RecyclerView myrecyclerview;

    private long idAlbum ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_musicas, container, false);

        /*Bundle bundle = this.getArguments();

        idAlbum = bundle.getLong("idAlbum");

        System.out.println("-->ERRO Fragment:" + idAlbum);*/

        System.out.println("FUNCIONA" + idAlbum);


        /*SingletonGestorConteudo.getInstance(getContext()).setMusicasListener(this);

        SingletonGestorConteudo.getInstance(getContext()).getMusicasAlbumAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idAlbum);*/

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public void onRefreshMusicas(ArrayList<Musica> listaMusicas) {
        myrecyclerview = view.findViewById(R.id.rv_Musicas);
        MusicaAdapter musicaAdapter = new MusicaAdapter(getContext(), listaMusicas);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(musicaAdapter);
    }


}
