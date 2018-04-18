package com.example.roma.android_challenge.pokemon_favorite;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.roma.android_challenge.R;
import com.example.roma.android_challenge.core.ScreenManager;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.example.roma.android_challenge.core.utils.BaseActivity;
import com.example.roma.android_challenge.pokemon_list.adapter.PokemonAdapter;

import java.util.ArrayList;

public class PokemonFavoriteActivity extends BaseActivity implements PokemonFavoriteContract.View {

    private PokemonFavoritePresenterImpl presenter;
    private RecyclerView rvPokemon;
    private View defaultLoader;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Snackbar snackbar;
    private ArrayList<PokemonModelRest> items;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(items != null)
            outState.putSerializable("ITEMS", items);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        getSupportActionBar().setTitle("Favoritos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout = findViewById(R.id.srl_repository);
        rvPokemon = findViewById(R.id.rv_repository);
        defaultLoader = findViewById(R.id.default_loader);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.load();
            }
        });

        rvPokemon.setLayoutManager(new LinearLayoutManager(this));
        final PokemonAdapter mAdapter = new PokemonAdapter(null, this, new PokemonAdapter.Listener() {
            @Override
            public void onClickListener(PokemonModelRest item, View view) {
                if(item.id != null) {
                    ScreenManager.gotoPokemonDetail(PokemonFavoriteActivity.this, item, view);
                } else {
                    Toast.makeText(PokemonFavoriteActivity.this, "Aguarde o carregamento", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rvPokemon.setAdapter(mAdapter);

        presenter = new PokemonFavoritePresenterImpl(PokemonFavoriteActivity.this);

        if (savedInstanceState != null) {
            ArrayList<PokemonModelRest> items = (ArrayList<PokemonModelRest>) savedInstanceState.getSerializable("ITEMS");
            if(items != null) {
                this.items = items;

                ((PokemonAdapter) rvPokemon.getAdapter()).setItems(items);
                rvPokemon.getAdapter().notifyDataSetChanged();
            }
        } else {
            presenter.load();
        }

    }

    @Override
    public void showLoading() {
        defaultLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        defaultLoader.setVisibility(View.GONE);
    }

    @Override
    public void loadError(String message) {
        snackbar = Snackbar.make(rvPokemon, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Tentar novamente", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.load();
            }
        });
        snackbar.show();
    }

    public void build(ArrayList<PokemonModelRest> items) {
        this.items = items;
        ((PokemonAdapter) rvPokemon.getAdapter()).setItems(items);
        rvPokemon.getAdapter().notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
