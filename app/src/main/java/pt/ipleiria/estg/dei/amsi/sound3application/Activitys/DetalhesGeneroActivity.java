package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import adaptadores.AlbumPesquisaAdapter;
import adaptadores.GeneroAdapter;
import models.Album;
import models.Genero;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class DetalhesGeneroActivity extends AppCompatActivity {

    private ImageView ivGenero;
    private TextView tvGeneroNome;
    private TextView tvGeneroDescricao;

    private ArrayList<Genero> lstGeneros;
    private ArrayList<Album> lstAlbuns;
    private ArrayList<Album> album;

    private RecyclerView recyclerViewGeneros;
    private RecyclerView recyclerViewAlbuns;

    Genero genero;
    long idGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_genero);


        getSupportActionBar().setTitle("Detalhes Genero");


        ivGenero = findViewById(R.id.iV_detalhes_generoImagem);
        tvGeneroNome = findViewById(R.id.tV_detalhes_generoNome);
        tvGeneroDescricao = findViewById(R.id.tV_detalhes_generoDescricao);

        idGenero = getIntent().getLongExtra(GeneroAdapter.DETALHES_GENERO,0);

        genero = SingletonGestorConteudo.getInstance(getApplicationContext()).getGenero(idGenero);


        Glide.with(this).load(""+genero.getImagem()).into(ivGenero);

        //ivGenero.setImageResource(genero.getImagem());
        tvGeneroNome.setText(genero.getNome());
        tvGeneroDescricao.setText(genero.getDescricao());


        lstAlbuns = SingletonGestorConteudo.getInstance(this).albunsGenero(idGenero);

        lstGeneros = SingletonGestorConteudo.getInstance(this).getGenerosBD();


        recyclerViewAlbuns = findViewById(R.id.rV_detalhes_generoAlbuns);
        recyclerViewAlbuns.setHasFixedSize(true);
        recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(this, lstAlbuns);
        recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);


        recyclerViewGeneros = findViewById(R.id.rV_detalhes_generoMaisAbuns);
        recyclerViewGeneros.setHasFixedSize(true);
        recyclerViewGeneros.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        GeneroAdapter generoAdapter = new GeneroAdapter(this, lstGeneros);
        recyclerViewGeneros.setAdapter(generoAdapter);

    }

}
