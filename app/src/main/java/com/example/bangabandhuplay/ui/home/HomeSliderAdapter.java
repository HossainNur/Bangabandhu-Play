package com.example.bangabandhuplay.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bangabandhuplay.R;
import com.example.bangabandhuplay.data.model.sliders.Original;
import com.example.bangabandhuplay.databinding.HomeSliderLayoutBinding;
import com.squareup.picasso.Picasso;
import java.util.List;

public class HomeSliderAdapter extends RecyclerView.Adapter<HomeSliderAdapter.mViewHolder> {

    private final List<Original> images;
    public HomeSliderAdapter(List<Original> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewHolder(HomeSliderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSliderAdapter.mViewHolder holder, int position) {
        Original current = images.get(position);
        Picasso.get().load(current.getImage()).resizeDimen(R.dimen.slider_image_width, R.dimen.slider_image_height).into(holder.binding.sliderThumbnailIv);
    }

    @Override
    public int getItemCount() {
        return images != null ? images.size(): 0;
    }

    static class mViewHolder extends RecyclerView.ViewHolder{
        HomeSliderLayoutBinding binding;
        public mViewHolder(@NonNull HomeSliderLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
