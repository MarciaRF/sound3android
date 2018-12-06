package models;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class SingletonGestorAlbuns implements Serializable {
    private static SingletonGestorAlbuns INSTANCE = null;
    private ArrayList<Album> albuns;
    private ModeloBDHelper modeloBDHelper = null;

    public static synchronized SingletonGestorAlbuns getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestorAlbuns(context);
        }

        return INSTANCE;
    }

    public SingletonGestorAlbuns(Context context) {
        albuns = new ArrayList<>();
        modeloBDHelper = new ModeloBDHelper(context);
    }

    /*private void gerarFakeData(){

    }*/

    public ArrayList<Album> getAlbuns() {
        albuns = modeloBDHelper.getAllAlbunsBD();
        return albuns;
    }

    public Album getAlbum(long idAlbum){
        for (Album album:albuns
             ) {
            if(album.getIdAlbum()==idAlbum){
                return album;
            }
        }
        return null;
    }
}
