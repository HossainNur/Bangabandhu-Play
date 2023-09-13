package com.example.bangabandhuplay.ui.more;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.Original;
import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.OttContent;
import com.example.bangabandhuplay.databinding.ContentLayoutBinding;
import com.example.bangabandhuplay.databinding.MoreContentLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoreHomeAdapter extends RecyclerView.Adapter<MoreHomeAdapter.mViewHolder> {

    private final String TAG = "WatchTrailerAdapter";
    private List<Original> originals;
    private Context context;

    public MoreHomeAdapter(List<Original> originals, Context context) {
        this.originals = originals;
        this.context = context;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewHolder(MoreContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        OttContent current = originals.get(position).getOttContent();

        holder.binding.mainProductCardThumbnailIv.setClipToOutline(true);
        //image
        String image = current.getPoster();
        if (image != null) {
            Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv);
        }
    }

    @Override
    public int getItemCount() {

        return originals == null ? 0 : originals.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        MoreContentLayoutBinding binding;

        public mViewHolder(@NonNull MoreContentLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
}
