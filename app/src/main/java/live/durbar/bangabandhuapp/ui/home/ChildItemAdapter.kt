package live.durbar.bangabandhuapp.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.R
import live.durbar.bangabandhuapp.databinding.ContentLayoutBinding
import live.durbar.bangabandhuapp.ui.player.PlayerActivity
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_contents.FrontendCustomContent
import live.durbar.bangabandhuapp.utils.Constants
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
                    Log.d("concat", "onBindViewHolder child old uuid: ${Constants.CONTENT_UUID}")
                    Log.d("concat", "onBindViewHolder child old CONTENT_SECTION_TITLE: ${Constants.CONTENT_SECTION_TITLE}")
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
