package com.durbar.bangabandhuplay.ui.family_members

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.ItemFamilyMemberRcvBinding

class FamilyMemberAdapter(private val context: Context, private val imageResIds: List<Int>): RecyclerView.Adapter<FamilyMemberAdapter.FamilyViewHolder>() {


    class FamilyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.ivFamilyMember_RCVItem)
        fun bind(imageResId: Int) {
            image.setImageResource(imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_family_member_rcv, parent, false)
        return FamilyMemberAdapter.FamilyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 9
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val imageResId = imageResIds[position]
        holder.bind(imageResId)
    }


}