package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adaptadores.AlbumPesquisaAdapter;
import adaptadores.ArtistaAdapter;
import adaptadores.ArtistaPesquisaAdapter;
import adaptadores.GeneroAdapter;
import models.Album;
import models.Artista;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class DetalhesArtistaActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlbuns;

    private ImageView ivImagemArtista;
    private TextView tvNomeArtista;
    private TextView tvNacionalidadeArtista;
    private TextView  tvAnoArtista;

    //private ArrayList<Album> lstAlbuns;
    private ArrayList<Artista> lstArtistas;

    private long idArtista;
    private Artista artista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_artista);

        ArrayList<Album> lstAlbuns = new ArrayList<>();

        ivImagemArtista = findViewById(R.id.iV_detalhes_artista_imagem);
        tvNomeArtista = findViewById(R.id.tV_detalhes_artista_nome);
        tvNacionalidadeArtista = findViewById(R.id.tV_detalhes_artista_nacionalidade);
        tvAnoArtista = findViewById(R.id.tV_detalhes_artista_ano);

        idArtista = getIntent().getLongExtra(ArtistaAdapter.DETALHES_ARTISTA,0);
        artista = SingletonGestorConteudo.getInstance(getApplicationContext()).getArtista(idArtista);

        ivImagemArtista.setImageResource(artista.getImagem());
        tvNomeArtista.setText(artista.getNome());
        tvNacionalidadeArtista.setText(artista.getNacionalidade());
        tvAnoArtista.setText("" + artista.getAno());


        lstAlbuns = SingletonGestorConteudo.getInstance(this).albunsArtista(idArtista);

        recyclerViewAlbuns = findViewById(R.id.rV_detalhes_artistaAlbuns);
        recyclerViewAlbuns.setHasFixedSize(true);
        recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(this, lstAlbuns);
        recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);
    }



}
