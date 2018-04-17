package com.example.roma.android_challenge.pokemon_list;

import com.example.roma.android_challenge.core.api.models.PokemonModelRest;

import java.util.ArrayList;

public class PokemonListPresenterImpl implements PokemonListContract.Presenter {

    private final PokemonListContract.View view;
    private final PokemonListInteractorImpl interactor;

    public PokemonListPresenterImpl(PokemonListContract.View view) {
        this.view = view;
        interactor = new PokemonListInteractorImpl(PokemonListPresenterImpl.this);
    }

    @Override
    public void loadNetworkError() {
        view.loadNetworkError();
    }

    @Override
    public void load() {
        view.showLoading();
        interactor.load(1);
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

    @Override
    public void loadPokemon(String id) {
        interactor.loadPokemon(id);
    }

    @Override
    public void buildPokemon(PokemonModelRest pokemonModelRest) {
        view.buildPokemon(pokemonModelRest);
    }

    @Override
    public void errorLoadPokemon(String message) {
        view.errorLoadPokemon(message);
    }

    @Override
    public void morePageBuild(ArrayList<PokemonModelRest> items) {
        view.hideLoading();
        view.morePageBuild(items);
    }

    @Override
    public void morePage(int page) {
        if(page > 15) {
            morePageBuild(new ArrayList<PokemonModelRest>());
        } else {
            view.showLoading();
            interactor.morePage(page);
        }

    }
}
