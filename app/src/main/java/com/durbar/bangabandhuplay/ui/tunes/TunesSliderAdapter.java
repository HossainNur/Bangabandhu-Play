package com.durbar.bangabandhuplay.ui.tunes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.data.model.category.root.single.CategorySlider;
import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.databinding.CategoriesSliderLayoutBinding;
import com.durbar.bangabandhuplay.databinding.MoviesSliderLayoutBinding;
import com.durbar.bangabandhuplay.ui.movies.MoviesSliderAdapter;
import com.durbar.bangabandhuplay.ui.player.PlayerActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TunesSliderAdapter extends RecyclerView.Adapter<TunesSliderAdapter.mViewHolder>{

    private  List<CategorySlider> categorySliderList;
    private Context context;

    public TunesSliderAdapter(List<CategorySlider> categorySliderList, Context context) {
        this.categorySliderList = categorySliderList;
        this.context = context;
    }

    @NonNull
    @Override
    public TunesSliderAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TunesSliderAdapter.mViewHolder(CategoriesSliderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TunesSliderAdapter.mViewHolder holder, int position) {

        CategorySlider current = categorySliderList.get(position);
        if (categorySliderList.get(position) != null){
            String title = current.getTitle();
            String image = current.getLandscapeImage();
            String description = current.getDescription().toString();
            String uuid = current.getContentUrl();
            if (image != null) Picasso.get().load(current.getImage()).into(holder.binding.categoriesSliderImage);
            if (title != null) holder.binding.sliderTitle.setText(title);
            if (description != null) holder.binding.sliderDesc.setText(description);
            holder.binding.getRoot().setOnClickListener(v -> {
                if (uuid != null && !uuid.isEmpty()) context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID, uuid).putExtra(Constants.CONTENT_SECTION_TITLE,"Slider"));
            });
        }

    }

    @Override
    public int getItemCount() {
       return categorySliderList != null ? categorySliderList.size(): 0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {

        CategoriesSliderLayoutBinding binding;
        public mViewHolder(@NonNull CategoriesSliderLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
