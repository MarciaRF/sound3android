package models;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.ComprasActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.AlbumFavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.ComprasRegistadasListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.FavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.DadosJsonParser;

public class SingletonGestorDados implements CommentListener, AlbumFavoritosListener, FavoritosListener {

    private ArrayList<Utilizador> utilizadores;
    private ArrayList<LinhaCompra> linhaCompras;
    private ArrayList<Comentario> comentarios;
    private ArrayList<FavoritoAlbum> favAlbuns;
    private ArrayList<FavoritoArtista> favArtistas;
    private ArrayList<FavoritoGenero> favGeneros;
    private ArrayList<FavoritoMusica> favMusicas;

    private ArrayList<Artista> artistas;
    private ArrayList<Genero> generos;
    private ArrayList<Musica> musicas;
    private ArrayList<Album> albuns;
    private ArrayList<Compra> compras;

    private AlbumFavoritosListener albumFavoritosListener;
    private FavoritosListener favoritosListener;
    private CommentListener commentListener;
    private ComprasRegistadasListener comprasRegistadasListener;

    private Album objetoAlbum;

    private ArrayList<Comentario> listaComments;

    private ModeloBDHelper modeloBDHelper = null;
    private static RequestQueue volleyQueue = null;
    private static SingletonGestorDados INSTANCE = null;

    private String mUrlApiUtilizadores = "http://192.168.1.146/sound3application/frontend/web/api/utilizadores";
    private String mUrlApiLinhaCompras = "http://192.168.1.146/sound3application/frontend/web/api/linhaCompras";
    private String mUrlApiCompras = "http://192.168.1.146/sound3application/frontend/web/api/compra/";
    private String mUrlAPIComentarios = "http://192.168.1.146/sound3application/frontend/web/api/comment/";

    // Favoritos
    private String mUrlFavAlbumAPI = "http://192.168.1.146/sound3application/frontend/web/api/favalbum/";
    private String mUrlFavArtistasAPI = "http://192.168.1.146/sound3application/frontend/web/api/favartista/";
    private String mUrlFavGenerosAPI = "http://192.168.1.146/sound3application/frontend/web/api/favgenero/";
    private String mUrlFavMusicasAPI = "http://192.168.1.146/sound3application/frontend/web/api/favmusica/";

    private String mUrlAPIAlbum = "http://192.168.1.146/sound3application/frontend/web/api/album/";


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

    /*public FavoritoAlbum getFavoritoAlbum(long idFavAlbum){
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
    }*/


    // Vai Buscar o Comentarios de Um Album pelo ID
    public void getComentariosAlbumAPI(final Context context, boolean isConnected, long id){
        if(!isConnected){

        }else{
            final JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIComentarios + id, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    listaComments = DadosJsonParser.parseJsonComentarios(response, context);

                    if(listaComments != null){
                        commentListener.onResfreshComment(listaComments);
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
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIComentarios, null, new Response.Listener<JSONArray>() {
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
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPIComentarios, new Response.Listener<String>() {
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



    //Adiconar Album aos Fav
    public void adicionarFavoritosAlbumAPI(final  Context context, final long utilizadorId, final long albumId){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlFavAlbumAPI + "criarfavoritoalbum",
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("-->Resposta Add Post : " + response);
                /*if(albumFavoritosListener != null){
                    albumFavoritosListener.onUpdateFavoritosAlbumBD(DadosJsonParser.parseJsonFavAlbum(response, context));
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Não Foi Possível Add", Toast.LENGTH_SHORT).show();
                System.out.println("-->Error add: " + error);
            }
        }) {
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<>();
                params.put("id_album", "" + utilizadorId);
                params.put("id_utilizador", "" + albumId);

                System.out.println("-->Error params: " + params);
                return params;
            }

        };

        System.out.println("-->Error req: " + req);
        volleyQueue.add(req);
    }

    public void adicionarFavoritosArtistaAPI(final FavoritoArtista favoritoArtista, final  Context context){
        StringRequest req = new StringRequest(Request.Method.POST,  mUrlFavArtistasAPI, new Response.Listener<String>() {
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
        StringRequest req = new StringRequest(Request.Method.POST, mUrlFavGenerosAPI, new Response.Listener<String>() {
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
        StringRequest req = new StringRequest(Request.Method.POST, mUrlFavMusicasAPI, new Response.Listener<String>() {
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




    // VAI BUSCAR TODOS OS FAVORITOS DO USER
    public void getAllFavoritosAlbumAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            //favArtistas = modeloBDHelper.getAllArtistasFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlFavAlbumAPI + "getallalbunsfavoritos?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    albuns = ConteudoJsonParser.parseJsonAlbum(response, context);
                    if(favoritosListener != null){
                        favoritosListener.onRefreshAlbunsFavoritos(albuns);
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

    public void getAllFavoritosArtistaAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            //favArtistas = modeloBDHelper.getAllArtistasFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlFavArtistasAPI + "getallartistasfavoritos?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    artistas = ConteudoJsonParser.parseJsonArtista(response, context);
                    //adicionarFavoritosArtistaBD(favArtistas);
                    if (favoritosListener != null){
                        favoritosListener.onRefreshArtistasFavoritos(artistas);
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

    public void getAllFavoritosGeneroAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            //favGeneros = modeloBDHelper.getAllGenerosFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlFavGenerosAPI + "getallgenerosfavoritos?userId="+ userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    generos = ConteudoJsonParser.parseJsonGenero(response, context);
                    //adicionarFavoritosGeneroBD(favGeneros);
                    if (favoritosListener != null){
                        favoritosListener.onRefreshGenerosFavoritos(generos);
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

    public void getAllFavoritosMusicaAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            //favMusicas = modeloBDHelper.getAllMusicasFavoritosBD();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlFavMusicasAPI + "getallmusicasfavoritos?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    musicas = ConteudoJsonParser.parseJsonMusica(response, context);
                    //adicionarFavoritosMusicaBD(favMusicas);
                    if (favoritosListener != null){
                        favoritosListener.onRefreshMusicasFavoritos(musicas);
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



    // Dados Para a Atividade dos Favoritos
    public void getFavoritosArtistaAtividadeAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlFavArtistasAPI + "getartistasfavoritos?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    artistas = ConteudoJsonParser.parseJsonArtista(response, context);

                    if(favoritosListener != null){
                        favoritosListener.onRefreshArtistasFavoritos(artistas);
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

    public void getFavoritosAlbumAtividadeAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlFavAlbumAPI + "getalbunsfavoritos?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    albuns = ConteudoJsonParser.parseJsonAlbum(response, context);

                    if(favoritosListener != null){
                        favoritosListener.onRefreshAlbunsFavoritos(albuns);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error 1: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }

    public void getFavoritosGeneroAtividadeAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlFavGenerosAPI + "getgenerosfavoritos?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    generos = ConteudoJsonParser.parseJsonGenero(response, context);

                    if(favoritosListener != null){
                        favoritosListener.onRefreshGenerosFavoritos(generos);
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

    public void getFavoritosMusicaAtividadeAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlFavMusicasAPI + "getmusicasfavoritos?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    musicas = ConteudoJsonParser.parseJsonMusica(response, context);
                    if(favoritosListener != null){
                        favoritosListener.onRefreshMusicasFavoritos(musicas);
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



    // Vai Buscar Dados do Album
    public void getAlbumAPI(final Context context, boolean isConnected, final long idAlbum){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIAlbum + "findalbumbyid?id=" + idAlbum, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject obj = null;
                    String artista = null;

                    try {
                        obj = new JSONObject(response);
                        JSONObject userJson = obj.getJSONObject("album");
                        String artistaJson =("artista");

                        ArrayList<String> tempAlbum = new ArrayList<>();

                        tempAlbum.add("" + userJson.getLong("id"));
                        tempAlbum.add(userJson.getString("nome"));
                        tempAlbum.add("" + userJson.getInt("ano"));
                        tempAlbum.add("" + userJson.getInt("preco"));
                        tempAlbum.add("" + userJson.getLong("id_artista"));
                        tempAlbum.add("" + userJson.getLong("id_genero"));
                        tempAlbum.add(userJson.getString("caminhoImagem"));
                        //tempAlbum.add(artistaJson.getString("artista"));

                        objetoAlbum = ConteudoJsonParser.parseJsonObejectAlbum(tempAlbum, context);

                        if (albumFavoritosListener != null) {
                            albumFavoritosListener.onRefreshAlbum(objetoAlbum);
                        }
                    }catch (Exception ex ){

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error : " + error);
                }
            });
            volleyQueue.add(req);
        }
    }


    // Ve que Musicas do Album Fav estão nos Fav
    public void getAllMusicasFavoritosAlbumAPI(final Context context, boolean isConnected, final Long userId, final Long musicaId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavAlbumAPI + "findfavmusica?userId="+userId+"&musicaId="+musicaId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //favAlbuns = DadosJsonParser.parseJsonFavAlbuns(response, context);
                            //adicionarFavoritosAlbumBD(favAlbuns);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error : " + error);
                }
            });
            volleyQueue.add(req);
        }
    }

    // Verificar se o Album está nos Favoritos
    public void getAlbumFavoritoAPI(final Context context, boolean isConnected, final Long userId, final Long albumId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavAlbumAPI+"findfavalbum?userId="+userId+"&albumId="+albumId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(albumFavoritosListener != null){
                                albumFavoritosListener.checkAlbumInFavoritos(response);
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

    public void getComprasRegistadasAPI(final Context context, boolean isConnected, final long userId) {
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlApiCompras + "getcomprasregistadas?userId=" + userId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    compras = ConteudoJsonParser.parseJsonCompra(response, context);
                    if(comprasRegistadasListener != null){
                        comprasRegistadasListener.onResponseGetCompras(compras);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Error: " + error);
                    System.out.println("----->FALHOU");
                }
            });
            volleyQueue.add(req);
        }
    }


    public void setAlbumFavoritosListener(AlbumFavoritosListener albumFavoritosListener){
        this.albumFavoritosListener = albumFavoritosListener;
    }

    public void setFavoritosListener(FavoritosListener favoritosListener){
        this.favoritosListener = favoritosListener;
    }

    public void setCommentListener(CommentListener commentListener){
        this.commentListener = commentListener;
    }

    public void setComprasRegistadasListener(ComprasRegistadasListener comprasRegistadasListener){
        this.comprasRegistadasListener = comprasRegistadasListener;
    }

    @Override
    public void onResfreshComment(ArrayList<Comentario> listaComentarios) {

    }

    @Override
    public void onRefreshFavoritosAlbum(ArrayList<FavoritoAlbum> favoritoAlbums) {

    }

    @Override
    public void checkAlbumInFavoritos(String check) {

    }

    @Override
    public void onRefreshAlbum(Album album) {

    }

    @Override
    public void onUpdateFavoritosAlbumBD(FavoritoAlbum favoritoAlbum) {

    }

    @Override
    public void onRefreshAlbunsFavoritos(ArrayList<Album> albuns) {

    }

    @Override
    public void onRefreshArtistasFavoritos(ArrayList<Artista> artistas) {

    }

    @Override
    public void onRefreshGenerosFavoritos(ArrayList<Genero> generos) {

    }

    @Override
    public void onRefreshMusicasFavoritos(ArrayList<Musica> musicas) {

    }


}
