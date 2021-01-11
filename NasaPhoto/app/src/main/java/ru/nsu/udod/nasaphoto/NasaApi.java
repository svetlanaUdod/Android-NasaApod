package ru.nsu.udod.nasaphoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaApi {
    @GET(".")
    public Call<List<NasaPhotoInfoDto>> getPhotoCount(@Query("count") int count,
                                                      @Query("api_key") String key);

    @GET(".")
    public Call<List<NasaPhotoInfoDto>> getPhotoWithDates(@Query("start_date") String start,
                                                          @Query("end_date") String end,
                                                          @Query("api_key") String key);
}
