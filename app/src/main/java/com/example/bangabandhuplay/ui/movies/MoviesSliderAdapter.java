package com.example.bangabandhuplay.ui.movies;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bangabandhuplay.data.model.sliders.Original;
import com.example.bangabandhuplay.databinding.MoviesSliderLayoutBinding;
import com.squareup.picasso.Picasso;


import java.util.List;

public class MoviesSliderAdapter extends RecyclerView.Adapter<MoviesSliderAdapter.mViewHolder> {
    private final List<Original> images;

    public MoviesSliderAdapter(List<Original> images) {
        this.images = images;
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
        String description = current.getDescription().toString();
        Picasso.get().load(current.getImage()).into(holder.binding.moviesSliderImage);
        if (title != null) holder.binding.sliderTitle.setText(title);
        if (description != null) holder.binding.sliderDesc.setText(description);
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
