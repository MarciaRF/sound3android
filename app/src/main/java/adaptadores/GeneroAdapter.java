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

import java.util.ArrayList;
import java.util.List;

import models.Album;
import models.Genero;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class GeneroAdapter extends RecyclerView.Adapter<GeneroAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Genero> mData;

        public GeneroAdapter(Context mContext, ArrayList<Genero> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_genero, parent, false);
        GeneroAdapter.MyViewHolder vHolder = new GeneroAdapter.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.mCapa.setImageResource(mData.get(position).getImagem());
        holder.mNome.setText(mData.get(position).getNome());

        Glide.with(mContext).load(mData.get(position).getImagem()).placeholder(R.drawable.ppop).thumbnail(0).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mCapa);
    }

    @Override
    public int getItemCount() { return mData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView mCapa;
        public TextView mNome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mCapa = itemView.findViewById(R.id.iV_genero);
            mNome = itemView.findViewById(R.id.tV_genero_nome);
        }
    }
}
