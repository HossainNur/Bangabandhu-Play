package com.durbar.bangabandhuplay.ui.photo_gallery.photo_gallery_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.databinding.MoviesSliderLayoutBinding
import com.durbar.bangabandhuplay.databinding.PhotoGalleryDetailsSliderLayoutBinding
import com.durbar.bangabandhuplay.ui.photo_gallery.PhotoGalleryAdapter
import com.squareup.picasso.Picasso

class PhotoGalleryDetailsSliderAdapter(private val dataList: List<Data>,private val callBack: CallBack? = null): RecyclerView.Adapter<PhotoGalleryDetailsSliderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGalleryDetailsSliderAdapter.ViewHolder {
        return PhotoGalleryDetailsSliderAdapter.ViewHolder(
            PhotoGalleryDetailsSliderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = dataList[position]
        val image = current.image
        val title = current.title
        holder.binding.photoGallerySliderImage.clipToOutline = true
        image?.let { Picasso.get().load(it).into(holder.binding.photoGallerySliderImage) }
        title?.let { holder.binding.photoGalleryTitle.text = title }
        holder.binding.ivLeft.setOnClickListener {
            callBack?.photoGalleryLeftClick(position)
        }
        holder.binding.ivRight.setOnClickListener {
            callBack?.photoGalleryRightClick(position)
        }
        if (position == 0) holder.binding.ivLeft.visibility = View.GONE else  holder.binding.ivLeft.visibility = View.VISIBLE
        if (position == dataList.size - 1) holder.binding.ivRight.visibility = View.GONE else  holder.binding.ivRight.visibility = View.VISIBLE
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val binding: PhotoGalleryDetailsSliderLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    interface CallBack {
        fun photoGalleryLeftClick(position: Int)
        fun photoGalleryRightClick(position: Int)
    }
}