package ru.nsu.udod.nasaphoto;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class NasaViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView date;
    private final ImageView img;
    private NasaPhotoInfoDto photoInfo;
    public View rootView;

    public NasaViewHolder(@NonNull View itemView) {
        super(itemView);
        rootView = itemView;
        title = itemView.findViewById(R.id.title);
        date = itemView.findViewById(R.id.date);
        img = itemView.findViewById(R.id.photo);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoActivity.start(
                        itemView.getContext(),
                        photoInfo.getTitle(),
                        photoInfo.getDate(),
                        photoInfo.getExplanation(),
                        photoInfo.getCopyright(),
                        photoInfo.getUrl()
                );
            }
        });
    }

    public void bindTo(NasaPhotoInfoDto answer) {
        photoInfo = answer;
        title.setText(answer.getTitle());
        date.setText(answer.getDate());
        Picasso.get().load(answer.getUrl()).placeholder(R.drawable.progress_animation).into(img);
    }

    public void clear() {
        title.setText("");
        date.setText("");
    }
}
