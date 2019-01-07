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
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.CarrinhoFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.FavoritosFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.HomeFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.Fragments.UtilizadorFragment;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

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

    //Arrays do Fake Data
    private ArrayList<Album> lstAlbum;
    private ArrayList<Musica> lstMusica;
    private ArrayList<Artista> lstArtista;
    private ArrayList<Genero> lstGenero;

    private SingletonGestorConteudo gestorConteudo;
    private static final String ESTADO_GESTOR_ALBUNS = "ESTADO_GESTOR_ALBUNS";

    //Notificacao
    private  final String CHANNEL_ID = "notificacao";
    private final int NOTIFICATION_ID = 1;

    //MQTT
    MqttAndroidClient client;
    private final String SERVERCONECTION = "tcp://192.168.43.44:1883";
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


        // Adiciona Fake Data na DB para TESTES
        SingletonGestorConteudo.getInstance(this).adicionarAlbumBD(criarAlbum());
        SingletonGestorConteudo.getInstance(this).adicionarArtistaBD(criarArtista());
        SingletonGestorConteudo.getInstance(this).adicionarMusicaBD(criarMusica());
        //SingletonGestorConteudo.getInstance(this).adicionarGeneroBD(criarGenero());


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
                    System.out.println("----->Ex"+exception);

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


// Subscrever Tópico
    private void subscription(){
        try {
            IMqttToken subToken = client.subscribe(TOPICOSUBSCRICAO, 1);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    System.out.println("1----> MQTT Tópico Subscrito");
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
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemPesquisa);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {

                intentPesquisa(newText);
                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

    // Intent da Pesquisa
    private void intentPesquisa(String newText){
        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtra(PESQUISA, newText);
        startActivity(intent);
    }



    // Gerar Fake Data para a DB
    public ArrayList<Album> criarAlbum(){
        lstAlbum = new ArrayList<>();

        lstAlbum.add(new Album( 1,"Filhos do Rossi", 2017, R.drawable.filhos_do_rossi,1,1));
        lstAlbum.add(new Album( 2,"Bad", 1987, R.drawable.bad,2,2));
        lstAlbum.add(new Album( 3,"Avici", 2017, R.drawable.avici,3,3));
        lstAlbum.add(new Album( 4,"Master of Puppets", 1986, R.drawable.mastertofpuppets,4,4));
        lstAlbum.add(new Album( 5,"Legend", 1984, R.drawable.legend,5,5));

        return lstAlbum;
    }

    public ArrayList<Musica> criarMusica(){
        lstMusica = new ArrayList<>();

        lstMusica.add(new Musica(1, "Intro", "2:17", 2, 1, "nao tem", 1));
        lstMusica.add(new Musica(2, "Todos Olham", "3:48", 2, 1, "nao tem", 2));
        lstMusica.add(new Musica(3, "Essa life é Good", "4:07", 2, 1, "nao tem", 3));
        lstMusica.add(new Musica(4, "Não Sinto", "5:11", 2, 1, "nao tem", 4));
        lstMusica.add(new Musica(5, "Kill 'Em All", "4:09", 2, 1, "nao tem", 5));
        lstMusica.add(new Musica(6, "Pagode", "3:37", 2, 1, "nao tem", 6));
        lstMusica.add(new Musica(7, "Não Tens Visto", "5:27", 2, 1, "nao tem", 7));

        return lstMusica;
    }

    public ArrayList<Artista> criarArtista(){
        lstArtista = new ArrayList<>();

        lstArtista.add(new Artista(1, "Wet Bed Gang", "PT", 2014, R.drawable.wbg));
        lstArtista.add(new Artista(2, "Michael Jackson", "EUA", 1964, R.drawable.michael_jackson));
        lstArtista.add(new Artista(3, "Avicii", "SWE", 2006, R.drawable.avicii));
        lstArtista.add(new Artista(4, "Metallica", "EUA", 1981, R.drawable.metallica));
        lstArtista.add(new Artista(5, "Bob Marlley", "JAM", 1962, R.drawable.bob_marley));

        return lstArtista;
    }

    /*public ArrayList<Genero> criarGenero(){
        lstGenero = new ArrayList<>();

        lstGenero.add(new Genero(1, "Eletronic", "Por definição, música eletrônica é toda e qualquer música criada ou modificada por meio de equipamentos e instrumentos eletrônicos, como gravadores digitais, computadores, softwares e sintetizadores.", R.drawable.eletronic));
        lstGenero.add(new Genero(2, "Hip Hop", "DescriçãoHip hop é um gênero musical, com uma subcultura iniciada durante a década de 1970, nas áreas centrais de comunidades jamaicanas, latinas e afro-americanas da cidade de Nova Iorque.", R.drawable.hiphop));
        lstGenero.add(new Genero(3, "Pop", "A música pop é um gênero da música popular que se originou durante a década de 1950 nos Estados Unidos e Reino Unido.", R.drawable.ppop));
        lstGenero.add(new Genero(4, "Rock", "Rock é um termo abrangente que define um gênero musical de música popular que se desenvolveu durante e após a década de 1950.", R.drawable.rock));
        lstGenero.add(new Genero(5, "Reggae", "Reggae é um gênero musical desenvolvido originalmente na Jamaica do fim da década de 1960. Embora por vezes seja usado num sentido mais amplo para se referir à maior parte dos tipos de música jamaicana.", R.drawable.reggae));

        return lstGenero;
    }*/


}
