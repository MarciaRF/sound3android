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

import models.Genero;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class GeneroPesquisaAdapter extends RecyclerView.Adapter<GeneroPesquisaAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Genero> mData;

    String url = "/sound3application/common/img/generos/";
    String urlImagem;

        public GeneroPesquisaAdapter(Context mContext, ArrayList<Genero> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_genero_pesquisa, parent, false);
        GeneroPesquisaAdapter.MyViewHolder vHolder = new GeneroPesquisaAdapter.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.mNome.setText(mData.get(position).getNome());

        //Vai Buscar o IP do Singleton e Concatena com o caminho
        urlImagem = "http://" + SingletonGestorConteudo.IP + url + mData.get(position).getImagem();

        Glide.with(mContext).
                load(urlImagem)
                .into(holder.mCapa);
    }

    @Override
    public int getItemCount() { return mData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView mCapa;
        public TextView mNome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mCapa = itemView.findViewById(R.id.iV_pesquisa_generoImagem);
            mNome = itemView.findViewById(R.id.tV_pesquisa_generoNome);
        }
    }
}
