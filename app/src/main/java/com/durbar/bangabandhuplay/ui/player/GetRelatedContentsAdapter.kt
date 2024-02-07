package com.durbar.bangabandhuplay.ui.player

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.databinding.ContentLayoutBinding
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.data.model.get_related_contents.SingleContentRelatedContent
import com.squareup.picasso.Picasso

class GetRelatedContentsAdapter(
    private val singleContentRelatedContentList: List<SingleContentRelatedContent>,
    private val context: Context,
    private val title: String
) : RecyclerView.Adapter<GetRelatedContentsAdapter.mViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            ContentLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val current = singleContentRelatedContentList[position]
        holder.binding.mainProductCardThumbnailIv.clipToOutline = true
        val image = current.thumbnailPortrait
        val uuid = current.uuid

        image?.let { Picasso.get().load(it).fit().into(holder.binding.mainProductCardThumbnailIv) }

        holder.binding.root.setOnClickListener {
            if (!uuid.isNullOrEmpty()) {
                context.startActivity(
                    Intent(context, PlayerActivity::class.java)
                        .putExtra(Constants.CONTENT_UUID, uuid)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, title)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return singleContentRelatedContentList.size
    }

    inner class mViewHolder(val binding: ContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
