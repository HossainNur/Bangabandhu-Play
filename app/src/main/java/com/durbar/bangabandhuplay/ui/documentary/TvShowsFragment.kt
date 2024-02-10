package com.durbar.bangabandhuplay.ui.documentary

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.category.root.single.Data
import com.durbar.bangabandhuplay.data.model.category.root.single.SubCategory
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.databinding.FragmentTvShowsBinding
import com.durbar.bangabandhuplay.ui.movies.MoviesContentAdapter
import com.durbar.bangabandhuplay.ui.movies.MoviesSliderAdapter
import com.durbar.bangabandhuplay.ui.movies.MoviesViewModel
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.NavigationHelper
import com.durbar.bangabandhuplay.utils.checkInternet

class TvShowsFragment : Fragment() {
    private var binding: FragmentTvShowsBinding? = null
    private var viewModel: MoviesViewModel? = null
    private var moviesSliderAdapter: MoviesSliderAdapter? = null
    private var isMoviesOriginalList: MutableList<Original>? = null
    private var moviesContentAdapter: MoviesContentAdapter? = null
    private var images: List<Original> = ArrayList()
    private var slider = false
    private var documentarySection = false
    private var prev1 = 0
    private var inc = true
    private val sliderHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().checkInternet()
        viewModel!!.sliders.observe(requireActivity()) { originals: List<Original>? ->
            try {
                if (originals != null) {
                    slider = true
                    isMoviesOriginalList = ArrayList()
                    for (isHomeList in originals) {
                        if (isHomeList.isHome == 0) {
                            (isMoviesOriginalList as ArrayList<Original>).add(isHomeList)
                        }
                    }
                    images = isMoviesOriginalList as ArrayList<Original>
                    setSlider(isMoviesOriginalList as ArrayList<Original>)
                    hideProgressBar()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        viewModel!!.fetchMoviesCategory("documentary")
            .observe(requireActivity()) { data->
                try {
                    if (data != null && !data.isEmpty()) {
                        documentarySection = true
                        val subCategories: MutableList<SubCategory> = ArrayList()
                        for (c in data[0].subCategories!!) {
                            if (c.ottContents != null && !c.ottContents.isEmpty()) {
                                subCategories.add(c)
                            }
                        }
                        binding!!.documentaryParentRecyclerview.layoutManager =
                            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
                        moviesContentAdapter =
                            MoviesContentAdapter(subCategories, requireActivity(), false)
                        binding!!.documentaryParentRecyclerview.adapter = moviesContentAdapter
                        hideProgressBar()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    override fun onResume() {
        super.onResume()
        NavigationHelper.instanceNavHelper?.currentFragment =
            Constants.DOCUMENTARY_FRAGMENT
    }

    private fun setSlider(originals: List<Original>) {
        moviesSliderAdapter = MoviesSliderAdapter(originals, requireActivity())
        binding!!.documentarySliderVp.adapter = moviesSliderAdapter
        binding!!.documentarySliderVp.clipToPadding = false
        binding!!.documentarySliderVp.clipChildren = false
        binding!!.documentarySliderVp.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding!!.documentarySliderVp.setPageTransformer(compositePageTransformer)
        //binding.homeSliderVp.setCurrentItem(1);
        binding!!.documentarySliderVp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //Log.v("@@@@", position+" ");
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
        binding!!.indicatorContainerLl.removeAllViews()
        for (i in originals.indices) {
            val item = TextView(requireActivity())
            item.background = resources.getDrawable(R.drawable.slider_indicator_background)
            item.id = i
            val params = LinearLayout.LayoutParams(15, 15)
            params.setMargins(5, 0, 5, 0)
            //item.setLayoutParams(new ViewGroup.LayoutParams(30,30));
            item.layoutParams = params
            binding!!.indicatorContainerLl.addView(item)
        }
        binding!!.documentarySliderVp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding!!.indicatorContainerLl.getChildAt(prev1).background =
                    resources.getDrawable(R.drawable.slider_indicator_background)
                binding!!.indicatorContainerLl.getChildAt(position).background =
                    resources.getDrawable(R.drawable.slider_indicatior_back)
                prev1 = position
            }
        })
    }

    private val sliderRunnable = Runnable {
        if (binding != null) {
            val current = binding!!.documentarySliderVp.currentItem
            if (current == images.size - 1) {
                inc = false
            }
            if (current == 0) inc = true
            if (inc) binding!!.documentarySliderVp.currentItem =
                current + 1 else binding!!.documentarySliderVp.currentItem = current - 1
        }
    }

    private fun hideProgressBar() {
        if (slider && documentarySection) {
            binding!!.documentaryContainer.visibility = View.VISIBLE
            binding!!.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}