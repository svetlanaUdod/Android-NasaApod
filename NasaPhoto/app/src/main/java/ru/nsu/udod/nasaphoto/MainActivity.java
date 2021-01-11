package ru.nsu.udod.nasaphoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import ru.nsu.udod.nasaphoto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private NasaViewModel viewModel;
    private NasaAdapter adapter;
    private ActivityMainBinding binding;
    private final String KEY_LAYOUT = "state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        getSupportActionBar().hide();
        setContentView(view);

        adapter = new NasaAdapter(this);
        viewModel = (new ViewModelProvider.NewInstanceFactory()).create(NasaViewModel.class);
        viewModel.getPagedListLiveData().observe(this, adapter::submitList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.list.setLayoutManager(layoutManager);
        binding.list.setAdapter(adapter);
    }

}