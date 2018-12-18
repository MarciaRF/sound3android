package adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;

import models.Album;
import pt.ipleiria.estg.dei.amsi.sound3application.DetalhesAlbum;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    Context mContext;
    List<Album> mData;

    public static final String DETALHES_ALBUM = "ALBUM";


    public AlbumAdapter(Context mContext, List<Album> mData){
        this.mContext = mContext;
        this.mData = mData;
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int position) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_album, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //holder.mAlbumCapa.setImageResource(mData.get(position).getImagem());
        holder.mAlbumNome.setText(mData.get(position).getNome());
        holder.mAlbumArtista.setText(mData.get(position).getNome());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "posicao: " + position + "id: " + mData.get(position).getIdAlbum(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DetalhesAlbum.class);
                intent.putExtra(DETALHES_ALBUM, mData.get(position).getIdAlbum());
                mContext.startActivity(intent);
            }
        });
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView mAlbumCapa;
        public TextView mAlbumNome;
        public TextView mAlbumArtista;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mAlbumCapa = itemView.findViewById(R.id.iV_album_capa);
            mAlbumNome = itemView.findViewById(R.id.tV_album_nome);
            mAlbumArtista = itemView.findViewById(R.id.tV_album_artista);

        }

    }
}
