package live.durbar.bangabandhuapp.ui.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.data.model.ott_content.CastAndCrew
import live.durbar.bangabandhuapp.databinding.CastCrewLayoutBinding
import com.squareup.picasso.Picasso

class CastCrewSliderAdapter(private val castAndCrewList: List<CastAndCrew>) :
    RecyclerView.Adapter<CastCrewSliderAdapter.mViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            CastCrewLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val current = castAndCrewList[position]
        val name = current.name
        val image = current.image
        val role = current.pivot?.role

        name?.let { holder.binding.castName.text = it }
        image?.let { Picasso.get().load(it).into(holder.binding.castCrewImage) }
        role?.let {
            if (it.contains("_")) {
                val parts = it.split("_")
                holder.binding.castRole.text =
                    "${parts[0].capitalize()} ${parts[1].capitalize()}"
            } else {
                holder.binding.castRole.text = it.capitalize()
            }
        }
    }

    override fun getItemCount(): Int {
        return castAndCrewList.size
    }

    inner class mViewHolder(val binding: CastCrewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}