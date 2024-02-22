package live.durbar.bangabandhuapp.ui.photo_gallery.photo_gallery_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.data.model.photo_gallery.Data
import live.durbar.bangabandhuapp.databinding.PhotoGalleryDetailsSliderLayoutBinding
import com.squareup.picasso.Picasso

class PhotoGalleryDetailsSliderAdapter(private val dataList: List<Data>,private val callBackLeft: (Int) ->Unit, private val callBackRight: (Int) ->Unit): RecyclerView.Adapter<PhotoGalleryDetailsSliderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGalleryDetailsSliderAdapter.ViewHolder {
        return PhotoGalleryDetailsSliderAdapter.ViewHolder(
            PhotoGalleryDetailsSliderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = dataList[position]
        val image = current.image
        val title = current.title
        holder.binding.photoGallerySliderImage.clipToOutline = true
        image?.let { Picasso.get().load(it).into(holder.binding.photoGallerySliderImage) }
        title?.let { holder.binding.photoGalleryTitle.text = title }
        holder.binding.ivLeft.setOnClickListener {
            callBackLeft(position)
        }
        holder.binding.ivRight.setOnClickListener {
            callBackRight(position)
        }
        if (position == 0) holder.binding.ivLeft.visibility = View.GONE else  holder.binding.ivLeft.visibility = View.VISIBLE
        if (position == dataList.size - 1) holder.binding.ivRight.visibility = View.GONE else  holder.binding.ivRight.visibility = View.VISIBLE
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(val binding: PhotoGalleryDetailsSliderLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}