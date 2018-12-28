package models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;

public class SingletonGestorConteudo  {

    private static SingletonGestorConteudo INSTANCE = null;
    private ArrayList<Album> albuns;
    private ArrayList<Artista> artistas;
    private ArrayList<Genero> generos;
    private ArrayList<Musica> musicas;

    private ArrayList<Musica> musicasAlbum;
    private ArrayList<Album> albunsArtista;
    private ArrayList<Album> albunsGenero;

    private ModeloBDHelper modeloBDHelper = null;
    private static RequestQueue volleyQueue = null;

    private String  mUrlAPIAlbuns = "http://amsi.dei.estg.ipleiria.pt/api/livr";
    private String mUrlAPIArtistas = "http://amsi.dei.estg.ipleiria.pt/api/livwdr";
    private String mUrlAPIGeneros = "http://amsi.dei.estg.ipleiria.pt/api/lwfwr";
    private String mUrlAPIMusicas = "http://amsi.dei.estg.ipleiria.pt/api/efwe";

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

    public Album getAlbum(long idAlbum){
        for(Album album:albuns){
            if(album.getIdAlbum() == idAlbum){
                return album;
            }
        }
        return null;
    }

    public ArrayList<Artista> getArtistasBD(){
        artistas = modeloBDHelper.getAllArtistasBD();
        return artistas;
    }

    public Artista getArtista(long idArtista){
        for (Artista artista:artistas) {
            if(artista.getIdArtista() == idArtista){
                return artista;
            }
        }
        return null;
    }


    public ArrayList<Genero> getGenerosBD(){
        generos = modeloBDHelper.getAllGenerosBD();
        return generos;
    }

    public Genero getGenero(long idGenero){
        for (Genero genero:generos) {
            if(genero.getIdGenero() == idGenero){
                return genero;
            }
        }
        return null;
    }


    public ArrayList<Musica> getMusicasBD(){
        musicas = modeloBDHelper.getAllMusicasBD();
        return musicas;
    }

    public Musica getMusica(long idMusica){
        for (Musica musica:musicas) {
            if(musica.getIdMusica() == idMusica){
                return musica;
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
                generos.add(auxGenero);
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

    // VAI BUSCAR TODOS OS ALBUNS DE UM ARTISTA
    public ArrayList<Album> albunsArtista(long idArtista){
        for (Album album:albuns){
            if(album.getId_Autor() == idArtista){
                albunsArtista.add(album);
            }
        }
        return albunsArtista;
    }

    // VAI BUSCAR TODOS OS ALBUNS DE UM GENERO
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
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIAlbuns, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    albuns = ConteudoJsonParser.parseJsonAlbum(response, context);
                    adicionarAlbunsBD(albuns);
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
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIArtistas, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    artistas = ConteudoJsonParser.parseJsonArtista(response, context);
                    adicionarArtistasBD(artistas);
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
            generos = modeloBDHelper.getAllGenerosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIGeneros, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    generos = ConteudoJsonParser.parseJsonGenero(response, context);
                    adicionarGenerosBD(generos);
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
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIMusicas, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    musicas = ConteudoJsonParser.parseJsonMusica(response, context);
                    adicionarMusicasBD(musicas);
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









}
