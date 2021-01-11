package ru.nsu.udod.nasaphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;

import ru.nsu.udod.nasaphoto.databinding.ActivityMainBinding;
import ru.nsu.udod.nasaphoto.databinding.ActivityPhotoBinding;

import static java.util.Objects.requireNonNull;

public class PhotoActivity extends AppCompatActivity {
    private final static String TITLE = "title";
    private final static String DATE = "date";
    private final static String EXPLANATION = "explanation";
    private final static String COPYRIGHT = "copyright";
    private final static String URL = "url";
    private ActivityPhotoBinding binding;

    public static void start(final Context context, String title, String date,
                             String explanation, String copyright, String url) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(DATE, date);
        intent.putExtra(EXPLANATION, explanation);
        intent.putExtra(COPYRIGHT, copyright);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        getSupportActionBar().hide();
        setContentView(view);

        String title = requireNonNull(getIntent().getExtras()).getString(TITLE);
        String date = requireNonNull(getIntent().getExtras()).getString(DATE);
        String exp = requireNonNull(getIntent().getExtras()).getString(EXPLANATION);
        String copyright = requireNonNull(getIntent().getExtras()).getString(COPYRIGHT);
        String url = requireNonNull(getIntent().getExtras()).getString(URL);

        binding.photoTitle.setText(title);
        binding.photoDate.setText(date);
        binding.photoExplanation.setText(exp);
        binding.photoCopyright.setText(copyright);
        Picasso.get().load(url).into(binding.photoPhoto);
    }
}