package ru.nsu.udod.nasaphoto;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod/";
    private static Retrofit mRetrofit;
    private static RetrofitClientInstance mInstance;

    private RetrofitClientInstance() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClientInstance getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClientInstance();
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
