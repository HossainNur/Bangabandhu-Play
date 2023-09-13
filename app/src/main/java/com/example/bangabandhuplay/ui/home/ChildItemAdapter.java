package com.example.bangabandhuplay.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_contents.FrontendCustomContent;
import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_contents.OttContent;
import com.example.bangabandhuplay.databinding.ContentLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<FrontendCustomContent> frontendCustomContentList;
    public ChildItemAdapter(List<FrontendCustomContent> frontendCustomContentList) {
        this.frontendCustomContentList = frontendCustomContentList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ChildViewHolder(ContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {

        holder.binding.mainProductCardThumbnailIv.setClipToOutline(true);

        OttContent current = frontendCustomContentList.get(position).getOttContent();

        String image = current.getThumbnailPortrait();

        if (image != null){
            Picasso.get().load(image).into(holder.binding.mainProductCardThumbnailIv);
        }

        holder.binding.getRoot().setOnClickListener(view -> {
        });
    }

    @Override
    public int getItemCount() {
        return frontendCustomContentList != null ? frontendCustomContentList.size() : 0;
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        ContentLayoutBinding binding;

        ChildViewHolder(ContentLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface CallBack{
        void addWishListItem(String uuid);
    }
}

