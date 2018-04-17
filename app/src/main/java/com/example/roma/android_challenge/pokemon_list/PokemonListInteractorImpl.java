package com.example.roma.android_challenge.pokemon_list;

import android.util.Log;

import com.example.roma.android_challenge.core.api.PokemonAPI;
import com.example.roma.android_challenge.core.api.models.NamedAPIResourceModelRest;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.example.roma.android_challenge.core.api.models.PokemonsModelRest;

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
    private CompositeDisposable disposables = new CompositeDisposable();

    public PokemonListInteractorImpl(PokemonListContract.Presenter presenter) {
        this.presenter = presenter;
        api = new PokemonAPI();
    }

    public Disposable getPokemons(int page, final PokemonsListener pokemonsListener) {
        Log.d("TESTE", String.format("%s - %s", String.valueOf(page), String.valueOf( (page-1)*api.PAGE_SIZE) ));
        return api.service.pokemons(api.PAGE_SIZE, (page-1) * api.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PokemonsModelRest>() {
                    @Override
                    public void accept(PokemonsModelRest pokemonsModelRest) throws Exception {
                        ArrayList<PokemonModelRest> mItems = new ArrayList<>();
                        if(pokemonsModelRest != null && pokemonsModelRest.results != null){

                            for (NamedAPIResourceModelRest item : pokemonsModelRest.results) {
                                PokemonModelRest mItem = new PokemonModelRest();
                                mItem.name = item.name;
                                mItems.add(mItem);
                            }
                            pokemonsListener.callback(mItems);

                            for (NamedAPIResourceModelRest item : pokemonsModelRest.results) {
                                loadPokemon(item.name);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TESTE getPokemons", throwable.getMessage());
                        if(throwable instanceof SocketException || throwable instanceof SocketTimeoutException) {
                            presenter.loadNetworkError();
                        } else {
                            presenter.loadError(throwable.getMessage().toString());
                        }
                    }
                });
    }

    @Override
    public void load(int page) {
        disposables.add(
                getPokemons(page, new PokemonsListener() {
                    @Override
                    public void callback(ArrayList<PokemonModelRest> items) {
                        presenter.build(items);
                    }
                })
        );
    }

    @Override
    public void loadPokemon(final String id) {
        disposables.add(
            api.service.pokemon(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PokemonModelRest>() {
                    @Override
                    public void accept(PokemonModelRest pokemonModelRest) throws Exception {
                        presenter.buildPokemon(pokemonModelRest);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(throwable instanceof SocketException || throwable instanceof SocketTimeoutException) {
                            presenter.loadNetworkError();
                        } else {
                            presenter.errorLoadPokemon(throwable.getMessage().toString());
                        }
                    }
                })
        );
    }

    @Override
    public void morePage(int page) {
        disposables.add(
                getPokemons(page, new PokemonsListener() {
                    @Override
                    public void callback(ArrayList<PokemonModelRest> items) {
                        presenter.morePageBuild(items);
                    }
                })
        );
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }

    interface PokemonsListener {
        void callback(ArrayList<PokemonModelRest> items);
    }
}
