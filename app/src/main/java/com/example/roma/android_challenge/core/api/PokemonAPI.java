package com.example.roma.android_challenge.core.api;

import android.util.Log;

import com.example.roma.android_challenge.core.api.models.PokemonModelRest;
import com.example.roma.android_challenge.core.api.models.PokemonsModelRest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class PokemonAPI {

    public static final int PAGE_SIZE = 10;
    private static final String TAG = "TESTE";
    public final API service;
    private final String baseUrl  = "https://pokeapi.co/api/v2/";

    public PokemonAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.client(getOkHttpClient())
                .build();

        this.service = retrofit.create(API.class);
    }

    public interface API {

        @GET("pokemon/")
        Flowable<PokemonsModelRest> pokemons(@Query("limit") int limit, @Query("offset") int offset);

        @GET("pokemon/{id}")
        Flowable<PokemonModelRest> pokemon(@Path("id") String id);

    }

    /* caso no futuro precise interceptar o request, passar um token e etc...*/
    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(50, TimeUnit.SECONDS);
        client.connectTimeout(10, TimeUnit.SECONDS);
        client.addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Request customization: add request headers
                        Response response;
                        Request.Builder requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());

                        Log.d(TAG, "URL: " + String.valueOf(original.url()));

                        Request request = requestBuilder.build();
                        response = chain.proceed(request);

                        return response;
                    }
                }
        );
        return client.build();
    }
}
