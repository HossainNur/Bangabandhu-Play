package com.durbar.bangabandhuplay.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.data.model.fronend_custom_slider.Original
import com.durbar.bangabandhuplay.databinding.CustomSliderCardBinding
import com.squareup.picasso.Picasso

class CustomSliderItemAdapter(private val sliders: List<Original>) : RecyclerView.Adapter<CustomSliderItemAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val binding = CustomSliderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.binding.sliderImageIv.clipToOutline = true
        val current = sliders[position]
        val image = current.image

        if (image != null) {
            Picasso.get().load(image).fit().into(holder.binding.sliderImageIv)
        }
    }

    override fun getItemCount(): Int {
        return sliders.size
    }

    inner class MViewHolder(val binding: CustomSliderCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}