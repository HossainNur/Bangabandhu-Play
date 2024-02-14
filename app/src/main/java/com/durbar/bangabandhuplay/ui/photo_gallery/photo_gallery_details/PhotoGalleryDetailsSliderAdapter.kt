package com.durbar.bangabandhuplay.ui.photo_gallery.photo_gallery_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.databinding.MoviesSliderLayoutBinding
import com.durbar.bangabandhuplay.databinding.PhotoGalleryDetailsSliderLayoutBinding
import com.squareup.picasso.Picasso

class PhotoGalleryDetailsSliderAdapter(private val dataList: List<Data>): RecyclerView.Adapter<PhotoGalleryDetailsSliderAdapter.ViewHolder>() {


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
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val binding: PhotoGalleryDetailsSliderLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}