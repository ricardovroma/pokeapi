package com.example.roma.android_challenge.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.example.roma.android_challenge.pokemon_detail.PokemonDetailActivity;
import com.example.roma.android_challenge.pokemon_list.PokemonListActivity;

public class ScreenManager {
    public static void gotoPokemonDetail(Activity activity, PokemonModelRest item, View animatedView) {
        Intent it = new Intent(activity, PokemonDetailActivity.class);
        it.putExtra(PokemonDetailActivity.ITEM, item);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, animatedView, "img");
        activity.startActivity(it, options.toBundle());
    }

    public static void gotoPokemonList(Context context) {
        Intent it = new Intent(context, PokemonListActivity.class);
        context.startActivity(it);
    }

    public static void gotoPokemonFavorite(Context context) {
//        Intent it = new Intent(context, PokemonFavoriteActivity.class);
//        context.startActivity(it);
    }
}
