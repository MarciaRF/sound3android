package pt.ipleiria.estg.dei.amsi.sound3application.Utils;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import models.Album;
import models.Comentario;

public class DadosJsonParser {


    public static ArrayList<Comentario> parseJsonComentatrio(JSONArray response, Context context){
        ArrayList<Comentario> tempListaComentario = new ArrayList<>();

        try{
            for(int i=0; i< response.length(); i++){

                JSONObject comentario = (JSONObject) response.get(i);

                int id_Comentario = comentario.getInt("id");
                String conteudo = comentario.getString("conteudo");
                String data_Criacao = comentario.getString("dataCriacao");
                int id_Utilizador = comentario.getInt("idUtilizador");
                int id_Album = comentario.getInt("idAlbum");

                Comentario auxComentario = new Comentario(id_Comentario, conteudo, data_Criacao, id_Utilizador, id_Album);
                tempListaComentario.add(auxComentario);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaComentario;
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



}
