package models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ModeloBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sound3";
    private static final int DB_VERSION = 1;

    //VARIAVEIS DAS TABLES:
    //GENERO
    private static final String TABLE_N_GENERO = "genero";
    public static final String ID_GENERO = "idGenero";
    public static final String NOME_GENERO = "nomeGenero";
    public static final String DESCRICAO_GENERO = "descricaoGenero";
    public static final String FOTO_GENERO = "caminhoImagem";

    //ARTISTA
    private static final String TABLE_N_ARTISTA = "artista";
    public static final String ID_ARTISTA = "idArtista";
    public static final String NOME_ARTISTA = "nomeArtista";
    public static final String NACIONALIDADE_ARTISTA = "nacionalidadeArtista";
    public static final String D_INI_CARREIRA_ARTISTA = "data_ini_carreira";
    public static final String FOTO_ARTISTA = "caminhoImagem";

    //ALBUM
    private static final String TABLE_N_ALBUM = "album";
    public static final String ID_ALBUM = "idAlbum";
    public static final String NOME_ALBUM = "nomeAlbum";
    public static final String D_LANCAMENTO_ALBUM = "data_lancamento";
    public static final String FOTO_ALBUM = "caminhoImagem";
    public static final String ID_AUTOR_ALBUM = "id_artista";
    public static final String ID_GENERO_DO_ALBUM = "id_generoAlbum";

    //MUSICA
    private static final String TABLE_N_MUSICA = "musica";
    public static final String ID_MUSICA = "idMusica";
    public static final String NOME_MUSICA = "nomeMusica";
    public static final String DURACAO_MUSICA = "duracaoMusica";
    public static final String PRECO_MUSICA = "preco";
    public static final String ID_ALBUM_DA_MUSICA = "id_albumMusica";

    //COMMENT
    private static final String TABLE_N_COMMENT ="comment";
    public static final String ID_COMMENT = "idComment";
    public static final String CONTEUDO_COMMENT = "conteudoComment";
    public static final String DATA_CRIACAO_COMMENT = "dataCriacaoComment";
    public static final String ID_UTILIZADOR_COMMENT = "id_utilizadorComment";
    public static final String ID_ALBUM_COMMENT = "id_albumComment";

    //COMPRA
    private static final String TABLE_N_COMPRA ="compra";
    public static final String ID_COMPRA = "idCompra";
    public static final String DATA_COMPRA = "dataCompra";
    public static final String VALOR_TOTAL_COMPRA = "valorTotalCompra";
    public static final String EFETIVADA_COMPRA = "efetivadaCompra";
    public static final String ID_UTILIZADOR_COMPRA = "id_utilizadorCompra";

    //FAV_ALBUM
    private static final String TABLE_N_FAV_ALBUM ="fav_album";
    public static final String ID_UTILIZADOR_FAV_ALBUM = "idUtilizador";
    public static final String ID_ALBUM_FAV = "idAlbumFav";

    //FAV_ARTISTA
    private static final String TABLE_N_FAV_ARTISTA ="fav_artista";
    public static final String ID_UTILIZADOR_FAV_ARTISTA = "idUtilizador";
    public static final String ID_ARTISTA_FAV = "idArtistaFav";

    //FAV_GENERO
    private static final String TABLE_N_FAV_GENERO="fav_genero";
    public static final String ID_UTILIZADOR_FAV_GENERO= "idUtilizador";
    public static final String ID_GENERO_FAV = "idGeneroFav";

    //FAV_Musica
    private static final String TABLE_N_FAV_MUSICA ="fav_musica";
    public static final String ID_UTILIZADOR_FAV_MUSICA = "idUtilizador";
    public static final String ID_MUSICA_FAV = "idMusicaFav";

    //LINHA COMPRA
    private static final String TABLE_N_LINHA_COMPRA ="linhaCompra";
    public static final String ID_COMPRA_LINHA ="id_compra";
    public static final String ID_MUSICA_LINHA= "id_musica";

    //UTILIZADOR
    private static final String TABLE_N_UTILIZADOR = "utilizador";
    public static final String ID_UTILIZADOR ="id_utilizador";
    public static final String NOME_UTILIZADOR ="nome_utilizador";
    public static final String PASSWORD_UTILIZADOR ="password_utilizador";
    public static final String EMAIL_UTILIZADOR ="email_utilizador";


    private final SQLiteDatabase database;
    //alterar para apenas receber o contexto, e o factory fica a null
    public ModeloBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createGeneroTable = "CREATE TABLE " + TABLE_N_GENERO +
                "( " + ID_GENERO + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                NOME_GENERO + " TEXT NOT NULL" +
                DESCRICAO_GENERO + "TEXT NOT NULL"+
                FOTO_GENERO + " INTEGER" +");";


        String createArtistaTable = "CREATE TABLE" + TABLE_N_ARTISTA +
                "(" + ID_ARTISTA + " INTEGER PRIMARY KEY AUTOINCREMENT "+
                NOME_ARTISTA + "TEXT NOT NULL" +
                NACIONALIDADE_ARTISTA + "TEXT NOT NULL" +
                D_INI_CARREIRA_ARTISTA + "DATE" +
                FOTO_ARTISTA + "INTEGER" + ");";


        String createAlbumTable = "CREATE TABLE" + TABLE_N_ALBUM +
                "(" + ID_ALBUM + "INTEGER PRIMARY KEY AUTOINCREMENT"+
                NOME_ALBUM + "TEXT NOT NULL" +
                D_LANCAMENTO_ALBUM + "DATE"+
                FOTO_ALBUM + "INTEGER"+
                ID_AUTOR_ALBUM + "INTEGER"+
                ID_GENERO_DO_ALBUM + "INTEGER"+
                "FOREIGN KEY ("+ ID_AUTOR_ALBUM + ") REFERENCES "+ TABLE_N_ARTISTA+"("+ ID_ARTISTA+"));" +
                "FOREIGN KEY ("+ ID_GENERO_DO_ALBUM + ") REFERENCES "+ TABLE_N_GENERO+"("+ ID_GENERO+"));";


        String createMusicaTable = "CREATE TABLE" + TABLE_N_MUSICA +
                "(" + ID_MUSICA + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                NOME_MUSICA + "TEXT NOT NULL" +
                DURACAO_MUSICA + "TEXT NOT NULL"+
                PRECO_MUSICA + "REAL" +
                ID_ALBUM_DA_MUSICA + "INTEGER" +
                "FOREIGN KEY ("+  ID_ALBUM_DA_MUSICA + ") REFERENCES "+ TABLE_N_ALBUM+"("+ ID_ALBUM+"));";


        String createUtilizadorTable ="CREATE TABLE" + TABLE_N_UTILIZADOR+
                "(" + ID_UTILIZADOR + "INTEGER RIMARY KEY AUTOINCREMENT"+
                NOME_UTILIZADOR + "TEXT NOT NULL"+
                PASSWORD_UTILIZADOR + "TEXT NOT NULL"+
                EMAIL_UTILIZADOR + "TEXT NOT NULL" + ");";


        String createCommentTable = "CREATE TABLE" + TABLE_N_COMMENT +
                "(" + ID_COMMENT+ "INTEGER PRIMARY KEY AUTOINCREMENT" +
                CONTEUDO_COMMENT + "TEXT NOT NULL" +
                DATA_CRIACAO_COMMENT + "DATE"+
                ID_UTILIZADOR_COMMENT + "INTEGER" +
                ID_ALBUM_COMMENT + "INTEGER" +
                "FOREIGN KEY ("+  ID_ALBUM_COMMENT + ") REFERENCES "+ TABLE_N_ALBUM+"("+ ID_ALBUM+"));"+
                "FOREIGN KEY ("+  ID_UTILIZADOR_COMMENT + ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"));";


        String createCompraTable = "CREATE TABLE" + TABLE_N_COMPRA +
                "(" + ID_COMPRA+ "INTEGER PRIMARY KEY AUTOINCREMENT" +
                DATA_COMPRA + "DATE" +
                VALOR_TOTAL_COMPRA + "TEXT"+
                EFETIVADA_COMPRA + " BOOLEAN" +
                ID_UTILIZADOR_COMPRA + "INTEGER" +
                "FOREIGN KEY ("+  ID_UTILIZADOR_COMPRA+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"));";


        String createFavAlbumTable = "CREATE TABLE " + TABLE_N_FAV_ALBUM +
                "(" + ID_UTILIZADOR_FAV_ALBUM + "INTEGER"+
                ID_ALBUM_FAV + "INTEGER "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_ALBUM+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"));"+
                "FOREIGN KEY ("+  ID_ALBUM_FAV+ ") REFERENCES "+ TABLE_N_ALBUM +"("+ ID_ALBUM+"));";


        String createFavArtistaTable = "CREATE TABLE " + TABLE_N_FAV_ALBUM +
                "(" + ID_UTILIZADOR_FAV_ARTISTA + "INTEGER"+
                ID_ARTISTA_FAV + "INTEGER "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_ARTISTA+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"));"+
                "FOREIGN KEY ("+  ID_ARTISTA_FAV+ ") REFERENCES "+ TABLE_N_ARTISTA +"("+ ID_ARTISTA+"));";


        String createFavGeneroTable = "CREATE TABLE " + TABLE_N_FAV_GENERO +
                "(" + ID_UTILIZADOR_FAV_GENERO + "INTEGER"+
                ID_GENERO_FAV + "INTEGER "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_GENERO+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"));"+
                "FOREIGN KEY ("+  ID_GENERO_FAV+ ") REFERENCES "+ TABLE_N_GENERO +"("+ ID_GENERO+"));";


        String createFavMusicaTable = "CREATE TABLE " + TABLE_N_FAV_MUSICA +
                "(" + ID_UTILIZADOR_FAV_MUSICA + "INTEGER"+
                ID_MUSICA_FAV + "INTEGER "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_MUSICA+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"));"+
                "FOREIGN KEY ("+  ID_MUSICA_FAV+ ") REFERENCES "+ TABLE_N_MUSICA +"("+ ID_MUSICA+"));";


        String createLinhaCompraTable = "CREATE TABLE " + TABLE_N_LINHA_COMPRA +
                "(" + ID_COMPRA_LINHA+ "INTEGER"+
                ID_MUSICA_LINHA+ "INTEGER "+
                "FOREIGN KEY ("+  ID_COMPRA_LINHA+ ") REFERENCES "+ TABLE_N_COMPRA +"("+ ID_COMPRA+"));"+
                "FOREIGN KEY ("+  ID_MUSICA_LINHA+ ") REFERENCES "+ TABLE_N_MUSICA +"("+ ID_MUSICA+"));";
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
