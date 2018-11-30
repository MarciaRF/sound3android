package adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import models.Album;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class GridAlbunsAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Album> albuns;

    public GridAlbunsAdaptador(Context context, ArrayList<Album> albuns) {
        this.context = context;
        this.albuns = albuns;
    }

    @Override
    public int getCount() {
        return albuns.size();
    }

    @Override
    public Object getItem(int position) {
        return albuns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_grid_albuns, null);
        }

        GridAlbunsAdaptador.ViewHolderGrid viewHolderGrid = (GridAlbunsAdaptador.ViewHolderGrid)convertView.getTag();

        if(viewHolderGrid == null){
            viewHolderGrid = new GridAlbunsAdaptador.ViewHolderGrid(convertView);
            convertView.setTag(viewHolderGrid);
        }

//        viewHolderGrid.update(albuns.get(position));
        return convertView;
    }

    private class ViewHolderGrid{

        private ImageView capa;
        private TextView nomeAlbum;
        private TextView nomeArtista;


        public ViewHolderGrid(View convertView){
            capa = convertView.findViewById(R.id.imageViewCapaAlbum);
            nomeAlbum = convertView.findViewById(R.id.textViewNomeGenero);
            nomeArtista = convertView.findViewById(R.id.textViewNomeArtista);
        }

        /*public void update(Album album){
           nomeAlbum.setText(album.getNome());
            nomeArtista.setText(album.getNomeAutor());
            capa.setImageResource(album.getCapa());
        }*/
    }
}
