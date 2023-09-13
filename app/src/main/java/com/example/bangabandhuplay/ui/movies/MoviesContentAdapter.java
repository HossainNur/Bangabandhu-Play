package com.example.bangabandhuplay.ui.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangabandhuplay.data.model.category.root.single.OttContent;
import com.example.bangabandhuplay.data.model.category.root.single.SubCategory;
import com.example.bangabandhuplay.databinding.SectionRecyclerViewLayoutBinding;

import java.util.List;

public class MoviesContentAdapter extends RecyclerView.Adapter<MoviesContentAdapter.ParentViewHolder> {


    private List<SubCategory> subCategories;

    private Context context;


    public MoviesContentAdapter(List<SubCategory> subCategories,  Context context) {
        this.subCategories = subCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewHolder(SectionRecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {

        SubCategory current = subCategories.get(position);

        List<OttContent> ottContents = current.getOttContents();

        /*holder.binding.itemRv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.binding.itemRv.setAdapter(new MoviesChildContentAdapter(ottContents));*/

        String title = current.getTitle();

        if (title != null){
            holder.binding.titleTv.setText(title);
        }

        holder.binding.moreTv.setOnClickListener(view -> {
        });



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

    public interface CallBack{
        void addWishListItem(String uuid);
    }
}