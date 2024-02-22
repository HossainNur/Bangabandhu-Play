package live.durbar.bangabandhuapp.ui.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.data.model.search_content.Data
import live.durbar.bangabandhuapp.databinding.SearchResultLayoutBinding
import live.durbar.bangabandhuapp.ui.player.PlayerActivity
import live.durbar.bangabandhuapp.utils.Constants
import com.squareup.picasso.Picasso

class SearchContentsAdapter(private val dataList: List<Data>?, private val context: Context) :
    RecyclerView.Adapter<SearchContentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchResultLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = dataList?.get(position)
        holder.binding.searchImage.clipToOutline = true
        val image = current?.thumbnailLandscape
        val title = current?.title
        val uuid = current?.uuid

        image?.let { Picasso.get().load(it).into(holder.binding.searchImage) }
        title?.let { holder.binding.searchContentName.text = it }

        holder.binding.root.setOnClickListener {
            if (uuid != null && uuid.isNotEmpty()) {
                context.startActivity(
                    Intent(context, PlayerActivity::class.java)
                        .putExtra(Constants.CONTENT_UUID, uuid)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, "Search Item")
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    fun clearListItem() {
        //dataList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: SearchResultLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
