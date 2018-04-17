package com.example.roma.android_challenge.pokemon_detail;

import com.example.roma.android_challenge.core.FavoriteDao;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;

public class PokemonDetailPresenterImpl implements PokemonDetailContract.Presenter {

    private final PokemonDetailContract.View view;

    public PokemonDetailPresenterImpl(PokemonDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void setfavoritePokemon(PokemonModelRest item) {
        FavoriteDao.sharedInstance().addFavorite(item);
    }

    @Override
    public PokemonModelRest getFavoritePokemon(Integer id) {
        return FavoriteDao.sharedInstance().getFavorite(id);
    }

    @Override
    public void deleteFavoritePokemon(Integer id) {
        FavoriteDao.sharedInstance().deleteFavorite(id);
    }
}
