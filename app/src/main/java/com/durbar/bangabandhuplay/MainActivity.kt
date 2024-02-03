package com.durbar.bangabandhuplay

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Process
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.durbar.bangabandhuplay.R.id
import com.durbar.bangabandhuplay.databinding.ActivityMainBinding
import com.durbar.bangabandhuplay.ui.live.LiveStreamingViewModel
import com.durbar.bangabandhuplay.ui.live.StreamingActivity
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.NavigationHelper
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var doubleBackToExitPressedOnce = false
    private var liveClose = false
    private var viewModel: LiveStreamingViewModel? = null
    private var navController: NavController? = null
    private val handler = Handler()
    private var runnable: Runnable? = null
    private val delayTime = 5000
    private var mRtcEngine: RtcEngine? = null
    private var userRole = 1
    private var token: String? = null
    private var appId: String? = null
    private var channelName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LiveStreamingViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding!!.drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding!!.drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        setSupportActionBar(binding!!.homeToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""
        binding!!.appTopBarLayout.visibility = View.VISIBLE
        val navHostFragment =
            supportFragmentManager.findFragmentById(id.nav_host_fragment_activity_main) as NavHostFragment?
        navController = navHostFragment!!.navController
        setupWithNavController(binding!!.navView, navController!!)
        binding!!.ivSearch.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    applicationContext, SearchResultActivity::class.java
                )
            )
        }

        //NAVIGATION DRAWER ITEM SELECTED LISTENER
        binding!!.navDrawer.setNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId == id.familyMemberFragment) {
                navController!!.navigate(id.familyMemberFragment)
                NavigationHelper.getINSTANCE().appBarLayout = binding!!.appTopBarLayout
                unCheckableBottomNavigation()
            } else if (item.itemId == id.live) {
                startActivity(Intent(applicationContext, StreamingActivity::class.java))
            } else if (item.itemId == id.pathshala) {
                navController!!.navigate(id.pathshala)
                unCheckableBottomNavigation()
            } else if (item.itemId == id.tunes) {
                navController!!.navigate(id.tunes)
                unCheckableBottomNavigation()
            } else {
                navController!!.navigate(id.photoGalleryFragment)
                unCheckableBottomNavigation()
            }
            binding!!.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        binding!!.navView.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == id.navigation_home) {
                navController!!.navigate(id.navigation_home)
                binding!!.navView.menu.getItem(Constants.HOME).setCheckable(true)
            } else if (item.itemId == id.navigation_movies) {
                navController!!.navigate(id.navigation_movies)
                binding!!.navView.menu.getItem(Constants.MOVIES).setCheckable(true)
            } else {
                navController!!.navigate(id.navigation_tvShows)
                binding!!.navView.menu.getItem(Constants.DOCUMENTARY).setCheckable(true)
            }
            true
        }
        checkCurrentBottomNav()
        binding!!.liveStreamingClose.setOnClickListener { v: View? ->
            binding!!.liveStreamingContainer.visibility = View.GONE
            liveClose = true
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delayTime.toLong())
            fetchActiveLive()
        }.also { runnable = it }, delayTime.toLong())
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
    }

    private fun fetchActiveLive() {
        viewModel!!.liveStreaming.observe(this) { data ->
            try {
                if (data != null && data.getStatus() == 1) {
                    channelName = data.getChannelName()
                    token = data.getToken()
                    appId = data.getAppId()
                    userRole = 0
                    if (channelName != null && token != null && appId != null) {
                        if (liveClose == false) {
                            binding!!.liveStreamingContainer.visibility = View.VISIBLE
                            initAgoraEngineAndJoinChannel()
                        }
                    }
                } else {
                    binding!!.liveStreamingContainer.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine()
        if (mRtcEngine != null) {
            mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
            mRtcEngine!!.setClientRole(userRole)
            mRtcEngine!!.enableVideo()
            if (userRole == 1) {
                setupLocalVideo()
            } else {
                val localVideoCanvas = findViewById<View>(id.local_video_view_container)
                localVideoCanvas.visibility = View.INVISIBLE
            }
            joinChannel()
        }
    }

    private fun setupLocalVideo() {
        val container = findViewById<FrameLayout>(id.local_video_view_container)
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        surfaceView.setZOrderMediaOverlay(true)
        container.addView(surfaceView)
        val videoCanvas = VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0)
        mRtcEngine!!.setupLocalVideo(videoCanvas)
    }

    private fun joinChannel() {
        if (mRtcEngine != null) {
            mRtcEngine!!.joinChannel(token, channelName, null, 0)
        }
    }

    private fun initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(baseContext, appId, mRtcEventHandler)
        } catch (e: Exception) {
            println("Exception: " + e.message)
        }
    }

    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onUserJoined(uid: Int, elapsed: Int) {
            runOnUiThread { setupRemoteVideo(uid) }
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            runOnUiThread { onRemoteUserLeft() }
        }

        override fun onJoinChannelSuccess(channel: String, uid: Int, elapsed: Int) {
            runOnUiThread { println("Join channel success: $uid") }
        }
    }

    private fun setupRemoteVideo(uid: Int) {
        val container = findViewById<FrameLayout>(id.remote_video_view_container)
        if (container.childCount >= 1) {
            return
        }
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        container.addView(surfaceView)
        val videoCanvas = VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid)
        mRtcEngine!!.setupRemoteVideo(videoCanvas)
        surfaceView.tag = uid
    }

    private fun onRemoteUserLeft() {
        val container = findViewById<FrameLayout>(id.remote_video_view_container)
        container.removeAllViews()
    }

    private fun checkCurrentBottomNav() {
        if (Constants.IS_FROM_PLAYER) {
            when (NavigationHelper.getINSTANCE().currentFragment) {
                Constants.HOME_FRAGMENT -> navController!!.navigate(id.navigation_home)
                Constants.MOVIES_FRAGMENT -> navController!!.navigate(id.navigation_movies)
                Constants.DOCUMENTARY_FRAGMENT -> navController!!.navigate(id.navigation_tvShows)
            }
        }
    }

    private fun unCheckableBottomNavigation() {
        binding!!.navView.menu.getItem(Constants.HOME).setCheckable(false)
        binding!!.navView.menu.getItem(Constants.MOVIES).setCheckable(false)
        binding!!.navView.menu.getItem(Constants.DOCUMENTARY).setCheckable(false)
    }

    private fun checkBottomMenuCheckable() {
        super.onBackPressed()
        when (NavigationHelper.getINSTANCE().currentFragment) {
            Constants.HOME_FRAGMENT -> binding!!.navView.menu.getItem(Constants.HOME)
                .setCheckable(true)

            Constants.MOVIES_FRAGMENT -> binding!!.navView.menu.getItem(Constants.MOVIES)
                .setCheckable(true)

            Constants.DOCUMENTARY_FRAGMENT -> binding!!.navView.menu.getItem(Constants.DOCUMENTARY)
                .setCheckable(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(
            item
        )
    }

    override fun onBackPressed() {
        val currentFragment = arrayOf("")
        val navControllerBack =
            findNavController((this as FragmentActivity), id.nav_host_fragment_activity_main)
        navControllerBack.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Log.e("onDestinationChanged", "onDestinationChanged: " + destination.label)
                currentFragment[0] = destination.label.toString()
            }
        })
        if ("fragment_pdf_view" == currentFragment[0]) {
            super.onBackPressed()
        } else if ("fragment_family_member_deatils" == currentFragment[0]) {
            super.onBackPressed()
        } else if ("fragment_pdf_web_view" == currentFragment[0]) {
            super.onBackPressed()
        } else if ("fragment_photo_gallery" == currentFragment[0]) {
            checkBottomMenuCheckable()
        } else if ("fragment_pathshala" == currentFragment[0]) {
            checkBottomMenuCheckable()
        } else if ("fragment_tunes" == currentFragment[0]) {
            checkBottomMenuCheckable()
        } else if ("fragment_family_member" == currentFragment[0]) {
            checkBottomMenuCheckable()
        } else {
            if (doubleBackToExitPressedOnce) {
                val a = Intent(Intent.ACTION_MAIN)
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                a.addCategory(Intent.CATEGORY_HOME)
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(a)
                clearALLData()
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Click twice to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    private fun clearALLData() {
        val p = Process.myPid()
        Process.killProcess(p)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mRtcEngine != null) {
            mRtcEngine!!.leaveChannel()
            RtcEngine.destroy()
            mRtcEngine = null
        }
    }
}