package com.durbar.bangabandhuplay.ui.home;

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
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.FrontendCustomContent;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.OttContent;

import com.durbar.bangabandhuplay.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<FrontendCustomContent> frontendCustomContentList;
    private Context context;
    private String title;

    public ChildItemAdapter(List<FrontendCustomContent> frontendCustomContentList, Context context, String title) {
        this.frontendCustomContentList = frontendCustomContentList;
        this.context = context;
        this.title = title;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ChildViewHolder(ContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {

        OttContent current = frontendCustomContentList.get(position).getOttContent();
        holder.binding.mainProductCardThumbnailIv.setClipToOutline(true);

        if (frontendCustomContentList.get(position).getOttContent() != null){
            String image = current.getThumbnailPortrait();
            String uuid = current.getUuid();

            if (image != null) {
                Picasso.get().load(image).into(holder.binding.mainProductCardThumbnailIv);
            }

            holder.binding.getRoot().setOnClickListener(view -> {
                if (uuid != null && !uuid.isEmpty())
                    context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID, uuid).putExtra(Constants.CONTENT_SECTION_TITLE, title));
            });

            holder.binding.contentImage.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.slide_up_animation));
        }


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

}

