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
import java.util.List;

import adaptadores.MusicaAdapter;
import models.Musica;
import adaptadores.MusicaAdapter;
import models.Musica;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class MusicaFragment extends Fragment {

    View view;

    private RecyclerView myrecyclerview;
    private List<Musica> lstMusica;

    public MusicaFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_musicas, container, false);
        myrecyclerview = view.findViewById(R.id.rv_Musicas);
        MusicaAdapter musicaAdapter = new MusicaAdapter(getContext(), lstMusica);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(musicaAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstMusica = SingletonGestorConteudo.getInstance(getContext()).getMusicasBD();
    }

    @Override
    public void onPause() {
        super.onPause();

        lstMusica.clear();
    }
}
