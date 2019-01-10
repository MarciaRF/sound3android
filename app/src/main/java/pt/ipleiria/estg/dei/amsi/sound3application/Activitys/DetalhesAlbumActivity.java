package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import adaptadores.AlbumAdapter;
import adaptadores.ViewPagerAdapter;
import models.Album;
import models.FavoritoAlbum;
import models.LinhaCompra;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import models.Utilizador;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.CommentFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.MusicaFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class DetalhesAlbumActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private TextView albumNome;
    private TextView nomeArtista;
    private TextView albumAno;
    private TextView albumPreco;
    private ImageView albumImagem;

    private FavoritoAlbum favAlbum;
    private long idUser;

    private SharedPreferences sharedPreferences;

    private long idCompra;
    private LinhaCompra addCarrinho;

    Album album;
    long idAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_album);

        idAlbum = getIntent().getLongExtra(AlbumAdapter.DETALHES_ALBUM, 0);

        albumNome = findViewById(R.id.eT_detalhes_album_nomeAlbum);
        nomeArtista = findViewById(R.id.eT_detalhes_album_nomeArtista);
        albumAno = findViewById(R.id.eT_detalhes_album_ano);
        albumPreco = findViewById(R.id.eT_detalhes_album_preco);
        albumImagem = findViewById(R.id.iV_detalhes_album_imagem);

        album = SingletonGestorConteudo.getInstance(getApplicationContext()).getAlbum(idAlbum);


        albumNome.setText(album.getNome());
        //nomeArtista.setText(al);
        albumAno.setText("" + album.getAno());
        //albumPreco.setText(album.ge);
        //albumImagem.setImageResource(album.getImagem());
        Glide.with(this).load(""+album.getImagem()).into(albumImagem);


        // Codigo das Tabs
        tabLayout = findViewById(R.id.tb_album);
        viewPager = findViewById(R.id.vp_album);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragmment(new MusicaFragment(),"");
        adapter.AddFragmment(new CommentFragment(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        
        
        
        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        
        idUser = sharedPreferences.getInt("idUser", 0);
        
        tabLayout.getTabAt(0).setIcon(R.drawable.music_note_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.comment_24dp);

        //Remove shade from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        
    }


    public void albumAddFavoritos(View view) {
        //if(){
            favAlbum = new FavoritoAlbum(idUser, idAlbum);
            SingletonGestorDados.getInstance(this).addFavoritoAlbumBD(favAlbum);
            Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
        //}else {
            Toast.makeText(this, "Removido dos Favorittos", Toast.LENGTH_SHORT).show();
        //}
    }

    public void albumAddCarrinho(View view) {
        //if(){
            addCarrinho = new LinhaCompra(idCompra ,idAlbum);
            SingletonGestorDados.getInstance(this).addLinhaCompraBD(addCarrinho);
            Toast.makeText(this, "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();
        //}else{
            Toast.makeText(this, "Removido do Carrinho", Toast.LENGTH_SHORT).show();
        //}

    }


    public void onClickCriarComment(View view) {
        Toast.makeText(this, "Criar Comment", Toast.LENGTH_SHORT).show();
    }


}
