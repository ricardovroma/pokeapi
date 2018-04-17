package com.example.roma.android_challenge.core;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;


@Table(name = "Favorites")
public class Favorite extends Model {
    @Column(name = "mId")
    public Integer mId;

    @Column(name = "pokemon")
    public String pokemon;
}