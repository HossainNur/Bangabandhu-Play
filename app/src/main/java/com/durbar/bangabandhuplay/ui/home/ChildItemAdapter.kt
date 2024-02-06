package com.durbar.bangabandhuplay.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.ContentLayoutBinding
import com.durbar.bangabandhuplay.ui.player.PlayerActivity
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.FrontendCustomContent
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.OttContent
import com.durbar.bangabandhuplay.utils.Constants
import com.squareup.picasso.Picasso

class ChildItemAdapter(
    private val frontendCustomContentList: List<FrontendCustomContent>,
    private val context: Context,
    private val title: String
) : RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder>() {

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
        val current = frontendCustomContentList[position].ottContent
        holder.binding.mainProductCardThumbnailIv.clipToOutline = true

        if (current != null) {
            val image = current.thumbnailPortrait
            val uuid = current.uuid

            if (image != null) {
                Picasso.get().load(image).into(holder.binding.mainProductCardThumbnailIv)
            }

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
    }

    override fun getItemCount(): Int {
        return frontendCustomContentList.size
    }

    class ChildViewHolder(val binding: ContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
