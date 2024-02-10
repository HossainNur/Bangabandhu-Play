package com.durbar.bangabandhuplay.ui.player

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.MainActivity
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.ActivityPlayerBinding
import com.durbar.bangabandhuplay.ui.more.MoreActivity
import com.durbar.bangabandhuplay.ui.tunes.TunesFragment
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.TrackSelectionDialog
import com.durbar.bangabandhuplay.utils.checkInternet
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class PlayerActivity : AppCompatActivity() {
    private var binding: ActivityPlayerBinding? = null
    private var title: String? = null
    private var viewModel: PlayerViewModel? = null
    private var uuid: String? = null
    private var videoPath: String? = null
    private var playerView: PlayerView? = null
    private var exoPlayer: ExoPlayer? = null
    private var btnFullScreen: ImageView? = null
    private var btnReplay: ImageView? = null
    private var btnForward: ImageView? = null
    private var btnBack: ImageView? = null
    private var btnLockscreen: ImageView? = null
    private var btnSetting: ImageView? = null
    private var contentTopSection: ConstraintLayout? = null
    private var moviesTitle: TextView? = null
    private var isShowingTrackSelectionDialog = false
    private val navController: NavController? = null
    var isFullScreen = false
    var isLock = false
    var ottContent = false
    var relatedContent = false
    var isMore = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(
            layoutInflater
        )
        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        uuid = intent.getStringExtra(Constants.CONTENT_UUID)
        setContentView(binding!!.root)
        this.checkInternet()
        title = intent.getStringExtra(Constants.CONTENT_SECTION_TITLE)
        isMore = intent.getBooleanExtra(Constants.CONTENT_IS_MORE, false)
        setSupportActionBar(binding!!.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar!!.title = title
        playerView = findViewById(R.id.video_player)
        btnFullScreen = findViewById(R.id.bt_fullscreen)
        btnReplay = findViewById(R.id.exo_replay)
        btnForward = findViewById(R.id.exo_forward)
        btnBack = findViewById(R.id.iv_back)
        moviesTitle = findViewById(R.id.tv_content_title)
        btnLockscreen = findViewById(R.id.exo_lock)
        btnSetting = findViewById(R.id.iv_setting)
        contentTopSection = findViewById(R.id.top_movies_section_container)
        viewModel?.fetchContent(uuid)?.observe(this) { singleOttContent ->
            try {
                if (singleOttContent?.data?.contentData != null) {
                    ottContent = true
                    val title = singleOttContent.data.contentData.title.orEmpty()
                    val description = singleOttContent.data.contentData.synopsisEnglish.orEmpty()
                    val releaseDate = singleOttContent.data.contentData.releaseDate?:""
                    val genre = singleOttContent.data.contentData.genre.orEmpty()
                    val runTime = singleOttContent.data.contentData.runtime.orEmpty()
                    val contentSourceList =
                        singleOttContent.data.contentData.contentSource.orEmpty()
                    if (title.isNotEmpty()) {
                        binding?.moviesTitle?.text = title
                        moviesTitle?.text = title
                    }
                    if (description.isNotEmpty()) binding?.moviesDescription?.text = description

                    if (singleOttContent.data.contentData.castAndCrews != null &&
                        singleOttContent.data.contentData.castAndCrews.isNotEmpty()
                    ) {
                        binding?.castCrewText?.visibility = View.VISIBLE
                        binding?.castCrewRv?.layoutManager =
                            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                        binding?.castCrewRv?.adapter =
                            CastCrewSliderAdapter(singleOttContent.data.contentData.castAndCrews)
                    } else binding?.castCrewText?.visibility = View.GONE

                    if (contentSourceList.isNotEmpty()) {
                        for (c in contentSourceList) {
                            if (c.sourceType.equals("content_path", true)) {
                                videoPath = c.contentSource.orEmpty()
                            }
                        }
                    }

                    if (videoPath?.isNotEmpty() == true) {
                        initializePlayer(videoPath!!)
                    }

                    if (runTime.isNotEmpty()) {
                        val timeSec = runTime.toLong()
                        val hours = (timeSec / 3600).toInt()
                        var temp = (timeSec - hours * 3600).toInt()
                        val mins = temp / 60
                        temp -= mins * 60
                        val secs = temp

                        val requiredFormat = "$hours h $mins m $secs s"
                        // content runtime
                        binding?.tvRunTime?.text = requiredFormat
                    }

                    if (releaseDate.isNotEmpty()) {
                        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        try {
                            val inputDate: Date = inputDateFormat.parse(releaseDate)!!
                            val outputDateFormat =
                                SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
                            val outputDateStr: String = outputDateFormat.format(inputDate)
                            binding?.tvReleaseDate?.text = outputDateStr
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    if (genre.isNotEmpty()) {
                        val gson = Gson()
                        val array: Array<String> = gson.fromJson(genre, Array<String>::class.java)

                        val detailsList = mutableListOf<String>()
                        for (item in array) {
                            detailsList.add(item.trim())
                        }

                        binding?.rvDetails?.layoutManager =
                            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                        binding?.rvDetails?.adapter = DetailsSectionAdapter(detailsList)

                    }
                    hideProgressBar()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        viewModel!!.getRelatedOttContents(uuid).observe(this) { singleContentRelatedContents->
                try {
                    if (singleContentRelatedContents != null && !singleContentRelatedContents.isEmpty()) {
                        relatedContent = true
                        binding!!.rvMoreLikeThis.layoutManager =
                            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                        binding!!.rvMoreLikeThis.adapter =
                            GetRelatedContentsAdapter(singleContentRelatedContents){ buuid->
                                if (!uuid.isNullOrEmpty()){
                                    finish()
                                    startActivity(Intent(this, PlayerActivity::class.java)
                                        .putExtra(Constants.CONTENT_UUID, uuid)
                                        .putExtra(Constants.CONTENT_SECTION_TITLE, title)
                                    )
                                }
                            }
                        hideProgressBar()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        btnReplay?.setOnClickListener(View.OnClickListener { view: View? ->
            if (exoPlayer!!.currentPosition == 0L) exoPlayer!!.seekTo(0) else {
                exoPlayer!!.seekTo(exoPlayer!!.currentPosition - 10000)
            }
        })
        btnForward?.setOnClickListener(View.OnClickListener { view: View? ->
            exoPlayer!!.seekTo(
                exoPlayer!!.currentPosition + 10000
            )
        })
        btnSetting?.setOnClickListener(View.OnClickListener { v: View? ->
            if (!isShowingTrackSelectionDialog && TrackSelectionDialog.willHaveContent(exoPlayer)) {
                isShowingTrackSelectionDialog = true
                val trackSelectionDialog = TrackSelectionDialog.createForPlayer(
                    exoPlayer
                )  /* onDismissListener= */
                { dismissedDialog: DialogInterface? -> isShowingTrackSelectionDialog = false }
                trackSelectionDialog.show(supportFragmentManager,  /* tag= */null)
            }
        })
        btnFullScreen?.setOnClickListener(View.OnClickListener { view: View? ->
            if (!isFullScreen) {
                btnFullScreen?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.baseline_fullscreen_exit_24
                    )
                )
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                val params = playerView?.getLayoutParams() as ConstraintLayout.LayoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.height = ViewGroup.LayoutParams.MATCH_PARENT
                playerView?.setLayoutParams(params)
                contentTopSection?.setVisibility(View.VISIBLE)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                binding!!.videoPlayer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            } else {
                videoFullScreen()
            }
            isFullScreen = !isFullScreen
        })
        btnBack?.setOnClickListener(View.OnClickListener { view: View? ->
            videoFullScreen()
            isFullScreen = !isFullScreen
        })
        btnLockscreen?.setOnClickListener(View.OnClickListener { view: View? ->
            //change icon base on toggle lock screen or unlock screen
            if (!isLock) {
                btnLockscreen?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.baseline_lock_24
                    )
                )
            } else {
                btnLockscreen?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.baseline_lock_open_24
                    )
                )
            }
            isLock = !isLock
            //method for toggle will do next
            lockScreen(isLock)
        })
    }

    private fun hideProgressBar() {
        if (ottContent && relatedContent) {
            binding!!.contentSectionContainer.visibility = View.VISIBLE
            binding!!.progressBar.visibility = View.GONE
        }
    }

    fun lockScreen(lock: Boolean) {
        //just hide the control for lock screen and vise versa
        val sec_mid = findViewById<LinearLayout>(R.id.sec_controlvid1)
        val sec_bottom = findViewById<LinearLayout>(R.id.sec_controlvid2)
        if (lock) {
            sec_mid.visibility = View.INVISIBLE
            sec_bottom.visibility = View.INVISIBLE
            btnBack!!.visibility = View.INVISIBLE
            moviesTitle!!.visibility = View.INVISIBLE
            btnSetting!!.visibility = View.INVISIBLE
        } else {
            sec_mid.visibility = View.VISIBLE
            sec_bottom.visibility = View.VISIBLE
            btnBack!!.visibility = View.VISIBLE
            moviesTitle!!.visibility = View.VISIBLE
            btnSetting!!.visibility = View.VISIBLE
            moviesTitle!!.visibility = View.VISIBLE
        }
    }

    private fun videoFullScreen() {
        btnFullScreen!!.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.baseline_fullscreen_24
            )
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val params = playerView!!.layoutParams as ConstraintLayout.LayoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = (200 * applicationContext.resources.displayMetrics.density).toInt()
        playerView!!.layoutParams = params
        contentTopSection!!.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun initializePlayer(videoPath: String) {
        try {
            val videoUri = Uri.parse(videoPath)
            exoPlayer = ExoPlayer.Builder(this).build()
            binding!!.videoPlayer.player = exoPlayer
            val mediaItem = MediaItem.fromUri(videoUri)
            exoPlayer!!.setMediaItem(mediaItem)
            exoPlayer!!.playWhenReady = true
            exoPlayer!!.prepare()
            exoPlayer!!.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        if (exoPlayer != null) {
            exoPlayer!!.pause()
            exoPlayer!!.playWhenReady = false
        }
    }

    override fun onStop() {
        super.onStop()
        if (exoPlayer != null) {
            exoPlayer!!.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        if (exoPlayer != null) {
            exoPlayer!!.release()
            exoPlayer = null
        }
    }

    override fun onBackPressed() {
        if (Constants.IS_MORE_CONTENT) {
            val id = Constants.getSharedPref(this, Constants.CONTENT_ID)
            val slug = Constants.getSharedPref(this, Constants.CONTENT_SLUG)
            val title = Constants.getSharedPref(this, Constants.CONTENT_SECTION_TITLE)
            if (Constants.IS_MORE_HOME) {
                val intent = Intent(this, MoreActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.putExtra(Constants.CONTENT_ID, id)
                intent.putExtra(Constants.CONTENT_SECTION_TITLE, title)
                intent.putExtra(Constants.CONTENT_IS_HOME, true)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MoreActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.putExtra(Constants.CONTENT_SECTION_TITLE, title)
                intent.putExtra(Constants.CONTENT_SLUG, slug)
                intent.putExtra(Constants.CONTENT_IS_HOME, false)
                startActivity(intent)
                finish()
            }
            Constants.IS_MORE_CONTENT = false
        }else if (Constants.IS_TUNES){

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val tunesFragment = TunesFragment()

            fragmentTransaction.replace(R.id.player_container, tunesFragment)
            fragmentTransaction.addToBackStack(null) // Add this line if you want to allow the user to navigate back to the previous fragment
            fragmentTransaction.commit()
            finish()


        }
        else {
            Constants.IS_FROM_PLAYER = true
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}