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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.ComprasActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.AlbumFavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.ComprasRegistadasListener;

import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesAlbumListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesArtistaListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesGeneroListener;

import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.FavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.PesquisaListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.DadosJsonParser;

public class SingletonGestorDados implements CommentListener, FavoritosListener,
        DetalhesGeneroListener, DetalhesArtistaListener, PesquisaListener, DetalhesAlbumListener
{

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


    private FavoritosListener favoritosListener;
    private CommentListener commentListener;

    private ComprasRegistadasListener comprasRegistadasListener;

    private DetalhesGeneroListener detalhesGeneroListener;
    private DetalhesArtistaListener detalhesArtistaListener;
    private PesquisaListener pesquisaListener;
    private DetalhesAlbumListener detalhesAlbumListener;

    private Album objetoAlbum;
    private Genero objetoGenero;
    private Artista objetoArtista;

    private ArrayList<Comentario> listaComments;

    private ModeloBDHelper modeloBDHelper = null;
    private static RequestQueue volleyQueue = null;
    private static SingletonGestorDados INSTANCE = null;


    private String mUrlApiCompras = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/compra/";

    private String mUrlAPIAlbum = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/album/";

    private String mUrlApiUtilizadores = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/utilizadores";
    private String mUrlApiLinhaCompras = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/linhaCompras";
    
    // Favoritos
    private String mUrlFavAlbumAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favalbum/";
    private String mUrlFavArtistasAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favartista/";
    private String mUrlFavGenerosAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favgenero/";
    private String mUrlFavMusicasAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favmusica/";

    private String mUrlAPIAlbum = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/album/";
    private String mUrlAPIGenero = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/genero/";
    private String mUrlAPIArtista = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/artista/";

    private String mUrlPesquisaAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/pesquisa/";

    private String mUrlCompraAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/compra/";

    private String mUrlComentarioAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/comment/";


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

    public void adicionarLin_haCompraAPI(final LinhaCompra linhaCompra, final  Context context){
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
                params.put("id_utilizador", ""+comentario.getId_Utilizador());
                params.put("id_album", ""+comentario.getId_Album());

                return params;
            }
        };
        volleyQueue.add(req);
    }



    //Adiconar Album aos Fav
    public void adicionarFavoritosAlbumAPI(final  Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){
        }else{
            StringRequest req = new StringRequest(Request.Method.POST, mUrlFavAlbumAPI + "criarfavoritoalbum",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesAlbumListener != null){
                                detalhesAlbumListener.checkAlbumInFavoritos(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Não Foi Possível Adicionar :", Toast.LENGTH_SHORT).show();
                    System.out.println("-->Error add: " + error);
                }
            }) {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String,String> params = new HashMap<>();
                    params.put("id_utilizador", "" + utilizadorId);
                    params.put("id_album", "" +  albumId);

                    return params;
                }
            };
            System.out.println("-->Error req: " + req);
            volleyQueue.add(req);
        }
    }

    public void adicionarFavoritosArtistaAPI(final  Context context, boolean isConnected,final long utilizadorId, final long artistaId){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlFavArtistasAPI + "criarfavoritoartista",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(detalhesArtistaListener != null){
                            detalhesArtistaListener.checkArtistaInFavoritos(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Não Foi Possível Adicionar", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<>();
                params.put("id_utilizador", "" + utilizadorId);
                params.put("id_artista", "" + artistaId);

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarFavoritosGeneroAPI(final  Context context, final long utilizadorId, final long generoId){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlFavGenerosAPI + "criarfavoritogenero",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(detalhesGeneroListener != null){
                            detalhesGeneroListener.checkGeneroInFavoritos(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Não Foi Possível Adicionar", Toast.LENGTH_SHORT).show();
                System.out.println("-->Error add: " + error);
            }
        }) {
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<>();
                params.put("id_utilizador", "" + utilizadorId);
                params.put("id_genero", "" + generoId);

                System.out.println("-->Error params: " + params);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarFavoritosMusicaAPI(final  Context context, final long utilizadorId, final long musicaId) {
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
                params.put("idUtilizador", "" + utilizadorId);
                params.put("idMusica", "" + musicaId);

                return params;
            }
        };
        volleyQueue.add(req);
    }


    public void apagarFavoritosAlbumAPI(final  Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlFavAlbumAPI +
                    "apagarfavalbum?userId=" + utilizadorId + "&albumId=" + albumId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesAlbumListener != null){
                                detalhesAlbumListener.checkAlbumInFavoritos(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Não Foi Possível Remover :", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String,String> params = new HashMap<>();
                    params.put("id_utilizador", "" + utilizadorId);
                    params.put("id_album", "" +  albumId);

                    return params;
                }
            };
            System.out.println("-->Error req: " + req);
            volleyQueue.add(req);
        }
    }

    public void apagarFavoritosArtistaAPI(final  Context context, boolean isConnected, final long utilizadorId, final long artistaId){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlFavArtistasAPI +
                    "apagarfavartista?userId=" + utilizadorId + "&artistaId=" + artistaId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesArtistaListener != null){
                                detalhesArtistaListener.checkArtistaInFavoritos(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Não Foi Possível Remover :", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String,String> params = new HashMap<>();
                    params.put("id_utilizador", "" + utilizadorId);
                    params.put("id_artista", "" +  artistaId);

                    return params;
                }
            };
            System.out.println("-->Error req: " + req);
            volleyQueue.add(req);
        }
    }

    public void apagarFavoritosGeneroAPI(final  Context context, boolean isConnected, final long utilizadorId, final long generoId){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlFavGenerosAPI +
                    "apagarfavgenero?userId=" + utilizadorId + "&generoId=" + generoId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesGeneroListener != null){
                                detalhesGeneroListener.checkGeneroInFavoritos(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Não Foi Possível Remover :", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String,String> params = new HashMap<>();
                    params.put("id_utilizador", "" + utilizadorId);
                    params.put("id_genero", "" +  generoId);

                    return params;
                }
            };
            System.out.println("-->Error req: " + req);
            volleyQueue.add(req);
        }
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



    // Vai Buscar Dados Para os Detalhes
    public void getAlbumAPI(final Context context, boolean isConnected, final long idAlbum){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIAlbum + "findalbumbyid?id=" + idAlbum, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject obj = null;

                    try {
                        obj = new JSONObject(response);
                        JSONObject userJson = obj.getJSONObject("album");

                        ArrayList<String> tempAlbum = new ArrayList<>();

                        tempAlbum.add("" + userJson.getLong("id"));
                        tempAlbum.add(userJson.getString("nome"));
                        tempAlbum.add("" + userJson.getInt("ano"));
                        tempAlbum.add("" + userJson.getInt("preco"));
                        tempAlbum.add("" + userJson.getLong("id_artista"));
                        tempAlbum.add("" + userJson.getLong("id_genero"));
                        tempAlbum.add(userJson.getString("caminhoImagem"));

                        objetoAlbum = ConteudoJsonParser.parseJsonObejectAlbum(tempAlbum, context);

                        if (detalhesAlbumListener != null) {
                            detalhesAlbumListener.onRefreshAlbum(objetoAlbum);
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

    public void getGeneroAPI(final Context context, boolean isConnected, final long idGenero){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIGenero + "findgenerobyid?generoId=" + idGenero, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject obj = null;

                    try {
                        obj = new JSONObject(response);
                        JSONObject userJson = obj.getJSONObject("genero");

                        ArrayList<String> tempGenero = new ArrayList<>();

                        tempGenero.add("" + userJson.getLong("id"));
                        tempGenero.add(userJson.getString("nome"));
                        tempGenero.add(userJson.getString("descricao"));
                        tempGenero.add(userJson.getString("caminhoImagem"));

                        objetoGenero = ConteudoJsonParser.parseJsonObejectGenero(tempGenero, context);

                        if (detalhesGeneroListener != null) {
                            detalhesGeneroListener.onRefreshGenero(objetoGenero);
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

    public void getArtistaAPI(final Context context, boolean isConnected, final long idArtista){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIArtista + "findartistabyid?id=" + idArtista, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject obj = null;

                    try {
                        obj = new JSONObject(response);
                        JSONObject artistaJson = obj.getJSONObject("artista");

                        ArrayList<String> tempArtista = new ArrayList<>();

                        tempArtista.add("" + artistaJson.getLong("id"));
                        tempArtista.add(artistaJson.getString("nome"));
                        tempArtista.add(artistaJson.getString("nacionalidade"));
                        tempArtista.add("" + artistaJson.getInt("ano"));
                        tempArtista.add(artistaJson.getString("caminhoImagem"));

                        objetoArtista = ConteudoJsonParser.parseJsonObejectArtista(tempArtista, context);

                        if (detalhesArtistaListener != null) {
                            detalhesArtistaListener.onRefreshArtista(objetoArtista);
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

    // Vai Buscar Artista que Criou Album
    public void getArtistaAlbumAPI(final Context context, boolean isConnected, final long idAlbum){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIAlbum + "artistaalbum?albumId=" + idAlbum, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject obj = null;


                    try {
                        obj = new JSONObject(response);
                        JSONObject artistaJson = obj.getJSONObject("artista");

                        ArrayList<String> tempArtista = new ArrayList<>();

                        tempArtista.add("" + artistaJson.getLong("id"));
                        tempArtista.add(artistaJson.getString("nome"));
                        tempArtista.add(artistaJson.getString("nacionalidade"));
                        tempArtista.add("" + artistaJson.getInt("ano"));
                        tempArtista.add(artistaJson.getString("caminhoImagem"));


                        objetoArtista = ConteudoJsonParser.parseJsonObejectArtista(tempArtista, context);

                        if (detalhesAlbumListener != null) {
                            detalhesAlbumListener.onRefreshArtistaAlbum(objetoArtista);
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
    
    public void setComprasRegistadasListener(ComprasRegistadasListener comprasRegistadasListener){
        this.comprasRegistadasListener = comprasRegistadasListener;
    }

    // Remover Album do Carrinho
    public void apagarAlbumCarrinhoAPI(final  Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){

        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlCompraAPI +
                    "removealbumcarrinho?userId=" + utilizadorId + "&albumId=" + albumId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesAlbumListener != null){
                                detalhesAlbumListener.checkAlbumInCarrinho(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Não Foi Possível Remover :", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String,String> params = new HashMap<>();
                    params.put("id_utilizador", "" + utilizadorId);
                    params.put("id_album", "" +  albumId);

                    return params;
                }
            };
            System.out.println("-->Error req: " + req);
            volleyQueue.add(req);
        }
    }




    // Vai Buscar todos os Albuns de Um Artista
    public void getAllAbunsArtistaAPI(final Context context, boolean isConnected, final long artistaId){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlAPIAlbum + "albunsartista?artistaId=" + artistaId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("-->Albuns : " + response + artistaId);
                    albuns = ConteudoJsonParser.parseJsonAlbum(response, context);
                    if(detalhesArtistaListener != null){
                        detalhesArtistaListener.onRefreshAbunsArtista(albuns);
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
                            if(detalhesAlbumListener != null){
                                //Toast.makeText(context, "check:"+response, Toast.LENGTH_SHORT).show();
                                detalhesAlbumListener.checkAlbumInFavoritos(response);
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

    // Vai Buscar todos os Albuns de um Genero
    public void getAllAlbunsDoGeneroAPI(final Context context, boolean isConnected, final long generoId){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlAPIGenero + "findalbunsgenero?generoId=" + generoId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    albuns = ConteudoJsonParser.parseJsonAlbum(response, context);
                    if(detalhesGeneroListener != null){
                        detalhesGeneroListener.onRefreshAlbunsGeneros(albuns);
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



    // Verifica se o Generos esta nos Favoritos
    public void getGeneroFavoritoAPI(final Context context, boolean isConnected, final Long userId, final Long generoId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavGenerosAPI+"findfavgenero?userId="+userId+"&generoId="+generoId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesGeneroListener != null){
                                detalhesGeneroListener.checkGeneroInFavoritos(response);
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

    // Verifica se o Artista esta nos Favoritos
    public void getArtistaFavoritoAPI(final Context context, boolean isConnected, final Long userId, final Long artistaId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavArtistasAPI+"findfavartista?userId="+userId+"&artistaId="+artistaId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesArtistaListener != null){
                                detalhesArtistaListener.checkArtistaInFavoritos(response);
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


    // Codigo da Pesquisa
    public void getPesquisaAlbunsAPI(final Context context, boolean isConnected, final String pesquisa){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlPesquisaAPI + "pesquisaalbuns?pesquisa=" + pesquisa,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    albuns = ConteudoJsonParser.parseJsonAlbum(response, context);
                    if(pesquisaListener != null){
                        pesquisaListener.onRefreshAlbunsPesquisa(albuns);
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

    public void getPesquisaGenerosAPI(final Context context, boolean isConnected, final String pesquisa){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlPesquisaAPI + "pesquisageneros?pesquisa=" + pesquisa,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    generos = ConteudoJsonParser.parseJsonGenero(response, context);
                    if(pesquisaListener != null){
                        pesquisaListener.onRefreshGenerosPesquisa(generos);
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

    public void getPesquisaArtistasAPI(final Context context, boolean isConnected, final String pesquisa){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlPesquisaAPI + "pesquisaartistas?pesquisa=" + pesquisa,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    artistas = ConteudoJsonParser.parseJsonArtista(response, context);
                    if(pesquisaListener != null){
                        pesquisaListener.onRefreshArtistasPesquisa(artistas);
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

    public void getPesquisaMusicasAPI(final Context context, boolean isConnected, final String pesquisa){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlPesquisaAPI + "pesquisamusicas?pesquisa=" + pesquisa,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    musicas = ConteudoJsonParser.parseJsonMusica(response, context);
                    if(pesquisaListener != null){
                        pesquisaListener.onRefreshAMusicasPesquisa(musicas);
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




    public void getAlbumCommentsAPI(final Context context, boolean isConnected, final long albumId){
        if(!isConnected){

        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlComentarioAPI + "getallcomments?albumId=" + albumId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("-->Comment: " +  response);
                    comentarios = DadosJsonParser.parseJsonComentarios(response, context);
                    if(commentListener != null){
                        commentListener.onResfreshComment(comentarios);
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



    //Adiconar Album aos Fav
    public void adicionarAlbumCarrinhoAPI(final  Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){
        }else{
            StringRequest req = new StringRequest(Request.Method.POST, mUrlCompraAPI + "adicionaralbum?userId="+ utilizadorId+
                    "&albumId=" +albumId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesAlbumListener != null){
                                detalhesAlbumListener.checkAlbumInCarrinho(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Não Foi Possível Adicionar :", Toast.LENGTH_SHORT).show();
                    System.out.println("-->Error add: " + error);
                }
            }) {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String,String> params = new HashMap<>();
                    params.put("id_utilizador", "" + utilizadorId);
                    params.put("id_album", "" +  albumId);

                    return params;
                }
            };
            System.out.println("-->Error req: " + req);
            volleyQueue.add(req);
        }
    }

    // Verificar se o Album está nos Favoritos
    public void getAlbumCarrinhoAPI(final Context context, boolean isConnected, final Long userId, final Long albumId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlCompraAPI+"checkalbumcarrinho?userId="+userId+"&albumId="+albumId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(detalhesAlbumListener != null){
                                detalhesAlbumListener.checkAlbumInCarrinho(response);
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






    public void setFavoritosListener(FavoritosListener favoritosListener){
        this.favoritosListener = favoritosListener;
    }

    public void setCommentListener(CommentListener commentListener){
        this.commentListener = commentListener;
    }

    public void setDetalhesGeneroListener(DetalhesGeneroListener detalhesGeneroListener){
        this.detalhesGeneroListener = detalhesGeneroListener;
    }

    public void setDetalhesArtistaListener(DetalhesArtistaListener detalhesArtistaListener){
        this.detalhesArtistaListener = detalhesArtistaListener;
    }

    public void setPesquisaListener(PesquisaListener pesquisaListener){
        this.pesquisaListener = pesquisaListener;
    }

    public void setDetalhesAlbumListener(DetalhesAlbumListener detalhesAlbumListener){
        this.detalhesAlbumListener = detalhesAlbumListener;
    }




    @Override
    public void onResfreshComment(ArrayList<Comentario> listaComentarios) {

    }

    @Override
    public void checkAlbumInFavoritos(String check) {

    }

    @Override
    public void checkAlbumInCarrinho(String check) {

    }

    @Override
    public void onRefreshAlbum(Album album) {

    }

    @Override
    public void onRefreshArtistaAlbum(Artista artista) {

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

    @Override
    public void onRefreshGenero(Genero genero) {

    }

    @Override
    public void onRefreshAlbunsGeneros(ArrayList<Album> albunsGenero) {

    }

    @Override
    public void checkGeneroInFavoritos(String check) {

    }

    @Override
    public void checkArtistaInFavoritos(String check) {

    }

    @Override
    public void onRefreshArtista(Artista artista) {

    }

    @Override
    public void onRefreshAbunsArtista(ArrayList<Album> albunsArtista) {

    }


    @Override
    public void onRefreshAlbunsPesquisa(ArrayList<Album> pesquisaAlbuns) {

    }

    @Override
    public void onRefreshGenerosPesquisa(ArrayList<Genero> pesquisaGeneros) {

    }

    @Override
    public void onRefreshArtistasPesquisa(ArrayList<Artista> pesquisaArtistas) {

    }

    @Override
    public void onRefreshAMusicasPesquisa(ArrayList<Musica> pesquisaMusicas) {

    }
}
