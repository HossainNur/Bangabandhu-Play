package live.durbar.bangabandhuapp.ui.tunes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.data.model.category.root.single.CategorySlider
import live.durbar.bangabandhuapp.databinding.CategoriesSliderLayoutBinding
import live.durbar.bangabandhuapp.ui.player.PlayerActivity
import live.durbar.bangabandhuapp.utils.Constants
import com.squareup.picasso.Picasso

class TunesSliderAdapter(private val categorySliderList: List<CategorySlider>, private val context: Context) :
    RecyclerView.Adapter<TunesSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoriesSliderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = categorySliderList[position]
        val title = current.title
        val image = current.landscapeImage
        val description = current.description.toString()
        val uuid = current.contentUrl

        image?.let { Picasso.get().load(current.image).into(holder.binding.categoriesSliderImage) }
        title?.let { holder.binding.sliderTitle.text = it }
        description?.let { holder.binding.sliderDesc.text = it }

        holder.binding.root.setOnClickListener {
            if (uuid != null && uuid.isNotEmpty()) {
                context.startActivity(
                    Intent(context, PlayerActivity::class.java)
                        .putExtra(Constants.CONTENT_UUID, uuid)
                        .putExtra(Constants.CONTENT_SECTION_TITLE, "Slider")
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return categorySliderList.size
    }

    class ViewHolder(val binding: CategoriesSliderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
