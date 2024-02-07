package com.durbar.bangabandhuplay.ui.more;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.MoreContentLayoutBinding;
import com.durbar.bangabandhuplay.ui.player.PlayerActivity;
import com.durbar.bangabandhuplay.data.model.category.single_sub.OttContent;
import com.durbar.bangabandhuplay.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoreOthersAdapter extends RecyclerView.Adapter<MoreOthersAdapter.mViewHolder> {
    private List<OttContent> ottContentList;
    private Context context;
    private String title;
    private boolean isMore;
    public MoreOthersAdapter(List<OttContent> ottContentList, Context context,String title,boolean isMore) {
        this.ottContentList = ottContentList;
        this.context = context;
        this.title = title;
        this.isMore = isMore;
    }

    @NonNull
    @Override
    public MoreOthersAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoreOthersAdapter.mViewHolder(MoreContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoreOthersAdapter.mViewHolder holder, int position) {
        OttContent current = ottContentList.get(position);

        holder.binding.mainProductCardThumbnailIv.setClipToOutline(true);
        if ( ottContentList.get(position) != null){
            //image
            String image = current.getThumbnailPortrait();
            String uuid = current.getUuid();
            if (image != null) {
                Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv);
            }
            holder.binding.getRoot().setOnClickListener(v -> {
                if (uuid != null && !uuid.isEmpty()) context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID,uuid).putExtra(Constants.CONTENT_SECTION_TITLE,title).putExtra(Constants.CONTENT_IS_MORE,true));
                Constants.IS_MORE_CONTENT = true;
                Constants.IS_MORE_HOME = false;
            });

            holder.binding.mainProductCardThumbnailIv.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fall_down));
        }

    }

    @Override
    public int getItemCount() {
        return ottContentList == null ? 0 : ottContentList.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        MoreContentLayoutBinding binding;
        public mViewHolder(@NonNull MoreContentLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}