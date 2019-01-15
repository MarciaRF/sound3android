package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import models.Comentario;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class CommentFragment extends Fragment implements CommentListener {

    View view;

    public CommentFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_comment, container, false);
        return view;
    }


    @Override
    public void onResfreshComment(ArrayList<Comentario> listaComentarios) {

    }
}
