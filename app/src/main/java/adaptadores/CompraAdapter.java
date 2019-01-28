package adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import models.Artista;
import models.Compra;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DetalhesArtistaActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.R;


public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.MyViewHolder> {

    Context mContext;
    List<Compra> mData;

    public static final String DETALHES_ARTISTA = "ARTISTA";

    public CompraAdapter(Context mContext, List<Compra> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_compra, parent, false);
        final CompraAdapter.MyViewHolder myViewHolder = new CompraAdapter.MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.mDataCompra.setText(mData.get(position).getDataCompra());
        holder.mValor.setText(""+mData.get(position).getValorTotal()+"€");
    }


    @Override
    public int getItemCount() { return mData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mDataCompra;
        public TextView mValor;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mDataCompra = itemView.findViewById(R.id.tv_compra_data);
            mValor = itemView.findViewById(R.id.tv_compra_valor);
        }
    }
}
