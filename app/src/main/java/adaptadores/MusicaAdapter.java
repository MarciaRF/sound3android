package adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import models.Album;
import models.Musica;

import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.ListenerPopUpMenu;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;

public class MusicaAdapter extends RecyclerView.Adapter<MusicaAdapter.MyViewHolder> implements ListenerPopUpMenu {

        Context mContext;
        List<Musica> mData;
        Long idUser;
        List<Musica> mFav;
        ArrayList<Album> mAlbum;
        View layout ;

        public MusicaAdapter(Context mContext, List<Musica> mData, ArrayList<Album> mAlbum, Long idUser, List<Musica> mFav) {
            this.mContext = mContext;
            this.mData = mData;
            this.mAlbum = mAlbum;
            this.idUser = idUser;
            this.mFav = mFav;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View v;
            v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_musica_pesquisa, parent, false);
            MyViewHolder vHolder = new MyViewHolder(v);

            return vHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

            holder.mMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    popup.inflate(R.menu.popup_menu);
                    layout=v;
                    Toast.makeText(mContext, "---->checkcarrinho-v", Toast.LENGTH_SHORT).show();
                    SingletonGestorDados.getInstance(mContext).getMusicaCarrinhoAPI(mContext,
                            ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item_addCarrinho:
                                    TextView tvCarrinho =  v.findViewById(R.id.item_addCarrinho);
                                    String textoCarrinho = tvCarrinho.getText().toString();

                                    if(textoCarrinho.equals("Adicionar ao Carrinho")){
                                        SingletonGestorDados.getInstance(mContext).adicionarMusicaCarrinhoAPI(mContext,
                                                ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                        Toast.makeText(mContext, "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();

                                    } else if (textoCarrinho.equals("Remover do Carrinho")){
                                        SingletonGestorDados.getInstance(mContext).apagarMusicaCarrinhoAPI(mContext,
                                                ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                        Toast.makeText(mContext, "Removido do Carrinho", Toast.LENGTH_SHORT).show();
                                    }
                                    return true;

                                case R.id.item_addFavoritos:
                                    TextView tvFavorito =  v.findViewById(R.id.item_addFavoritos);
                                    String textoFavorito = tvFavorito.getText().toString();

                                    if(textoFavorito.equals("Adicionar aos Favoritos")){
                                        SingletonGestorDados.getInstance(mContext).adicionarFavoritosMusicaAPI(mContext,
                                                ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                        Toast.makeText(mContext, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
                                        tvFavorito.setText("Remover dos Favoritos");

                                    }else if (textoFavorito.equals("Remover do Carrinho")){
                                        SingletonGestorDados.getInstance(mContext).apagarFavoritosMusicaAPI(mContext,
                                                ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                        Toast.makeText(mContext, "Removido dos Favoritos", Toast.LENGTH_SHORT).show();
                                        tvFavorito.setText("Adicionar aos Favoritos");
                                    }
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.show();
                }
            });

            holder.mNome.setText(mData.get(position).getNome());
            holder.mTempo.setText(mData.get(position).getDuracao());
            holder.mArtista.setText(mAlbum.get(position).getNome());
            holder.mPreco.setText(""+mData.get(position).getPreco()+"€");

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

    @Override
    public void checkMusicaInFavoritos(String check) {

    }

    @Override
    public void checkMusicaInCarrinho(String check) {
        if (layout != null) {
            TextView tv_item = layout.findViewById(R.id.item_addCarrinho);
            if (check.equals("false")) {
                System.out.println("---->checkcarrinho-false");
                tv_item.setText("Adicionar ao Carrinho");
            } else if(check.equals("true")){
                System.out.println("---->checkcarrinho-true");
                tv_item.setText("Remover do Carrinho");
            }
        }
        System.out.println("---->checkcarrinho-layoutfalse");
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

            public ImageView mMenu;
            public TextView mNome;
            public TextView mTempo;
            public TextView mArtista;
            public TextView mPreco;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                mMenu = itemView.findViewById(R.id.iB_pesquisa_musicaMenu);
                mNome = itemView.findViewById(R.id.tV_pesquisa_musicaNome);
                mTempo = itemView.findViewById(R.id.tV_pesquisa_musicaTempo);
                mArtista = itemView.findViewById(R.id.tV_pesquisa_musicaAlbum);
                mPreco = itemView.findViewById(R.id.tV_pesquisa_musicaAlbum_preco);
            }
        }


}


