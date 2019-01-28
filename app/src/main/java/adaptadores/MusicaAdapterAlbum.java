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

import java.util.List;

import models.Album;
import models.Musica;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;

public class MusicaAdapterAlbum extends RecyclerView.Adapter<MusicaAdapterAlbum.MyViewHolder>{

        Context mContext;
        List<Musica> mData;
        Long idUser;
        List<Musica> mFav;
        Album mAlbum;

        public MusicaAdapterAlbum(Context mContext, List<Musica> mData, Album mAlbum, Long idUser, List<Musica> mFav) {
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
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item_addCarrinho:
                                    SingletonGestorDados.getInstance(mContext).adicionarMusicaCarrinhoAPI(mContext,
                                            ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                    Toast.makeText(mContext, "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();
                                    return true;
                                case R.id.item_addFavoritos:
                                    System.out.println("mfav"+mFav.get(0).getNome());
                                    if(mFav != null){
                                        for (Musica tempMusica:mFav
                                             ) {
                                            if(tempMusica.getIdMusica()== mData.get(position).getIdMusica()){
                                                Toast.makeText(mContext, "É favorita", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        if(mData.get(position).getIdMusica() == mFav.get(position).getIdMusica()){
                                            SingletonGestorDados.getInstance(mContext).adicionarFavoritosMusicaAPI(mContext,
                                                    ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                            //Toast.makeText(mContext, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
                                        }else{
                                            SingletonGestorDados.getInstance(mContext).apagarFavoritosMusicaAPI(mContext,
                                                    ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                            Toast.makeText(mContext, "Removido dos Favoritos", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        SingletonGestorDados.getInstance(mContext).adicionarFavoritosMusicaAPI(mContext,
                                                ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                        Toast.makeText(mContext, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show();
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
            holder.mArtista.setText(mAlbum.getNome());
            holder.mPreco.setText(""+mData.get(position).getPreco()+"€");

        }

        @Override
        public int getItemCount() {
            return mData.size();
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


