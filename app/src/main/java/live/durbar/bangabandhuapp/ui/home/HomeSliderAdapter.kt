package live.durbar.bangabandhuapp.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.R
import live.durbar.bangabandhuapp.data.model.sliders.Original
import live.durbar.bangabandhuapp.databinding.HomeSliderLayoutBinding
import live.durbar.bangabandhuapp.ui.player.PlayerActivity
import live.durbar.bangabandhuapp.utils.Constants
import com.squareup.picasso.Picasso

class HomeSliderAdapter(private val images: List<Original>, private val context: Context) :
    RecyclerView.Adapter<HomeSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeSliderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = images[position]
        val image = current.image
        val uuid = current.contentUrl
        val title = current.title

        image?.let {
            Picasso.get()
                .load(it)
                .resizeDimen(R.dimen.slider_image_width, R.dimen.slider_image_height)
                .into(holder.binding.sliderThumbnailIv)
        }

        holder.binding.root.setOnClickListener {
            if (uuid != null && uuid.isNotEmpty()) {
                context.startActivity(
                    Intent(context, PlayerActivity::class.java)
                        .putExtra(Constants.CONTENT_UUID, uuid)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, "Slider")
                )
            }
            Constants.IS_MORE_CONTENT = false
            Constants.IS_MORE_HOME = false
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(val binding: HomeSliderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
