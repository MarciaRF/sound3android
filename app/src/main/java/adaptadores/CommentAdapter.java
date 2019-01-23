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

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import models.Artista;
import models.Comentario;
import models.SingletonGestorConteudo;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DetalhesArtistaActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.R;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Comentario> mData;

    String url = "http://" + SingletonGestorConteudo.IP +"/sound3application/common/img/artistas/";


    public static final String DETALHES_ARTISTA = "ARTISTA";

    public CommentAdapter(Context mContext, ArrayList<Comentario> mData){
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_comment, parent, false);
        final CommentAdapter.MyViewHolder myViewHolder = new CommentAdapter.MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.mNomeUser.setText(""+mData.get(position).getId_Utilizador());
        holder.mData.setText(""+mData.get(position).getData_Criacao());
        holder.mComentario.setText(mData.get(position).getConteudo());


        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetalhesArtistaActivity.class);
                intent.putExtra(DETALHES_ARTISTA, mData.get(position).getIdArtista());
                mContext.startActivity(intent);
            }
        });*/

    }


    @Override
    public int getItemCount() { return mData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mNomeUser;
        public TextView mData;
        public TextView mComentario;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mNomeUser = itemView.findViewById(R.id.tv_comment_nomeUtilizador);
            mData = itemView.findViewById(R.id.tv_comment_data);
            mComentario = itemView.findViewById(R.id.tv_comment_comentario);
        }
    }
}
