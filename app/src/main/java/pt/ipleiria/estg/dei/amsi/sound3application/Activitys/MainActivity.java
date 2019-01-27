package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;
import models.SingletonGestorConteudo;
import models.SingletonGestorDados;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.CarrinhoFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.FavoritosFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.HomeFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.UtilizadorFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

import static pt.ipleiria.estg.dei.amsi.sound3application.Activitys.PesquisaActivity.PESQUISA;

/**
 *  Link para ver o Bottom Navigation
 *  https://www.youtube.com/watch?v=tPV8xA7m-iw
 *
 *  Link para o MQTT
 *  https://www.youtube.com/watch?v=BAkGm02WBc0
 *  https://www.youtube.com/watch?v=6AE4D8INs_U&t=491s
 */

public class MainActivity extends AppCompatActivity {

    private SingletonGestorConteudo gestorConteudo;
    private static final String ESTADO_GESTOR_ALBUNS = "ESTADO_GESTOR_ALBUNS";

    //Notificacao
    private  final String CHANNEL_ID = "notificacao";
    private final int NOTIFICATION_ID = 1;

    //MQTT
    MqttAndroidClient client;
    private final String SERVERCONECTION = "tcp://192.168.1.119:1883";
    private final String TOPICOSUBSCRICAO = "INSERT";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            //define a home como default ao abrir a app
            bottomNav.setSelectedItemId(R.id.nav_home);
            //gerar
            this.gestorConteudo = new SingletonGestorConteudo(getApplicationContext());
        }else{
            this.gestorConteudo = (SingletonGestorConteudo)
                    savedInstanceState.getSerializable(ESTADO_GESTOR_ALBUNS);
        }


        // Código do MQTT
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), SERVERCONECTION, clientId);
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                    // Chama método de subscricao
                    subscription();
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this, ""+exception, Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //Toast.makeText(MainActivity.this, "Message: "+ message, Toast.LENGTH_SHORT).show();
                android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Novo Conteudo");
                alert.setMessage("Novo Conteudo Foi Adicionado")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                android.support.v7.app.AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }


    // Subscrever Tópico MQTT
    private void subscription(){
        try {
            IMqttToken subToken = client.subscribe(TOPICOSUBSCRICAO, 1);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    System.out.println("1----> MQTT Tópico "+TOPICOSUBSCRICAO+" Subscrito");
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    System.out.println("1----> MQTT Falha ao Subscrever");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    //Código do Menu de Baixo
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem MenuItem) {
                    Fragment selectedFragment = null;
                    switch(MenuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new UtilizadorFragment();
                            break;
                        case R.id.nav_favoritos:
                            selectedFragment = new FavoritosFragment();
                            break;
                        case R.id.nav_carrinho:
                            selectedFragment = new CarrinhoFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };


    // Código Barra de Pesquisa
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pesquisa, menu);

        MenuItem itemPesquisa = menu.findItem(R.id.itemPesquisa);
        SearchView searchView = (SearchView) itemPesquisa.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                intentPesquisa(s);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_opcoes_logout){
            finish();
            GestorSharedPref.getInstance(getApplicationContext()).logout();
        }

        return super.onOptionsItemSelected(item);
    }

    // Intent da Pesquisa
    private void intentPesquisa(String newText){
        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtra(PesquisaActivity.PESQUISA, newText);
        startActivity(intent);
    }

    public void mostrarCompras(View view) {
        Intent intent = new Intent(this,ComprasActivity.class);
        startActivity(intent);

    }

}
