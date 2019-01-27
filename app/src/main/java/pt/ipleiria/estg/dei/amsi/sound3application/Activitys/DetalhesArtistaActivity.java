package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import adaptadores.AlbumPesquisaAdapter;
import adaptadores.ArtistaAdapter;
import adaptadores.ArtistaPesquisaAdapter;
import adaptadores.GeneroAdapter;
import models.Album;
import models.Artista;
import models.FavoritoAlbum;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesArtistaListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class DetalhesArtistaActivity extends AppCompatActivity implements DetalhesArtistaListener {

    private RecyclerView recyclerViewAlbuns;

    private ImageView ivImagemArtista;
    private TextView tvNomeArtista;
    private TextView tvNacionalidadeArtista;
    private TextView  tvAnoArtista;

    private long idArtista;

    private String checkArtistaFav;
    private ImageButton btnAddFavoritos;

    private long idUtilizador;

    String url = "http://" + SingletonGestorConteudo.IP +"/sound3application/common/img/artistas/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_artista);

        ivImagemArtista = findViewById(R.id.iV_detalhes_artista_imagem);
        tvNomeArtista = findViewById(R.id.tV_detalhes_artista_nome);
        tvNacionalidadeArtista = findViewById(R.id.tV_detalhes_artista_nacionalidade);
        tvAnoArtista = findViewById(R.id.tV_detalhes_artista_ano);

        btnAddFavoritos = findViewById(R.id.iB_detalhes_artista_favoritos);

        // Vai Buscar Id do Utilizador as Shared
        idUtilizador = GestorSharedPref.getInstance(this).getIdUtilizador();


        idArtista = getIntent().getLongExtra(ArtistaAdapter.DETALHES_ARTISTA,0);


        SingletonGestorDados.getInstance(this).setDetalhesArtistaListener(this);

        SingletonGestorDados.getInstance(this).getArtistaAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idArtista);

        SingletonGestorDados.getInstance(this).getAllAbunsArtistaAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idArtista);

        // Ver Se este Artista esta nos Favoritos
        SingletonGestorDados.getInstance(this).getArtistaFavoritoAPI(this,
                ConteudoJsonParser.isConnectionInternet(this),idUtilizador, idArtista);
    }


    @Override
    public void onRefreshArtista(Artista artista) {
        tvNomeArtista.setText(artista.getNome());
        tvNacionalidadeArtista.setText(artista.getNacionalidade());
        tvAnoArtista.setText("" + artista.getAno());
        Glide.with(this)
                .load(url + artista.getImagem())
                .into(ivImagemArtista);
    }

    @Override
    public void onRefreshAbunsArtista(ArrayList<Album> albunsArtista, ArrayList<Artista> artistas) {
        recyclerViewAlbuns = findViewById(R.id.rV_detalhes_artistaAlbuns);
        recyclerViewAlbuns.setHasFixedSize(true);
        recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(this, albunsArtista, artistas);
        recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);
    }

    public void addArtistaFav(View view) {
        if(checkArtistaFav.equals("false")){
            SingletonGestorDados.getInstance(this).adicionarFavoritosArtistaAPI(this,
                    ConteudoJsonParser.isConnectionInternet(this), idUtilizador, idArtista);
            Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
        }else {
            SingletonGestorDados.getInstance(this).apagarFavoritosArtistaAPI(this,
                    ConteudoJsonParser.isConnectionInternet(this), idUtilizador, idArtista);
            Toast.makeText(this, "Removido dos Favorittos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkArtistaInFavoritos(String check) {
        checkArtistaFav = check;

        if(checkArtistaFav.equals("false")){
            btnAddFavoritos.setImageResource(R.drawable.outline_favorite_border_white_24);
        }else{
            btnAddFavoritos.setImageResource(R.drawable.ic_favorite_white_cheio_24dp);
        }
    }



}
