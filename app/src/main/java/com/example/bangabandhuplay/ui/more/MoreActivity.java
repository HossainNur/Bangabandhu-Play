package com.example.bangabandhuplay.ui.more;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.bangabandhuplay.R;
import com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider.SectionSliders;
import com.example.bangabandhuplay.databinding.ActivityMoreBinding;
import com.example.bangabandhuplay.utils.Constants;

import java.util.List;

public class MoreActivity extends AppCompatActivity {

    private ActivityMoreBinding binding;
    private String slug,title;
    private MoreHomeViewModel moreHomeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreBinding.inflate(getLayoutInflater());
        moreHomeViewModel = new ViewModelProvider(this).get(MoreHomeViewModel.class);
        setContentView(binding.getRoot());

        slug = getIntent().getStringExtra(Constants.CONTENT_SLUG);
        title = getIntent().getStringExtra(Constants.CONTENT_SECTION_TITLE);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle(title);


        //slider
        moreHomeViewModel.getSlider(slug).observe(this, frontendCustomContentSlider -> {
            try {
                //binding.sliderViewpagerContainer.setVisibility(View.VISIBLE);
                setTopSlider(frontendCustomContentSlider.getData().getFrontendCustomContentSlider());
            }catch (Exception e){
                //binding.sliderViewpagerContainer.setVisibility(View.GONE);
                e.printStackTrace();
            }

        });

        moreHomeViewModel.getContentHome(slug).observe(this, customContentBySlug -> {
            try {
                binding.watchTrailerRv.setLayoutManager(new GridLayoutManager(this, 2));
                binding.watchTrailerRv.setAdapter(new MoreHomeAdapter(customContentBySlug.getData().getOriginal(), this));
            }catch (Exception e){
                e.printStackTrace();
            }

        });

    }

    private void setTopSlider(List<SectionSliders> sliders) {

        SectionSliderAdapter sliderAdapter = new SectionSliderAdapter(sliders,this);
        binding.sliderVp.setAdapter(sliderAdapter);
        binding.sliderVp.setClipToPadding(false);
        binding.sliderVp.setClipChildren(false);
        binding.sliderVp.setOffscreenPageLimit(3);
        binding.sliderVp.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        binding.sliderVp.setPageTransformer(compositePageTransformer);
        binding.sliderVp.setCurrentItem(1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        finish();
        return super.onOptionsItemSelected(item);
    }
}