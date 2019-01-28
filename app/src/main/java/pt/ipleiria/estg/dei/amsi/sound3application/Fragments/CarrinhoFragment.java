package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import adaptadores.MusicaAdapter;
import models.Album;
import models.FavoritoMusica;
import models.Musica;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CarrinhoListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class CarrinhoFragment extends Fragment implements CarrinhoListener {

    View view;

    private long idUtilizador;
    private RecyclerView recyclerViewItems;
    private TextView textViewTitle;
    private TextView textViewPreco;
    private Button btn_checkout;
    private ArrayList<Musica> musicasFavoritos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vai Buscar Id do Utilizador as Shared
        idUtilizador = GestorSharedPref.getInstance(getContext()).getIdUtilizador();

        SingletonGestorDados.getInstance(getContext()).setCarrinhoListener(this);

        SingletonGestorDados.getInstance(getContext()).getItemsCarrinhoAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idUtilizador);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_carrinho, container, false);

        textViewTitle = view.findViewById(R.id.textViewTitleCarrinho);
        textViewPreco = view.findViewById(R.id.textViewPreco);
        btn_checkout = view.findViewById(R.id.buttonCheckout);
        textViewPreco.append("0€");
        btn_checkout.setEnabled(false);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/user/checkout?userLogado="+idUtilizador));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onRefreshCarrinho(ArrayList<Musica> musicas, ArrayList<Album> album) {

        textViewTitle.append("Carrinho " + "(" + musicas.size() + ")" );

        if(!musicas.isEmpty()){
            int total = 0;

            for (Musica musica: musicas) {
                total += musica.getPreco();
            }

            textViewPreco.setText(R.string.preco_carrinho);
            textViewPreco.append(" "+total+"€");
            btn_checkout.setEnabled(true);

            recyclerViewItems = view.findViewById(R.id.rV_items_carrinho);
            recyclerViewItems.setHasFixedSize(true);//Otimização
            recyclerViewItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            MusicaAdapter musicasAdapter = new MusicaAdapter(getContext(), musicas, album, idUtilizador, musicasFavoritos);
            recyclerViewItems.setAdapter(musicasAdapter);

        } else{
            btn_checkout.setEnabled(false);
        }
    }

    @Override
    public void onRefreshMusicasFavoritos(ArrayList<Musica> favoritoMusicas) {
        musicasFavoritos = favoritoMusicas;
    }
}
