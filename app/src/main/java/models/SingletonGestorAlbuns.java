package models;

import java.io.Serializable;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class SingletonGestorAlbuns implements Serializable {
    private static SingletonGestorAlbuns INSTANCE = null;
    private ArrayList<Album> albuns;

    public static synchronized SingletonGestorAlbuns getInstance() {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestorAlbuns();
        }

        return INSTANCE;
    }

    public SingletonGestorAlbuns() {
        albuns = new ArrayList<>();
        gerarFakeData();
    }

    private void gerarFakeData(){
        albuns.add(new Album(1,"Meteora", "Linkin Park", 2003, R.drawable.meteora));
        albuns.add(new Album(2,"Hybrid Theory", "Linkin Park", 2000, R.drawable.hybridtheory));
        albuns.add(new Album(3,"Hybrid Theory2", "Linkin Park", 2000, R.drawable.hybridtheory));
        albuns.add(new Album(4,"Meteora2", "Linkin Park", 2003, R.drawable.meteora));
        albuns.add(new Album(5,"Meteora3", "Linkin Park", 2003, R.drawable.meteora));
        albuns.add(new Album(6,"Hybrid Theory3", "Linkin Park", 2000, R.drawable.hybridtheory));

    }

    public ArrayList<Album> getLivros() {
        return new ArrayList<>(albuns);
    }

    public Album getLivro(long idLivro){
        for (Album album:albuns
             ) {
            if(album.getIdAlbum()==idLivro){
                return album;
            }
        }
        return null;
    }
}
