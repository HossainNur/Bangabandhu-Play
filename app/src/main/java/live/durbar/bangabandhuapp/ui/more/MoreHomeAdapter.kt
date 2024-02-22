package live.durbar.bangabandhuapp.ui.more

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.R
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_content_by_slug.Content
import live.durbar.bangabandhuapp.databinding.MoreContentLayoutBinding
import live.durbar.bangabandhuapp.ui.player.PlayerActivity
import live.durbar.bangabandhuapp.utils.Constants
import com.squareup.picasso.Picasso

class MoreHomeAdapter(
    private val contentList: List<Content>,
    private val context: Context,
    private val title: String,
    private val isMore: Boolean
) : RecyclerView.Adapter<MoreHomeAdapter.ViewHolder>() {

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
        val current = contentList[position].ottContent

        holder.binding.mainProductCardThumbnailIv.clipToOutline = true

        // image
        val image = current?.thumbnailPortrait
        val uuid = current?.uuid

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
            Constants.IS_MORE_HOME = true
        }

        holder.binding.mainProductCardThumbnailIv.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.fall_down
            )
        )
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    inner class ViewHolder(val binding: MoreContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
