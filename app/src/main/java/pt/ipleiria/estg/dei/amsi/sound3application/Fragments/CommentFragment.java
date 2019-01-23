package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import adaptadores.AlbumAdapter;
import adaptadores.CommentAdapter;
import adaptadores.MusicaAdapter;
import models.Comentario;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DetalhesAlbumActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.FavoritosRecyclerActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;

public class CommentFragment extends Fragment implements CommentListener {

    View view;

    private long idAlbum ;
    private FloatingActionButton btnAddComment;

    private RecyclerView recyclerViewAlbumComment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_comment, container, false);

        recyclerViewAlbumComment = view.findViewById(R.id.rv_albumComments);
        btnAddComment =  view.findViewById(R.id.fbrn_addComment);

        DetalhesAlbumActivity atividade = (DetalhesAlbumActivity)getActivity();
        idAlbum = atividade.idAlbum;

        SingletonGestorDados.getInstance(getContext()).setCommentListener(this);

        SingletonGestorDados.getInstance(getContext()).getAlbumCommentsAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idAlbum);


        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getContext(), FavoritosRecyclerActivity.class);
                intent.putExtra(FavoritosRecyclerActivity.MOSTRAS_DADOS, 1);
                startActivity(intent);*/
                Toast.makeText(getContext(), "OLE", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    @Override
    public void onResfreshComment(ArrayList<Comentario> listaComentarios) {
        recyclerViewAlbumComment.setHasFixedSize(true);//Otimização
        recyclerViewAlbumComment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        CommentAdapter commentAdapter = new CommentAdapter(getContext(), listaComentarios);
        recyclerViewAlbumComment.setAdapter(commentAdapter);
    }
}
