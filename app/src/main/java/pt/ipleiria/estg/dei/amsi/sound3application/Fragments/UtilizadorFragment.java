package pt.ipleiria.estg.dei.amsi.sound3application.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

import models.Utilizador;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.ComprasActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.DownloadActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class UtilizadorFragment extends Fragment {
    View view;
    private Button btnRegistoCompras;
    private Button btnListaDownload;
    private ArrayList<String> user;
    private TextView nomeUser;
    private TextView emailUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.inflate(R.layout.fragment_utilizador, container, false);

        nomeUser = view.findViewById(R.id.tv_username);
        emailUser = view.findViewById(R.id.tv_email);

        btnRegistoCompras = view.findViewById(R.id.buttonRegistoCompra);
        btnListaDownload = view.findViewById(R.id.buttonDownload);

        user = GestorSharedPref.getInstance(getContext()).getUser();

        nomeUser.setText(user.get(1));
        emailUser.setText(user.get(2));

        btnRegistoCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getContext(), ComprasActivity.class);
            startActivity(intent);
            }
        });

        btnListaDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getContext(), DownloadActivity.class);
            startActivity(intent);
            }
        });

        return view;
    }

}
