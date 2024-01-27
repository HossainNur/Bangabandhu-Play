package com.durbar.bangabandhuplay.ui.more;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.ActivityMoreBinding;
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity;
import com.durbar.bangabandhuplay.utils.Constants;

public class MoreActivity extends AppCompatActivity {

    private ActivityMoreBinding binding;
    private String id, title, slug;
    private MoreHomeViewModel viewModel;
    private boolean moreSection = false, isHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MoreHomeViewModel.class);
        setContentView(binding.getRoot());

        id = getIntent().getStringExtra(Constants.CONTENT_ID);
        title = getIntent().getStringExtra(Constants.CONTENT_SECTION_TITLE);
        slug = getIntent().getStringExtra(Constants.CONTENT_SLUG);
        isHome = getIntent().getBooleanExtra(Constants.CONTENT_IS_HOME, false);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle(title);


        if (isHome) {
            viewModel.getContentHome(id).observe(this, customContentBySlug -> {
                try {
                    if (customContentBySlug.getData().getContents() != null) {
                        moreSection = true;
                        binding.watchTrailerRv.setLayoutManager(new GridLayoutManager(this, 2));
                        binding.watchTrailerRv.setAdapter(new MoreHomeAdapter(customContentBySlug.getData().getContents(), this,title));
                        hideProgressBar();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            viewModel.getSingleSubCategoryContents(slug).observe(this, singleSubCategoryRes -> {
                if (singleSubCategoryRes.getData().getOttContents() != null) {
                    moreSection = true;
                    binding.watchTrailerRv.setLayoutManager(new GridLayoutManager(this, 2));
                    binding.watchTrailerRv.setAdapter(new MoreOthersAdapter(singleSubCategoryRes.getData().getOttContents(), this));
                    hideProgressBar();
                }

            });
        }


        binding.ivSearch.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SearchResultActivity.class));
        });
    }

    private void hideProgressBar() {
        if (moreSection) {
            binding.watchTrailerRv.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        finish();
        return super.onOptionsItemSelected(item);
    }
}