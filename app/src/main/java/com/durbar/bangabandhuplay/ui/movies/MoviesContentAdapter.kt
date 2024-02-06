package com.durbar.bangabandhuplay.ui.movies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.data.model.category.root.single.SubCategory
import com.durbar.bangabandhuplay.data.model.category.root.single.OttContent
import com.durbar.bangabandhuplay.databinding.SectionRecyclerViewLayoutBinding
import com.durbar.bangabandhuplay.ui.more.MoreActivity
import com.durbar.bangabandhuplay.utils.Constants

class MoviesContentAdapter(private val subCategories: List<SubCategory>, private val context: Context) :
    RecyclerView.Adapter<MoviesContentAdapter.ParentViewHolder>() {

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
        val current = subCategories[position]
        val title = current.title
        val slug = current.slug
        val ottContents = current.ottContents

        holder.binding.itemRv.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        holder.binding.itemRv.adapter = ottContents?.let { MoviesChildContentAdapter(it, context, title?:"") }

        title?.let { holder.binding.titleTv.text = it }

        holder.binding.moreTv.setOnClickListener {
            if (slug != null && title != null) {
                context.startActivity(
                    Intent(context, MoreActivity::class.java)
                        .putExtra(Constants.CONTENT_SLUG, slug)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, title)
                        .putExtra(Constants.CONTENT_IS_HOME, false)
                )
                Constants.setEditor(context, Constants.CONTENT_SLUG, slug)
                Constants.setEditor(context, Constants.CONTENT_SECTION_TITLE, title)
                Constants.setEditor(context, Constants.CONTENT_IS_HOME, false)
            }
        }
    }

    override fun getItemCount(): Int {
        return subCategories.size
    }

    inner class ParentViewHolder(val binding: SectionRecyclerViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
