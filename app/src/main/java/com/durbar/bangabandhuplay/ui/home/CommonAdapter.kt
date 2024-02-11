package com.durbar.bangabandhuplay.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.FrontendCustomContent
import com.durbar.bangabandhuplay.databinding.ContentLayoutBinding
import com.durbar.bangabandhuplay.databinding.ItemParentHomeBinding
import com.durbar.bangabandhuplay.ui.more.MoreActivity
import com.durbar.bangabandhuplay.ui.player.PlayerActivity
import com.durbar.bangabandhuplay.utils.Constants
import com.squareup.picasso.Picasso

class CommonAdapter(private val categoryName: Data): RecyclerView.Adapter<CommonAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemParentHomeBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonAdapter.ViewHolder {
        return ViewHolder(ItemParentHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CommonAdapter.ViewHolder, position: Int) {
       holder.binding.categoryTvHome.text = categoryName.contentTypeTitle?:"No title found"
        val title = categoryName.contentTypeTitle
        val id = categoryName.id.toString()

        holder.binding.rcvItemHome.apply {
            val layoutManager = LinearLayoutManager(holder.binding.rcvItemHome.context, LinearLayoutManager.HORIZONTAL, false)
            setLayoutManager(layoutManager)
            adapter = CategoryItemAdapter(categoryName.frontendCustomContent, holder.itemView.context,title?:"" )
        }
        holder.binding.moreTv.setOnClickListener {
            if (id != null && title != null) {
                holder.itemView.context.startActivity(
                    Intent(holder.itemView.context, MoreActivity::class.java)
                        .putExtra(Constants.CONTENT_ID, id)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, title)
                        .putExtra(Constants.CONTENT_IS_HOME, true)
                )
            }
            Constants.setEditor(holder.itemView.context, Constants.CONTENT_ID, id)
            Constants.setEditor(holder.itemView.context, Constants.CONTENT_SECTION_TITLE, title)
            Constants.setEditor(holder.itemView.context, Constants.CONTENT_IS_HOME, true)
        }
    }

    override fun getItemCount(): Int = 1



    class CategoryItemAdapter(
        private val frontendCustomContentList: List<FrontendCustomContent>,
        private val context: Context,
        private val title: String
    ) : RecyclerView.Adapter<CategoryItemAdapter.ChildViewHolder>() {

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
                        Log.d("concat", "onBindViewHolder child uuid: ${Constants.CONTENT_UUID}")
                        Log.d("concat", "onBindViewHolder child CONTENT_SECTION_TITLE: ${Constants.CONTENT_SECTION_TITLE}")
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

}