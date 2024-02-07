package com.durbar.bangabandhuplay.ui.player;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.databinding.DetailsSectionLayoutBinding;

import java.util.List;

public class DetailsSectionAdapter extends RecyclerView.Adapter<DetailsSectionAdapter.mViewHolder> {

    private List<String> detailsList;
    public DetailsSectionAdapter(List<String> detailsList) {
        this.detailsList = detailsList;
    }


    @NonNull
    @Override
    public DetailsSectionAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailsSectionAdapter.mViewHolder(DetailsSectionLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsSectionAdapter.mViewHolder holder, int position) {

        String details = detailsList.get(position);
        if (detailsList.get(position) != null){
            if (details != null) holder.binding.tvDetails.setText(details);
        }
    }

    @Override
    public int getItemCount() {
         return detailsList != null ? detailsList.size() : 0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        DetailsSectionLayoutBinding binding;
        public mViewHolder(@NonNull DetailsSectionLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
