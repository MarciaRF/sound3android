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

    private ArrayList<Utilizador> utilizadores;
    private ArrayList<LinhaCompra> linhaCompras;
    private ArrayList<Comentario> comentarios;
    private ArrayList<FavoritoAlbum> favAlbuns;
    private ArrayList<FavoritoArtista> favArtistas;
    private ArrayList<FavoritoGenero> favGeneros;
    private ArrayList<FavoritoMusica> favMusicas;

    private ModeloBDHelper modeloBDHelper = null;
    private static RequestQueue volleyQueue = null;
    private static SingletonGestorDados INSTANCE = null;

    private String mUrlApiUtilizadores = "http://127.0.0.1/sound3application/frontend/api/utilizadores";
    private String mUrlApiLinhaCompras = "http://127.0.0.1/sound3application/frontend/api/linhaCompras";
    private String mUrlApiComentarios = "http://127.0.0.1/sound3application/frontend/api/comentarios";
    private String mUrlApiFavAlbuns = "http://127.0.0.1/sound3application/frontend/api/favoritosAlbuns";
    private String mUrlApiFavArtistas = "http://127.0.0.1/sound3application/frontend/api/favoritosArtistas";
    private String mUrlApiFavGeneros = "http://127.0.0.1/sound3application/frontend/api/favoritosGeneros";
    private String mUrlApiFavMusicas = "http://127.0.0.1/sound3application/frontend/api/favoritosMusicas";


    public SingletonGestorDados(Context context) {

        comentarios = new ArrayList<>();
        favAlbuns = new ArrayList<>();
        favArtistas = new ArrayList<>();
        favGeneros = new ArrayList<>();
        favMusicas = new ArrayList<>();
        linhaCompras = new ArrayList<>();
        utilizadores = new ArrayList<>();

        modeloBDHelper = new ModeloBDHelper(context);
    }


    public static synchronized SingletonGestorDados getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestorDados(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return INSTANCE;
    }


    // É chamado nos métodos de (adicionar) | Vai inserir na BD 1 a 1
    public void addUtilizadorBD(Utilizador utilizador){
        modeloBDHelper.adicionarUtilizadorDB(utilizador);
    }

    public void addLinhaCompraBD(LinhaCompra linhaCompra){
        modeloBDHelper.adicionarLinhaCompraDB(linhaCompra);
    }

    public void addComentarioBD(Comentario comentario){
        modeloBDHelper.adicionarComentarioBD(comentario);
    }

    public void addFavoritoAlbumBD(FavoritoAlbum favoritoAlbum){
        modeloBDHelper.adicionarAlbumFavoritoDB(favoritoAlbum);
    }

    public void addFavoritoArtistaBD(FavoritoArtista favoritoArtista){
        modeloBDHelper.adicionarArtistaFavoritoDB(favoritoArtista);
    }

    public void addFavoritoGeneroBD(FavoritoGenero favoritoGenero){
        modeloBDHelper.adicionarGeneroFavoritoDB(favoritoGenero);
    }

    public void addFavoritoMusicBD(FavoritoMusica favoritoMusica){
        modeloBDHelper.adicionarMusicaFavoritoDB(favoritoMusica);
    }



    //Adiciona Array á BD
    public void adicionarUtilizadoresBd(ArrayList<Utilizador> utilizadores){
        modeloBDHelper.removeAllUtilizadores();
        for (Utilizador utilizador : utilizadores){
            addUtilizadorBD(utilizador);
        }
    }

    public void adicionarLinhaComprasBD(ArrayList<LinhaCompra> listaLinhaCompra){
        modeloBDHelper.removeAllLinhasCompras();
        for(LinhaCompra lCompra : listaLinhaCompra){
            addLinhaCompraBD(lCompra);
        }
    }

    public void adicionarComentariosBD(ArrayList<Comentario> listaComentarios){
        modeloBDHelper.removeAllComentarios();
        for(Comentario comentario : listaComentarios){
            addComentarioBD(comentario);
        }
    }

    public void adicionarFavoritosAlbumBD(ArrayList<FavoritoAlbum> listaFavAlbum){
        modeloBDHelper.removeAllFavoritosAlbum();
        for(FavoritoAlbum favAlbum : listaFavAlbum){
            addFavoritoAlbumBD(favAlbum);
        }
    }

    public void adicionarFavoritosArtistaBD(ArrayList<FavoritoArtista> listaFavArtista){
        modeloBDHelper.removeAllFavoritosArtista();
        for(FavoritoArtista favArtista : listaFavArtista){
            addFavoritoArtistaBD(favArtista);
        }
    }

    public void adicionarFavoritosGeneroBD(ArrayList<FavoritoGenero> listaFavGenero){
        modeloBDHelper.removeAllFavoritosGenero();
        for(FavoritoGenero favGenero : listaFavGenero){
            addFavoritoGeneroBD(favGenero);
        }
    }

    public void adicionarFavoritosMusicaBD(ArrayList<FavoritoMusica> listaFavMusica){
        modeloBDHelper.removeAllFavoritosMusica();
            for(FavoritoMusica favMusica : listaFavMusica){
            addFavoritoMusicBD(favMusica);
        }
    }



    //Vai Buscar Dados á BD
    public ArrayList<Utilizador> getAllUtilizadoresBD(){
        utilizadores = modeloBDHelper.getAllUtilizadoresBD();
        return utilizadores;
    }

    public ArrayList<LinhaCompra> getLinhaComprasBD(){
        linhaCompras = modeloBDHelper.getAllLinhaComprasBD();
        return linhaCompras;
    }

    public ArrayList<Comentario> getComentariosBD(){
        comentarios = modeloBDHelper.getAllComentariosDB();
        return comentarios;
    }

    public ArrayList<FavoritoAlbum> getFavoritosAlbumBD(){
        favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
        return favAlbuns;
    }

    public ArrayList<FavoritoArtista> getFavoritosArtistaBD(){
        favArtistas = modeloBDHelper.getAllArtistasFavoritosBD();
        return favArtistas;
    }

    public ArrayList<FavoritoGenero> getFavoritosGeneroBD(){
        favGeneros = modeloBDHelper.getAllGenerosFavoritosBD();
        return favGeneros;
    }

    public ArrayList<FavoritoMusica> getFavoritosMusicaBD(){
        favMusicas = modeloBDHelper.getAllMusicasFavoritosBD();
        return favMusicas;
    }



    //Vai buscar um item espcifico
    public Utilizador getUrilizador(long idUrilizador){
        for(Utilizador utilizador : utilizadores){
            if(utilizador.getIdUtilizador() == idUrilizador){
                return utilizador;
            }
        }
        return null;
    }

    public LinhaCompra getLinhaCompras(long idLinhaCompra) {
        for(LinhaCompra lCompra : linhaCompras){
            if(lCompra.getIdCompra() == idLinhaCompra){
                return lCompra;
            }
        }
        return null;
    }

    public Comentario getComentario(long idComentario){
        for(Comentario comentario : comentarios){
            if(comentario.getIdComentario() == idComentario){
                return comentario;
            }
        }
        return null;
    }

    public FavoritoAlbum getFavoritoAlbum(long idFavAlbum){
        for(FavoritoAlbum favoritoAlbum : favAlbuns){
            if(favoritoAlbum.getIdFavAlbum() == idFavAlbum){
                return favoritoAlbum;
            }
        }
        return null;
    }

    public FavoritoArtista getFavoritoArtista(long idFavArtista){
        for(FavoritoArtista favoritoArtista : favArtistas){
            if(favoritoArtista.getIdFavArtista() == idFavArtista){
                return favoritoArtista;
            }
        }
        return null;
    }

    public FavoritoGenero getFavoritoGenero(long idFavGenero){
        for(FavoritoGenero favoritoGenero : favGeneros){
            if(favoritoGenero.getIdFavGenero() == idFavGenero){
                return favoritoGenero;
            }
        }
        return null;
    }

    public FavoritoMusica getFavoritoMusica(long idFavMusica){
        for(FavoritoMusica favoritoMusica : favMusicas){
            if(favoritoMusica.getIdFavMusica() == idFavMusica){
                return favoritoMusica;
            }
        }
        return null;
    }



    // Vai buscar Dados á API  e insere nos arrays e BD
    public void getAllUtilizadoresAPI(final Context context, boolean isConnected){
        if(!isConnected){
            utilizadores = modeloBDHelper.getAllUtilizadoresBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiUtilizadores, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    utilizadores = DadosJsonParser.parseJsonUtilizador(response, context);
                    adicionarUtilizadoresBd(utilizadores);
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

    public void getAllLinhaComprasAPI(final Context context, boolean isConnected){
        if(!isConnected){
            linhaCompras = modeloBDHelper.getAllLinhaComprasBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiLinhaCompras, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    linhaCompras = DadosJsonParser.parseJsonLinhaCompra(response, context);
                    adicionarLinhaComprasBD(linhaCompras);
                    Toast.makeText(context, "LinhasComprasAPI", Toast.LENGTH_SHORT).show();
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

    public void getAllComentariosAPI(final Context context, boolean isConnected){
        if(!isConnected){
            comentarios = modeloBDHelper.getAllComentariosDB();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiComentarios, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    comentarios = DadosJsonParser.parseJsonComentarios(response, context);
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

    public void getAllFavoritosAlbumAPI(final Context context, boolean isConnected){
        if(!isConnected){
            favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiFavAlbuns, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    favAlbuns = DadosJsonParser.parseJsonFavAlbuns(response, context);
                    adicionarFavoritosAlbumBD(favAlbuns);

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

    public void getAllFavoritosArtistaAPI(final Context context, boolean isConnected){
        if(!isConnected){
            favArtistas = modeloBDHelper.getAllArtistasFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiFavArtistas, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    favArtistas = DadosJsonParser.parseJsonFavArtista(response, context);
                    adicionarFavoritosArtistaBD(favArtistas);
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

    public void getAllFavoritosGeneroAPI(final Context context, boolean isConnected){
        if(!isConnected){
            favGeneros = modeloBDHelper.getAllGenerosFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiFavGeneros, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    favGeneros = DadosJsonParser.parseJsonFavGenero(response, context);
                    adicionarFavoritosGeneroBD(favGeneros);
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

    public void getAllFavoritosMusicaAPI(final Context context, boolean isConnected){
        if(!isConnected){
            favMusicas = modeloBDHelper.getAllMusicasFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlApiFavMusicas, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    favMusicas = DadosJsonParser.parseJsonFavMusica(response, context);
                    adicionarFavoritosMusicaBD(favMusicas);
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


    // Enviar Dados para a API
    public void adicionarUtilizadorAPI(final Utilizador utilizador, final  Context context){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlApiUtilizadores, new Response.Listener<String>() {
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
                params.put("nomeUtilizador", utilizador.getNomeUtilizador());
                params.put("emailUtilizador", utilizador.getEmailUtilizador());
                params.put("passwordUtilizador", utilizador.getPasswordUtilizador());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarLinhaCompraAPI(final LinhaCompra linhaCompra, final  Context context){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlApiLinhaCompras, new Response.Listener<String>() {
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
                params.put("idCompra", "" + linhaCompra.getIdCompra());
                params.put("idUtilizador", "" + linhaCompra.getIdMusica());

                return params;
            }
        };
        volleyQueue.add(req);
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

    public void adicionarFavoritosAlbumAPI(final FavoritoAlbum favoritoAlbum, final  Context context){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlApiFavAlbuns, new Response.Listener<String>() {
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
                params.put("idUtilizador", "" + favoritoAlbum.getIdUtilizador());
                params.put("idAlbum", "" + favoritoAlbum.getIdAlbum());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarFavoritosArtistaAPI(final FavoritoArtista favoritoArtista, final  Context context){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlApiFavArtistas, new Response.Listener<String>() {
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
                params.put("idUtilizador", "" + favoritoArtista.getIdUtilizador());
                params.put("idArtista", "" + favoritoArtista.getIdArtista());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarFavoritosGeneroAPI(final FavoritoGenero favoritoGenero, final  Context context){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlApiFavGeneros, new Response.Listener<String>() {
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
                params.put("idUtilizador", "" + favoritoGenero.getIdUtilizador());
                params.put("idGenero", "" + favoritoGenero.getIdGenero());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarFavoritosMusicaAPI(final FavoritoMusica favoritoMusica, final  Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlApiFavMusicas, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("-->Resposta Add Post" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("token", "AMSI-TOKEN");
                params.put("idUtilizador", "" + favoritoMusica.getIdUtilizador());
                params.put("idMusica", "" + favoritoMusica.getIdMusica());

                return params;
            }
        };
        volleyQueue.add(req);
    }


}
