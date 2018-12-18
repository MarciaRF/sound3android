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
import adaptadores.MusicaAdapter;
import models.Album;
import models.Artista;
import models.Musica;
import pt.ipleiria.estg.dei.amsi.sound3application.R;


public class HomeFragment extends Fragment{

    View view;

    private ArrayList<Album> lstAlbuns;
    private ArrayList<Musica> lstMusica;
    private ArrayList<Artista> lstArtista;

    private RecyclerView recyclerViewAlbuns;
    private RecyclerView recyclerViewArtista;
    private RecyclerView recyclerViewMusicas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstAlbuns = new ArrayList<>();
        lstAlbuns.add(new Album("Filhos do Rossi", 1,R.drawable.filhos_do_rossi,3,4));
        lstAlbuns.add(new Album("Filhos do Rossi", 1,R.drawable.filhos_do_rossi,3,4));
        lstAlbuns.add(new Album("Filhos do Rossi", 1,R.drawable.filhos_do_rossi,3,4));
        lstAlbuns.add(new Album("Filhos do Rossi", 1,R.drawable.filhos_do_rossi,3,4));
        lstAlbuns.add(new Album("Filhos do Rossi", 1,R.drawable.filhos_do_rossi,3,4));


        lstMusica = new ArrayList<>();
        lstMusica.add(new Musica("Filhos do Rossi", "1",3,3,"ole", 9));
        lstMusica.add(new Musica("Filhos do Rossi", "1",3,3,"ole", 9));
        lstMusica.add(new Musica("Filhos do Rossi", "1",3,3,"ole", 9));
        lstMusica.add(new Musica("Filhos do Rossi", "1",3,3,"ole", 9));
        lstMusica.add(new Musica("Filhos do Rossi", "1",3,3,"ole", 9));

        lstArtista = new ArrayList<>();
        lstArtista.add(new Artista("2Pac","EUA", 1990, R.drawable.topac));
        lstArtista.add(new Artista("2Pac","EUA", 1990, R.drawable.topac));
        lstArtista.add(new Artista("2Pac","EUA", 1990, R.drawable.topac));
        lstArtista.add(new Artista("2Pac","EUA", 1990, R.drawable.topac));
        lstArtista.add(new Artista("2Pac","EUA", 1990, R.drawable.topac));



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        //RV TOP ALBUNS
        recyclerViewAlbuns = view.findViewById(R.id.rV_home_albuns);
        recyclerViewAlbuns.setHasFixedSize(true);//Otimização
        recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        AlbumAdapter albumAdapter = new AlbumAdapter(getContext(), lstAlbuns);

        recyclerViewAlbuns.setAdapter(albumAdapter);


        //RV ARTISTAS DO MOMENTO
        recyclerViewArtista = view.findViewById(R.id.rV_home_artistas);
        recyclerViewArtista.setHasFixedSize(true);
        recyclerViewArtista.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArtistaAdapter artistaAdapter = new ArtistaAdapter(getContext(), lstArtista);

        recyclerViewArtista.setAdapter(artistaAdapter);


        //RV FIRE
        recyclerViewMusicas = view.findViewById(R.id.rV_home_musicas);
        recyclerViewMusicas.setHasFixedSize(true);
        MusicaAdapter musicaAdapter = new MusicaAdapter(getContext(), lstMusica);
        recyclerViewMusicas.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMusicas.setAdapter(musicaAdapter);



        return view;
    }



}
