package com.durbar.bangabandhuplay.ui.tunes

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.category.root.single.CategorySlider
import com.durbar.bangabandhuplay.data.model.category.root.single.SubCategory
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.databinding.FragmentTunesBinding
import com.durbar.bangabandhuplay.ui.movies.MoviesContentAdapter
import com.durbar.bangabandhuplay.ui.movies.MoviesSliderAdapter
import com.durbar.bangabandhuplay.ui.movies.MoviesViewModel
import com.durbar.bangabandhuplay.utils.checkInternet


class TunesFragment : Fragment() {

    private lateinit var binding: FragmentTunesBinding
    private lateinit var viewModel: MoviesViewModel
    private val sliderHandler = Handler()
    private var inc = true
    private var prev1 = 0
    private var slider = false
    private var moviesSection : Boolean = false
    private var isMoviesOriginalList: List<Original>? = null
    private var images: List<Original> = java.util.ArrayList()
    private var categorySliderList: List<CategorySlider> = java.util.ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentTunesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().checkInternet()

        viewModel.sliders.observe(viewLifecycleOwner){originals ->
            try {
                if (originals != null) {
                    isMoviesOriginalList = java.util.ArrayList<Original>()
                    for (isHomeList in originals) {
                        if (isHomeList.isHome == 0) {
                            (isMoviesOriginalList as java.util.ArrayList<Original>).add(isHomeList)
                        }
                    }
                    images = isMoviesOriginalList as java.util.ArrayList<Original>
                    setSlider(isMoviesOriginalList as java.util.ArrayList<Original>)
                 //   hideProgressBar()
                }
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        }



        viewModel.fetchMoviesCategory("tunes").observe(viewLifecycleOwner){data ->
            try {
                if (data != null && !data.isEmpty()) {

                    /*if (data.get(0).categorySliders != null && !data.get(0).categorySliders.isNullOrEmpty()){
                        binding.tunesSliderVp.visibility = View.VISIBLE
                        categorySliderList = data.get(0).categorySliders
                        setSlider(categorySliderList)
                    }else{
                        binding.tunesSliderVp.visibility = View.GONE
                    }*/

                    val subCategories: MutableList<SubCategory> = ArrayList()
                    for (c in data[0].subCategories!!) {
                        if (c.ottContents != null && !c.ottContents.isEmpty()) {
                            subCategories.add(c)
                        }
                    }
                    binding.tunesRv.setLayoutManager(LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false))
                    binding.tunesRv.adapter = MoviesContentAdapter(subCategories,requireActivity(), true)
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun setSlider(originals: List<Original>) {
        binding.tunesSliderVp.setAdapter(MoviesSliderAdapter(originals, requireActivity()))
        binding.tunesSliderVp.setClipToPadding(false)
        binding.tunesSliderVp.setClipChildren(false)
        binding.tunesSliderVp.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER)
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding.tunesSliderVp.setPageTransformer(compositePageTransformer)
        //binding.homeSliderVp.setCurrentItem(1);
        binding.tunesSliderVp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //Log.v("@@@@", position+" ");
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })

        /*binding.indicatorContainerLl.removeAllViews()
        for (i in originals.indices) {
            val item = TextView(requireActivity())
            item.background = resources.getDrawable(R.drawable.slider_indicator_background)
            item.id = i
            val params = LinearLayout.LayoutParams(15, 15)
            params.setMargins(5, 0, 5, 0)
            //item.setLayoutParams(new ViewGroup.LayoutParams(30,30));
            item.layoutParams = params
            binding.indicatorContainerLl.addView(item)
        }
        binding.tunesSliderVp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicatorContainerLl.getChildAt(prev1).background =
                    resources.getDrawable(R.drawable.slider_indicator_background)
                binding.indicatorContainerLl.getChildAt(position).background =
                    resources.getDrawable(R.drawable.slider_indicatior_back)
                prev1 = position
            }
        })*/
    }

 /*   private fun hideProgressBar() {
        if (slider && moviesSection) {
            binding.tunesContainer.setVisibility(View.VISIBLE)
            binding.progressBar.setVisibility(View.GONE)
        }
    }*/

    val sliderRunnable = Runnable {
        if (binding != null) {
            val current: Int = binding.tunesSliderVp.getCurrentItem()
            if (current == images.size - 1) {
                inc = false
            }
            if (current == 0) inc = true
            if (inc) binding.tunesSliderVp.setCurrentItem(current + 1) else binding.tunesSliderVp.setCurrentItem(current - 1)
        }
    }

}