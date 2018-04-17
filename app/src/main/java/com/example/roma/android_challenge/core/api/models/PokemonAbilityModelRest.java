package com.example.roma.android_challenge.core.api.models;

import java.io.Serializable;

public class PokemonAbilityModelRest implements Serializable {
    public boolean is_hidden;
    public Integer slot;
    public NamedAPIResourceModelRest ability;
}
