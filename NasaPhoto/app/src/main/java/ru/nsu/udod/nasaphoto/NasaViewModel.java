package ru.nsu.udod.nasaphoto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NasaViewModel extends ViewModel {
    private static NasaPhotoDataSource datasource;
    private static LiveData<PagedList<NasaPhotoInfoDto>> pagedListLiveData;

    static {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(5)
                .setInitialLoadSizeHint(10)
                .setPrefetchDistance(4)
                .build();

        Executor executor = Executors.newFixedThreadPool(5);
        LivePagedListBuilder<LocalDate, NasaPhotoInfoDto> builder = new LivePagedListBuilder<>(
                new DataSource.Factory<LocalDate, NasaPhotoInfoDto>() {
                    @Override
                    public DataSource<LocalDate, NasaPhotoInfoDto> create() {
                        datasource = new NasaPhotoDataSource();
                        return datasource;
                    }
                },
                config
        );
        pagedListLiveData = builder
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<NasaPhotoInfoDto>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
