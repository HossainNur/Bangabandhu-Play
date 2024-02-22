package live.durbar.bangabandhuapp.ui.photo_gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.data.model.photo_gallery.Data
import live.durbar.bangabandhuapp.databinding.GalleryContentLayoutBinding
import com.squareup.picasso.Picasso

class PhotoGalleryAdapter(
    private val dataList: List<Data>?,
    private val callBack: (Int) -> Unit
) : RecyclerView.Adapter<PhotoGalleryAdapter.mViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryContentLayoutBinding.inflate(inflater, parent, false)
        return mViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val current = dataList?.get(position)
        val image = current?.image
        val title = current?.title


        if (image != null) {
            holder.binding.mainProductCardThumbnailIv.clipToOutline = true
            Picasso.get().load(image).fit().into(holder.binding.mainProductCardThumbnailIv)
        }

        holder.binding.root.setOnClickListener {

            if (dataList != null){
                callBack(position)
            }

        }

    }

    inner class mViewHolder(val binding: GalleryContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}