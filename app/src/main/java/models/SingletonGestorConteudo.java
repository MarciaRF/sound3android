package models;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.sound3application.R;

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
            //modeloBDHelper.adicionarAlbumBD(album);
            auxAlbuns.add(modeloBDHelper.adicionarAlbumBD(album));
        }
        if(auxAlbuns != null){
            for (Album auxAlbum:auxAlbuns) {
                albuns.add(auxAlbum);
            }
        }
    }

    public void adicionarArtistaBD(ArrayList<Artista> artistas){
        ArrayList<Artista> auxArtistas = new ArrayList<>();
        for (Artista artista:artistas){
            //modeloBDHelper.adicionarArtistaBD(artista);
            auxArtistas.add(modeloBDHelper.adicionarArtistaBD(artista));
        }
        if(auxArtistas != null){
            for (Artista auxArtista:auxArtistas) {
                artistas.add(auxArtista);
            }
        }
    }

    public void adicionarMusicaBD(ArrayList<Musica> musicas){
        ArrayList<Musica> auxMusicas = new ArrayList<>();
        for (Musica musica:musicas){
            //modeloBDHelper.adicionarMusicaBD(musica);
            auxMusicas.add(modeloBDHelper.adicionarMusicaBD(musica));
        }
        if(auxMusicas != null){
            for (Musica auxMusica:auxMusicas) {
                musicas.add(auxMusica);
            }
        }
    }

    public void adicionarGeneroBD(ArrayList<Genero> generos){
        ArrayList<Genero> auxGeneros = new ArrayList<>();
        for (Genero genero:generos){
            //modeloBDHelper.adicionarGeneroBD(genero);
            auxGeneros.add(modeloBDHelper.adicionarGeneroBD(genero));
        }
        if(auxGeneros != null){
            for (Genero auxGenero:auxGeneros) {
                generos.add(auxGenero);
            }
        }
    }

    // VAI BUSCAR TODAS AS MUSICAS DE UM ALBUM
    public ArrayList<Musica> musicasAlbum(long idAlbum){
        ArrayList<Musica> musicasAlbum = new ArrayList<>();
        for(Musica musica:musicas){
            if(musica.getIdAlbum() == idAlbum){
                musicasAlbum.add(musica);
            }
        }
        return musicasAlbum;
    }

    // VAI BUSCAR TODOS OS ALBUNS DE UM ARTISTA
    public ArrayList<Album> albunsArtista(long idArtista){
        ArrayList<Album> albunsArtista = new ArrayList<>();
        for (Album album:albuns){
            if(album.getId_Autor() == idArtista){
                albunsArtista.add(album);
            }
        }
        return albunsArtista;
    }

    // VAI BUSCAR TODOS OS ALBUNS DE UM GENERO
    public ArrayList<Album> albunsGenero(long idGenero){
        ArrayList<Album> albunsGenero = new ArrayList<>();
        for (Album album:albuns){
            if(album.getId_Genero() == idGenero){
                albunsGenero.add(album);
            }
        }
        return albunsGenero;
    }











}
