package com.durbar.bangabandhuplay.ui.more

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.MoreContentLayoutBinding
import com.durbar.bangabandhuplay.ui.player.PlayerActivity
import com.durbar.bangabandhuplay.data.model.category.single_sub.OttContent
import com.durbar.bangabandhuplay.utils.Constants
import com.squareup.picasso.Picasso

class MoreOthersAdapter(
    private val ottContentList: List<OttContent>,
    private val context: Context,
    private val title: String,
    private val isMore: Boolean
) : RecyclerView.Adapter<MoreOthersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MoreContentLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = ottContentList[position]

        holder.binding.mainProductCardThumbnailIv.clipToOutline = true

        // image
        val image = current.thumbnailPortrait
        val uuid = current.uuid

        image?.let { Picasso.get().load(it).fit().into(holder.binding.mainProductCardThumbnailIv) }

        holder.binding.root.setOnClickListener {
            if (uuid != null && uuid.isNotEmpty()) {
                context.startActivity(
                    Intent(context, PlayerActivity::class.java)
                        .putExtra(Constants.CONTENT_UUID, uuid)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, title)
                        .putExtra(Constants.CONTENT_IS_MORE, true)
                )
            }
            Constants.IS_MORE_CONTENT = true
            Constants.IS_MORE_HOME = false
        }

        holder.binding.mainProductCardThumbnailIv.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.fall_down
            )
        )
    }

    override fun getItemCount(): Int {
        return ottContentList.size
    }

    inner class ViewHolder(val binding: MoreContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
