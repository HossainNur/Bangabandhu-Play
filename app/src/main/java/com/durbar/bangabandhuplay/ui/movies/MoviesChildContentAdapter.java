package com.durbar.bangabandhuplay.ui.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.ContentLayoutBinding;
import com.durbar.bangabandhuplay.ui.player.PlayerActivity;
import com.durbar.bangabandhuplay.data.model.category.root.single.OttContent;
import com.durbar.bangabandhuplay.utils.Constants;
import com.squareup.picasso.Picasso;
import java.util.List;


public class MoviesChildContentAdapter extends RecyclerView.Adapter<MoviesChildContentAdapter.ChildViewHolder> {

    private List<OttContent> contentList;
    private Context context;

    public MoviesChildContentAdapter(List<OttContent> contentList,Context context) {
        this.contentList = contentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ChildViewHolder(ContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        OttContent current = contentList.get(position);

        String image = current.getThumbnailPortrait();
        String uuid = current.getUuid();

        holder.binding.mainProductCardThumbnailIv.setClipToOutline(true);

        if (image != null){
            Picasso.get().load(image).into(holder.binding.mainProductCardThumbnailIv);
        }

        holder.binding.getRoot().setOnClickListener(view -> {
            if (uuid != null && !uuid.isEmpty()) context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID,uuid));
        });

        holder.binding.contentImage.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.slide_up_animation));
    }

    @Override
    public int getItemCount() {
        return contentList != null ? contentList.size() : 0;
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        ContentLayoutBinding binding;

        ChildViewHolder(ContentLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

}
