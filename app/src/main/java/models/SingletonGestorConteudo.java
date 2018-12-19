package models;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class SingletonGestorConteudo  {

    private static SingletonGestorConteudo INSTANCE = null;
    private ArrayList<Album> albuns;
    private ArrayList<Artista> artistas;
    private ArrayList<Genero> generos;
    private ArrayList<Musica> musicas;

    private ModeloBDHelper modeloBDHelper = null;

    public static synchronized SingletonGestorConteudo getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestorConteudo(context);
        }

        return INSTANCE;
    }

    public SingletonGestorConteudo(Context context) {
        albuns = new ArrayList<>();
        artistas = new ArrayList<>();
        generos = new ArrayList<>();
        musicas = new ArrayList<>();

        modeloBDHelper = new ModeloBDHelper(context);
    }

    public ArrayList<Album> getAlbunsBD(){
        albuns = modeloBDHelper.getAllAlbunsBD();
        return albuns;
    }

    public Album getAlbum(long idAlbum){
        for(Album album:albuns){
            if(album.getIdAlbum() == idAlbum){
                return album;
            }
        }
        return null;
    }

    public ArrayList<Artista> getArtistasBD(){
        artistas = modeloBDHelper.getAllArtistasBD();
        return artistas;
    }

    public Artista getArtista(long idArtista){
        for (Artista artista:artistas) {
            if(artista.getIdArtista() == idArtista){
                return artista;
            }
        }
        return null;
    }


    public ArrayList<Genero> getGenerosBD(){
        generos = modeloBDHelper.getAllGenerosBD();
        return generos;
    }

    public Genero getGenero(long idGenero){
        for (Genero genero:generos) {
            if(genero.getIdGenero() == idGenero){
                return genero;
            }
        }
        return null;
    }

    public ArrayList<Musica> getMusicasBD(){
        musicas = modeloBDHelper.getAllMusicasBD();
        return musicas;
    }

    public Musica getMusica(long idMusica){
        for (Musica musica:musicas) {
            if(musica.getIdMusica() == idMusica){
                return musica;
            }
        }
        return null;
    }


    public void adicionarAlbumBD(ArrayList<Album> albuns){
        ArrayList<Album> auxAlbuns = new ArrayList<>();
        for (Album album:albuns){
            modeloBDHelper.adicionarAlbumBD(album);
            auxAlbuns.add(modeloBDHelper.adicionarAlbumBD(album));
        }

        if(auxAlbuns != null){
            for (Album auxAlbum:auxAlbuns) {
                albuns.add(auxAlbum);
            }
        }

    }




}
