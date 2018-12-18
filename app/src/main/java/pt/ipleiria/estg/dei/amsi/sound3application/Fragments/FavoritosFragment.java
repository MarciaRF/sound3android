package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adaptadores.ViewPagerAdapter;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class FavoritosFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, container, false);

/*
        // Codigo das Tabs
        tabLayout = tabLayout.findViewById(R.id.tb_favoritos);
        viewPager = viewPager.findViewById(R.id.vp_favoritos);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragmment(new MusicaFragment(),"");
        adapter.AddFragmment(new CommentFragment(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.music_note_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.comment_24dp);

        //Remove shade from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);*/

    }
}
