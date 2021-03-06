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
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import models.Artista;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DetalhesArtistaActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.R;


public class ArtistaPesquisaAdapter extends RecyclerView.Adapter<ArtistaPesquisaAdapter.MyViewHolder> {

    Context mContext;
    List<Artista> mData;

    String url = "/sound3application/common/img/artistas/";
    String urlImagem;

    public ArtistaPesquisaAdapter(Context mContext, List<Artista> mData){
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_artista_pesquisa, parent, false);
        final ArtistaPesquisaAdapter.MyViewHolder myViewHolder = new ArtistaPesquisaAdapter.MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //holder.mImagem.setImageResource(mData.get(position).getImagem());
        holder.mNome.setText(mData.get(position).getNome());

        urlImagem = "http://" + SingletonGestorConteudo.IP + url + mData.get(position).getImagem();

        Glide.with(mContext).
                load(urlImagem)
                .into(holder.mImagem);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetalhesArtistaActivity.class);
                intent.putExtra(ArtistaAdapter.DETALHES_ARTISTA, mData.get(position).getIdArtista());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() { return mData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImagem;
        public TextView mNome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImagem = itemView.findViewById(R.id.iV_pesquisa_artistaImagem);
            mNome = itemView.findViewById(R.id.tV_pesquisa_artistaNome);
        }
    }
}
