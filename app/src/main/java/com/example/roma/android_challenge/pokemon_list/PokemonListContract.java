package com.example.roma.android_challenge.pokemon_list;

import com.example.roma.android_challenge.core.api.models.NamedAPIResourceModelRest;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;

import java.util.ArrayList;

public interface PokemonListContract {

    interface View {
        void showLoading();
        void hideLoading();

        void loadError(String message);
        void build(ArrayList<PokemonModelRest> items);

        void errorLoadPokemon(String message);
        void buildPokemon(PokemonModelRest pokemonModelRest);

        void morePageBuild(ArrayList<PokemonModelRest> items);

        void loadNetworkError();
    }

    interface Presenter {
        void loadNetworkError();

        void load();
        void build(ArrayList<PokemonModelRest> items);
        void loadError(String message);

        void loadPokemon(String id);
        void buildPokemon(PokemonModelRest pokemonModelRest);
        void errorLoadPokemon(String message);

        void morePage(int page);
        void morePageBuild(ArrayList<PokemonModelRest> items);


    }

    interface Interactor {
        void load(int page);
        void loadPokemon(String id);
        void morePage(int page);
        void onDestroy();
    }
}