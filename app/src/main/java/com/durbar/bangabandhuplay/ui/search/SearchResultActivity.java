package com.durbar.bangabandhuplay.ui.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.ActivitySearchResultBinding;
import com.durbar.bangabandhuplay.utils.NetworkUtil;

public class SearchResultActivity extends AppCompatActivity {

    private ActivitySearchResultBinding binding;
    private SearchContentsViewModel viewModel;
    private static int SPLASH_TIME_OUT = 2000;
    private NetworkUtil networkUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(SearchContentsViewModel.class);
        networkUtil = NetworkUtil.getInstance(this);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle(R.string.search);

        if (!networkUtil.isNetworkAvailable()) {
            NetworkUtil.showNoInternetDialog(this);
        }

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    showProgressBar();
                    fetchSearchContent(query.trim());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    binding.tvFound.setVisibility(View.GONE);
                    binding.searchResultRv.setVisibility(View.GONE);
                    binding.progressBar.setVisibility(View.GONE);
                }
                else {
                    showProgressBar();
                    binding.searchResultRv.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fetchSearchContent(newText.trim());
                        }
                    },SPLASH_TIME_OUT);

                }
                return false;
            }
        });
    }

    private void fetchSearchContent(String query) {
        viewModel.getSearchContents(query).observe(this, searchResultRes -> {
            try {
                if (searchResultRes.getData() != null && !searchResultRes.getData().isEmpty()) {
                    binding.searchResultRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                    binding.searchResultRv.setAdapter(new SearchContentsAdapter(searchResultRes.getData(), this));
                    binding.progressBar.setVisibility(View.GONE);
                    binding.tvFound.setVisibility(View.GONE);
                    binding.searchResultRv.setVisibility(View.VISIBLE);
                } else {
                    binding.searchResultRv.setVisibility(View.GONE);
                    binding.progressBar.setVisibility(View.GONE);
                    binding.tvFound.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        finish();
        return super.onOptionsItemSelected(item);
    }
}