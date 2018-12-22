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

import models.Musica;

import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class MusicaAdapter extends RecyclerView.Adapter<MusicaAdapter.MyViewHolder>{

        Context mContext;
        List<Musica> mData;

        public MusicaAdapter(Context mContext, List<Musica> mData) {
            this.mContext = mContext;
            this.mData = mData;
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

            //holder.mMenu.setImageResource(mData.get(position).getMenu());
            holder.mMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popup = new PopupMenu(v.getContext(), v);

                    popup.inflate(R.menu.popup_menu);

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item_addCarrinho:
                                    Toast.makeText(mContext, "Adicionado ao Carrinho"+position, Toast.LENGTH_SHORT).show();
                                    return true;
                                case R.id.item_addFavoritos:
                                    Toast.makeText(mContext, "Adicionado aos Favoritos"+position, Toast.LENGTH_SHORT).show();
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
            holder.mArtista.setText(mData.get(position).getNome());

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

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                mMenu = itemView.findViewById(R.id.iB_pesquisa_musicaMenu);
                mNome = itemView.findViewById(R.id.tV_pesquisa_musicaNome);
                mTempo = itemView.findViewById(R.id.tV_pesquisa_musicaTempo);
                mArtista = itemView.findViewById(R.id.tV_pesquisa_musicaAlbum);

            }
        }


}


