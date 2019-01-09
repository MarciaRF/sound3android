package pt.ipleiria.estg.dei.amsi.sound3application.Utils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import pt.ipleiria.estg.dei.amsi.sound3application.R;


public class ComprasChildViewHolder extends ChildViewHolder {

    public TextView mComprasTitleTextViewChild;
    public ImageButton mComprasDownload;

    public ComprasChildViewHolder (View itemView){
        super(itemView);

        mComprasTitleTextViewChild =itemView.findViewById(R.id.child_list_item_compras_text_view);
        mComprasDownload =itemView.findViewById(R.id.download);
    }
}
