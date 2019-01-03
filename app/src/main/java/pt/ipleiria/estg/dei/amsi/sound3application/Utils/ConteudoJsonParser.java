package pt.ipleiria.estg.dei.amsi.sound3application.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import models.Album;
import models.Artista;
import models.Genero;
import models.Musica;

public class ConteudoJsonParser {

    public static ArrayList<Album> parseJsonAlbum (JSONArray response, Context context){
        ArrayList<Album> tempListaAlbum = new ArrayList<>();
/*
        try{
            for(int i=0; i< response.length(); i++){

                JSONObject album = (JSONObject) response.get(i);

                int idAlbum = album.getInt("id");
                String nome = album.getString("nome");
                int ano = album.getInt("ano");
                String capa = album.getString("capa");
                int idAutor = album.getInt("idAutor");
                int idGenero = album.getInt("idGenero");

                Album auxAlbum = new Album(idAlbum, nome, ano, capa, idAutor, idGenero);
                tempListaAlbum.add(auxAlbum);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
*/
        return tempListaAlbum;
    }

    public static ArrayList<Artista> parseJsonArtista (JSONArray response, Context context){
        ArrayList<Artista> tempListaArtista = new ArrayList<>();
/*
        try{
            for(int i=0; i< response.length(); i++){

                JSONObject artista = (JSONObject) response.get(i);

                int idArtista = artista.getInt("id");
                String nome = artista.getString("nome");
                String nacionalidade = artista.getString("nacionalidade");
                int ano = artista.getInt("ano");
                String imagem = artista.getString("imagem");


                Artista auxArtista = new Artista(idArtista, nome, nacionalidade, ano, imagem);
                tempListaArtista.add(auxArtista);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
*/
        return tempListaArtista;
    }

    public static ArrayList<Genero> parseJsonGenero (JSONArray response, Context context){
        ArrayList<Genero> tempListaGenero = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject genero = (JSONObject) response.get(i);

                int idGenero = genero.getInt("id");
                String nome = genero.getString("nome");
                String descricao = genero.getString("descricao");
                String imagem = genero.getString("caminhoImagem");

                Genero auxGenero = new Genero(idGenero, nome, descricao, imagem);
                tempListaGenero.add(auxGenero);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaGenero;
    }

    public static ArrayList<Musica> parseJsonMusica (JSONArray response, Context context){
        ArrayList<Musica> tempListaMusica = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject musica = (JSONObject) response.get(i);

                int idMusica = musica.getInt("id");
                String nome = musica.getString("nome");
                String duracao = musica.getString("duracao");
                int preco = musica.getInt("preco");
                int idAlbum = musica.getInt("idAlbum");
                String caminhoMusica = musica.getString("caminhoMusica");
                int posicao = musica.getInt("posicao");

                Musica auxMusica = new Musica(idMusica, nome, duracao, preco, idAlbum, caminhoMusica, posicao);
                tempListaMusica.add(auxMusica);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaMusica;
    }



    public static  boolean isConnectionInternet(Context context){
        ConnectivityManager cm=
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }


}
