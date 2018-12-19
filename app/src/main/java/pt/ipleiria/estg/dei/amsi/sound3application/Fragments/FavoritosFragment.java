package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adaptadores.AlbumAdapter;
import adaptadores.ViewPagerAdapter;

import adaptadores.ViewPagerAdapter;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class FavoritosFragment extends Fragment {

    View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        /*// Codigo das Tabs
        tabLayout = getActivity().findViewById(R.id.tb_favoritos);
        viewPager = (ViewPager) view.findViewById(R.id.vp_favoritos);


        adapter = new ViewPagerAdapter(getFragmentManager());

        adapter.AddFragmment(new MusicasFavoritosFragment(),"Musicas");
        adapter.AddFragmment(new AlbunsFavoritosFragment(),"Albuns");
        adapter.AddFragmment(new ArtistasFavoritosFragment(),"Artistas");
        adapter.AddFragmment(new GenerosFavoritosFragment(),"Generos");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        //Remove shade from action bar
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setElevation(0);*/

        return view;
    }



}
