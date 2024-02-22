package live.durbar.bangabandhuapp.ui.more

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import live.durbar.bangabandhuapp.MainActivity
import live.durbar.bangabandhuapp.R
import live.durbar.bangabandhuapp.databinding.ActivityMoreBinding
import live.durbar.bangabandhuapp.ui.search.SearchResultActivity
import live.durbar.bangabandhuapp.utils.Constants
import live.durbar.bangabandhuapp.utils.checkInternet
import live.durbar.bangabandhuapp.utils.observeInternetConnection

class MoreActivity : AppCompatActivity() {
    private var binding: ActivityMoreBinding? = null
    private var id: String? = null
    private var title: String? = null
    private var slug: String? = null
    private var viewModel: MoreHomeViewModel? = null
    private var moreSection = false
    private var isHome = false
    private val isMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MoreHomeViewModel::class.java)
        setContentView(binding!!.root)

        this.checkInternet()
        observeInternetConnection()

        id = intent.getStringExtra(Constants.CONTENT_ID)
        title = intent.getStringExtra(Constants.CONTENT_SECTION_TITLE)
        slug = intent.getStringExtra(Constants.CONTENT_SLUG)
        isHome = intent.getBooleanExtra(Constants.CONTENT_IS_HOME, false)
        setSupportActionBar(binding!!.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar!!.title = title
        if (isHome) {
            id?.let {
                viewModel!!.getContentHome(it).observe(this) { customContentBySlug ->
                    try {
                        if (customContentBySlug?.data?.contents != null) {
                            moreSection = true
                            binding?.watchTrailerRv?.layoutManager = GridLayoutManager(this, 2)
                            binding?.watchTrailerRv?.adapter =
                                MoreHomeAdapter(
                                    customContentBySlug?.data.contents,
                                    this,
                                    title ?: "",
                                    isMore
                                )
                            hideProgressBar()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } else {
            slug?.let {
                viewModel?.getSingleSubCategoryContents(it)?.observe(this) { singleSubCategoryRes ->
                    try {
                        if (singleSubCategoryRes?.data?.ottContents != null) {
                            moreSection = true
                            binding?.watchTrailerRv?.layoutManager = GridLayoutManager(this, 2)
                            binding?.watchTrailerRv?.adapter =
                                MoreOthersAdapter(
                                    singleSubCategoryRes.data.ottContents,
                                    this,
                                    title ?: "",
                                    isMore
                                )
                            hideProgressBar()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
        binding!!.ivSearch.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    applicationContext, SearchResultActivity::class.java
                )
            )
        }
    }

    private fun hideProgressBar() {
        if (moreSection) {
            binding!!.watchTrailerRv.visibility = View.VISIBLE
            binding!!.progressBar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}