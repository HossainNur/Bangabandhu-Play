package com.durbar.bangabandhuplay.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.databinding.SectionRecyclerViewLayoutBinding;
import com.durbar.bangabandhuplay.ui.more.MoreActivity;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.FrontendCustomContent;
import com.durbar.bangabandhuplay.utils.Constants;

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

        if (dataList.get(position) != null) {
            String title = current.getContentTypeTitle();
            String id = current.getId().toString();
            List<FrontendCustomContent> frontendCustomContents = current.getFrontendCustomContent();

            holder.binding.itemRv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            holder.binding.itemRv.setAdapter(new ChildItemAdapter(frontendCustomContents, context, title));


            if (title != null) {
                holder.binding.titleTv.setText(title);
            }

            holder.binding.moreTv.setOnClickListener(view -> {
                if (id != null && title != null)
                    context.startActivity(new Intent(context, MoreActivity.class).putExtra(Constants.CONTENT_ID, id).putExtra(Constants.CONTENT_SECTION_TITLE, title).putExtra(Constants.CONTENT_IS_HOME, true));
                Constants.setEditor(context, Constants.CONTENT_ID, id);
                Constants.setEditor(context, Constants.CONTENT_SECTION_TITLE, title);
                Constants.setEditor(context, Constants.CONTENT_IS_HOME, true);
            });
        }

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

