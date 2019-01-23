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
import adaptadores.GeneroAdapter;
import models.Album;
import models.FavoritoGenero;
import models.Genero;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesGeneroListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class DetalhesGeneroActivity extends AppCompatActivity implements DetalhesGeneroListener{

    private ImageView ivGenero;
    private TextView tvGeneroNome;
    private TextView tvGeneroDescricao;

    private String checkGeneroFav;
    private ImageButton btnAddFavoritos;

    private ArrayList utilizador;
    private long idUtilizador;

    private RecyclerView recyclerViewAlbuns;

    String url = "http://" + SingletonGestorConteudo.IP + "/sound3application/common/img/generos/";

    private long idGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_genero);

        utilizador = GestorSharedPref.getInstance(this).getUser();
        idUtilizador = Integer.parseInt(utilizador.get(0).toString());

        getSupportActionBar().setTitle("Detalhes Genero");

        ivGenero = findViewById(R.id.iV_detalhes_generoImagem);
        tvGeneroNome = findViewById(R.id.tV_detalhes_generoNome);
        tvGeneroDescricao = findViewById(R.id.tV_detalhes_generoDescricao);

        btnAddFavoritos = findViewById(R.id.ib_addFavoritos_genero);

        //Recebe o Id do Genero
        idGenero = getIntent().getLongExtra(GeneroAdapter.DETALHES_GENERO,0);
        System.out.println("-->GENERO : " + idGenero);

        SingletonGestorDados.getInstance(this).setDetalhesGeneroListener(this);

        // Vai Buscar a Info do Genero
        SingletonGestorDados.getInstance(this).getGeneroAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idGenero);

        // Mostra todos os Albuns do Genero
        SingletonGestorDados.getInstance(this).getAllAlbunsDoGeneroAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idGenero);

        // CHeca se o Album esta nos Favoritos
        SingletonGestorDados.getInstance(this).getGeneroFavoritoAPI(this,
                ConteudoJsonParser.isConnectionInternet(this),idUtilizador, idGenero);

    }

    @Override
    public void onRefreshGenero(Genero genero) {
        tvGeneroNome.setText(genero.getNome());
        tvGeneroDescricao.setText(genero.getDescricao());
        Glide.with(this).
                load(url + genero.getImagem())
                .into(ivGenero);
    }

    @Override
    public void onRefreshAlbunsGeneros(ArrayList<Album> albunsGenero) {
        recyclerViewAlbuns = findViewById(R.id.rV_detalhes_generoAlbuns);
        recyclerViewAlbuns.setHasFixedSize(true);
        recyclerViewAlbuns.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AlbumPesquisaAdapter albumPesquisaAdapter = new AlbumPesquisaAdapter(this, albunsGenero);
        recyclerViewAlbuns.setAdapter(albumPesquisaAdapter);
    }

    @Override
    public void checkGeneroInFavoritos(String check) {
        System.out.println("-->GENERo CHECK :" + check);
        checkGeneroFav = check;

        if(checkGeneroFav.equals("false")){
            btnAddFavoritos.setImageResource(R.drawable.outline_favorite_border_white_24);
        }else{
            btnAddFavoritos.setImageResource(R.drawable.ic_favorite_white_cheio_24dp);
        }
    }


    public void addGeneroFav(View view) {
        if(checkGeneroFav.equals("false")){
            SingletonGestorDados.getInstance(this).adicionarFavoritosGeneroAPI(this, idUtilizador, idGenero);
            Toast.makeText(this, "Adicionado aos Favoritos 3" + checkGeneroFav, Toast.LENGTH_SHORT).show();
        }else {
            SingletonGestorDados.getInstance(this).apagarFavoritosGeneroAPI(this,
                    ConteudoJsonParser.isConnectionInternet(this), idUtilizador, idGenero);
            Toast.makeText(this, "Removido dos Favorittos"+ checkGeneroFav, Toast.LENGTH_SHORT).show();
        }
    }
}
