package com.durbar.bangabandhuplay.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.databinding.HomeSliderLayoutBinding;
import com.durbar.bangabandhuplay.ui.player.PlayerActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.squareup.picasso.Picasso;
import java.util.List;

public class HomeSliderAdapter extends RecyclerView.Adapter<HomeSliderAdapter.mViewHolder> {

    private final List<Original> images;
    private final Context context;
    public HomeSliderAdapter(List<Original> images,Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewHolder(HomeSliderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSliderAdapter.mViewHolder holder, int position) {
        Original current = images.get(position);
        String image = current.getImage();
        String uuid = current.getContentUrl();
        String title = current.getTitle();
        if (image != null)  Picasso.get().load(current.getImage()).resizeDimen(R.dimen.slider_image_width, R.dimen.slider_image_height).into(holder.binding.sliderThumbnailIv);
        holder.binding.getRoot().setOnClickListener(v -> {
            if (uuid != null && !uuid.isEmpty()) context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID, uuid).putExtra(Constants.CONTENT_SECTION_TITLE,"Home"));
        });


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
