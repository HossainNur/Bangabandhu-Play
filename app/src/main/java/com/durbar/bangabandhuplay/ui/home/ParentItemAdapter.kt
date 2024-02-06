package com.durbar.bangabandhuplay.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.databinding.SectionRecyclerViewLayoutBinding
import com.durbar.bangabandhuplay.ui.more.MoreActivity
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data
import com.durbar.bangabandhuplay.utils.Constants

class ParentItemAdapter(private val dataList: List<Data>, private val context: Context) :
    RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(
            SectionRecyclerViewLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val current = dataList[position]

        val title = current.contentTypeTitle
        val id = current.id.toString()
        val frontendCustomContents = current.frontendCustomContent

        holder.binding.itemRv.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        holder.binding.itemRv.adapter =
            frontendCustomContents?.let { ChildItemAdapter(it, context, title?:"") }

        if (title != null) {
            holder.binding.titleTv.text = title
        }

        holder.binding.moreTv.setOnClickListener {
            if (id != null && title != null) {
                context.startActivity(
                    Intent(context, MoreActivity::class.java)
                        .putExtra(Constants.CONTENT_ID, id)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, title)
                        .putExtra(Constants.CONTENT_IS_HOME, true)
                )
            }
            Constants.setEditor(context, Constants.CONTENT_ID, id)
            Constants.setEditor(context, Constants.CONTENT_SECTION_TITLE, title)
            Constants.setEditor(context, Constants.CONTENT_IS_HOME, true)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ParentViewHolder(val binding: SectionRecyclerViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
