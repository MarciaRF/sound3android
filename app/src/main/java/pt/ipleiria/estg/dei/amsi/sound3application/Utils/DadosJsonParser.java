package pt.ipleiria.estg.dei.amsi.sound3application.Utils;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import models.Album;
import models.Comentario;
import models.FavoritoAlbum;
import models.FavoritoArtista;
import models.FavoritoGenero;
import models.FavoritoMusica;
import models.LinhaCompra;
import models.Utilizador;
import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.PesquisaActivity;

public class DadosJsonParser {

    // Passar de Json para DB
    public static ArrayList<Utilizador> parseJsonUtilizador(JSONArray response, Context context){
        ArrayList<Utilizador> tempListaUtilizador = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject utilizador = (JSONObject) response.get(i);

                int idUtilizador = utilizador.getInt("idUtilizador");
                String nomeUtilizafor = utilizador.getString("nomeUtilizador");
                String passwordUtilizafor = utilizador.getString("passwordUtilizador");
                String emailUtilizafor = utilizador.getString("emailUtilizador");

                Utilizador auxUtilizador = new Utilizador(idUtilizador, nomeUtilizafor, passwordUtilizafor, emailUtilizafor);
                tempListaUtilizador.add(auxUtilizador);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaUtilizador;
    }

    public static ArrayList<LinhaCompra> parseJsonLinhaCompra(JSONArray response, Context context){
        ArrayList<LinhaCompra> tempListaLinhaCompra = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject linhaCompra = (JSONObject) response.get(i);

                int idCompra = linhaCompra.getInt("idCompra");
                int idMusica = linhaCompra.getInt("idMusica");

                LinhaCompra auxLinhaCompra = new LinhaCompra(idCompra, idMusica);
                tempListaLinhaCompra.add(auxLinhaCompra);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaLinhaCompra;
    }

    public static ArrayList<Comentario> parseJsonComentarios(JSONArray response, Context context){
        ArrayList<Comentario> tempListaComentario = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject comentario = (JSONObject) response.get(i);

                int idComentario = comentario.getInt("id");
                String conteudo = comentario.getString("conteudo");
                String dataCriacao = comentario.getString("dataCriacao");
                int idUtilizador = comentario.getInt("idUtilizador");
                int idAlbum = comentario.getInt("idAlbum");

                Comentario auxComentario = new Comentario(idComentario, conteudo, dataCriacao, idUtilizador, idAlbum);
                tempListaComentario.add(auxComentario);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaComentario;
    }

    public static ArrayList<FavoritoAlbum> parseJsonFavAlbuns(JSONArray response, Context context){
        ArrayList<FavoritoAlbum> tempListaFavAlbum = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject favAlbum = (JSONObject) response.get(i);

                int idFavAlbum = favAlbum.getInt("idFavAlbum");
                int idUtilizador = favAlbum.getInt("idUtilizador");
                int idAlbum = favAlbum.getInt("idAlbum");

                FavoritoAlbum auxFavAlbum = new FavoritoAlbum(idFavAlbum, idUtilizador, idAlbum);
                tempListaFavAlbum.add(auxFavAlbum);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaFavAlbum;
    }

    public static ArrayList<FavoritoArtista> parseJsonFavArtista(JSONArray response, Context context){
        ArrayList<FavoritoArtista> tempListaFavArtista = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject favArtista = (JSONObject) response.get(i);

                int idFavArtista = favArtista.getInt("idFavArtista");
                int idUtilizador = favArtista.getInt("idUtilizador");
                int idArtista = favArtista.getInt("idArtista");

                FavoritoArtista auxFavArtista = new FavoritoArtista(idFavArtista, idUtilizador, idArtista);
                tempListaFavArtista.add(auxFavArtista);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaFavArtista;
    }

    public static ArrayList<FavoritoGenero> parseJsonFavGenero(JSONArray response, Context context){
        ArrayList<FavoritoGenero> tempListaFavGenero = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject favGenero = (JSONObject) response.get(i);

                int idFavGenero = favGenero.getInt("idFavGenero");
                int idUtilizador = favGenero.getInt("idUtilizador");
                int idGenero = favGenero.getInt("idGenero");

                FavoritoGenero auxFavGenero = new FavoritoGenero(idFavGenero, idUtilizador, idGenero);
                tempListaFavGenero.add(auxFavGenero);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaFavGenero;
    }

    public static ArrayList<FavoritoMusica> parseJsonFavMusica(JSONArray response, Context context){
        ArrayList<FavoritoMusica> tempListaFavMusica = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject favMusica = (JSONObject) response.get(i);

                int idFavMusica = favMusica.getInt("idFavMusica");
                int idUtilizador = favMusica.getInt("idUtilizador");
                int idMusica = favMusica.getInt("idMusica");

                FavoritoMusica auxFavMusica = new FavoritoMusica(idFavMusica, idUtilizador, idMusica);
                tempListaFavMusica.add(auxFavMusica);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaFavMusica;
    }



    // Passar para JSON
    public static Utilizador parseJsonUtilizador (String responde, Context context){
        Utilizador auxUtilizador = null;

        try{
            JSONObject utilizador = new JSONObject(responde);
            int idUtilizador = utilizador.getInt("idUtilizador");
            String nomeUtilizador = utilizador.getString("nomeUtilizador");
            String passwordUtilizador = utilizador.getString("passwordUtilizador");
            String emailUtilizador = utilizador.getString("emailUtilizador");

            auxUtilizador = new Utilizador(idUtilizador, nomeUtilizador, passwordUtilizador, emailUtilizador);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return  auxUtilizador;
    }

    public static LinhaCompra parseJsonLinhaCompra (String responde, Context context){
        LinhaCompra auxLinhaCompra = null;

        try{
            JSONObject linhaCompra = new JSONObject(responde);
            int idCompra = linhaCompra.getInt("idCompra");
            int idUtilizador = linhaCompra.getInt("idUtilizador");

            auxLinhaCompra = new LinhaCompra(idCompra, idUtilizador);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return  auxLinhaCompra;
    }

    public static Comentario parseJsonComentarios (String responde, Context context){
        Comentario auxComentario = null;

        try{
            JSONObject comentario = new JSONObject(responde);
            int idComentario = comentario.getInt("id");
            String conteudo = comentario.getString("conteudo");
            String dataCriacao = comentario.getString("dataCriacao");
            int idUtilizador = comentario.getInt("idUtilizador");
            int idAlbum = comentario.getInt("idAlbum");

            auxComentario = new Comentario(idComentario, conteudo, dataCriacao, idUtilizador, idAlbum);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return  auxComentario;
    }

    public static FavoritoAlbum parseJsonFavAlbum (String responde, Context context){
        FavoritoAlbum auxFavAlbum = null;

        try{
            JSONObject favAlbum = new JSONObject(responde);
            int idFavAlbum = favAlbum.getInt("idFavAlbum");
            int idUtilizador = favAlbum.getInt("idUtilizador");
            int idAlbum = favAlbum.getInt("idAlbum");

            auxFavAlbum = new FavoritoAlbum(idFavAlbum, idUtilizador, idAlbum);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return auxFavAlbum;
    }

    public static FavoritoArtista parseJsonFavArtista (String responde, Context context){
        FavoritoArtista auxFavArtista = null;

        try{
            JSONObject favArtista = new JSONObject(responde);
            int idFavArtista = favArtista.getInt("idFavArtista");
            int idUtilizador = favArtista.getInt("idUtilizador");
            int idArtista = favArtista.getInt("idArtista");

            auxFavArtista = new FavoritoArtista(idFavArtista, idUtilizador, idArtista);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return auxFavArtista;
    }

    public static FavoritoGenero parseJsonFavGenero (String responde, Context context){
        FavoritoGenero auxFavGenero = null;

        try{
            JSONObject favGenero = new JSONObject(responde);
            int idFavGenero = favGenero.getInt("idFavGenero");
            int idUtilizador = favGenero.getInt("idUtilizador");
            int idGenero = favGenero.getInt("idGenero");

            auxFavGenero = new FavoritoGenero(idFavGenero, idUtilizador, idGenero);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return auxFavGenero;
    }

    public static FavoritoMusica parseJsonFavMusica (String responde, Context context){
        FavoritoMusica auxFavMusica = null;

        try{
            JSONObject favAGenero = new JSONObject(responde);
            int idFavMusica = favAGenero.getInt("idFavMusica");
            int idUtilizador = favAGenero.getInt("idUtilizador");
            int idMusica = favAGenero.getInt("idMusica");

            auxFavMusica = new FavoritoMusica(idFavMusica, idUtilizador, idMusica);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return auxFavMusica;
    }
}
