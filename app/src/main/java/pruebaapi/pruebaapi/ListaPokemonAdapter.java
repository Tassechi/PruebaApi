package pruebaapi.pruebaapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pruebaapi.pruebaapi.models.pokemon;

/**
 * Created by orimu on 15/05/2018.
 */

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder>{

    private ArrayList<pokemon> dataset;
    private Context context;

    public ListaPokemonAdapter( Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }

    public ListaPokemonAdapter(ArrayList<pokemon> dataset){

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mostrador, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        pokemon p = dataset.get(position);
        holder.nombretext.setText(p.getName());

        Glide.with(context)
                .load("https//pokeapi.co/media/sprites/pokemon/"+ p.getNumber() +".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void adicionarlistapokemon(ArrayList<pokemon> listapokemon) {
        dataset.addAll(listapokemon);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView nombretext;


        public ViewHolder(View itemView) {
            super(itemView);

            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombretext = (TextView) itemView.findViewById(R.id.nombretext);
        }
    }
}
