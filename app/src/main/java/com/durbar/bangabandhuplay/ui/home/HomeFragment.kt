package com.durbar.bangabandhuplay.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.databinding.FragmentHomeBinding
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.NavigationHelper
import com.durbar.bangabandhuplay.utils.NetworkUtils
import com.durbar.bangabandhuplay.utils.checkInternet

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private var homeViewModel: HomeViewModel? = null
    private var homeSliderAdapter: HomeSliderAdapter? = null
    private val isHomeOriginalList: MutableList<Original> = ArrayList()
    private var images: List<Original> = ArrayList()
    private val sliderHandler = Handler()
    private var inc = true
    private var adapter: ParentItemAdapter? = null
    private var slider = false
    private var frontendSection = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireContext().checkInternet()

        homeViewModel!!.sliders.observe(requireActivity()) { originals: List<Original>? ->
            try {
                if (originals != null) {
                    slider = true
                    for (isHomeList in originals) {
                        if (isHomeList.isHome == 1) {
                            isHomeOriginalList.add(isHomeList)
                        }
                    }
                    images = isHomeOriginalList
                    setSlider(isHomeOriginalList)
                    hideProgressBar()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        homeViewModel!!.frontendSection.observe(requireActivity()) { data ->
            try {
                if (data != null && !data.isEmpty()) {
              /*      frontendSection = true
                    val dataList: MutableList<Data> = ArrayList()
                    for (d in data) {
                        if (d.frontendCustomContent != null) {
                            dataList.add(d)
                        }
                    }
                    binding!!.homeParentRecyclerView.layoutManager =
                        LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
                    adapter = ParentItemAdapter(dataList, requireActivity())
                    binding!!.homeParentRecyclerView.adapter = adapter
                    hideProgressBar()*/

                    frontendSection = true
                    val concatAdapter = ConcatAdapter()
                    data.forEach {
                        concatAdapter.addAdapter(CommonAdapter(it))
                    }
                    binding!!.homeParentRecyclerView.adapter = concatAdapter
                    hideProgressBar()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        NavigationHelper.instanceNavHelper?.currentFragment = Constants.HOME_FRAGMENT
    }

    private fun hideProgressBar() {
        if (slider && frontendSection) {
            binding!!.homeContainer.visibility = View.VISIBLE
            binding!!.progressBar.visibility = View.GONE
        }
    }

    private fun setSlider(originals: List<Original>) {
        homeSliderAdapter = HomeSliderAdapter(originals, requireActivity())
        binding!!.homeSliderVp.adapter = homeSliderAdapter
        binding!!.homeSliderVp.clipToPadding = false
        binding!!.homeSliderVp.clipChildren = false
        binding!!.homeSliderVp.offscreenPageLimit = 3
        binding!!.homeSliderVp.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(5))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding!!.homeSliderVp.setPageTransformer(compositePageTransformer)
        //binding.homeSliderVp.setCurrentItem(1);
        binding!!.homeSliderVp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //Log.v("@@@@", position+" ");
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
    }

    private val sliderRunnable = Runnable {
        if (binding != null) {
            val current = binding!!.homeSliderVp.currentItem
            if (current == images.size - 1) {
                inc = false
            }
            if (current == 0) inc = true
            if (inc) binding!!.homeSliderVp.currentItem =
                current + 1 else binding!!.homeSliderVp.currentItem = current - 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}