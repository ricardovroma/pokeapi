package com.example.roma.android_challenge.pokemon_detail;

import com.example.roma.android_challenge.core.api.models.PokemonModelRest;

public interface PokemonDetailContract {

    interface View {

    }

    interface Presenter {

        void setfavoritePokemon(PokemonModelRest item);
        PokemonModelRest getFavoritePokemon(Integer id);
        void deleteFavoritePokemon(Integer id);
    }
}