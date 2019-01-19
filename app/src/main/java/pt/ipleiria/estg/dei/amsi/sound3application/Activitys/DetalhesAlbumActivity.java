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
import models.FavoritoAlbum;
import models.LinhaCompra;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.CommentFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.MusicaFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.AlbumFavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class DetalhesAlbumActivity extends AppCompatActivity implements  AlbumFavoritosListener {

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

    private ArrayList utilizador;
    private long idUtilizador;

    private String checkAlbumFav;
    private FavoritoAlbum favAlbum;

    private long idCompra;
    private LinhaCompra addCarrinho;

    long idAlbum = 0;

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
        utilizador = GestorSharedPref.getInstance(this).getUser();
        idUtilizador = Integer.parseInt(utilizador.get(0).toString());


        SingletonGestorDados.getInstance(this).setAlbumFavoritosListener(this);

        // Vai Buscar Info. do Album
        SingletonGestorDados.getInstance(this).getAlbumAPI(this,
                ConteudoJsonParser.isConnectionInternet(this), idAlbum);

        // Ver Se este Album esta nos Favoritos
        SingletonGestorDados.getInstance(this).getAlbumFavoritoAPI(this,
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
/*
        //Passar o IDALBUM para o Fragmento das Musicas
        Bundle bundle = new Bundle();
        bundle.putLong("idAlbum", idAlbum);
        MusicaFragment musicaFragment = new MusicaFragment();
        musicaFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.vp_album, musicaFragment).commit();*/

        //Remove shade from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }

    @Override
    public void onRefreshAlbum(Album album) {
        System.out.println("-->TESTE " + album);
        albumNome.setText(album.getNome());
        albumAno.setText("" + album.getAno());
        albumPreco.setText("" + album.getPreco() + "€");
        Glide.with(this)
                .load("http://" + SingletonGestorConteudo.IP + "/sound3application/common/img/capas/" + album.getImagem())
                .into(albumImagem);
    }

    public void albumAddFavoritos(View view) {
        if(checkAlbumFav.equals("false")){
            SingletonGestorDados.getInstance(this).adicionarFavoritosAlbumAPI(this, idUtilizador, idAlbum);
            Toast.makeText(this, "Adicionado aos Favoritos 3" + checkAlbumFav, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Removido dos Favorittos"+ checkAlbumFav, Toast.LENGTH_SHORT).show();
            System.out.println();
        }

    }

    public void albumAddCarrinho(View view) {
        if(checkAlbumFav.equals("false")){
            addCarrinho = new LinhaCompra(idCompra ,idAlbum);
            SingletonGestorDados.getInstance(this).addLinhaCompraBD(addCarrinho);
            Toast.makeText(this, "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Removido do Carrinho", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickCriarComment(View view) {
        Toast.makeText(this, "Criar Comment", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRefreshFavoritosAlbum(ArrayList<FavoritoAlbum> favoritoAlbums) {

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
    public void onUpdateFavoritosAlbumBD(FavoritoAlbum favoritoAlbum) {

    }
}
