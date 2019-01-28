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
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.ListaMusicasAlbumListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;

public class MusicaAdapterAlbum extends RecyclerView.Adapter<MusicaAdapterAlbum.MyViewHolder> implements ListaMusicasAlbumListener {

        Context mContext;
        List<Musica> mData;
        Long idUser;
        List<Musica> mFav;
        Album mAlbum;
        List<Musica> mCart;
        ListaMusicasAlbumListener listaMusicasAlbumListener;

        public MusicaAdapterAlbum(Context mContext, List<Musica> mData, Album mAlbum, Long idUser, List<Musica> mFav, List<Musica> mCart) {
            this.mContext = mContext;
            this.mData = mData;
            this.mAlbum = mAlbum;
            this.idUser = idUser;
            this.mFav = mFav;
            this.mCart = mCart;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View v;
            v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_musica_pesquisa, parent, false);
            MyViewHolder vHolder = new MyViewHolder(v);

            setListaMusicasAlbumListener(this);

            return vHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

            holder.mMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    popup.inflate(R.menu.popup_menu);
                    if(mFav != null){
                        boolean checkfav = false;
                        //check estado do favorito da musica em causa
                        for (Musica tempMusica:mFav
                                ) {
                            if(tempMusica.getIdMusica()== mData.get(position).getIdMusica()){
                                checkfav = true;
                            }
                        }
                        if(checkfav){
                            popup.getMenu().getItem(1).setTitle("Remover dos Favoritos");
                        }else{
                            popup.getMenu().getItem(1).setTitle("Adicionar aos Favoritos");
                        }
                    }
                    if(mCart != null){
                        boolean checkcart = false;
                        //check estado do favorito da musica em causa
                        for (Musica tempMusica:mCart
                                ) {
                            if(tempMusica.getIdMusica()== mData.get(position).getIdMusica()){
                                checkcart = true;
                            }
                        }
                        if(checkcart){
                            popup.getMenu().getItem(0).setTitle("Remover do Carrinho");
                        }else{
                            popup.getMenu().getItem(0).setTitle("Adicionar ao Carrinho");
                        }
                    }
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item_addCarrinho:
                                    if(mCart != null){

                                        //envio para as rotas
                                        if(item.getTitle().equals("Adicionar ao Carrinho")){
                                            SingletonGestorDados.getInstance(mContext).adicionarMusicaCarrinhoAPI(mContext,
                                                    ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                            Toast.makeText(mContext, "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();
                                            listaMusicasAlbumListener.onCreateCartMusica(mCart, mData.get(position));
                                        }else if(item.getTitle().equals("Remover do Carrinho")){
                                            SingletonGestorDados.getInstance(mContext).apagarMusicaCarrinhoAPI(mContext,
                                                    ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                            Toast.makeText(mContext, "Removido do Carrinho!", Toast.LENGTH_SHORT).show();
                                            listaMusicasAlbumListener.onDeleteCartMusica(mCart, mData.get(position));
                                        }
                                    }else{
                                        SingletonGestorDados.getInstance(mContext).adicionarMusicaCarrinhoAPI(mContext,
                                                ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                        Toast.makeText(mContext, "Adicionado ao Carrinho!", Toast.LENGTH_SHORT).show();
                                        listaMusicasAlbumListener.onCreateCartMusica(mCart, mData.get(position));
                                    }
                                    return true;

                                case R.id.item_addFavoritos:
                                    if(mFav != null){

                                        //envio para as rotas
                                        if(item.getTitle().equals("Adicionar aos Favoritos")){
                                            SingletonGestorDados.getInstance(mContext).adicionarFavoritosMusicaAPI(mContext,
                                                    ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                            Toast.makeText(mContext, "Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
                                            listaMusicasAlbumListener.onCreateFavMusica(mFav, mData.get(position));
                                        }else if(item.getTitle().equals("Remover dos Favoritos")){
                                            SingletonGestorDados.getInstance(mContext).apagarFavoritosMusicaAPI(mContext,
                                                    ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                            Toast.makeText(mContext, "Removido com Sucesso!", Toast.LENGTH_SHORT).show();
                                            listaMusicasAlbumListener.onDeleteFavMusica(mFav, mData.get(position));
                                        }
                                    }else{
                                        SingletonGestorDados.getInstance(mContext).adicionarFavoritosMusicaAPI(mContext,
                                                ConteudoJsonParser.isConnectionInternet(mContext), idUser, mData.get(position).getIdMusica());
                                        Toast.makeText(mContext, "Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
                                        listaMusicasAlbumListener.onCreateFavMusica(mFav, mData.get(position));
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

    @Override
    public void onDeleteFavMusica(List<Musica> mFav, Musica musica) {
        if(mFav!=null){
            for (Musica tempMusica:mFav
                    ) {
                if(tempMusica.getIdMusica()== musica.getIdMusica()){
                    mFav.remove(musica);
                }
            }
        }
    }

    @Override
    public void onCreateFavMusica(List<Musica> mFav, Musica musica) {
        if(mFav!=null){
            if(!mFav.contains(musica)){
                mFav.add(musica);
            }
        }
    }

    @Override
    public void onDeleteCartMusica(List<Musica> mCart, Musica musica) {
        if(mCart!=null){
            for (Musica tempMusica:mCart
                    ) {
                if(tempMusica.getIdMusica()== musica.getIdMusica()){
                    mCart.remove(musica);
                }
            }
        }
    }

    @Override
    public void onCreateCartMusica(List<Musica> mCart, Musica musica) {
        if(mCart!=null){
            if(!mCart.contains(musica)){
                mCart.add(musica);
            }
        }
    }

    public void setListaMusicasAlbumListener(ListaMusicasAlbumListener listaMusicasAlbumListener){
            this.listaMusicasAlbumListener = listaMusicasAlbumListener;
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


