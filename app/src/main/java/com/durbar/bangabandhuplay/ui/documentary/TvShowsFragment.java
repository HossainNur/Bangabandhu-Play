package com.durbar.bangabandhuplay.ui.documentary;

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
import com.durbar.bangabandhuplay.data.model.category.root.single.SubCategory;

import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.databinding.FragmentTvShowsBinding;
import com.durbar.bangabandhuplay.ui.movies.MoviesContentAdapter;
import com.durbar.bangabandhuplay.ui.movies.MoviesSliderAdapter;
import com.durbar.bangabandhuplay.ui.movies.MoviesViewModel;
import com.durbar.bangabandhuplay.utils.Constants;
import com.durbar.bangabandhuplay.utils.NavigationHelper;
import com.durbar.bangabandhuplay.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

public class TvShowsFragment extends Fragment {
    private FragmentTvShowsBinding binding;
    private MoviesViewModel viewModel;
    private MoviesSliderAdapter moviesSliderAdapter;

    private List<Original> isMoviesOriginalList;
    private MoviesContentAdapter moviesContentAdapter;
    private List<Original> images = new ArrayList<>();
    private boolean slider = false, documentarySection = false;
    private int prev1 = 0;
    private boolean inc = true;
    private Handler sliderHandler = new Handler();
    private NetworkUtil networkUtil;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTvShowsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        networkUtil = NetworkUtil.getInstance(requireActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!networkUtil.isNetworkAvailable()) {
            NetworkUtil.showNoInternetDialog(requireActivity());
        }

        viewModel.getSliders().observe(requireActivity(), originals -> {
            try {
                if (originals != null) {
                    slider = true;
                    isMoviesOriginalList = new ArrayList<>();
                    for (Original isHomeList : originals) {
                        if (isHomeList.getIsHome() == 0) {
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

        viewModel.fetchMoviesCategory("documentary").observe(requireActivity(), data -> {
            try {
                if (data != null && !data.isEmpty()) {
                    documentarySection = true;
                    List<SubCategory> subCategories = new ArrayList<>();
                    for (SubCategory c: data.get(0).getSubCategories()){
                        if (c.getOttContents() != null && !c.getOttContents().isEmpty()){
                            subCategories.add(c);
                        }
                    }
                    binding.documentaryParentRecyclerview.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
                    moviesContentAdapter = new MoviesContentAdapter(subCategories, requireActivity(),false);
                    binding.documentaryParentRecyclerview.setAdapter(moviesContentAdapter);
                    hideProgressBar();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationHelper.getINSTANCE().setCurrentFragment(Constants.DOCUMENTARY_FRAGMENT);
    }

    private void setSlider(List<Original> originals) {
        moviesSliderAdapter = new MoviesSliderAdapter(originals,requireActivity());
        binding.documentarySliderVp.setAdapter(moviesSliderAdapter);
        binding.documentarySliderVp.setClipToPadding(false);
        binding.documentarySliderVp.setClipChildren(false);
        binding.documentarySliderVp.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        binding.documentarySliderVp.setPageTransformer(compositePageTransformer);
        //binding.homeSliderVp.setCurrentItem(1);
        binding.documentarySliderVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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


        binding.documentarySliderVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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
                int current = binding.documentarySliderVp.getCurrentItem();
                if (current == images.size() - 1) {
                    inc = false;
                }
                if (current == 0) inc = true;
                if (inc) binding.documentarySliderVp.setCurrentItem(current + 1);
                else binding.documentarySliderVp.setCurrentItem(current - 1);
            }

        }
    };

    private void hideProgressBar() {
        if (slider && documentarySection) {
            binding.documentaryContainer.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}