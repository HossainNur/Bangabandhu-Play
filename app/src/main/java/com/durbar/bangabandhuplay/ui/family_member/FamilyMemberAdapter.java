package com.durbar.bangabandhuplay.ui.family_member;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durbar.bangabandhuplay.data.model.family_member.Data;
import com.durbar.bangabandhuplay.databinding.GalleryContentLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FamilyMemberAdapter extends RecyclerView.Adapter<FamilyMemberAdapter.mViewHolder>{
    private List<Data> dataList;
    private Context context;
    private CallBack callBack;

    public FamilyMemberAdapter(List<Data> dataList,Context context,CallBack callBack) {
        this.dataList = dataList;
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public FamilyMemberAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FamilyMemberAdapter.mViewHolder(GalleryContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyMemberAdapter.mViewHolder holder, int position) {

        Data current = dataList.get(position);
        String image = current.getImage();
        String title = current.getTitle();
        String shortTitle = current.getShortTitle();
        String description = current.getDescription();

        if (image != null) {
            Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv);
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            if (title != null && shortTitle != null && description != null && image != null){
                callBack.familyMemberDetails(title,shortTitle,description,image);
            }

        });
    }

    @Override
    public int getItemCount() {
         return dataList == null ? 0 : dataList.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {

        GalleryContentLayoutBinding binding;
        public mViewHolder(@NonNull GalleryContentLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface  CallBack{
        void familyMemberDetails(String title, String shortTitle, String description, String image);
    }
}
