package com.example.bangabandhuplay.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangabandhuplay.ui.more.MoreActivity;
import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data;
import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_contents.FrontendCustomContent;
import com.example.bangabandhuplay.databinding.SectionRecyclerViewLayoutBinding;
import com.example.bangabandhuplay.utils.Constants;

import java.util.List;


public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {

    private List<Data> dataList;
    private Context context;

    public ParentItemAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewHolder(SectionRecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {

        Data current = dataList.get(position);

        List<FrontendCustomContent> frontendCustomContents = current.getFrontendCustomContent();

        holder.binding.itemRv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.binding.itemRv.setAdapter(new ChildItemAdapter(frontendCustomContents));

        String title = current.getContentTypeTitle();
        String slug = current.getContentTypeSlug().toString();


        if (title != null) {
            holder.binding.titleTv.setText(title);
        }

        holder.binding.moreTv.setOnClickListener(view -> {
            //if (slug != null && title != null) context.startActivity(new Intent(context, MoreActivity.class).putExtra(Constants.CONTENT_SLUG, slug).putExtra(Constants.CONTENT_SECTION_TITLE, title));
        });
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }


    class ParentViewHolder extends RecyclerView.ViewHolder {
        SectionRecyclerViewLayoutBinding binding;

        ParentViewHolder(SectionRecyclerViewLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

