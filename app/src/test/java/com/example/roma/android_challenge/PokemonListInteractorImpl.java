package com.example.roma.android_challenge;

import android.util.Log;

import com.example.roma.android_challenge.core.api.PokemonAPI;
import com.example.roma.android_challenge.core.api.models.NamedAPIResourceModelRest;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.example.roma.android_challenge.core.api.models.PokemonsModelRest;
import com.example.roma.android_challenge.pokemon_list.PokemonListContract;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PokemonListInteractorImpl implements PokemonListContract.Interactor {

    private final PokemonListContract.Presenter presenter;
    private final PokemonAPI api;

    public PokemonListInteractorImpl(PokemonListContract.Presenter presenter) {
        this.presenter = presenter;
        api = new PokemonAPI();
    }

    @Override
    public void load(int page) {

    }

    @Override
    public void loadPokemon(final String id) {

    }

    @Override
    public void morePage(int page) {

    }

    @Override
    public void onDestroy() {

    }

    interface PokemonsListener {
        void callback(ArrayList<PokemonModelRest> items);
    }
}
