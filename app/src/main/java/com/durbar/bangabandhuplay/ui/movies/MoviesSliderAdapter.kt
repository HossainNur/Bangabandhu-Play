package com.durbar.bangabandhuplay.ui.movies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.databinding.MoviesSliderLayoutBinding
import com.durbar.bangabandhuplay.ui.player.PlayerActivity
import com.durbar.bangabandhuplay.utils.Constants
import com.squareup.picasso.Picasso

class MoviesSliderAdapter(private val images: List<Original>, private val context: Context) :
    RecyclerView.Adapter<MoviesSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MoviesSliderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = images[position]
        val image = current.image
        val uuid = current.contentUrl

        image?.let { Picasso.get().load(it).into(holder.binding.moviesSliderImage) }

        holder.binding.root.setOnClickListener {
            if (uuid != null && uuid.isNotEmpty()) {
                context.startActivity(
                    Intent(context, PlayerActivity::class.java)
                        .putExtra(Constants.CONTENT_UUID, uuid)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, "Slider")
                )
            }
            Constants.IS_MORE_CONTENT = false
            Constants.IS_MORE_HOME = false
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(val binding: MoviesSliderLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
