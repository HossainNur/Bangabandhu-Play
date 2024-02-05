package com.durbar.bangabandhuplay.ui.movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.data.model.category.root.single.CategorySlider;
import com.durbar.bangabandhuplay.data.model.category.root.single.SubCategory;
import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.databinding.FragmentMoviesBinding;
import com.durbar.bangabandhuplay.ui.tunes.TunesSliderAdapter;
import com.durbar.bangabandhuplay.utils.Constants;
import com.durbar.bangabandhuplay.utils.NavigationHelper;

import java.util.ArrayList;
import java.util.List;


public class MoviesFragment extends Fragment {
    private FragmentMoviesBinding binding;
    private MoviesViewModel moviesViewModel;
    private List<Original> isMoviesOriginalList;
    private List<CategorySlider> categorySliderList = new ArrayList<>();
    private List<Original> images = new ArrayList<>();
    private MoviesSliderAdapter moviesSliderAdapter;
    private MoviesContentAdapter moviesContentAdapter;
    private Handler sliderHandler = new Handler();
    private boolean inc = true;
    private int prev1 = 0;
    private boolean slider = false, moviesSection = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesViewModel.getSliders().observe(requireActivity(), originals -> {
            try {
                if (originals != null) {
                    slider = true;
                    isMoviesOriginalList = new ArrayList<>();
                    for (Original isHomeList : originals) {
                        if (isHomeList.isHome() == 0) {
                            isMoviesOriginalList.add(isHomeList);
                        }
                    }
                    images = isMoviesOriginalList;
                    setSlider(isMoviesOriginalList);
                    hideProgressBar();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        moviesViewModel.fetchMoviesCategory("speech-of-bangabandhu").observe(requireActivity(), data -> {
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

                    moviesSection = true;
                    List<SubCategory> subCategories = new ArrayList<>();
                    for (SubCategory c: data.get(0).getSubCategories()){
                        if (c.getOttContents() !=null && !c.getOttContents().isEmpty()){
                            subCategories.add(c);
                        }
                    }
                    binding.moviesParentRecyclerview.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
                    moviesContentAdapter = new MoviesContentAdapter(subCategories, requireActivity());
                    binding.moviesParentRecyclerview.setAdapter(moviesContentAdapter);
                    hideProgressBar();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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

    @Override
    public void onResume() {
        super.onResume();
        NavigationHelper.getINSTANCE().setCurrentFragment(Constants.MOVIES_FRAGMENT);
    }

    private void hideProgressBar() {
        if (slider && moviesSection) {
            binding.moviesContainer.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private void setSlider(List<Original> originals) {
        moviesSliderAdapter = new MoviesSliderAdapter(originals,requireActivity());
        binding.moviesSliderVp.setAdapter(moviesSliderAdapter);
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

        for (int i = 0; i < originals.size(); i++) {
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
    }



    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (binding != null) {
                int current = binding.moviesSliderVp.getCurrentItem();
                if (current == images.size() - 1) {
                    inc = false;
                }
                if (current == 0) inc = true;
                if (inc) binding.moviesSliderVp.setCurrentItem(current + 1);
                else binding.moviesSliderVp.setCurrentItem(current - 1);
            }

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}