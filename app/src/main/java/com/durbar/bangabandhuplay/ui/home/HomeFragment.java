package com.durbar.bangabandhuplay.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data;
import com.durbar.bangabandhuplay.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private HomeSliderAdapter homeSliderAdapter;
    private List<Original> isHomeOriginalList = new ArrayList<>();
    private List<Original> images = new ArrayList<>();
    private Handler sliderHandler = new Handler();
    private boolean inc = true;
    private ParentItemAdapter adapter;
    private boolean slider = false, frontendSection = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        homeViewModel.getSliders().observe(requireActivity(), originals -> {
            try {
                if (originals != null) {
                    slider = true;
                    for (Original isHomeList : originals) {
                        if (isHomeList.getIsHome() == 1) {
                            isHomeOriginalList.add(isHomeList);
                        }
                    }
                    images = isHomeOriginalList;
                    setSlider(isHomeOriginalList);
                    hideProgressBar();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        homeViewModel.getFrontendSection().observe(requireActivity(), data -> {
            try {
                if (data != null && !data.isEmpty()) {
                    frontendSection = true;
                    List<Data> dataList = new ArrayList<>();
                    for (Data d : data) {
                        if (d.getFrontendCustomContent() != null) {
                            dataList.add(d);
                        }
                    }

                    binding.homeParentRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
                    adapter = new ParentItemAdapter(dataList, requireActivity());
                    binding.homeParentRecyclerView.setAdapter(adapter);
                    hideProgressBar();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private void hideProgressBar() {
        if (slider && frontendSection) {
            binding.homeContainer.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private void setSlider(List<Original> originals) {
        homeSliderAdapter = new HomeSliderAdapter(originals, requireActivity());
        binding.homeSliderVp.setAdapter(homeSliderAdapter);
        binding.homeSliderVp.setClipToPadding(false);
        binding.homeSliderVp.setClipChildren(false);
        binding.homeSliderVp.setOffscreenPageLimit(3);
        binding.homeSliderVp.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(5));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        binding.homeSliderVp.setPageTransformer(compositePageTransformer);
        //binding.homeSliderVp.setCurrentItem(1);
        binding.homeSliderVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //Log.v("@@@@", position+" ");

                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });
    }

    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (binding != null) {
                int current = binding.homeSliderVp.getCurrentItem();
                if (current == images.size() - 1) {
                    inc = false;
                }
                if (current == 0) inc = true;
                if (inc) binding.homeSliderVp.setCurrentItem(current + 1);
                else binding.homeSliderVp.setCurrentItem(current - 1);
            }

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}