package pt.ipleiria.estg.dei.amsi.sound3application.Utils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import pt.ipleiria.estg.dei.amsi.sound3application.R;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

public class ComprasParentViewHolder extends ParentViewHolder {
    public TextView mComprasTitleTextViewParent;
    public ImageButton mComprasDropDownArrow;

    public ComprasParentViewHolder(View itemView){
            super(itemView);

            mComprasTitleTextViewParent=itemView.findViewById(R.id.parent_list_item_compras_text_view);
            mComprasDropDownArrow=itemView.findViewById(R.id.parent_list_item_compras_expand);
    }

}
