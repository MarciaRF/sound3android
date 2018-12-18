package pt.ipleiria.estg.dei.amsi.sound3application;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import adaptadores.AlbumAdapter;
import adaptadores.ViewPagerAdapter;
import models.Album;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.CommentFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.MusicaFragment;

public class DetalhesAlbum extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private TextView albumNome;
    private TextView nomeArtista;
    private TextView albumAno;
    private TextView albumPreco;
    private ImageView albumImagem;

    Album album;
    long idAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_album);

        albumNome = findViewById(R.id.eT_detalhes_album_nomeAlbum);
        nomeArtista = findViewById(R.id.eT_detalhes_album_nomeArtista);
        albumAno = findViewById(R.id.eT_detalhes_album_ano);
        albumPreco = findViewById(R.id.eT_detalhes_album_preco);
        albumImagem = findViewById(R.id.iV_detalhes_album_imagem);

        idAlbum = getIntent().getLongExtra(AlbumAdapter.DETALHES_ALBUM, 0);


        album = SingletonGestorConteudo.getInstance(getApplicationContext()).getAlbum(idAlbum);
        mostrarDadosAlbum();



        // Codigo das Tabs
        tabLayout = findViewById(R.id.tb_album);
        //appBarLayout = findViewById(R.id.bl_album);
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


    public void mostrarDadosAlbum(){
         albumNome.setText(album.getNome());
        //nomeArtista.setText(al);
        albumAno.setText(album.getAno());
        //albumPreco.setText(album.ge);
        albumImagem.setImageResource(album.getImagem());
    }

    public void albumAddFavoritos(View view) {
        Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
    }

    public void albumAddCarrinho(View view) {
        Toast.makeText(this, "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();
    }


    public void onClickCriarComment(View view) {
    }
}
