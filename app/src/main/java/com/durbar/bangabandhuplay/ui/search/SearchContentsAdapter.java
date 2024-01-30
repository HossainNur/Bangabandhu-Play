package com.durbar.bangabandhuplay.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.databinding.SearchResultLayoutBinding;
import com.durbar.bangabandhuplay.ui.player.PlayerActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.durbar.bangabandhuplay.data.model.search_content.Data;
import com.squareup.picasso.Picasso;
import java.util.List;

public class SearchContentsAdapter extends RecyclerView.Adapter<SearchContentsAdapter.mViewHolder> {

    private List<Data> dataList;
    private Context context;
    public SearchContentsAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }
    @NonNull
    @Override
    public SearchContentsAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchContentsAdapter.mViewHolder(SearchResultLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchContentsAdapter.mViewHolder holder, int position) {
        Data current = dataList.get(position);
        holder.binding.searchImage.setClipToOutline(true);
        String image = current.getThumbnailLandscape();
        String title = current.getTitle();
        String uuid = current.getUuid();
        if (image != null){
            Picasso.get().load(image).into(holder.binding.searchImage);
        }

        if (title != null){
            holder.binding.searchContentName.setText(title);
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            if (uuid != null && !uuid.isEmpty()) context.startActivity(new Intent(context, PlayerActivity.class).putExtra(Constants.CONTENT_UUID,uuid).putExtra(Constants.CONTENT_SECTION_TITLE,title));
        });

    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void clearListItem(){
        if (dataList != null){
            dataList.clear();
            notifyDataSetChanged();
        }
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        SearchResultLayoutBinding binding;
        public mViewHolder(@NonNull SearchResultLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
