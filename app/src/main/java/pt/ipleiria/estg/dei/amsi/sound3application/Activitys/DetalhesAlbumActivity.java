package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import adaptadores.AlbumAdapter;
import adaptadores.ViewPagerAdapter;
import models.Album;
import models.Artista;
import models.FavoritoAlbum;
import models.LinhaCompra;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.CommentFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.MusicaFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesAlbumListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class DetalhesAlbumActivity extends AppCompatActivity implements  DetalhesAlbumListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private TextView albumNome;
    private TextView nomeArtista;
    private TextView albumAno;
    private TextView albumPreco;
    private ImageView albumImagem;

    private ImageButton btnAddCarrinho;
    private ImageButton btnAddFavoritos;

    private String checkAlbumFav;
    private String checkAlbumCart;

    private String urlImagem = "http://" + SingletonGestorConteudo.IP + "/sound3application/common/img/capas/";

    private long idUtilizador;
    public long idAlbum ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_album);

        albumNome = findViewById(R.id.eT_detalhes_album_nomeAlbum);
        nomeArtista = findViewById(R.id.eT_detalhes_album_nomeArtista);
        albumAno = findViewById(R.id.eT_detalhes_album_ano);
        albumPreco = findViewById(R.id.eT_detalhes_album_preco);
        albumImagem = findViewById(R.id.iV_detalhes_album_imagem);

        btnAddCarrinho = findViewById(R.id.iB_add_carrinho);
        btnAddFavoritos = findViewById(R.id.iB_add_favoritos);

        //Recebe ID Album do onClick na lista
        idAlbum = getIntent().getLongExtra(AlbumAdapter.DETALHES_ALBUM, 0);

        // Vai Buscar Id do Utilizador as Shared
        idUtilizador = GestorSharedPref.getInstance(this).getIdUtilizador();


        SingletonGestorDados.getInstance(this).setDetalhesAlbumListener(this);

        // Vai Buscar Info. do Album
        SingletonGestorDados.getInstance(this).getAlbumAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idAlbum);

        SingletonGestorDados.getInstance(this).getArtistaAlbumAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idAlbum);

        // Ver Se este Album esta nos Favoritos
        SingletonGestorDados.getInstance(this).getAlbumFavoritoAPI(this,
                ConteudoJsonParser.isConnectionInternet(this),idUtilizador, idAlbum);

        // Ver Se este Album esta no Carrinho
        SingletonGestorDados.getInstance(this).getAlbumCarrinhoAPI(this,
                ConteudoJsonParser.isConnectionInternet(this),idUtilizador, idAlbum);


        //Ver as Musicas Favoritas Deste Album
        long userid = 2;
        long musicaid = 4;
        SingletonGestorDados.getInstance(this).getAllMusicasFavoritosAlbumAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), userid, musicaid);


        // Codigo das Tabs
        tabLayout = findViewById(R.id.tb_album);
        viewPager = findViewById(R.id.vp_album);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragmment(new MusicaFragment(),"");
        adapter.AddFragmment(new CommentFragment(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        
        tabLayout.getTabAt(0).setIcon(R.drawable.music_note_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.comment_24dp);


        //Remove shade from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }

    @Override
    public void onRefreshAlbum(Album album) {
        albumNome.setText(album.getNome());
        albumAno.setText("" + album.getAno());
        albumPreco.setText("" + album.getPreco() + "€");
        Glide.with(this)
                .load(urlImagem + album.getImagem())
                .into(albumImagem);
    }

    @Override
    public void onRefreshArtistaAlbum(Artista artista) {
        nomeArtista.setText(artista.getNome());
    }

    public void albumAddFavoritos(View view) {
        if(checkAlbumFav.equals("false")){
            SingletonGestorDados.getInstance(this).adicionarFavoritosAlbumAPI(this,
                    ConteudoJsonParser.isConnectionInternet(this), idUtilizador, idAlbum);
            Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
        }else {
            SingletonGestorDados.getInstance(this).apagarFavoritosAlbumAPI(this,
                    ConteudoJsonParser.isConnectionInternet(this), idUtilizador, idAlbum);
            Toast.makeText(this, "Removido dos Favorittos", Toast.LENGTH_SHORT).show();
        }
    }

    public void albumAddCarrinho(View view) {
        if(checkAlbumCart.equals("false")){
            SingletonGestorDados.getInstance(this).adicionarAlbumCarrinhoAPI(this,
                    ConteudoJsonParser.isConnectionInternet(this), idUtilizador, idAlbum);
            Toast.makeText(this, "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();
        }else{
            SingletonGestorDados.getInstance(this).apagarAlbumCarrinhoAPI(this,
                    ConteudoJsonParser.isConnectionInternet(this), idUtilizador, idAlbum);
            Toast.makeText(this, "Removido do Carrinho", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkAlbumInFavoritos(String check) {
        checkAlbumFav = check;
        if(checkAlbumFav.equals("false")){
            btnAddFavoritos.setImageResource(R.drawable.outline_favorite_border_white_24);
        }else{
            btnAddFavoritos.setImageResource(R.drawable.ic_favorite_white_cheio_24dp);
        }
    }

    @Override
    public void checkAlbumInCarrinho(String check) {
        checkAlbumCart = check;
        if(checkAlbumCart.equals("false")){
            btnAddCarrinho.setImageResource(R.drawable.cart_add);
        }else{
            btnAddCarrinho.setImageResource(R.drawable.cart_remove);
        }
    }

}
