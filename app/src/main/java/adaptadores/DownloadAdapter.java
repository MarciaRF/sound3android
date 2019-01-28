package adaptadores;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
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
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.MyViewHolder>{

        Context mContext;
        List<Musica> mData;
        List<Album> mAlbum;
        DownloadManager downloadManager;

        public DownloadAdapter(Context mContext, List<Musica> mData, List<Album> mAlbum) {
            this.mContext = mContext;
            this.mData = mData;
            this.mAlbum = mAlbum;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View v;
            v = LayoutInflater.from(mContext).inflate(R.layout.item_lista_musica_download, parent, false);
            MyViewHolder vHolder = new MyViewHolder(v);

            return vHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            holder.mNome.setText(mData.get(position).getNome());
            holder.mTempo.setText(mData.get(position).getDuracao());
            holder.mArtista.setText(mAlbum.get(position).getNome());
            holder.mPreco.setText(""+mData.get(position).getPreco()+"€");

            holder.mMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //Toast.makeText(mContext, "OLE", Toast.LENGTH_SHORT).show();
                    String nomeFicheiro = mData.get(position).getCaminhoMusica();

                downloadManager =  (DownloadManager) mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://" + SingletonGestorConteudo.IP + "/sound3application/common/musicas/" + nomeFicheiro);
                    System.out.println("-->URI n: " + nomeFicheiro);
                    System.out.println("-->URI: " + uri);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadManager.enqueue(request);
                }
            });
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
            public TextView mPreco;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                mMenu = itemView.findViewById(R.id.iB_pesquisa_musicaDownload);
                mNome = itemView.findViewById(R.id.tV_pesquisa_musicaNome);
                mTempo = itemView.findViewById(R.id.tV_pesquisa_musicaTempo);
                mArtista = itemView.findViewById(R.id.tV_pesquisa_musicaAlbum);
                mPreco = itemView.findViewById(R.id.tV_pesquisa_musicaAlbum_preco);
            }
        }


}


