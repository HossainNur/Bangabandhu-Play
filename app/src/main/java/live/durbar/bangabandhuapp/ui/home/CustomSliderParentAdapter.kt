package live.durbar.bangabandhuapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import live.durbar.bangabandhuapp.R
import live.durbar.bangabandhuapp.data.model.fronend_custom_slider.Original
import live.durbar.bangabandhuapp.databinding.SliderLayoutBinding

class CustomSliderParentAdapter(private val frontEndCustomSlider: List<Original>): RecyclerView.Adapter<CustomSliderParentAdapter.Viewholder>() {

    private var prev1: Int = 0
    private lateinit var context: Context

    class Viewholder(var binding: SliderLayoutBinding): RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomSliderParentAdapter.Viewholder {
        context = parent.context
        return Viewholder( SliderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomSliderParentAdapter.Viewholder, position: Int) {
       if (frontEndCustomSlider != null && frontEndCustomSlider != null && frontEndCustomSlider.isNotEmpty()){
           holder.binding.sliderOneContainer.visibility = View.VISIBLE

           val customSliderAdapter1 = CustomSliderItemAdapter(frontEndCustomSlider)   //CustomSliderItemAdapter initialization

           holder.binding.imageVp.adapter = customSliderAdapter1
           holder.binding.imageVp.clipToPadding = true
           holder. binding.imageVp.clipChildren = true
           holder.binding.imageVp.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
           for (i in frontEndCustomSlider.indices) {
               val item = TextView(holder.binding.sliderOneContainer.context)
               item.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.rounded_back_light_grey)
               item.id = i
               val params = LinearLayout.LayoutParams(15, 15)
               params.setMargins(5, 0, 5, 0)
               //item.setLayoutParams(new ViewGroup.LayoutParams(30,30));
               item.layoutParams = params
               holder.binding.indicatorContainerLl.addView(item)
           }
           holder.binding.imageVp.registerOnPageChangeCallback(object :
               ViewPager2.OnPageChangeCallback() {
               override fun onPageSelected(position: Int) {
                   super.onPageSelected(position)
                   holder.binding.indicatorContainerLl.getChildAt(prev1).background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.rounded_back_light_grey)
                   holder.binding.indicatorContainerLl.getChildAt(position).background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.rounded_back)
                   prev1 = position
               }
           })
       }else{
           holder.binding.sliderOneContainer.visibility = View.GONE
       }
    }

    override fun getItemCount() = 1
}