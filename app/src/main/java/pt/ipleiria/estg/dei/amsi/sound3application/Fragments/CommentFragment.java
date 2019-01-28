package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import adaptadores.CommentAdapter;
import models.Comentario;
import models.SingletonGestorDados;
import models.Utilizador;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DetalhesAlbumActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class CommentFragment extends Fragment implements CommentListener {

    View view;

    private FloatingActionButton btnAddComment;
    private RecyclerView recyclerViewAlbumComment;

    private String novoComentario;

    private long idUtilizador;
    private long idAlbum ;

    private String checkComentarios;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_comment, container, false);

        recyclerViewAlbumComment = view.findViewById(R.id.rv_albumComments);
        btnAddComment =  view.findViewById(R.id.fbrn_addComment);


        idUtilizador = GestorSharedPref.getInstance(getContext()).getIdUtilizador();

        DetalhesAlbumActivity atividade = (DetalhesAlbumActivity)getActivity();
        idAlbum = atividade.idAlbum;

        SingletonGestorDados.getInstance(getContext()).setCommentListener(this);


        SingletonGestorDados.getInstance(getContext()).getAlbumCommentsAPI(getContext(),
                ConteudoJsonParser.isConnectionInternet(getContext()), idAlbum);


        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Adicionar Comentario");

                View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_albuns_comment, (ViewGroup) getView(), false);

                final EditText input = (EditText) viewInflated.findViewById(R.id.eT_comment);

                builder.setView(viewInflated);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        novoComentario = input.getText().toString();

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        SingletonGestorDados.getInstance(getContext()).adicionarCommentAlbumAPI(getContext(),
                                ConteudoJsonParser.isConnectionInternet(getContext()), idAlbum, idUtilizador, novoComentario, sqlDate);

                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        return view;
    }


    @Override
    public void onResfreshComment(ArrayList<Comentario> listaComentarios, ArrayList<Utilizador> listaComentariosUser) {
        recyclerViewAlbumComment.setHasFixedSize(true);//Otimização
        recyclerViewAlbumComment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        CommentAdapter commentAdapter = new CommentAdapter(getContext(), listaComentarios, listaComentariosUser, idUtilizador, idAlbum);
        recyclerViewAlbumComment.setAdapter(commentAdapter);
    }

    @Override
    public void onResfreshNovoComment(String checkNovoComment) {
        checkComentarios = checkNovoComment;
        if(checkComentarios.equals("true")){
            SingletonGestorDados.getInstance(getContext()).getAlbumCommentsAPI(getContext(),
                    ConteudoJsonParser.isConnectionInternet(getContext()), idAlbum);
        }
    }

}
