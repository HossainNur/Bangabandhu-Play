package com.durbar.bangabandhuplay.ui.movies

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
import com.durbar.bangabandhuplay.data.model.category.root.single.CategorySlider
import com.durbar.bangabandhuplay.data.model.category.root.single.Data
import com.durbar.bangabandhuplay.data.model.category.root.single.SubCategory
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.databinding.FragmentMoviesBinding
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.NavigationHelper

class MoviesFragment : Fragment() {
    private var binding: FragmentMoviesBinding? = null
    private var moviesViewModel: MoviesViewModel? = null
    private var isMoviesOriginalList: MutableList<Original>? = null
    private val categorySliderList: List<CategorySlider> = ArrayList()
    private var images: List<Original> = ArrayList()
    private var moviesSliderAdapter: MoviesSliderAdapter? = null
    private var moviesContentAdapter: MoviesContentAdapter? = null
    private val sliderHandler = Handler()
    private var inc = true
    private var prev1 = 0
    private var slider = false
    private var moviesSection = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel!!.sliders.observe(requireActivity()) { originals: List<Original>? ->
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
        moviesViewModel!!.fetchMoviesCategory("speech-of-bangabandhu")
            .observe(requireActivity()) { data: List<Data>? ->
                try {
                    if (data != null && !data.isEmpty()) {

                        /*if (data.get(0).getCategorySliders() != null && !data.get(0).getCategorySliders().isEmpty()){
                        slider = true;
                        binding.moviesSliderVp.setVisibility(View.VISIBLE);
                        categorySliderList = data.get(0).getCategorySliders();
                        setSlider(categorySliderList );
                    }else {
                        binding.moviesSliderVp.setVisibility(View.GONE);
                    }*/
                        moviesSection = true
                        val subCategories: MutableList<SubCategory> = ArrayList()
                        for (c in data[0].subCategories!!) {
                            if (c.ottContents != null && !c.ottContents.isEmpty()) {
                                subCategories.add(c)
                            }
                        }
                        binding!!.moviesParentRecyclerview.layoutManager =
                            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
                        moviesContentAdapter =
                            MoviesContentAdapter(subCategories, requireActivity())
                        binding!!.moviesParentRecyclerview.adapter = moviesContentAdapter
                        hideProgressBar()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    /*private void setSlider(List<CategorySlider> categorySliderList) {
        binding.moviesSliderVp.setAdapter(new TunesSliderAdapter(categorySliderList,requireActivity()));
        binding.moviesSliderVp.setClipToPadding(false);
        binding.moviesSliderVp.setClipChildren(false);
        binding.moviesSliderVp.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        binding.moviesSliderVp.setPageTransformer(compositePageTransformer);
        //binding.homeSliderVp.setCurrentItem(1);
        binding.moviesSliderVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //Log.v("@@@@", position+" ");

                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        binding.indicatorContainerLl.removeAllViews();

        for (int i = 0; i < categorySliderList.size(); i++) {
            TextView item = new TextView(requireActivity());

            item.setBackground(getResources().getDrawable(R.drawable.slider_indicator_background));
            item.setId(i);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(5, 0, 5, 0);
            //item.setLayoutParams(new ViewGroup.LayoutParams(30,30));
            item.setLayoutParams(params);

            binding.indicatorContainerLl.addView(item);
        }


        binding.moviesSliderVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.indicatorContainerLl.getChildAt(prev1).setBackground(getResources().getDrawable(R.drawable.slider_indicator_background));
                binding.indicatorContainerLl.getChildAt(position).setBackground(getResources().getDrawable(R.drawable.slider_indicatior_back));
                prev1 = position;
            }
        });
    }*/
    override fun onResume() {
        super.onResume()
        NavigationHelper.instanceNavHelper?.currentFragment = Constants.MOVIES_FRAGMENT
    }

    private fun hideProgressBar() {
        if (slider && moviesSection) {
            binding!!.moviesContainer.visibility = View.VISIBLE
            binding!!.progressBar.visibility = View.GONE
        }
    }

    private fun setSlider(originals: List<Original>) {
        moviesSliderAdapter = MoviesSliderAdapter(originals, requireActivity())
        binding!!.moviesSliderVp.adapter = moviesSliderAdapter
        binding!!.moviesSliderVp.clipToPadding = false
        binding!!.moviesSliderVp.clipChildren = false
        binding!!.moviesSliderVp.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding!!.moviesSliderVp.setPageTransformer(compositePageTransformer)
        //binding.homeSliderVp.setCurrentItem(1);
        binding!!.moviesSliderVp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
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
        binding!!.moviesSliderVp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
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
            val current = binding!!.moviesSliderVp.currentItem
            if (current == images.size - 1) {
                inc = false
            }
            if (current == 0) inc = true
            if (inc) binding!!.moviesSliderVp.currentItem =
                current + 1 else binding!!.moviesSliderVp.currentItem = current - 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}