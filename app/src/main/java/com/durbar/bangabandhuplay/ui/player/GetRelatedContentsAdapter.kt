package com.durbar.bangabandhuplay.ui.player;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.databinding.ContentLayoutBinding;
import com.durbar.bangabandhuplay.utils.Constants;
import com.durbar.bangabandhuplay.data.model.get_related_contents.SingleContentRelatedContent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GetRelatedContentsAdapter extends RecyclerView.Adapter<GetRelatedContentsAdapter.mViewHolder> {
    private List<SingleContentRelatedContent> singleContentRelatedContentList;
    private Context context;
    private String title;
    private boolean isMore;

    public GetRelatedContentsAdapter(List<SingleContentRelatedContent> singleContentRelatedContentList, Context context,String title) {
        this.singleContentRelatedContentList = singleContentRelatedContentList;
        this.context = context;
        this.title = title;
    }

    @NonNull
    @Override
    public GetRelatedContentsAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewHolder(ContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetRelatedContentsAdapter.mViewHolder holder, int position) {

        SingleContentRelatedContent current = singleContentRelatedContentList.get(position);
        holder.binding.mainProductCardThumbnailIv.setClipToOutline(true);
        String image = current.getThumbnailPortrait();
        String uuid = current.getUuid();
        if (image != null) Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv);

        holder.binding.getRoot().setOnClickListener(view -> {
            if (uuid != null && !uuid.isEmpty()) context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID, uuid).putExtra(Constants.CONTENT_SECTION_TITLE,title));
        });
    }

    @Override
    public int getItemCount() {
        return singleContentRelatedContentList != null ? singleContentRelatedContentList.size(): 0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        ContentLayoutBinding binding;
        public mViewHolder(@NonNull ContentLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
