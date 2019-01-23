package adaptadores;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;

import models.Compra;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.LinhaCompraClickListener;
import pt.ipleiria.estg.dei.amsi.sound3application.R;

class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ExpandableLinearLayout expandableLayout;
    public RelativeLayout button;
    public TextView textView,textViewChild;

    LinhaCompraClickListener linhaCompraClickListener;


    public MyViewHolder(View itemView){
        super(itemView);
        textView=itemView.findViewById(R.id.textView);
        textViewChild=itemView.findViewById(R.id.textViewChild);
        button=itemView.findViewById(R.id.button);
        expandableLayout=itemView.findViewById(R.id.expandableLayout);

        itemView.setOnClickListener(this);

    }

    public void setLinhaCompraClickListener(LinhaCompraClickListener linhaCompraClickListener) {
        this.linhaCompraClickListener = linhaCompraClickListener;
    }

    @Override
    public void onClick(View v) {
            linhaCompraClickListener.onClick(v,getAdapterPosition(),false);
    }
}
public class CompraAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Compra> compras;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public CompraAdapter(Context context,List<Compra> compras){
        this.compras=compras;
        this.context=context;
        for(int i=0;i<compras.size();i++)
            expandState.append(i,false);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       this.context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_compras_parent,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        switch (viewHolder.getItemViewType()){
            case 0:
            {
                final MyViewHolder myViewHolder =(MyViewHolder)viewHolder;
                final Compra compra= compras.get(i);
                myViewHolder.setIsRecyclable(false);
                myViewHolder.textView.setText("#"+ i +" "+compra.getDataCompra());

                myViewHolder.expandableLayout.setInRecyclerView(true);
                myViewHolder.expandableLayout.setExpanded(expandState.get(i));
                myViewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

                    @Override
                    public void onPreOpen() {
                        changeRotate(myViewHolder.button,0f,180f).start();
                        expandState.put(i,true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(myViewHolder.button,180f,0f).start();
                        expandState.put(i,false);
                    }

                });
                myViewHolder.button.setRotation(expandState.get(i)?180f:0f);
                myViewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myViewHolder.expandableLayout.toggle();
                    }
                });
                
                myViewHolder.textViewChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, ""+compras.get(i).getDataCompra(), Toast.LENGTH_SHORT).show();
                    }
                });
                
                myViewHolder.setLinhaCompraClickListener(new LinhaCompraClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(context, "Click"+compras.get(i).getValorTotal(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

    private ObjectAnimator changeRotate(RelativeLayout button, float to, float from) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button,"rotation",from,to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
