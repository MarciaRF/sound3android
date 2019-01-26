package models;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesArtistaListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.HomeListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.MusicasListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;


public class SingletonGestorConteudo  implements HomeListener, MusicasListener, DetalhesArtistaListener {


    private static SingletonGestorConteudo INSTANCE = null;
    private ArrayList<Album> albuns;
    private ArrayList<Artista> artistas;
    private ArrayList<Genero> generos;
    private ArrayList<Musica> musicas;

    private ArrayList<Album> topAlbuns;
    private ArrayList<Artista> artistasMaisVendidos;

    private ArrayList<Album> albunsRecentes;
    private ArrayList<Artista> albunsRecentesArtistas;

    private ArrayList<Musica> musicasAlbum;
    private ArrayList<Album> albunsArtista;
    private ArrayList<Album> albunsGenero;

    private ModeloBDHelper modeloBDHelper = null;
    private static RequestQueue volleyQueue = null;

    private HomeListener homeListener;
    private MusicasListener musicasListener;
    private DetalhesArtistaListener detalhesArtistaListener;

    private ArrayList<Artista> topAlbunsArtistas;

    private Album album;


    public static final String IP = "192.168.1.83";


    private String mUrlAPIAlbuns = "http://" + IP + "/sound3application/frontend/api/album";
    private String mUrlAPIArtistas = "http://" + IP + "/sound3application/frontend/api/artista";
    private String mUrlAPIGeneros = "http://" + IP + "/sound3application/frontend/web/api/genero";
    private String mUrlAPIMusicas = "http://" + IP + "/sound3application/frontend/api/musica";

    private String mUrlAPITopAlbuns = "http://" + IP + "/sound3application/frontend/web/api/album/topalbuns";
    private String mUrlAPIArtistasMaisVendidos = "http://" + IP + "/sound3application/frontend/web/api/artista/artistasrandom";
    private String mUrlAPIAlbunsMaisRecentes = "http://" + IP + "/sound3application/frontend/web/api/album/albunsrecentes";

    private String mUrlAPIMusicasAlbum =  "http://" + IP + "/sound3application/frontend/web/api/album/findmusicas?id=";

    public static synchronized SingletonGestorConteudo getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestorConteudo(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    public SingletonGestorConteudo(Context context) {
        albuns = new ArrayList<>();
        artistas = new ArrayList<>();
        generos = new ArrayList<>();
        musicas = new ArrayList<>();

        musicasAlbum = new ArrayList<>();
        albunsArtista = new ArrayList<>();
        albunsGenero = new ArrayList<>();

        modeloBDHelper = new ModeloBDHelper(context);
    }

    public ArrayList<Album> getAlbunsBD(){
        albuns = modeloBDHelper.getAllAlbunsBD();
        return albuns;
    }

    public ArrayList<Artista> getArtistasBD(){
        artistas = modeloBDHelper.getAllArtistasBD();
        return artistas;
    }

    public ArrayList<Genero> getGenerosBD(){
        generos = modeloBDHelper.getAllGenerosBD();
        return generos;
    }

    public ArrayList<Musica> getMusicasBD(){
        musicas = modeloBDHelper.getAllMusicasBD();
        return musicas;
    }



    public Genero getGenero(long idGenero){
        for (Genero genero:generos) {
            if(genero.getIdGenero() == idGenero){
                return genero;
            }
        }
        return null;
    }

    public Musica getMusica(long idMusica){
        for (Musica musica:musicas) {
            if(musica.getIdMusica() == idMusica){
                return musica;
            }
        }
        return null;
    }

    public Artista getArtista(long idArtista){
        for (Artista artista:artistas) {
            if(artista.getIdArtista() == idArtista){
                return artista;
            }
        }
        return null;
    }

    public Album getAlbum(long idAlbum){
        for(Album album:albuns){
            if(album.getIdAlbum() == idAlbum){
                return album;
            }
        }
        return null;
    }


    public void adicionarAlbumBD(ArrayList<Album> albuns){
        ArrayList<Album> auxAlbuns = new ArrayList<>();
        for (Album album:albuns){
            auxAlbuns.add(modeloBDHelper.adicionarAlbumBD(album));
        }
        if(auxAlbuns != null){
            for (Album auxAlbum:auxAlbuns) {
                albuns.add(auxAlbum);
            }
        }
    }

    public void adicionarArtistaBD(ArrayList<Artista> artistas){
        ArrayList<Artista> auxArtistas = new ArrayList<>();
        for (Artista artista:artistas){
            auxArtistas.add(modeloBDHelper.adicionarArtistaBD(artista));
        }
        if(auxArtistas != null){
            for (Artista auxArtista:auxArtistas) {
                artistas.add(auxArtista);
            }
        }
    }

    public void adicionarMusicaBD(ArrayList<Musica> musicas){
        ArrayList<Musica> auxMusicas = new ArrayList<>();
        for (Musica musica:musicas){
            auxMusicas.add(modeloBDHelper.adicionarMusicaBD(musica));
        }
        if(auxMusicas != null){
            for (Musica auxMusica:auxMusicas) {
                musicas.add(auxMusica);
            }
        }
    }

    public void adicionarGeneroBD(ArrayList<Genero> generos){
        ArrayList<Genero> auxGeneros = new ArrayList<>();
        for (Genero genero:generos){
            auxGeneros.add(modeloBDHelper.adicionarGeneroBD(genero));
        }
        if(auxGeneros != null){
            for (Genero auxGenero:auxGeneros) {
                this.generos.add(auxGenero);
            }
        }
    }


    // VAI BUSCAR TODAS AS MUSICAS DE UM ALBUM
    public ArrayList<Musica> musicasAlbum(long idAlbum){
        for(Musica musica:musicas){
            if(musica.getIdAlbum() == idAlbum){
                musicasAlbum.add(musica);
            }
        }
        return musicasAlbum;
    }

    public ArrayList<Album> albunsArtista(long idArtista){
        for (Album album:albuns){
            if(album.getId_Autor() == idArtista){
                albunsArtista.add(album);
            }
        }
        return albunsArtista;
    }

    public ArrayList<Album> albunsGenero(long idGenero){
        for (Album album:albuns){
            if(album.getId_Genero() == idGenero){
                albunsGenero.add(album);
            }
        }
        return albunsGenero;
    }


    public void addAlbumBD(Album album){
        modeloBDHelper.adicionarAlbumBD(album);
    }

    public void addArtistaBD(Artista artista){
        modeloBDHelper.adicionarArtistaBD(artista);
    }

    public void addGeneroBD(Genero genero){
        modeloBDHelper.adicionarGeneroBD(genero);
    }

    public void addMusicaBD(Musica musica){
        modeloBDHelper.adicionarMusicaBD(musica);
    }


    public void adicionarAlbunsBD(ArrayList<Album> listaAlbuns){
        modeloBDHelper.removeAllAlbuns();
        for(Album album : listaAlbuns){
            addAlbumBD(album);
        }
    }

    public void adicionarArtistasBD(ArrayList<Artista> listaArtistas){
        modeloBDHelper.removeAllArtistas();
        for(Artista artista : listaArtistas){
            addArtistaBD(artista);
        }
    }

    public void adicionarGenerosBD(ArrayList<Genero> listaGeneros){
        modeloBDHelper.removeAllGeneros();

        for(Genero genero : listaGeneros){
            addGeneroBD(genero);
        }
    }

    public void adicionarMusicasBD(ArrayList<Musica> listaMusicas){
        modeloBDHelper.removeAllMusicas();
        for(Musica musica : listaMusicas){
            addMusicaBD(musica);
        }
    }



    public void getAllAlbunsAPI(final Context context, boolean isConnected){
        if(!isConnected){
            albuns = modeloBDHelper.getAllAlbunsBD();
            if(modeloBDHelper != null){
                homeListener.onRefreshAlbuns(albuns);
            }
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIAlbuns, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    albuns = ConteudoJsonParser.parseJsonAlbum(response, context);
                    adicionarAlbunsBD(albuns);
                    if(homeListener != null){
                        homeListener.onRefreshAlbuns(albuns);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }

    public void getAllArtistasAPI(final Context context, boolean isConnected){
        if(!isConnected){
            artistas = modeloBDHelper.getAllArtistasBD();
            if(modeloBDHelper != null){
                homeListener.onRefreshArtistas(artistas);
            }
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIArtistas, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    artistas = ConteudoJsonParser.parseJsonArtista(response, context);
                    adicionarArtistasBD(artistas);
                    if(homeListener != null){
                        homeListener.onRefreshArtistas(artistas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }

    public void getAllMusicasAPI(final Context context, boolean isConnected){
        if(!isConnected){
            musicas = modeloBDHelper.getAllMusicasBD();
            if(modeloBDHelper != null){
                homeListener.onRefreshMusicas(musicas);
            }
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIMusicas, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    musicas = ConteudoJsonParser.parseJsonMusica(response, context);
                    adicionarMusicasBD(musicas);
                    if(homeListener != null){
                        homeListener.onRefreshMusicas(musicas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }


    public void getTopAlbunsAPI(final Context context, boolean isConnected){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPITopAlbuns, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objAlbum = null;
                        JSONArray objArtista = null;
                        objAlbum = objeto.getJSONArray("albuns");
                        objArtista = objeto.getJSONArray("artistas");

                        topAlbuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);
                        topAlbunsArtistas = ConteudoJsonParser.parseJsonArtista(objArtista, context);

                        if(homeListener != null){
                            homeListener.onRefreshTopAlbuns(topAlbuns, topAlbunsArtistas);
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }

    public void getAlbunsMaisRecentesAPI(final Context context, boolean isConnected){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIAlbunsMaisRecentes, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objAlbum = null;
                        JSONArray objArtista = null;
                        objAlbum = objeto.getJSONArray("albuns");
                        objArtista = objeto.getJSONArray("artistas");

                        albunsRecentes = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);
                        albunsRecentesArtistas = ConteudoJsonParser.parseJsonArtista(objArtista, context);

                        if(homeListener != null){
                            homeListener.onRefreshAlbunsMaisRecentes(albunsRecentes, albunsRecentesArtistas);
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }

    public void getMusicasAlbumAPI(final Context context, boolean isConnected, long IdAlbum){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIMusicasAlbum + IdAlbum, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objMusica = null;
                        JSONObject objAlbum = null;
                        objMusica = objeto.getJSONArray("musica");
                        objAlbum = objeto.getJSONObject("album");



                        ArrayList<String> tempAlbum = new ArrayList<>();

                        tempAlbum.add("" + objAlbum.getLong("id"));
                        tempAlbum.add(objAlbum.getString("nome"));
                        tempAlbum.add("" + objAlbum.getInt("ano"));
                        tempAlbum.add("" + objAlbum.getInt("preco"));
                        tempAlbum.add("" + objAlbum.getLong("id_artista"));
                        tempAlbum.add("" + objAlbum.getLong("id_genero"));
                        tempAlbum.add(objAlbum.getString("caminhoImagem"));


                        musicas = ConteudoJsonParser.parseJsonMusica(objMusica, context);
                        album = ConteudoJsonParser.parseJsonObejectAlbum(tempAlbum, context);

                        if(homeListener != null){
                            musicasListener.onRefreshMusicas(musicas, album);
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }

    }

    public void getArtistasMaisVendidosAPI(final Context context, boolean isConnected){
        if(!isConnected){

        }else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIArtistasMaisVendidos, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    artistasMaisVendidos = ConteudoJsonParser.parseJsonArtista(response, context);

                    if(homeListener != null){
                        homeListener.onRefreshArtistasMaisVendidos(artistasMaisVendidos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }



    public void getAllGenerosAPI(final Context context, boolean isConnected){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIGeneros, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    generos = ConteudoJsonParser.parseJsonGenero(response, context);
                    //adicionarGenerosBD(generos);
                    if(homeListener != null){
                        homeListener.onRefreshGeneros(generos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Erros Generos: " + error, Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }



    public void setConteudoListener(HomeListener homeListener){
        this.homeListener = homeListener;
    }

    public void setMusicasListener(MusicasListener musicasListener){
        this.musicasListener = musicasListener;
    }

    public void setDetalhesArtistaListener(DetalhesArtistaListener detalhesArtistaListener){
        this.detalhesArtistaListener = detalhesArtistaListener;
    }




    @Override
    public void onRefreshGeneros(ArrayList<Genero> listaGeneros) {
    }

    @Override
    public void onRefreshAlbuns(ArrayList<Album> listaAlbuns) {
    }

    @Override
    public void onRefreshArtistas(ArrayList<Artista> listaArtistas) {
    }

    @Override
    public void onRefreshMusicas(ArrayList<Musica> listaMusicas) {
    }

    @Override
    public void onRefreshTopAlbuns(ArrayList<Album> listaTopAlbuns, ArrayList<Artista> listaArtistasTopAlbuns) { }

    @Override
    public void onRefreshArtistasMaisVendidos(ArrayList<Artista> listArtistasMaisVendidos) { }

    @Override
    public void onRefreshAlbunsMaisRecentes(ArrayList<Album> listaAlbunsMiasVendidos, ArrayList<Artista> listaArtistasTopAlbuns) { }


    @Override
    public void onRefreshArtista(Artista artista) { }

    @Override
    public void onRefreshAbunsArtista(ArrayList<Album> albunsArtista, ArrayList<Artista> artistas) { }

    @Override
    public void checkArtistaInFavoritos(String check) { }

    @Override
    public void onRefreshMusicas(ArrayList<Musica> listaMusicas, Album listaMusicasArtistas) {

    }
}
