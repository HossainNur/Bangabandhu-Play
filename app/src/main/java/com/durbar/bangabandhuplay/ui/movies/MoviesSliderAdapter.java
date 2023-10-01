package com.durbar.bangabandhuplay.ui.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.databinding.MoviesSliderLayoutBinding;
import com.durbar.bangabandhuplay.ui.player.PlayerActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.squareup.picasso.Picasso;


import java.util.List;

public class MoviesSliderAdapter extends RecyclerView.Adapter<MoviesSliderAdapter.mViewHolder> {
    private final List<Original> images;
    private Context context;

    public MoviesSliderAdapter(List<Original> images,Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesSliderAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesSliderAdapter.mViewHolder(MoviesSliderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesSliderAdapter.mViewHolder holder, int position) {
        Original current = images.get(position);
        String title = current.getTitle();
        String image = current.getImage();
        String description = current.getDescription().toString();
        String uuid = current.getContentUrl();
        if (image != null) Picasso.get().load(current.getImage()).into(holder.binding.moviesSliderImage);
        if (title != null) holder.binding.sliderTitle.setText(title);
        if (description != null) holder.binding.sliderDesc.setText(description);
        holder.binding.playContent.setOnClickListener(v -> {
            if (uuid != null && !uuid.isEmpty()) context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID, uuid));
        });
    }

    @Override
    public int getItemCount() {
        return images != null ? images.size(): 0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        MoviesSliderLayoutBinding binding;
        public mViewHolder(@NonNull MoviesSliderLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
