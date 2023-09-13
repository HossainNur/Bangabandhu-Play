package com.example.bangabandhuplay.ui.movies;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bangabandhuplay.data.model.category.root.single.OttContent;
import com.example.bangabandhuplay.databinding.ContentLayoutBinding;
import com.squareup.picasso.Picasso;
import java.util.List;


public class MoviesChildContentAdapter extends RecyclerView.Adapter<MoviesChildContentAdapter.ChildViewHolder> {

    private List<OttContent> contentList;

    public MoviesChildContentAdapter(List<OttContent> contentList) {
        this.contentList = contentList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ChildViewHolder(ContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        OttContent current = contentList.get(position);

        String image = current.getPoster();

        holder.binding.mainProductCardThumbnailIv.setClipToOutline(true);

        if (image != null){
            Picasso.get().load(image).into(holder.binding.mainProductCardThumbnailIv);
        }

        holder.binding.getRoot().setOnClickListener(view -> {
        });
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
