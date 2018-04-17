package com.example.roma.android_challenge.pokemon_detail;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roma.android_challenge.R;
import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.example.roma.android_challenge.core.utils.BaseActivity;

public class PokemonDetailActivity extends BaseActivity implements PokemonDetailContract.View {

    public static final String ITEM = "ITEM";
    private PokemonDetailPresenterImpl presenter;
    private PokemonModelRest item;
    private TextView tvPokemonName;
    private ImageView ivPokemonFeatureImg;
    private ImageView ivPokemonSpriteFrontDefautl;
    private ImageView ivPokemonSpriteBackDefautl;
    private TextView tvPokemonType;
    private ImageView ivPokemonFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_detail);

        presenter = new PokemonDetailPresenterImpl(this);

        item = (PokemonModelRest) getIntent().getSerializableExtra(ITEM);

        ivPokemonFeatureImg = findViewById(R.id.iv_pokemon_feature_img);
        ivPokemonSpriteFrontDefautl = findViewById(R.id.iv_pokemon_sprite_front_defautl);
        ivPokemonSpriteBackDefautl = findViewById(R.id.iv_pokemon_sprite_back_defautl);

        ivPokemonFavorite = findViewById(R.id.iv_pokemon_favorite);

        tvPokemonName = findViewById(R.id.tv_pokemon_name);
        tvPokemonType = findViewById(R.id.tv_pokemon_type);

        build();
    }

    private void build() {
        tvPokemonName.setText(item.name);

        if(item.types != null && item.types.get(0).type != null)
            tvPokemonType.setText(item.types.get(0).type.name);

        if(presenter.getFavoritePokemon(item.id) != null) {
            ivPokemonFavorite.setImageDrawable(ContextCompat.getDrawable(PokemonDetailActivity.this, android.R.drawable.btn_star_big_on));
        } else {
            ivPokemonFavorite.setImageDrawable(ContextCompat.getDrawable(PokemonDetailActivity.this, android.R.drawable.btn_star_big_off));
        }

        ivPokemonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(presenter.getFavoritePokemon(item.id) != null) {
                    ivPokemonFavorite.setImageDrawable(ContextCompat.getDrawable(PokemonDetailActivity.this, android.R.drawable.btn_star_big_off));
                    presenter.deleteFavoritePokemon(item.id);
                } else {
                    ivPokemonFavorite.setImageDrawable(ContextCompat.getDrawable(PokemonDetailActivity.this, android.R.drawable.btn_star_big_on));
                    presenter.setfavoritePokemon(item);
                }
            }
        });

        Glide.with(this)
                .load(String.format("http://www.pkparaiso.com/imagenes/xy/sprites/animados/%s.gif", item.name))
                .into(ivPokemonFeatureImg);

        Glide.with(this).load(item.sprites.front_default).into(ivPokemonSpriteFrontDefautl);
        Glide.with(this).load(item.sprites.back_default).into(ivPokemonSpriteBackDefautl);

    }
}
