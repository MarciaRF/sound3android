package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adaptadores.CompraAdapter;
import models.Compra;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.ComprasRegistadasListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class ComprasActivity extends AppCompatActivity implements ComprasRegistadasListener {

    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Compra> compras;
    private long idUtilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        ArrayList utilizador = GestorSharedPref.getInstance(this).getUser();
        idUtilizador = Integer.parseInt(utilizador.get(0).toString());

        SingletonGestorDados.getInstance(this).setComprasRegistadasListener(this);
        SingletonGestorDados.getInstance(this).getComprasRegistadasAPI(this,ConteudoJsonParser.isConnectionInternet(this), idUtilizador);

        list = findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);


    }

    @Override
    public void onResponseGetCompras(ArrayList<Compra> compras) {
        if(compras!=null){
            System.out.println("----->ActComprasOnResponse");
            CompraAdapter compraAdapter = new CompraAdapter(this,compras);
            list.setAdapter(compraAdapter);
        }
    }
}
