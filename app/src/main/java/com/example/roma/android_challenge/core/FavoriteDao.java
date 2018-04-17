package com.example.roma.android_challenge.core;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDao {

    private static FavoriteDao sharedInstance;
    Gson gson = new Gson();

    public static FavoriteDao sharedInstance() {
        if(sharedInstance == null) {
            sharedInstance = new FavoriteDao();
        }
        return sharedInstance;
    }

    public ArrayList<PokemonModelRest> getFavorites() {
        List<Favorite> items = new Select().from(Favorite.class).execute();
        ArrayList<PokemonModelRest> mItems = new ArrayList<>();
        if(items != null) {
            for (Favorite item: items) {
                mItems.add(gson.fromJson(item.pokemon, PokemonModelRest.class));
            }
            return mItems;
        }
        return null;
    }

    public PokemonModelRest getFavorite(int id) {

        Favorite item = new Select()
                .from(Favorite.class)
                .where("mId = ?", id)
                .executeSingle();
        if(item != null)
            return gson.fromJson(item.pokemon, PokemonModelRest.class);
        else {
            return null;
        }
    }

    public boolean addFavorite(PokemonModelRest item) {
        if(getFavorite(item.id) == null) {
            Favorite favorite = new Favorite();
            favorite.pokemon = gson.toJson(item);
            favorite.mId  = item.id;
            favorite.save();
            return true;
        }
        return false;

    }

    public void deleteFavorite(int id) {
        new Delete().from(Favorite.class).where("mId = ?", id).execute();
    }
}
