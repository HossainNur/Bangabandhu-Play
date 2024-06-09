package com.durbar.bangabandhuplay.ui.photo_gallery

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.databinding.GalleryContentLayoutBinding
import com.durbar.bangabandhuplay.ui.family_member.FamilyMemberAdapter.CallBack
import com.squareup.picasso.Picasso

class PhotoGalleryAdapter(
    private val dataList: List<Data>?,
    private val callBack: CallBack? = null
) : RecyclerView.Adapter<PhotoGalleryAdapter.mViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryContentLayoutBinding.inflate(inflater, parent, false)
        return mViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val current = dataList?.get(position)
        val image = current?.image
        val title = current?.title

        if (image != null) {
            holder.binding.mainProductCardThumbnailIv.clipToOutline = true
            Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv)
        }

        holder.binding.root.setOnClickListener {
            if (title != null && image != null) {
                callBack?.photoGalleryDetails(title,image)
            }
        }

    }

    inner class mViewHolder(val binding: GalleryContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface CallBack {
        fun photoGalleryDetails(title: String,image: String)
    }
}