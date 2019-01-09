package models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class ModeloBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sound3";
    private static final int DB_VERSION = 2;


    //VARIAVEIS DAS TABLES:
    //GENERO
    private static final String TABLE_N_GENERO = "genero";
    public static final String ID_GENERO = "id";
    public static final String NOME_GENERO = "nome";
    public static final String DESCRICAO_GENERO = "descricao";
    public static final String FOTO_GENERO = "caminhoImagem";

    //ARTISTA
    private static final String TABLE_N_ARTISTA = "artista";
    public static final String ID_ARTISTA = "id";
    public static final String NOME_ARTISTA = "nome";
    public static final String NACIONALIDADE_ARTISTA = "nacionalidade";
    public static final String ANO_ARTISTA = "ano";
    public static final String FOTO_ARTISTA = "caminhoImagem";

    //ALBUM
    private static final String TABLE_N_ALBUM = "album";
    public static final String ID_ALBUM = "id";
    public static final String NOME_ALBUM = "nome";
    public static final String ANO_ALBUM = "ano";
    public static final String FOTO_ALBUM = "caminhoImagem";
    public static final String ID_AUTOR_ALBUM = "id_artista";
    public static final String ID_GENERO_DO_ALBUM = "id_genero";

    //MUSICA
    private static final String TABLE_N_MUSICA = "musica";
    public static final String ID_MUSICA = "id";
    public static final String NOME_MUSICA = "nome";
    public static final String DURACAO_MUSICA = "duracao";
    public static final String PRECO_MUSICA = "preco";
    public static final String ID_ALBUM_DA_MUSICA = "id_album";
    public static final String CAMINHO_MP3_MUSICA = "caminhoMP3";
    public static final String POSICAO_MUSICA = "posicao";

    //COMMENT
    private static final String TABLE_N_COMMENT ="comment";
    public static final String ID_COMMENT = "id";
    public static final String CONTEUDO_COMMENT = "conteudo";
    public static final String DATA_CRIACAO_COMMENT = "data_criacao";
    public static final String ID_UTILIZADOR_COMMENT = "id_utilizador";
    public static final String ID_ALBUM_COMMENT = "id_album";

    //COMPRA
    private static final String TABLE_N_COMPRA ="compra";
    public static final String ID_COMPRA = "id";
    public static final String DATA_COMPRA = "data_compra";
    public static final String VALOR_TOTAL_COMPRA = "valor_total";
    public static final String EFETIVADA_COMPRA = "efetivada";
    public static final String ID_UTILIZADOR_COMPRA = "id_utilizador";

    //FAV_ALBUM
    private static final String TABLE_N_FAV_ALBUM ="fav_album";
    public static final String ID_UTILIZADOR_FAV_ALBUM = "id_utilizador";
    public static final String ID_ALBUM_FAV = "id_album";

    //FAV_ARTISTA
    private static final String TABLE_N_FAV_ARTISTA ="fav_artista";
    public static final String ID_UTILIZADOR_FAV_ARTISTA = "id_utilizador";
    public static final String ID_ARTISTA_FAV = "id_artista";

    //FAV_GENERO
    private static final String TABLE_N_FAV_GENERO="fav_genero";
    public static final String ID_UTILIZADOR_FAV_GENERO= "id_utilizador";
    public static final String ID_GENERO_FAV = "id_genero";

    //FAV_Musica
    private static final String TABLE_N_FAV_MUSICA ="fav_musica";
    public static final String ID_UTILIZADOR_FAV_MUSICA = "id_utilizador";
    public static final String ID_MUSICA_FAV = "id_musica";

    //LINHA COMPRA
    private static final String TABLE_N_LINHA_COMPRA ="linha_compra";
    public static final String ID_COMPRA_LINHA ="id_compra";
    public static final String ID_MUSICA_LINHA= "id_musica";

    //UTILIZADOR
    private static final String TABLE_N_UTILIZADOR = "user";
    public static final String ID_UTILIZADOR ="id";
    public static final String NOME_UTILIZADOR ="username";
    public static final String PASSWORD_UTILIZADOR ="password";
    public static final String EMAIL_UTILIZADOR ="email";


    private final SQLiteDatabase database;

    //alterar para apenas receber o contexto, e o factory fica a null
    public ModeloBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    //Criar Tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createGeneroTable = " CREATE TABLE " + TABLE_N_GENERO +
                "( " + ID_GENERO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_GENERO + " TEXT NOT NULL, " +
                DESCRICAO_GENERO + " TEXT, " +
                FOTO_GENERO + " TEXT" + ");";
        db.execSQL(createGeneroTable);


        String createArtistaTable = " CREATE TABLE " + TABLE_N_ARTISTA +
                "(" + ID_ARTISTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOME_ARTISTA + " TEXT NOT NULL, " +
                NACIONALIDADE_ARTISTA + " TEXT NOT NULL, " +
                ANO_ARTISTA + " INTEGER, " +
                FOTO_ARTISTA + " INTEGER NOT NULL" + ");";
        db.execSQL(createArtistaTable);


        String createAlbumTable = " CREATE TABLE " + TABLE_N_ALBUM +
                "(" + ID_ALBUM + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOME_ALBUM + " TEXT NOT NULL, " +
                ANO_ALBUM + " INTEGER, "+
                FOTO_ALBUM + " INTEGER, "+
                ID_AUTOR_ALBUM + " INTEGER, "+
                ID_GENERO_DO_ALBUM + " INTEGER, "+
                "FOREIGN KEY ("+ ID_AUTOR_ALBUM + ") REFERENCES "+ TABLE_N_ARTISTA+"("+ ID_ARTISTA+")," +
                "FOREIGN KEY ("+ ID_GENERO_DO_ALBUM + ") REFERENCES "+ TABLE_N_GENERO+"("+ ID_GENERO+"))";
        db.execSQL(createAlbumTable);


        String createMusicaTable = " CREATE TABLE " + TABLE_N_MUSICA +
                "(" + ID_MUSICA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_MUSICA + " TEXT NOT NULL, " +
                DURACAO_MUSICA + " TEXT NOT NULL, "+
                PRECO_MUSICA + " INTEGER, " +
                ID_ALBUM_DA_MUSICA + " INTEGER, " +
                CAMINHO_MP3_MUSICA + " TEXT, " +
                POSICAO_MUSICA + " INTEGER, " +
                "FOREIGN KEY ("+  ID_ALBUM_DA_MUSICA + ") REFERENCES "+ TABLE_N_ALBUM+"("+ ID_ALBUM+"))";
        db.execSQL(createMusicaTable);


        String createUtilizadorTable =" CREATE TABLE " + TABLE_N_UTILIZADOR+
                "(" + ID_UTILIZADOR + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOME_UTILIZADOR + " TEXT NOT NULL, "+
                PASSWORD_UTILIZADOR + " TEXT NOT NULL, "+
                EMAIL_UTILIZADOR + " TEXT NOT NULL " + ")";
        db.execSQL(createUtilizadorTable);


        String createCommentTable = " CREATE TABLE " + TABLE_N_COMMENT +
                "(" + ID_COMMENT+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CONTEUDO_COMMENT + " TEXT NOT NULL, " +
                DATA_CRIACAO_COMMENT + " DATE, "+
                ID_UTILIZADOR_COMMENT + " INTEGER, " +
                ID_ALBUM_COMMENT + " INTEGER, " +
                "FOREIGN KEY ("+  ID_ALBUM_COMMENT + ") REFERENCES "+ TABLE_N_ALBUM+"("+ ID_ALBUM+"),"+
                "FOREIGN KEY ("+  ID_UTILIZADOR_COMMENT + ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"))";
        db.execSQL(createCommentTable);


        String createCompraTable = " CREATE TABLE " + TABLE_N_COMPRA +
                "(" + ID_COMPRA+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATA_COMPRA + " DATE, " +
                VALOR_TOTAL_COMPRA + " TEXT, "+
                EFETIVADA_COMPRA + " BOOLEAN, " +
                ID_UTILIZADOR_COMPRA + " INTEGER, " +
                "FOREIGN KEY ("+  ID_UTILIZADOR_COMPRA+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"))";
        db.execSQL(createCompraTable);


        String createFavAlbumTable = " CREATE TABLE " + TABLE_N_FAV_ALBUM +
                "(" + ID_UTILIZADOR_FAV_ALBUM + " INTEGER,"+
                ID_ALBUM_FAV + " INTEGER, "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_ALBUM+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"),"+
                "FOREIGN KEY ("+  ID_ALBUM_FAV+ ") REFERENCES "+ TABLE_N_ALBUM +"("+ ID_ALBUM+")," +
                "PRIMARY KEY ("+ID_UTILIZADOR_FAV_ALBUM + "," + ID_ALBUM_FAV +"))";
        db.execSQL(createFavAlbumTable);


        String createFavArtistaTable = " CREATE TABLE " + TABLE_N_FAV_ARTISTA +
                "(" + ID_UTILIZADOR_FAV_ARTISTA + " INTEGER, "+
                ID_ARTISTA_FAV + " INTEGER, "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_ARTISTA+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"),"+
                "FOREIGN KEY ("+  ID_ARTISTA_FAV+ ") REFERENCES "+ TABLE_N_ARTISTA +"("+ ID_ARTISTA+")," +
                "PRIMARY KEY ("+ ID_UTILIZADOR_FAV_ARTISTA + "," + ID_ARTISTA_FAV +"))";
        db.execSQL(createFavArtistaTable);


        String createFavGeneroTable = " CREATE TABLE " + TABLE_N_FAV_GENERO +
                "(" + ID_UTILIZADOR_FAV_GENERO + " INTEGER, "+
                ID_GENERO_FAV + " INTEGER, "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_GENERO+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"),"+
                "FOREIGN KEY ("+  ID_GENERO_FAV+ ") REFERENCES "+ TABLE_N_GENERO +"("+ ID_GENERO+")," +
                "PRIMARY KEY ("+ ID_UTILIZADOR_FAV_GENERO + "," + ID_GENERO_FAV +"))";
        db.execSQL(createFavGeneroTable);


        String createFavMusicaTable = " CREATE TABLE " + TABLE_N_FAV_MUSICA +
                "(" + ID_UTILIZADOR_FAV_MUSICA + " INTEGER, "+
                ID_MUSICA_FAV + " INTEGER, "+
                "FOREIGN KEY ("+  ID_UTILIZADOR_FAV_MUSICA+ ") REFERENCES "+ TABLE_N_UTILIZADOR +"("+ ID_UTILIZADOR+"),"+
                "FOREIGN KEY ("+  ID_MUSICA_FAV+ ") REFERENCES "+ TABLE_N_MUSICA +"("+ ID_MUSICA+"),"+
                "PRIMARY KEY ("+ ID_UTILIZADOR_FAV_MUSICA + "," + ID_MUSICA_FAV+"))";
        db.execSQL(createFavMusicaTable);


        String createLinhaCompraTable = " CREATE TABLE " + TABLE_N_LINHA_COMPRA +
                "(" + ID_COMPRA_LINHA + " INTEGER, "+
                ID_MUSICA_LINHA + " INTEGER, "+
                "FOREIGN KEY ("+  ID_COMPRA_LINHA+ ") REFERENCES "+ TABLE_N_COMPRA +"("+ ID_COMPRA+"),"+
                "FOREIGN KEY ("+  ID_MUSICA_LINHA+ ") REFERENCES "+ TABLE_N_MUSICA +"("+ ID_MUSICA+"),"+
                "PRIMARY KEY ("+ ID_COMPRA_LINHA + "," + ID_MUSICA_LINHA+"))";
        db.execSQL(createLinhaCompraTable);


    }


    //Apagar Tabelas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_UTILIZADOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_GENERO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_ARTISTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_ALBUM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_MUSICA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_COMMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_COMPRA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_FAV_MUSICA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_FAV_GENERO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_FAV_ALBUM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_FAV_ARTISTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_N_LINHA_COMPRA);
        this.onCreate(db);

    }



    // Adiciona dados nas Tabelas
    public Utilizador adicionarUtilizadorDB(Utilizador utilizador){
        ContentValues values = new ContentValues();

        values.put(NOME_UTILIZADOR, utilizador.getNomeUtilizador());
        values.put(PASSWORD_UTILIZADOR, utilizador.getPasswordUtilizador());
        values.put(EMAIL_UTILIZADOR, utilizador.getEmailUtilizador());

        long id = this.database.insert(TABLE_N_LINHA_COMPRA, null, values);

        if(id >-1){
            utilizador.setIdUtilizador(id);
            return utilizador;
        }

        return null;
    }

    public LinhaCompra adicionarLinhaCompraDB(LinhaCompra linhacompra){
        ContentValues values = new ContentValues();

        values.put(ID_COMPRA_LINHA, linhacompra.getIdCompra());
        values.put(ID_MUSICA_LINHA, linhacompra.getIdMusica());

        long id = this.database.insert(TABLE_N_LINHA_COMPRA, null, values);

        if(id >-1){
            linhacompra.setIdCompra(id);
            return linhacompra;
        }

        return null;
    }

    public Comentario adicionarComentarioBD(Comentario comentario){
        ContentValues values = new ContentValues();

        values.put(CONTEUDO_COMMENT, comentario.getConteudo());
        values.put(DATA_CRIACAO_COMMENT, comentario.getData_Criacao());
        values.put(ID_UTILIZADOR_COMMENT, comentario.getId_Utilizador());
        values.put(ID_ALBUM_COMMENT, comentario.getId_Album());


        long id = this.database.insert(TABLE_N_COMMENT, null, values);

        if(id >-1){
            comentario.setIdComentario(id);
            return comentario;
        }
        return null;
    }

    public FavoritoAlbum adicionarAlbumFavoritoDB(FavoritoAlbum favoritoalbum){
        ContentValues values = new ContentValues();

        values.put(ID_UTILIZADOR_FAV_ALBUM, favoritoalbum.getIdUtilizador());
        values.put(ID_ALBUM_FAV, favoritoalbum.getIdAlbum());

        this.database.insert(TABLE_N_FAV_ALBUM, null, values);

        return null;
    }

    public FavoritoArtista adicionarArtistaFavoritoDB(FavoritoArtista favoritoartista){
        ContentValues values = new ContentValues();

        values.put(ID_UTILIZADOR_FAV_ARTISTA, favoritoartista.getIdArtista());
        values.put(ID_ARTISTA_FAV, favoritoartista.getIdArtista());

        this.database.insert(TABLE_N_FAV_ARTISTA, null, values);

        return null;
    }

    public FavoritoGenero adicionarGeneroFavoritoDB(FavoritoGenero favoritogenero){
        ContentValues values = new ContentValues();

        values.put(ID_UTILIZADOR_FAV_GENERO, favoritogenero.getIdUtilizador());
        values.put(ID_GENERO_FAV, favoritogenero.getIdGenero());

        this.database.insert(TABLE_N_FAV_GENERO, null, values);

        return null;
    }

    public FavoritoMusica adicionarMusicaFavoritoDB(FavoritoMusica favoritomusica){
        ContentValues values = new ContentValues();

        values.put(ID_UTILIZADOR_FAV_MUSICA, favoritomusica.getIdUtilizador());
        values.put(ID_MUSICA_FAV, favoritomusica.getIdMusica());

        this.database.insert(TABLE_N_FAV_MUSICA, null, values);

        return null;
    }

    public Album adicionarAlbumBD(Album album){
        ContentValues values = new ContentValues();

        values.put(NOME_ALBUM, album.getNome());
        values.put(ANO_ALBUM, album.getAno());
        values.put(FOTO_ALBUM, album.getImagem());
        values.put(ID_AUTOR_ALBUM, album.getId_Autor());
        values.put(ID_GENERO_DO_ALBUM, album.getId_Genero());

        long id = this.database.insert(TABLE_N_ALBUM, null, values);

        if(id >-1){
            album.setIdAlbum(id);
            return album;
        }
        return  null;
    }

    public Artista adicionarArtistaBD(Artista artista){
        ContentValues values = new ContentValues();

        values.put(NOME_ARTISTA, artista.getNome());
        values.put(NACIONALIDADE_ARTISTA, artista.getNacionalidade());
        values.put(ANO_ARTISTA, artista.getAno());
        values.put(FOTO_ARTISTA, artista.getImagem());

        long id = this.database.insert(TABLE_N_ARTISTA, null, values);

        if(id >-1){
            artista.setIdArtista(id);
            return artista;
        }
        return  null;
    }

    public Genero adicionarGeneroBD(Genero genero){
        ContentValues values = new ContentValues();
        System.out.println("----->BD RECEBE BD 4 : " + genero.getNome());
        values.put(NOME_GENERO, genero.getNome());
        values.put(DESCRICAO_GENERO, genero.getDescricao());
        values.put(FOTO_GENERO, genero.getImagem());

        System.out.println("----->BD INSERE BD 5 : " + this.database.insert(TABLE_N_GENERO, null, values));
        long id = this.database.insert(TABLE_N_GENERO, null, values);


        if(id >-1){
            genero.setIdGenero(id);
            return genero;
        }
        return  null;
    }

    public Musica adicionarMusicaBD(Musica musica){
        ContentValues values = new ContentValues();

        values.put(NOME_MUSICA, musica.getNome());
        values.put(DURACAO_MUSICA, musica.getDuracao());
        values.put(PRECO_MUSICA, musica.getPreco());
        values.put(ID_ALBUM_DA_MUSICA, musica.getIdAlbum());
        values.put(CAMINHO_MP3_MUSICA, musica.getCaminhoMusica());
        values.put(POSICAO_MUSICA, musica.getPosicao());

        long id = this.database.insert(TABLE_N_MUSICA, null, values);

        if(id >-1){
            musica.setIdMusica(id);
            return musica;
        }
        return  null;
    }



    // Vais Buscar todos os dados das Tabelas
    public ArrayList<Utilizador> getAllUtilizadoresBD(){
        ArrayList<Utilizador> utilizadores = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_UTILIZADOR, new String[]{" id ", NOME_UTILIZADOR, PASSWORD_UTILIZADOR, EMAIL_UTILIZADOR},
                null, null, null, null, null );

        if(cursor.moveToFirst()){
            do {
                Utilizador auxUtilizador = new Utilizador(0,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                auxUtilizador.setIdUtilizador(cursor.getLong(0));
                System.out.println();
                utilizadores.add(auxUtilizador);
            }while(cursor.moveToNext());
        }
        return utilizadores;
    }

    public ArrayList<LinhaCompra> getAllLinhaComprasBD(){
        ArrayList<LinhaCompra> linhaCompra = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_LINHA_COMPRA, new String[]{" id ", ID_COMPRA_LINHA, ID_MUSICA_LINHA},
                null, null, null, null, null );

        if(cursor.moveToFirst()){
            do {
                LinhaCompra auxLinhaCompra = new LinhaCompra(0,
                        cursor.getInt(1));
                auxLinhaCompra.setIdCompra(cursor.getLong(0));
                linhaCompra.add(auxLinhaCompra);
            }while(cursor.moveToNext());
        }
        return linhaCompra;
    }

    public ArrayList<Comentario> getAllComentariosDB(){
        ArrayList<Comentario> comentarios = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_COMMENT, new String[]{"id", CONTEUDO_COMMENT, DATA_CRIACAO_COMMENT, ID_UTILIZADOR_COMMENT, ID_ALBUM_COMMENT},
                null, null, null, null, null);

        if (cursor.moveToFirst()){
            do {
                Comentario auxComentario = new Comentario(0,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        cursor.getLong(4));
                auxComentario.setIdComentario(cursor.getLong(0));
                comentarios.add(auxComentario);
            }while (cursor.moveToNext());
        }
        return  comentarios;
    }

    public ArrayList<FavoritoAlbum> getAllAlbunsFavoritosBD(){
        ArrayList<FavoritoAlbum> favAlbuns = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_FAV_ALBUM, new String[]{ID_UTILIZADOR_FAV_ALBUM, ID_ALBUM_FAV},
                null, null, null, null, null );

        if(cursor.moveToFirst()){
            do {
                FavoritoAlbum auxFavAlbum = new FavoritoAlbum(
                        cursor.getInt(0),
                        cursor.getInt(1));
                favAlbuns.add(auxFavAlbum);
            }while(cursor.moveToNext());
        }
        return favAlbuns;
    }

    public ArrayList<FavoritoArtista> getAllArtistasFavoritosBD(){
        ArrayList<FavoritoArtista> favArtistas = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_FAV_ARTISTA, new String[]{ID_UTILIZADOR_FAV_ARTISTA, ID_ARTISTA_FAV},
                null, null, null, null, null );

        if(cursor.moveToFirst()){
            do {
                FavoritoArtista auxFavArtista = new FavoritoArtista(
                        cursor.getInt(0),
                        cursor.getInt(1));
                favArtistas.add(auxFavArtista);
            }while(cursor.moveToNext());
        }
        return favArtistas;
    }

    public ArrayList<FavoritoGenero> getAllGenerosFavoritosBD(){
        ArrayList<FavoritoGenero> favGeneros = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_FAV_GENERO, new String[]{ID_UTILIZADOR_FAV_GENERO, ID_GENERO_FAV},
                null, null, null, null, null );

        if(cursor.moveToFirst()){
            do {
                FavoritoGenero auxFavGenero = new FavoritoGenero(
                        cursor.getInt(0),
                        cursor.getInt(1));
                favGeneros.add(auxFavGenero);
            }while(cursor.moveToNext());
        }
        return favGeneros;
    }

    public ArrayList<FavoritoMusica> getAllMusicasFavoritosBD(){
        ArrayList<FavoritoMusica> favMusicas = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_FAV_MUSICA, new String[]{ID_UTILIZADOR_FAV_MUSICA, ID_MUSICA_FAV},
                null, null, null, null, null );

        if(cursor.moveToFirst()){
            do {
                FavoritoMusica auxFavMusica = new FavoritoMusica(
                        cursor.getInt(1),
                        cursor.getInt(2));
                favMusicas.add(auxFavMusica);
            }while(cursor.moveToNext());
        }
        return favMusicas;
    }

    public ArrayList<Album> getAllAlbunsBD(){
        ArrayList<Album> albuns = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_ALBUM, new String[]{" id ", NOME_ALBUM, ANO_ALBUM, FOTO_ALBUM, ID_AUTOR_ALBUM, ID_GENERO_DO_ALBUM},
                null, null,null, null, null);

        if(cursor.moveToFirst()){
            do{
                Album auxAlbum = new Album(0,
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5));
                auxAlbum.setIdAlbum(cursor.getLong(0));
                albuns.add(auxAlbum);
            }while(cursor.moveToNext());
        }
        return albuns;
    }

    public ArrayList<Artista> getAllArtistasBD(){
        ArrayList<Artista> artistas = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_ARTISTA, new String[]{" id ", NOME_ARTISTA, NACIONALIDADE_ARTISTA, ANO_ARTISTA, FOTO_ARTISTA},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                Artista auxArtista = new Artista(0,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4));
                auxArtista.setIdArtista(cursor.getLong(0));
                artistas.add(auxArtista);
            }while (cursor.moveToNext());
        }
        return artistas;
    }

    public ArrayList<Genero> getAllGenerosBD(){
        ArrayList<Genero> generos = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_GENERO, new String[]{" id ", NOME_GENERO, DESCRICAO_GENERO, FOTO_GENERO},
                null, null,null, null, null);

        System.out.println("----->BD RECEBE CURSOR 6 : " + cursor.getCount());

        if(cursor.moveToFirst()){
            do{
                Genero auxGenero = new Genero(0,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                auxGenero.setIdGenero(cursor.getLong(0));
                System.out.println("----->BD RECEBE AUX 7 : " + auxGenero.getNome());
                generos.add(auxGenero);
            }while(cursor.moveToNext());
        }
        return generos;
    }

    public ArrayList<Musica> getAllMusicasBD(){
        ArrayList<Musica> musicas = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_N_MUSICA, new String[]{"id", NOME_MUSICA, DURACAO_MUSICA, PRECO_MUSICA, ID_ALBUM_DA_MUSICA, CAMINHO_MP3_MUSICA, POSICAO_MUSICA},
                null, null,null, null, null);

        if(cursor.moveToFirst()){
            do{
                Musica auxMusica = new Musica(0,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6));
                auxMusica.setIdMusica(cursor.getLong(0));
                musicas.add(auxMusica);
            }while(cursor.moveToNext());
        }
        return musicas;
    }



    // Apaga as Tabelas
    public void removeAllUtilizadores(){this.database.delete(TABLE_N_UTILIZADOR, null, null);}

    public void removeAllLinhasCompras(){this.database.delete(TABLE_N_LINHA_COMPRA, null, null); }

    public void removeAllComentarios(){this.database.delete(TABLE_N_COMMENT, null, null); }

    public void removeAllFavoritosAlbum(){this.database.delete(TABLE_N_FAV_ALBUM, null, null); }

    public void removeAllFavoritosArtista(){this.database.delete(TABLE_N_FAV_ARTISTA, null, null); }

    public void removeAllFavoritosGenero(){this.database.delete(TABLE_N_FAV_GENERO, null, null); }

    public void removeAllFavoritosMusica(){this.database.delete(TABLE_N_FAV_MUSICA, null, null); }

    public void removeAllAlbuns() {
        this.database.delete(TABLE_N_ALBUM,null,null);
    }

    public void removeAllArtistas() {
        this.database.delete(TABLE_N_ARTISTA,null,null);
    }

    public void removeAllGeneros() { this.database.delete(TABLE_N_GENERO, null,null); }

    public void removeAllMusicas() {
        this.database.delete(TABLE_N_MUSICA,null,null);
    }


}
