package com.example.roma.android_challenge.core.api.models;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonModelRest implements Serializable {
    public Integer id;
    public String name;
    public ArrayList<PokemonMovesModelRest> moves;
    public PokemonSpritesModelRest sprites;
    public ArrayList<PokemonTypeModelRest> types;
    public ArrayList<PokemonAbilityModelRest> abilities;
    public ArrayList<PokemonStatModelRest> stats;

}
