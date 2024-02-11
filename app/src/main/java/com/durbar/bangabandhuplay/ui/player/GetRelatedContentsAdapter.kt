package com.durbar.bangabandhuplay.ui.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.databinding.ContentLayoutBinding
import com.durbar.bangabandhuplay.data.model.get_related_contents.SingleContentRelatedContent
import com.squareup.picasso.Picasso

class GetRelatedContentsAdapter(
    private val singleContentRelatedContentList: List<SingleContentRelatedContent>,
    private val callBack: (String) -> Unit
) : RecyclerView.Adapter<GetRelatedContentsAdapter.mViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(ContentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val current = singleContentRelatedContentList[position]
        holder.binding.mainProductCardThumbnailIv.clipToOutline = true

        if (current != null) {
            val image = current.thumbnailPortrait
            val uuid = current.uuid
            if (image != null) {
                Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv)
            }

            holder.binding.root.setOnClickListener {
                if (uuid != null && uuid.isNotEmpty()) {
                    callBack(uuid)
                    //context.startActivity(Intent(context, PlayerActivity::class.java).putExtra(Constants.CONTENT_UUID, uuid).putExtra(Constants.CONTENT_SECTION_TITLE, title))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return singleContentRelatedContentList.size
    }

    inner class mViewHolder(val binding: ContentLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    interface CallBack {
        fun passContentData(uuid: String)
    }
}
