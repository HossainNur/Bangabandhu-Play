package com.durbar.bangabandhuplay.ui.search

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.search_content.SearchResultRes
import com.durbar.bangabandhuplay.databinding.ActivitySearchResultBinding
import com.durbar.bangabandhuplay.utils.checkInternet
import com.durbar.bangabandhuplay.utils.observeInternetConnection

class SearchResultActivity() : AppCompatActivity() {
    private var binding: ActivitySearchResultBinding? = null
    private var viewModel: SearchContentsViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(
            layoutInflater
        )
        viewModel = ViewModelProvider(this).get(SearchContentsViewModel::class.java)
        setContentView(binding!!.root)

        this.checkInternet()
        observeInternetConnection()

        setSupportActionBar(binding!!.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar!!.setTitle(R.string.search)
        binding!!.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    showProgressBar()
                    fetchSearchContent(query.trim { it <= ' ' })
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    binding!!.tvFound.visibility = View.GONE
                    binding!!.searchResultRv.visibility = View.GONE
                    binding!!.progressBar.visibility = View.GONE
                } else {
                    showProgressBar()
                    binding!!.searchResultRv.visibility = View.GONE
                    Handler().postDelayed(
                        Runnable { fetchSearchContent(newText.trim { it <= ' ' }) },
                        SPLASH_TIME_OUT.toLong()
                    )
                }
                return false
            }
        })
    }

    private fun fetchSearchContent(query: String) {
        viewModel!!.getSearchContents(query).observe(this) { searchResultRes ->
            try {
                if (searchResultRes?.data != null && !searchResultRes.data.isEmpty()) {
                    binding!!.searchResultRv.setLayoutManager(
                        LinearLayoutManager(
                            this,
                            RecyclerView.VERTICAL,
                            false
                        )
                    )
                    binding!!.searchResultRv.setAdapter(
                        SearchContentsAdapter(
                            searchResultRes.data,
                            this
                        )
                    )
                    binding!!.progressBar.setVisibility(View.GONE)
                    binding!!.tvFound.setVisibility(View.GONE)
                    binding!!.searchResultRv.setVisibility(View.VISIBLE)
                } else {
                    binding!!.searchResultRv.setVisibility(View.GONE)
                    binding!!.progressBar.setVisibility(View.GONE)
                    binding!!.tvFound.setVisibility(View.VISIBLE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showProgressBar() {
        binding!!.progressBar.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val SPLASH_TIME_OUT = 2000
    }
}