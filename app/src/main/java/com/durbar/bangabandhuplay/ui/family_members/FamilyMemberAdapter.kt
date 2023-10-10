package com.durbar.bangabandhuplay.ui.family_members

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.databinding.ItemFamilyMemberRcvBinding
import com.squareup.picasso.Picasso

/*class FamilyMemberAdapter(private val context: Context, private val imageResIds: List<Int>): RecyclerView.Adapter<FamilyMemberAdapter.FamilyViewHolder>() {*/
class FamilyMemberAdapter( private val dataList: List<Data> ): RecyclerView.Adapter<FamilyMemberAdapter.FamilyViewHolder>() {

    inner class FamilyViewHolder(val binding: ItemFamilyMemberRcvBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        val binding = ItemFamilyMemberRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FamilyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val data = dataList[position]
        val imageUrl = data.image // Replace 'image' with your image URL field
        Picasso.get().load(imageUrl).into(holder.binding.ivFamilyMemberRCVItem)
    }
}