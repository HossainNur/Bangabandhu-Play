package com.durbar.bangabandhuplay.ui.family_members

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.databinding.ItemFamilyMemberRcvBinding
import com.durbar.bangabandhuplay.utils.RoundedCornerTransformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

/*class FamilyMemberAdapter(private val context: Context, private val imageResIds: List<Int>): RecyclerView.Adapter<FamilyMemberAdapter.FamilyViewHolder>() {*/
class FamilyMemberAdapter( private val dataList: List<Data>, private val callback: ( Int, Int, String, String, String, String) -> Unit ): RecyclerView.Adapter<FamilyMemberAdapter.FamilyViewHolder>() {

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
        val imageUrl = data.image ?: "" // Replace 'image' with your image URL field
        // Picasso.get().load(imageUrl).into(holder.binding.ivFamilyMemberRCVItem)

        val transformation = RoundedCornerTransformation(40f)  //for rounding image as i want
        Picasso.get()
            .load(imageUrl)
            .transform(transformation)
            .into(holder.binding.ivFamilyMemberRCVItem)

        val id = data.id ?: 0
        val name = data.title ?: ""
        val shortTitle = data.shortTitle.toString() ?: ""
        val description = data.description.toString() ?: ""


        holder.itemView.setOnClickListener {
            callback( position, id, imageUrl, name, shortTitle, description)
        }
    }
}
