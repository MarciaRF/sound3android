package adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import models.Artista;
import pt.ipleiria.estg.dei.amsi.sound3application.R;


public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.MyViewHolder> {

    Context mContext;
    List<Artista> mData;


    public ArtistaAdapter(Context mContext, List<Artista> mData){
        this.mContext = mContext;
        this.mData = mData;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_artista, parent, false);
        final ArtistaAdapter.MyViewHolder myViewHolder = new ArtistaAdapter.MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //holder.mImagem.setImageResource(mData.get(position).getImagem());
        holder.mNome.setText(mData.get(position).getNome());

        Glide.with(mContext).load(mData.get(position).getImagem()).placeholder(R.drawable.wbg).thumbnail(0).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImagem);
    }


    @Override
    public int getItemCount() { return mData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImagem;
        public TextView mNome;
        public TextView mNacionalidade;
        public TextView mAno;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImagem = itemView.findViewById(R.id.iV_artista_imagem);
            mNome = itemView.findViewById(R.id.tV_artista_nome);
        }
    }
}
