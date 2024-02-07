package com.durbar.bangabandhuplay.ui.player;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.data.model.ott_content.CastAndCrew;
import com.durbar.bangabandhuplay.databinding.CastCrewLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastCrewSliderAdapter extends RecyclerView.Adapter<CastCrewSliderAdapter.mViewHolder> {

    private List<CastAndCrew> castAndCrewList;

    public CastCrewSliderAdapter(List<CastAndCrew> castAndCrewList) {
        this.castAndCrewList = castAndCrewList;
    }

    @NonNull
    @Override
    public CastCrewSliderAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewHolder(CastCrewLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastCrewSliderAdapter.mViewHolder holder, int position) {

        CastAndCrew current = castAndCrewList.get(position);
        if (castAndCrewList.get(position) != null) {
            String name = current.getName();
            String image = current.getImage();
            String role = current.getPivot().getRole();

            if (name != null) holder.binding.castName.setText(name);
            if (image != null) Picasso.get().load(image).into(holder.binding.castCrewImage);
            if (role != null) {

                if (role.contains("_")) {
                    String[] parts = role.split("_");

                    for (int i = 0; i < parts.length; i++) {
                        holder.binding.castRole.setText(parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1) + " " + parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1));
                    }
                } else
                    holder.binding.castRole.setText(role.substring(0, 1).toUpperCase() + role.substring(1));
            }
        }

    }

    @Override
    public int getItemCount() {
        return castAndCrewList != null ? castAndCrewList.size() : 0;
    }


    public class mViewHolder extends RecyclerView.ViewHolder {

        CastCrewLayoutBinding binding;

        public mViewHolder(@NonNull CastCrewLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
