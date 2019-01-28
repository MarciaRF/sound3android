package adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import models.Artista;
import models.Comentario;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import models.Utilizador;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DetalhesArtistaActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Comentario> mData;
    ArrayList<Utilizador> mUser;
    long idUser;
    long idAlbum;


    String url = "http://" + SingletonGestorConteudo.IP +"/sound3application/common/img/artistas/";

    public static final String DETALHES_ARTISTA = "ARTISTA";

    public CommentAdapter(Context mContext, ArrayList<Comentario> mData, ArrayList<Utilizador> mUser, long idUser, long idAlbum){
        this.mContext = mContext;
        this.mData = mData;
        this.mUser = mUser;
        this.idUser = idUser;
        this.idAlbum = idAlbum;
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

        holder.mNomeUser.setText(mUser.get(position).getNomeUtilizador());
        holder.mData.setText(""+mData.get(position).getData_Criacao());
        holder.mComentario.setText(mData.get(position).getConteudo());

        if( mUser.get(position).getIdUtilizador() == idUser){
            holder.mButtonApagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mButtonApagar.setVisibility(View.VISIBLE);

                    SingletonGestorDados.getInstance(mContext).apagarCommentAlbumAPI(mContext,
                            ConteudoJsonParser.isConnectionInternet(mContext), mData.get(position).getIdComentario(), idAlbum);

                    Toast.makeText(mContext, "Comentario Apagado", Toast.LENGTH_SHORT).show();

                }
            });
        }else{
            holder.mButtonApagar.setVisibility(View.INVISIBLE);
        }
        
    }


    @Override
    public int getItemCount() { return mData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mNomeUser;
        public TextView mData;
        public TextView mComentario;
        public ImageButton mButtonApagar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mNomeUser = itemView.findViewById(R.id.tv_comment_nomeUtilizador);
            mData = itemView.findViewById(R.id.tv_comment_data);
            mComentario = itemView.findViewById(R.id.tv_comment_comentario);
            mButtonApagar = itemView.findViewById(R.id.iB_apagar);

        }
    }
}
