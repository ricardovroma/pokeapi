package com.example.roma.android_challenge.pokemon_list.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roma.android_challenge.R;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final Listener listener;
    private ArrayList<PokemonModelRest> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvPokemonId;
        private final View viewDefaultLoader;
        private final TextView tvPokemonType;
        private ImageView ivPokemon;
        private ImageView ivFavorite;
        private View viewContainer;
        private TextView tvPokemonName;

        @SuppressLint("WrongViewCast")
        ViewHolder(View itemView) {
            super(itemView);
            viewContainer = itemView.findViewById(R.id.view_container);
            tvPokemonName = itemView.findViewById(R.id.tv_pokemon_name);
            tvPokemonType = itemView.findViewById(R.id.tv_pokemon_type);
            tvPokemonId = itemView.findViewById(R.id.tv_pokemon_id);
            ivPokemon = itemView.findViewById(R.id.iv_pokemon);
            viewDefaultLoader = itemView.findViewById(R.id.default_loader);
        }
    }

    public PokemonAdapter(ArrayList<PokemonModelRest> items, Context context, Listener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pokemon_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PokemonModelRest item = (PokemonModelRest) items.get(position);
        final ViewHolder holder = (ViewHolder) viewHolder;

        if(item.id != null) {
            holder.tvPokemonId.setText(item.id.toString());
            holder.viewDefaultLoader.setVisibility(View.GONE);

            if(item.types != null && item.types.get(0).type != null)
                holder.tvPokemonType.setText(item.types.get(0).type.name);
        } else {
            holder.tvPokemonId.setText("?");
            holder.tvPokemonType.setText("?");
            holder.viewDefaultLoader.setVisibility(View.VISIBLE);
        }

        holder.tvPokemonName.setText(item.name);
        holder.viewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(item, holder.ivPokemon);
            }
        });
        if(item.sprites != null && item.sprites .front_default != null) {
            Glide.with(context).load(item.sprites.front_default).into(holder.ivPokemon);
        } else {
            holder.ivPokemon.setImageDrawable(null);
        }

    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public ArrayList<PokemonModelRest> getItems() {
        return items;
    }

    public void setItems(ArrayList<PokemonModelRest> items) {
        this.items = items;
    }

    public interface Listener {
        void onClickListener(PokemonModelRest item, View view);
    }
}