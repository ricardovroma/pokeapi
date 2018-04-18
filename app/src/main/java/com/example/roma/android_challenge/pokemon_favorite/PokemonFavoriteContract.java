package com.example.roma.android_challenge.pokemon_favorite;

import com.example.roma.android_challenge.core.api.models.PokemonModelRest;

import java.util.ArrayList;

public interface PokemonFavoriteContract {

    interface View {
        void showLoading();
        void hideLoading();

        void loadError(String message);
        void build(ArrayList<PokemonModelRest> items);
    }

    interface Presenter {
        void load();
        void build(ArrayList<PokemonModelRest> items);
        void loadError(String message);
    }

    interface Interactor {
        void load(int page);
    }
}