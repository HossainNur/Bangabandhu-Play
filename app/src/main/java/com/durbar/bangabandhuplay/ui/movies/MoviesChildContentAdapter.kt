package com.durbar.bangabandhuplay.ui.movies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.category.root.single.OttContent
import com.durbar.bangabandhuplay.databinding.ContentLayoutBinding
import com.durbar.bangabandhuplay.ui.player.PlayerActivity
import com.durbar.bangabandhuplay.utils.Constants
import com.squareup.picasso.Picasso

class MoviesChildContentAdapter(
    private val contentList: List<OttContent>,
    private val context: Context,
    private val title: String,
) : RecyclerView.Adapter<MoviesChildContentAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(
            ContentLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val current = contentList[position]

        val image = current.thumbnailPortrait
        val uuid = current.uuid

        holder.binding.mainProductCardThumbnailIv.clipToOutline = true

        image?.let { Picasso.get().load(it).into(holder.binding.mainProductCardThumbnailIv) }

        holder.binding.root.setOnClickListener {
            if (uuid != null && uuid.isNotEmpty()) {
                context.startActivity(
                    Intent(context, PlayerActivity::class.java)
                        .putExtra(Constants.CONTENT_UUID, uuid)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, title)
                )
            }
        }

        holder.binding.contentImage.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.slide_up_animation
            )
        )
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    inner class ChildViewHolder(val binding: ContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
