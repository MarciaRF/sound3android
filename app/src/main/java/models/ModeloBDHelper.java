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
    public static final String ID_GENERO = "id";
    public static final String NOME_GENERO = "nome";
    public static final String DESCRICAO_GENERO = "descricao";
    public static final String FOTO_GENERO = "caminhoImagem";

    //ARTISTA
    private static final String TABLE_N_ARTISTA = "artista";
    public static final String ID_ARTISTA = "id";
    public static final String NOME_ARTISTA = "nome";
    public static final String NACIONALIDADE_ARTISTA = "nacionalidade";
    public static final String D_INI_CARREIRA_ARTISTA = "data_ini_carreira";
    public static final String FOTO_ARTISTA = "caminhoImagem";

    //ALBUM
    private static final String TABLE_N_ALBUM = "genero";
    public static final String ID_ALBUM = "id";
    public static final String NOME_ALBUM = "nome";
    public static final String D_LANCAMENTO_ALBUM = "data_lancamento";
    public static final String FOTO_ALBUM = "caminhoImagem";
    public static final String ID_AUTOR_ALBUM = "id_artista";
    public static final String ID_GENERO_DO_ALBUM = "id_album";

    //MUSICA
    private static final String TABLE_N_MUSICA = "genero";
    public static final String ID_MUSICA = "id";
    public static final String NOME_MUSICA = "nome";
    public static final String DURACAO_MUSICA = "duracao";
    public static final String PRECO_MUSICA = "preco";
    public static final String ID_ALBUM_DA_MUSICA = "id_album";


    private final SQLiteDatabase database;
    //alterar para apenas receber o contexto, e o factory fica a null
    public ModeloBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContactoTable = "CREATE TABLE " + TABLE_N_GENERO +
                "( " + ID_GENERO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_GENERO + " TEXT NOT NULL, " +
                FOTO_CONTACTO + " INTEGER" +
                ");";
        db.execSQL(createContactoTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
