package ru.nsu.udod.nasaphoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;

public class NasaAdapter extends PagedListAdapter<NasaPhotoInfoDto, NasaViewHolder> {
    private Context context;

    public static final ItemCallback<NasaPhotoInfoDto> CALLBACK = new ItemCallback<NasaPhotoInfoDto>() {
        @Override
        public boolean areItemsTheSame(@NonNull NasaPhotoInfoDto oldItem,
                                       @NonNull NasaPhotoInfoDto newItem) {
            return oldItem.getDate().equals(newItem.getDate());
        }

        @Override
        public boolean areContentsTheSame(@NonNull NasaPhotoInfoDto oldItem,
                                          @NonNull NasaPhotoInfoDto newItem) {
            return true;
        }
    };

    protected NasaAdapter(Context context) {
        super(CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public NasaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.photo, parent, false);
        return new NasaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NasaViewHolder holder, int position) {
        NasaPhotoInfoDto answer = getItem(position);
        if (answer != null) {
            holder.bindTo(answer);
            setAnimation(holder.rootView, position);
        } else {
            holder.clear();
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        final Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);
    }
}
