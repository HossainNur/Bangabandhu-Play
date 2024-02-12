package com.durbar.bangabandhuplay.ui.more;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.durbar.bangabandhuplay.MainActivity;
import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.ActivityMoreBinding;
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.durbar.bangabandhuplay.utils.NetworkUtil;

public class MoreActivity extends AppCompatActivity {

    private ActivityMoreBinding binding;
    private String id = null, title = null, slug = null;
    private MoreHomeViewModel viewModel;
    private boolean moreSection = false, isHome,isMore = true;
    private NetworkUtil networkUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MoreHomeViewModel.class);
        networkUtil = NetworkUtil.getInstance(this);
        setContentView(binding.getRoot());

        id = getIntent().getStringExtra(Constants.CONTENT_ID);
        title = getIntent().getStringExtra(Constants.CONTENT_SECTION_TITLE);
        slug = getIntent().getStringExtra(Constants.CONTENT_SLUG);
        isHome = getIntent().getBooleanExtra(Constants.CONTENT_IS_HOME, false);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle(title);

        if (!networkUtil.isNetworkAvailable()) {
            NetworkUtil.showNoInternetDialog(this);
        }


        if (isHome) {
            if (id != null){
                viewModel.getContentHome(id).observe(this, customContentBySlug -> {
                    try {
                        if (customContentBySlug.getData().getContents() != null) {
                            moreSection = true;
                            binding.watchTrailerRv.setLayoutManager(new GridLayoutManager(this, 2));
                            binding.watchTrailerRv.setAdapter(new MoreHomeAdapter(customContentBySlug.getData().getContents(), this,title,isMore));
                            hideProgressBar();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        } else {
            if (slug != null){
                viewModel.getSingleSubCategoryContents(slug).observe(this, singleSubCategoryRes -> {
                    try {
                        if (singleSubCategoryRes.getData().getOttContents() != null) {
                            moreSection = true;
                            binding.watchTrailerRv.setLayoutManager(new GridLayoutManager(this, 2));
                            binding.watchTrailerRv.setAdapter(new MoreOthersAdapter(singleSubCategoryRes.getData().getOttContents(), this,title,isMore));
                            hideProgressBar();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
            }

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
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}