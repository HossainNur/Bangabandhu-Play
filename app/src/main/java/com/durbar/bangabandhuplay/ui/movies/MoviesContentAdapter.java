package com.durbar.bangabandhuplay.ui.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.data.model.category.root.single.SubCategory;
import com.durbar.bangabandhuplay.data.model.category.root.single.OttContent;
import com.durbar.bangabandhuplay.databinding.SectionRecyclerViewLayoutBinding;
import com.durbar.bangabandhuplay.ui.more.MoreActivity;
import com.durbar.bangabandhuplay.utils.Constants;

import java.util.List;

public class MoviesContentAdapter extends RecyclerView.Adapter<MoviesContentAdapter.ParentViewHolder> {
    private List<SubCategory> subCategories;
    private Context context;
    private boolean isTunes;


    public MoviesContentAdapter(List<SubCategory> subCategories, Context context,boolean isTunes) {
        this.subCategories = subCategories;
        this.context = context;
        this.isTunes = isTunes;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewHolder(SectionRecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {

        SubCategory current = subCategories.get(position);

        if (subCategories.get(position) != null){
            String title = current.getTitle();
            String slug = current.getSlug();

            List<OttContent> ottContents = current.getOttContents();

            holder.binding.itemRv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            holder.binding.itemRv.setAdapter(new MoviesChildContentAdapter(ottContents, context,title));

            if (title != null) {
                holder.binding.titleTv.setText(title);
            }

            holder.binding.moreTv.setOnClickListener(view -> {
                if (slug != null && title != null) context.startActivity(new Intent(context, MoreActivity.class).putExtra(Constants.CONTENT_SLUG, slug).putExtra(Constants.CONTENT_SECTION_TITLE, title).putExtra(Constants.CONTENT_IS_HOME, false));
                Constants.setEditor(context,Constants.CONTENT_SLUG,slug);
                Constants.setEditor(context,Constants.CONTENT_SECTION_TITLE,title);
                Constants.setEditor(context,Constants.CONTENT_IS_HOME,false);
            });
            if (isTunes == true){
                Constants.IS_TUNES = true;
            }else Constants.IS_TUNES = false;
        }

    }

    @Override
    public int getItemCount() {
        return subCategories != null ? subCategories.size() : 0;
    }

    class ParentViewHolder extends RecyclerView.ViewHolder {
        SectionRecyclerViewLayoutBinding binding;

        ParentViewHolder(SectionRecyclerViewLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}