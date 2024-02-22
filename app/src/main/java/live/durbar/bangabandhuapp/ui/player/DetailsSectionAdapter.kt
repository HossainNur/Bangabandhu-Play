package live.durbar.bangabandhuapp.ui.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.databinding.DetailsSectionLayoutBinding

class DetailsSectionAdapter(private val detailsList: List<String>?) :
    RecyclerView.Adapter<DetailsSectionAdapter.mViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            DetailsSectionLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val details = detailsList!![position]
        if (details != null) holder.binding.tvDetails.text = details
    }

    override fun getItemCount(): Int {
        return detailsList?.size ?: 0
    }

    inner class mViewHolder(var binding: DetailsSectionLayoutBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}
