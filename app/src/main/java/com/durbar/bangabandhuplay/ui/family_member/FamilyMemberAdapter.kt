package com.durbar.bangabandhuplay.ui.family_member

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.data.model.family_member.Data
import com.durbar.bangabandhuplay.databinding.GalleryContentLayoutBinding
import com.squareup.picasso.Picasso

class FamilyMemberAdapter(private val dataList: List<Data>, private val context: Context, private val callBack: CallBack) :
    RecyclerView.Adapter<FamilyMemberAdapter.mViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            GalleryContentLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val current = dataList[position]
        val image = current.image
        val title = current.title
        val shortTitle = current.shortTitle
        val description = current.description

        if (image != null) {
            holder.binding.mainProductCardThumbnailIv.clipToOutline = true
            Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv)
        }

        holder.binding.root.setOnClickListener {
            title?.let { t ->
                shortTitle?.let { st ->
                    description?.let { d ->
                        image?.let { i ->
                            callBack.familyMemberDetails(t, st, d, i)
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class mViewHolder(val binding: GalleryContentLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    interface CallBack {
        fun familyMemberDetails(title: String, shortTitle: String, description: String, image: String)
    }
}
