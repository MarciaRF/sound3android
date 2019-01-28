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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.ComprasActivity;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CarrinhoListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.ComprasRegistadasListener;

import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.CommentListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesAlbumListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesArtistaListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DetalhesGeneroListener;

import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.DownloadListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.FavoritosListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.ListenerPopUpMenu;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.MusicaFavoritosCarrinhoListenner;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.MusicasListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.PesquisaListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.DadosJsonParser;

public class SingletonGestorDados implements CommentListener, FavoritosListener,
        DetalhesGeneroListener, DetalhesArtistaListener, PesquisaListener, DetalhesAlbumListener,
        ComprasRegistadasListener, MusicasListener, MusicaFavoritosCarrinhoListenner, CarrinhoListener, DownloadListener, ListenerPopUpMenu

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


    private ComprasRegistadasListener comprasRegistadasListener;
    private DetalhesGeneroListener detalhesGeneroListener;
    private DetalhesArtistaListener detalhesArtistaListener;
    private PesquisaListener pesquisaListener;
    private DetalhesAlbumListener detalhesAlbumListener;
    private FavoritosListener favoritosListener;
    private CommentListener commentListener;
    private MusicasListener musicasListener;
    private MusicaFavoritosCarrinhoListenner musicaFavoritosCarrinhoListenner;
    private CarrinhoListener carrinhoListener;
    private DownloadListener downloadListener;
    private ListenerPopUpMenu listenerPopUpMenu;

    private Album objetoAlbum;
    private Genero objetoGenero;
    private Artista objetoArtista;

    private ModeloBDHelper modeloBDHelper = null;
    private static RequestQueue volleyQueue = null;
    private static SingletonGestorDados INSTANCE = null;

    private String mUrlApiCompras = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/compra/";
    private String mUrlAPIAlbum = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/album/";
    private String mUrlApiUtilizadores = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/utilizadores";
    private String mUrlApiLinhaCompras = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/linhaCompras";
    private String mUrlFavAlbumAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favalbum/";
    private String mUrlFavArtistasAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favartista/";
    private String mUrlFavGenerosAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favgenero/";
    private String mUrlFavMusicasAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/favmusica/";
    private String mUrlAPIGenero = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/genero/";
    private String mUrlAPIArtista = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/artista/";
    private String mUrlPesquisaAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/pesquisa/";
    private String mUrlCompraAPI = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/compra/";
    private String mUrlAPIComentarios = "http://" + SingletonGestorConteudo.IP + "/sound3application/frontend/web/api/comment/";


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


    // VAI BUSCAR TODOS OS FAVORITOS DO USER
    public void getAllFavoritosAlbumAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavAlbumAPI + "getallalbunsfavoritos?userId=" + userId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objAlbum = null;
                        JSONArray objArtista = null;
                        objAlbum = objeto.getJSONArray("albuns");
                        objArtista = objeto.getJSONArray("artistas");

                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);
                        artistas = ConteudoJsonParser.parseJsonArtista(objArtista, context);

                        if(favoritosListener != null){
                            favoritosListener.onRefreshAlbunsFavoritos(albuns, artistas);
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

    public void getAllFavoritosArtistaAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavMusicasAPI + "getallmusicasfavoritos?userId=" + userId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objMusica = null;
                        JSONArray objAlbum = null;
                        objMusica = objeto.getJSONArray("musicas");
                        objAlbum = objeto.getJSONArray("albuns");

                        musicas = ConteudoJsonParser.parseJsonMusica(objMusica, context);
                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);

                        if (favoritosListener != null){
                            favoritosListener.onRefreshMusicasFavoritos(musicas, albuns);
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


    // ADICIONA AOS FAVORITOS
    public void adicionarFavoritosAlbumAPI(final  Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            volleyQueue.add(req);
        }
    }

    public void adicionarFavoritosArtistaAPI(final  Context context, boolean isConnected,final long utilizadorId, final long artistaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
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

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarFavoritosMusicaAPI(final  Context context, boolean isConnected, final long utilizadorId, final long musicaId) {
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.POST, mUrlFavMusicasAPI + "adicionarmusicafavoritos", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("-->Resposta Add Post " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("id_utilizador", "" + utilizadorId);
                    params.put("id_musica", "" + musicaId);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }



    // LISTAGEM DE DADOS PARA A ATIVIDADE DOS FAVORITOS
    public void getFavoritosArtistaAtividadeAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavAlbumAPI + "getalbunsfavoritos?userId=" + userId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objAlbum = null;
                        JSONArray objArtista = null;
                        objAlbum = objeto.getJSONArray("albuns");
                        objArtista = objeto.getJSONArray("artistas");

                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);
                        artistas = ConteudoJsonParser.parseJsonArtista(objArtista, context);

                        if(favoritosListener != null){
                            favoritosListener.onRefreshAlbunsFavoritos(albuns, artistas);
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

    public void getFavoritosGeneroAtividadeAPI(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavMusicasAPI + "getmusicasfavoritos?userId=" + userId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objMusica = null;
                        JSONArray objAlbum = null;
                        objMusica = objeto.getJSONArray("musicas");
                        objAlbum = objeto.getJSONArray("albuns");

                        musicas = ConteudoJsonParser.parseJsonMusica(objMusica, context);
                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);

                        if (favoritosListener != null){
                            favoritosListener.onRefreshMusicasFavoritos(musicas, albuns);
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


    // APAGAR FAVORITOS
    public void apagarFavoritosAlbumAPI(final  Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            volleyQueue.add(req);
        }
    }

    public void apagarFavoritosArtistaAPI(final  Context context, boolean isConnected, final long utilizadorId, final long artistaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlFavArtistasAPI +
                    "apagarfavoritoartista?userId=" + utilizadorId + "&artistaId=" + artistaId,
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
            volleyQueue.add(req);
        }
    }

    public void apagarFavoritosGeneroAPI(final  Context context, boolean isConnected, final long utilizadorId, final long generoId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            volleyQueue.add(req);
        }
    }


    // Verifica se  esta nos Favoritos
    public void getGeneroFavoritoAPI(final Context context, boolean isConnected, final Long userId, final Long generoId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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

    public void getArtistaFavoritoAPI(final Context context, boolean isConnected, final Long userId, final Long artistaId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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

    public void getAlbumFavoritoAPI(final Context context, boolean isConnected, final Long userId, final Long albumId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavAlbumAPI+"findfavalbum?userId=" + userId + "&albumId=" + albumId,
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


    // QUE MUSICAS DO ALBUM ESTAO NOS FAVORITOS
    public void getAllMusicasFavoritosAlbumAPI(final Context context, boolean isConnected, final Long userId, final Long musicaId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlFavAlbumAPI + "findfavmusica?userId=" + userId + "&musicaId=" + musicaId,
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



    // DETALHES ESPECIFICOS DOS ITEMS
    public void getAlbumAPI(final Context context, boolean isConnected, final long idAlbum){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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




    // Codigo da Pesquisa
    public void getPesquisaAlbunsAPI(final Context context, boolean isConnected, final String pesquisa){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlPesquisaAPI + "pesquisaalbuns?pesquisa=" + pesquisa, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objAlbum = null;
                        JSONArray objArtista = null;
                        objAlbum = objeto.getJSONArray("albuns");
                        objArtista = objeto.getJSONArray("artistas");

                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);
                        artistas = ConteudoJsonParser.parseJsonArtista(objArtista, context);

                        if(pesquisaListener != null){
                            pesquisaListener.onRefreshAlbunsPesquisa(albuns, artistas);
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

    public void getPesquisaGenerosAPI(final Context context, boolean isConnected, final String pesquisa){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlPesquisaAPI + "pesquisamusicas?pesquisa=" + pesquisa, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objMusica = null;
                        JSONArray objAlbum = null;
                        objMusica = objeto.getJSONArray("musicas");
                        objAlbum = objeto.getJSONArray("albuns");

                        musicas = ConteudoJsonParser.parseJsonMusica(objMusica, context);
                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);

                        if(pesquisaListener != null){
                            pesquisaListener.onRefreshAMusicasPesquisa(musicas, albuns);
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

    // adicionar musica ao Carrinho
    public void adicionarMusicaCarrinhoAPI(final Context context, boolean isConnected, final long utilizadorId, final long musicaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.POST, mUrlCompraAPI + "adicionarmusicacarrinho",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(listenerPopUpMenu != null){
                                listenerPopUpMenu.checkMusicaInCarrinho(response);
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
                    params.put("id_musica", "" + musicaId);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    // Remover Album do Carrinho
    public void apagarMusicaCarrinhoAPI(final Context context, boolean isConnected, final long utilizadorId, final long musicaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlCompraAPI +
                    "remover?userId=" + utilizadorId + "&musicaId=" + musicaId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(listenerPopUpMenu != null){
                                listenerPopUpMenu.checkMusicaInCarrinho(response);
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
                    params.put("id_musica", "" +  musicaId);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    // Verificar se a Música está no Carrinho
    public void getMusicaCarrinhoAPI(final Context context, boolean isConnected, final Long userId, final Long musicaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET, mUrlCompraAPI+"checkmusicacarrinho?userId="+userId+"&musicaId="+musicaId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(listenerPopUpMenu != null){
                                listenerPopUpMenu.checkMusicaInCarrinho(response);
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

    //adicionar musica aos favoritos
    public void adicionarMusicaFavoritosAPI(final Context context, boolean isConnected, final long utilizadorId, final long musicaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.POST, mUrlFavMusicasAPI + "adicionarmusicafavoritos",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(listenerPopUpMenu != null){
                                listenerPopUpMenu.checkMusicaInFavoritos(response);
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
                    params.put("id_musica", "" + musicaId);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    // Remover Album do Carrinho
    public void apagarMusicaFavoritosAPI(final Context context, boolean isConnected, final long utilizadorId, final long musicaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlFavMusicasAPI +
                    "apagar-favorito-musica?userId=" + utilizadorId + "&musicaId=" + musicaId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(listenerPopUpMenu != null){
                                listenerPopUpMenu.checkMusicaInFavoritos(response);
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
                    params.put("id_musica", "" +  musicaId);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    //Adicionar Album ao Carrinho
    public void adicionarAlbumCarrinhoAPI(final Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlCompraAPI + "adicionaralbum?userId="+ utilizadorId+
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
            volleyQueue.add(req);
        }
    }

    // Remover Album do Carrinho
    public void apagarAlbumCarrinhoAPI(final Context context, boolean isConnected, final long utilizadorId, final long albumId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
            volleyQueue.add(req);
        }
    }

    // Verificar se o Album está no Carrinho
    public void getAlbumCarrinhoAPI(final Context context, boolean isConnected, final Long userId, final Long albumId){
        if(!isConnected){
            //favAlbuns = modeloBDHelper.getAllAlbunsFavoritosBD();
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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

    // Get Artista do Album
    public void getArtistaAlbumAPI(final Context context, boolean isConnected, final long idAlbum){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
                    System.out.println("-->Error: " + error);
                }
            });
            volleyQueue.add(req);
        }
    }

    // Vai Buscar todos os Albuns de Um Artista
    public void getAllAbunsArtistaAPI(final Context context, boolean isConnected, final long artistaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIAlbum + "albunsartista?artistaId=" + artistaId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objAlbum = null;
                        JSONArray objArtista = null;
                        objAlbum = objeto.getJSONArray("albuns");
                        objArtista = objeto.getJSONArray("artistas");

                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);
                        artistas = ConteudoJsonParser.parseJsonArtista(objArtista, context);

                        if(detalhesArtistaListener != null){
                            detalhesArtistaListener.onRefreshAbunsArtista(albuns, artistas);
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

    // Adiciona Comentario a um Album
    public void adicionarCommentAlbumAPI(final  Context context, boolean isConnected, final long idAlbum, final long idUser, final String comentario, final java.sql.Date data){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.POST,
                    mUrlAPIComentarios + "criarcomment?albumId=" + idAlbum +"&userId=" +idUser + "&comentario" + comentario
                    , new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(commentListener != null){
                        commentListener.onResfreshNovoComment(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<>();
                    params.put("conteudo", comentario);
                    params.put("data_criacao", "" + data);
                    params.put("id_utilizador", "" + idUser);
                    params.put("id_album", "" +idAlbum);

                    return params;
                }
            };
            volleyQueue.add(req);
        }

    }


    // Todos Comentarios do Album
    public void getAlbumCommentsAPI(final Context context, boolean isConnected, final long albumId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIComentarios + "getallcomments?albumId=" + albumId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objComment = null;
                        JSONArray objUser = null;
                        objComment = objeto.getJSONArray("comments");
                        objUser = objeto.getJSONArray("users");

                        comentarios = DadosJsonParser.parseJsonComentarios(objComment, context);
                        utilizadores = DadosJsonParser.parseJsonUtilizador(objUser, context);

                        if(commentListener != null){
                            commentListener.onResfreshComment(comentarios, utilizadores);
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

    // Vai Buscar todos os Albuns de um Genero
    public void getAllAlbunsDoGeneroAPI(final Context context, boolean isConnected, final long generoId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIGenero + "findalbunsgenero?generoId=" + generoId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objAlbum = null;
                        JSONArray objArtista = null;
                        objAlbum = objeto.getJSONArray("albuns");
                        objArtista = objeto.getJSONArray("artistas");

                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbum, context);
                        artistas = ConteudoJsonParser.parseJsonArtista(objArtista, context);

                        if(detalhesGeneroListener != null){
                            detalhesGeneroListener.onRefreshAlbunsGeneros(albuns, artistas);
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

    public void getComprasRegistadasAPI(final Context context, boolean isConnected, final long userId) {
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
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
                }
            });
            volleyQueue.add(req);
        }
    }

    // Ve que Musicas estão no carrinho
    public void getMusicasAlbumCarrinho(final Context context, boolean isConnected, final long userId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,  mUrlApiCompras + "getcarrinho?userId=" + userId,
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

                }
            });
            volleyQueue.add(req);
        }
    }

    //musicas de album favs
    public void checkMusicasAlbumNosFavoritosAPI(final Context context, boolean isConnected, final long userId, final long albumId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,   mUrlFavMusicasAPI + "checkmusicasalbumfavoritos?userId="
                    + userId + "&albumId=" + albumId,
                    null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    musicas = ConteudoJsonParser.parseJsonMusica(response, context);
                    if(musicaFavoritosCarrinhoListenner != null){
                        musicaFavoritosCarrinhoListenner.onMusicasNosFavoritos(musicas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            volleyQueue.add(req);
        }
    }

    public void apagarFavoritosMusicaAPI(final  Context context, boolean isConnected, final long utilizadorId, final long musicaId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlFavMusicasAPI +
                    "apagarfavoritomusica?userId=" + utilizadorId + "&musicaId=" + musicaId,
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
                    params.put("id_musica", "" +  musicaId);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    //get Items Carrinho
    public void getItemsCarrinhoAPI(final Context context, boolean isConnected, final long utilizadorId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlCompraAPI + "getcarrinho?userId=" + utilizadorId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objMusicas = null;
                        JSONArray objAlbuns = null;
                        objMusicas = objeto.getJSONArray("musicas");
                        objAlbuns = objeto.getJSONArray("albuns");

                        musicas = ConteudoJsonParser.parseJsonMusica(objMusicas, context);
                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbuns, context);

                        if(carrinhoListener != null){
                            carrinhoListener.onRefreshCarrinho(musicas, albuns);
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

    // Vais Buscar todoas as musicas compradas para fazer download
    public void getMusicasDownloadAPI(final Context context, boolean isConnected, final long utilizadorId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.GET, mUrlCompraAPI + "getmusicascompradas?userId=" + utilizadorId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONObject objeto = new JSONObject(response);
                        JSONArray objMusicas = null;
                        JSONArray objAlbuns = null;
                        objMusicas = objeto.getJSONArray("musicas");
                        objAlbuns = objeto.getJSONArray("albuns");

                        musicas = ConteudoJsonParser.parseJsonMusica(objMusicas, context);
                        albuns = ConteudoJsonParser.parseJsonAlbum(objAlbuns, context);

                        if(downloadListener != null){
                            downloadListener.onRefreshMusicasDownload(musicas, albuns);
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



    // Apagar Comentario
    public void apagarCommentAlbumAPI(final  Context context, boolean isConnected, final long commentId, final long albumId){
        if(!isConnected){
            Toast.makeText(context, "Verifique a ligação á Internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIComentarios +
                    "apagarcomment?commentId=" + commentId + "&albumId=" + albumId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject objeto = new JSONObject(response);
                                JSONArray objComment = null;
                                JSONArray objUser = null;
                                objComment = objeto.getJSONArray("comments");
                                objUser = objeto.getJSONArray("users");

                                comentarios = DadosJsonParser.parseJsonComentarios(objComment, context);
                                utilizadores = DadosJsonParser.parseJsonUtilizador(objUser, context);

                                if(commentListener != null){
                                    commentListener.onResfreshComment(comentarios, utilizadores);
                                }

                            } catch (JSONException e) {

                                e.printStackTrace();
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
                    params.put("id", "" + commentId);

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }


    
    public void setMusicaFavoritosCarrinhoListenner(MusicaFavoritosCarrinhoListenner musicaFavoritosCarrinhoListenner){
        this.musicaFavoritosCarrinhoListenner = musicaFavoritosCarrinhoListenner;
    }

    public void setMusicasListener(MusicasListener musicasListener){
        this.musicasListener = musicasListener;
    }

    public void setComprasRegistadasListener(ComprasRegistadasListener comprasRegistadasListener) {
        this.comprasRegistadasListener = comprasRegistadasListener;
    }


    public void setCarrinhoListener (CarrinhoListener carrinhoListener){
        this.carrinhoListener = carrinhoListener;
    }

    public void setFavoritosListener (FavoritosListener favoritosListener){
        this.favoritosListener = favoritosListener;
    }

    public void setCommentListener (CommentListener commentListener){
        this.commentListener = commentListener;
    }

    public void setDetalhesGeneroListener (DetalhesGeneroListener detalhesGeneroListener){
        this.detalhesGeneroListener = detalhesGeneroListener;
    }

    public void setDetalhesArtistaListener (DetalhesArtistaListener detalhesArtistaListener){
        this.detalhesArtistaListener = detalhesArtistaListener;
    }

    public void setPesquisaListener (PesquisaListener pesquisaListener){
        this.pesquisaListener = pesquisaListener;
    }

    public void setDetalhesAlbumListener (DetalhesAlbumListener detalhesAlbumListener){
        this.detalhesAlbumListener = detalhesAlbumListener;
    }

    public void setDownloadListener (DownloadListener downloadListener){
        this.downloadListener = downloadListener;
    }

    public void setListenerPopUpMenu (ListenerPopUpMenu listenerPopUpMenu){
        this.listenerPopUpMenu = listenerPopUpMenu;
    }


    @Override
    public void onResfreshComment(ArrayList<Comentario> listaComentarios, ArrayList<Utilizador> listaComentariosUser) {

    }

    @Override
    public void onResfreshNovoComment(String checkNovoComment) {

    }

    @Override
    public void onResponseGetCompras(ArrayList<Compra> compras) {

    }

    @Override
    public void onRefreshAlbum(Album album) {

    }

    @Override
    public void onRefreshArtistaAlbum(Artista artista) {

    }

    @Override
    public void checkAlbumInFavoritos(String check) {

    }

    @Override
    public void checkAlbumInCarrinho(String check) {

    }

    @Override
    public void onRefreshArtista(Artista artista) {

    }

    @Override
    public void onRefreshAbunsArtista(ArrayList<Album> albunsArtista, ArrayList<Artista> artistas) {

    }

    @Override
    public void checkArtistaInFavoritos(String check) {

    }

    @Override
    public void onRefreshGenero(Genero genero) {

    }

    @Override
    public void onRefreshAlbunsGeneros(ArrayList<Album> albunsGenero, ArrayList<Artista> artistas) {

    }

    @Override
    public void checkGeneroInFavoritos(String check) {

    }

    @Override
    public void onRefreshAlbunsFavoritos(ArrayList<Album> albuns, ArrayList<Artista> artistas) {

    }

    @Override
    public void onRefreshArtistasFavoritos(ArrayList<Artista> artistas) {

    }

    @Override
    public void onRefreshGenerosFavoritos(ArrayList<Genero> generos) {

    }

    @Override
    public void onRefreshMusicasFavoritos(ArrayList<Musica> musicas, ArrayList<Album> album) {

    }

    @Override
    public void onMusicasNosFavoritos(ArrayList<Musica> listaMusicasFavoritos) {

    }

    @Override
    public void onMusicasNosCarrinho(ArrayList<Musica> listaMusicasCarrinho) {

    }

    @Override
    public void onRefreshMusicas(ArrayList<Musica> listaMusicas, Album listaMusicasArtistas) {

    }

    @Override
    public void onRefreshAlbunsPesquisa(ArrayList<Album> pesquisaAlbuns, ArrayList<Artista> artistas) {

    }

    @Override
    public void onRefreshGenerosPesquisa(ArrayList<Genero> pesquisaGeneros) {

    }

    @Override
    public void onRefreshArtistasPesquisa(ArrayList<Artista> pesquisaArtistas) {

    }

    @Override
    public void onRefreshAMusicasPesquisa(ArrayList<Musica> pesquisaMusicas, ArrayList<Album> albuns) {

    }

    @Override
    public void onRefreshCarrinho(ArrayList<Musica> musicas, ArrayList<Album> album) {

    }
    @Override
    public void onRefreshMusicasFavoritos(ArrayList<Musica> favoritoMusicas) {

    }

    @Override
    public void onRefreshMusicasDownload(ArrayList<Musica> musicas, ArrayList<Album> albumMusicas) {

    }

    @Override
    public void checkMusicaInFavoritos(String check) {

    }

    @Override
    public void checkMusicaInCarrinho(String check) {

    }
}
