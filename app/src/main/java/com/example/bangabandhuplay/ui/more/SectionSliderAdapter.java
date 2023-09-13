package com.example.bangabandhuplay.ui.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider.SectionSliders;
import com.example.bangabandhuplay.databinding.ContentLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SectionSliderAdapter extends RecyclerView.Adapter<SectionSliderAdapter.mViewHolder> {
    private List<SectionSliders> sliders;
    private Context context;

    public SectionSliderAdapter(List<SectionSliders> sliders,Context context) {
        this.sliders = sliders;
        this.context = context;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewHolder(ContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        SectionSliders current = sliders.get(position);
        String image = current.getImage();
        if (image != null){
            Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv);
        }

    }

    @Override
    public int getItemCount() {
        return sliders != null ? sliders.size():0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ContentLayoutBinding binding;
        public mViewHolder(@NonNull ContentLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
