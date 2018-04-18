package com.example.roma.android_challenge.pokemon_favorite;

import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.example.roma.android_challenge.pokemon_list.PokemonListContract;
import com.example.roma.android_challenge.pokemon_list.PokemonListInteractorImpl;

import java.util.ArrayList;

public class PokemonFavoritePresenterImpl implements PokemonFavoriteContract.Presenter {

    private final PokemonFavoriteContract.View view;
    private final PokemonFavoriteInteractorImpl interactor;

    public PokemonFavoritePresenterImpl(PokemonFavoriteContract.View view) {
        this.view = view;
        interactor = new PokemonFavoriteInteractorImpl(PokemonFavoritePresenterImpl.this);
    }

    @Override
    public void load() {
        view.showLoading();
        interactor.load();
    }

    @Override
    public void build(ArrayList<PokemonModelRest> items) {
        view.hideLoading();
        view.build(items);
    }

    @Override
    public void loadError(String message) {
        view.loadError(message);
    }
}
