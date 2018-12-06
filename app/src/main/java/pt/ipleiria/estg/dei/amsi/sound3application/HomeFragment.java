package pt.ipleiria.estg.dei.amsi.sound3application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import adaptadores.GridAlbunsAdaptador;
import models.Album;
import models.SingletonGestorAlbuns;

public class HomeFragment extends Fragment {

    private ArrayList<Album> albuns;
    private GridView gridViewRecentes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        gridViewRecentes = view.findViewById(R.id.gridViewAlbRecentes);
        albuns = SingletonGestorAlbuns.getInstance(getActivity().getApplicationContext()).getAlbuns();
        gridViewRecentes.setAdapter(new GridAlbunsAdaptador(getActivity().getApplicationContext(),albuns));

        return view;
    }


}
