package com.example.roma.android_challenge.pokemon_favorite;

import android.util.Log;

import com.example.roma.android_challenge.core.FavoriteDao;
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

public class PokemonFavoriteInteractorImpl implements PokemonFavoriteContract.Interactor {

    private final PokemonFavoriteContract.Presenter presenter;

    public PokemonFavoriteInteractorImpl(PokemonFavoriteContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void load() {
        try {
            presenter.build(FavoriteDao.sharedInstance().getFavorites());
        }catch (Exception e) {
            presenter.loadError("Erro ao buscar os favoritos");
        }

    }
}
