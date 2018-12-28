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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.amsi.sound3application.Utils.DadosJsonParser;

public class SingletonGestorDados {

    private static SingletonGestorDados INSTANCE = null;
    private ArrayList<Comentario> comentarios;

    private ModeloBDHelper modeloBDHelper = null;
    private static RequestQueue volleyQueue = null;

    private String mUrlApiComentarios = "http://amsi.dei.estg.ipleiria.pt/api/efwe";

    public SingletonGestorDados(Context context) {

        comentarios = new ArrayList<>();

        modeloBDHelper = new ModeloBDHelper(context);
    }


    public static synchronized SingletonGestorDados getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestorDados(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return INSTANCE;
    }



    public void addComentarioBD(Comentario comentario){
        modeloBDHelper.adicionarComentarioBD(comentario);
    }

    public void adicionarComentariosBD(ArrayList<Comentario> listaComentarios){
        modeloBDHelper.removeAllComentarios();
        for(Comentario comentario : listaComentarios){
            addComentarioBD(comentario);
        }
    }

    public ArrayList<Comentario> getComentariosBD(){
        comentarios = modeloBDHelper.getAllComentariosDB();
        return comentarios;
    }

    public Comentario getComentario(long idComentario){
        for(Comentario comentario : comentarios){
            if(comentario.getIdComentario() == idComentario){
                return comentario;
            }
        }
        return null;
    }


    public void getAllComentariosAPI(final Context context, boolean isConnected){
       if(!isConnected){
            comentarios = modeloBDHelper.getAllComentariosDB();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiComentarios, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    comentarios = DadosJsonParser.parseJsonComentatrio(response, context);
                    adicionarComentariosBD(comentarios);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: ");
                }
            });
            volleyQueue.add(req);
        }
    }


    public void adicionarComentarioAPI(final Comentario comentario, final  Context context){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlApiComentarios, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("-->Resposta Add Post" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", "AMSI-TOKEN");
                params.put("conteudo", comentario.getConteudo());
                params.put("data_criacao", comentario.getData_Criacao());
                params.put("id_utilizador", ""+comentario.getId_Utilizador());
                params.put("id_album", ""+comentario.getId_Album());

                return params;
            }
        };
        volleyQueue.add(req);
    }


}
