package ru.nsu.udod.nasaphoto;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.paging.PageKeyedDataSource;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Collections.*;

public class NasaPhotoDataSource extends PageKeyedDataSource<LocalDate, NasaPhotoInfoDto> {
    private final String KEY = "U9sSbeHD05Dgnrcd5bnTnFpxUw7efqC7Mgg4A0oM";
    private NasaApi api;
    private final int COUNT = 5;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void loadInitial(@NonNull LoadInitialParams<LocalDate> params,
                            @NonNull LoadInitialCallback<LocalDate, NasaPhotoInfoDto> callback) {
        LocalDate now = LocalDate.now();
        api = RetrofitClientInstance.getInstance().getRetrofit().create(NasaApi.class);
        Call<List<NasaPhotoInfoDto>> data = api.getPhotoWithDates(
                now.minusDays(1 + COUNT).toString(),
                now.minusDays(1).toString(),
                KEY
        );

        data.enqueue(new Callback<List<NasaPhotoInfoDto>>() {
            @Override
            public void onResponse(Call<List<NasaPhotoInfoDto>> call,
                                   Response<List<NasaPhotoInfoDto>> response) {
                List<NasaPhotoInfoDto> photosList = response.body();
                if (photosList != null) {
                    reverse(photosList);
                    for (Iterator<NasaPhotoInfoDto> iterator = photosList.iterator(); iterator.hasNext(); ) {
                        if (!iterator.next().getMedia_type().equals("image")) {
                            iterator.remove();
                        }
                    }
                    callback.onResult(photosList, null, now.minusDays(6));
                }
            }

            @Override
            public void onFailure(Call<List<NasaPhotoInfoDto>> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<LocalDate> params,
                           @NonNull LoadCallback<LocalDate, NasaPhotoInfoDto> callback) {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void loadAfter(@NonNull LoadParams<LocalDate> params,
                          @NonNull LoadCallback<LocalDate, NasaPhotoInfoDto> callback) {
        LocalDate nextDate = params.key;
        Call<List<NasaPhotoInfoDto>> data = api.getPhotoWithDates(
                nextDate.minusDays(1 + COUNT).toString(),
                nextDate.minusDays(1).toString(),
                KEY
        );

        data.enqueue(new Callback<List<NasaPhotoInfoDto>>() {
            @Override
            public void onResponse(Call<List<NasaPhotoInfoDto>> call,
                                   Response<List<NasaPhotoInfoDto>> response) {
                List<NasaPhotoInfoDto> photosList = response.body();
                if (photosList != null) {
                    reverse(photosList);
                    for (Iterator<NasaPhotoInfoDto> iterator = photosList.iterator(); iterator.hasNext(); ) {
                        if (!iterator.next().getMedia_type().equals("image")) {
                            iterator.remove();
                        }
                    }
                    callback.onResult(photosList, nextDate.minusDays(1 + COUNT));
                }
            }

            @Override
            public void onFailure(Call<List<NasaPhotoInfoDto>> call, Throwable t) {
            }
        });
    }
}
