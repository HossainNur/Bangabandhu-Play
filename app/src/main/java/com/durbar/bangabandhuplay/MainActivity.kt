package com.durbar.bangabandhuplay

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.durbar.bangabandhuplay.R.id
import com.durbar.bangabandhuplay.databinding.ActivityMainBinding
import com.durbar.bangabandhuplay.ui.live.LiveStreamingViewModel
import com.durbar.bangabandhuplay.ui.live.StreamingActivity
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.Constants.END_CALL_PRESSED
import com.durbar.bangabandhuplay.utils.NavigationHelper
import com.durbar.bangabandhuplay.utils.checkInternet
import com.durbar.bangabandhuplay.utils.observeInternetConnection
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: LiveStreamingViewModel
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var doubleBackToExitPressedOnce = false
    private var liveClose = false
    private var navController: NavController? = null
    private var mRtcEngine: RtcEngine? = null
    private var userRole = 1
    private var token : String? = null
    private var appId: String? = null
    private var channelName : String? = null
    private var handler: Handler = Handler()
    private var runnable: Runnable? = null
    private var delay = 4000

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
            if (isGranted) Toast.makeText(this,"Granted", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[LiveStreamingViewModel::class.java]
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        this.checkInternet()
        observeInternetConnection()


        val isBannerOut = Constants.getSharedPrefBoolean(this, "REMOVE_BANNER")
        if (isBannerOut){
            binding.bannerContainer.visibility = View.GONE
        }else{
            binding.bannerContainer.visibility = View.VISIBLE
        }

        binding.bannerImageClose.setOnClickListener {
            Constants.setEditor(this, "REMOVE_BANNER", true)
            binding.bannerContainer.visibility = View.GONE
        }

        // notification
        handlePushNotification()

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        setSupportActionBar(binding.homeToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""
        binding.appTopBarLayout.visibility = View.VISIBLE
        val navHostFragment =
            supportFragmentManager.findFragmentById(id.nav_host_fragment_activity_main) as NavHostFragment?
        navController = navHostFragment!!.navController
        setupWithNavController(binding.navView, navController!!)
        binding.ivSearch.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    applicationContext, SearchResultActivity::class.java
                )
            )
        }

        //NAVIGATION DRAWER ITEM SELECTED LISTENER
       /* binding.navDrawer.setNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId == id.familyMemberFragment) {
                navController!!.navigate(id.familyMemberFragment)
                NavigationHelper.instanceNavHelper?.appBarLayout = binding.appTopBarLayout
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
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }*/

        binding.navDrawer.setNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                id.familyMemberFragment -> {
                    navController!!.popBackStack(navController!!.graph.startDestinationId, false)
                    NavigationHelper.instanceNavHelper?.appBarLayout = binding.appTopBarLayout
                    unCheckableBottomNavigation()
                }
                id.live ->{
                    startActivity(Intent(applicationContext, StreamingActivity::class.java))
                }
                id.pathshala -> {
                    navController!!.popBackStack(navController!!.graph.startDestinationId, false)
                    unCheckableBottomNavigation()
                }
                id.tunes ->{
                    navController!!.popBackStack(navController!!.graph.startDestinationId, false)
                    unCheckableBottomNavigation()
                }
                id.photoGalleryFragment ->{
                    navController!!.popBackStack(navController!!.graph.startDestinationId, false)
                    unCheckableBottomNavigation()
                }
            }
            // Navigate to the selected destination
            navController!!.navigate(item.itemId)

            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        binding.navView.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == id.navigation_home) {
                navController!!.navigate(id.navigation_home)
                binding.navView.menu.getItem(Constants.HOME).setCheckable(true)
            } else if (item.itemId == id.navigation_movies) {
                navController!!.navigate(id.navigation_movies)
                binding.navView.menu.getItem(Constants.MOVIES).setCheckable(true)
            } else {
                navController!!.navigate(id.navigation_tvShows)
                binding.navView.menu.getItem(Constants.DOCUMENTARY).setCheckable(true)
            }
            true
        }

        checkCurrentBottomNav()

        if (END_CALL_PRESSED){
            binding.liveStreamingContainer.visibility = View.GONE
            liveClose = true
            if (mRtcEngine != null) {
                mRtcEngine!!.leaveChannel();
                RtcEngine.destroy();
                mRtcEngine = null;
            }
        }

        binding.liveStreamingClose.setOnClickListener {
            binding.liveStreamingContainer.visibility = View.GONE
            liveClose = true
            if (mRtcEngine != null) {
                mRtcEngine!!.leaveChannel();
                RtcEngine.destroy();
                mRtcEngine = null;
            }
            END_CALL_PRESSED = true
        }

        binding.remoteVideoViewContainer.setOnClickListener {
            binding.liveStreamingContainer.visibility = View.GONE
            liveClose = true
            if (mRtcEngine != null) {
                mRtcEngine!!.leaveChannel();
                RtcEngine.destroy();
                mRtcEngine = null;
            }
            startActivity(Intent(applicationContext,StreamingActivity::class.java))
           // finish()
        }
    }

    private fun checkCurrentBottomNav() {
        if (Constants.IS_FROM_PLAYER) {
            when (NavigationHelper.instanceNavHelper?.currentFragment) {
                Constants.HOME_FRAGMENT -> navController!!.navigate(id.navigation_home)
                Constants.MOVIES_FRAGMENT -> navController!!.navigate(id.navigation_movies)
                Constants.DOCUMENTARY_FRAGMENT -> navController!!.navigate(id.navigation_tvShows)
            }
        }
    }

    private fun unCheckableBottomNavigation() {
        binding.navView.menu.getItem(Constants.HOME).setCheckable(false)
        binding.navView.menu.getItem(Constants.MOVIES).setCheckable(false)
        binding.navView.menu.getItem(Constants.DOCUMENTARY).setCheckable(false)
    }

    private fun checkBottomMenuCheckable() {
        super.onBackPressed()
        when (NavigationHelper.instanceNavHelper?.currentFragment) {
            Constants.HOME_FRAGMENT -> binding.navView.menu.getItem(Constants.HOME)
                .setCheckable(true)

            Constants.MOVIES_FRAGMENT -> binding.navView.menu.getItem(Constants.MOVIES)
                .setCheckable(true)

            Constants.DOCUMENTARY_FRAGMENT -> binding.navView.menu.getItem(Constants.DOCUMENTARY)
                .setCheckable(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val currentFragment = arrayOf("")
        val navControllerBack =
            findNavController((this as FragmentActivity), id.nav_host_fragment_activity_main)
        navControllerBack.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
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
        } else if ("fragment_photo_gallery_details" == currentFragment[0]) {
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
                a.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
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


    // notification
    fun handlePushNotification(){
        // for individual notification reciever
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("notification", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            //  val msg = getString(R.string.msg_token_fmt, token)
            System.out.println(token)
            // Toast.makeText(this,"device rg token is"+ token, Toast.LENGTH_SHORT).show()
            Log.d("token", "token: $token")
        })

        // for all device/user
        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d("notifi", msg)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

        if (Build.VERSION.SDK_INT > 32) {
            checkNotificationPermission()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkNotificationPermission() {
        val permission = Manifest.permission.POST_NOTIFICATIONS
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                // make your action here
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // showPermissionRationaleDialog() // permission denied permanently
            }
            else -> {
                requestNotificationPermission.launch(permission)
            }
        }
    }

    override fun onResume() {
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())
            fetchActiveLive()
        }.also { runnable = it }, delay.toLong())
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
        binding.liveStreamingContainer.visibility = View.GONE
        liveClose = true
        handler.removeCallbacks(runnable!!)
        if (mRtcEngine != null) {
            mRtcEngine!!.leaveChannel();
            RtcEngine.destroy();
            mRtcEngine = null;
        }
    }

    private fun fetchActiveLive() {
        viewmodel.liveStreaming.observe(this) { data ->
            try {
                if (data != null && data.status == 1) {
                    channelName = data.channelName
                    token = data.token
                    appId = data.appId
                    userRole = 0

                    if (channelName != null && token != null && appId != null) {
                        if (!liveClose) {
                            binding.liveStreamingContainer.visibility = View.VISIBLE
                            initAgoraEngineAndJoinChannel()
                        }
                    }
                } else {
                    binding.liveStreamingContainer.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine()

        mRtcEngine!!.setChannelProfile(io.agora.rtc.Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        mRtcEngine!!.setClientRole(userRole)

        mRtcEngine!!.enableVideo()
        if (userRole == 1)
            setupLocalVideo()
        else {
            val localVideoCanvas = findViewById<View>(R.id.local_video_view_container)
            localVideoCanvas.isVisible = false
        }

        joinChannel()
    }

    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onUserJoined(uid: Int, elapsed: Int) {
            runOnUiThread { setupRemoteVideo(uid) }
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            runOnUiThread {
               // onRemoteUserLeft()
            }
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            runOnUiThread { println("Join channel success: $uid") }
        }
    }

    private fun initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(baseContext, appId, mRtcEventHandler)
        } catch (e: java.lang.Exception) {
            println("Exception: ${e.message}")
        }
    }

    fun onSwitchCameraClicked(view: View) {
        mRtcEngine!!.switchCamera()
    }

    fun onEndCalledClicked(view: View) {
        finish()
    }

    fun onLocalAudioMuteClicked(view: View) {
        val iv = view as ImageView
        if (iv.isSelected){
            iv.isSelected = false
            iv.clearColorFilter()
        }else {
            iv.isSelected = true
            iv.setColorFilter(resources.getColor(R.color.purple_200), PorterDuff.Mode.MULTIPLY)
        }

        mRtcEngine!!.muteLocalAudioStream(iv.isSelected)
    }
    private fun setupLocalVideo(){
        val container = findViewById<View>(R.id.local_video_view_container) as FrameLayout
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        surfaceView.setZOrderMediaOverlay(true)
        container.addView(surfaceView)
        mRtcEngine!!.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0))
    }

    private fun joinChannel(){
        mRtcEngine!!.joinChannel(token, channelName, null, 0)
    }
    private fun setupRemoteVideo(uid: Int){
        val container = findViewById<View>(R.id.remote_video_view_container) as FrameLayout
        if (container.childCount >= 1) return
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        container.addView(surfaceView)
        mRtcEngine!!.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid))
        surfaceView.tag = uid
    }
    private fun onRemoteUserLeft(){
        val container = findViewById<View>(R.id.remote_video_view_container) as FrameLayout
        container.removeAllViews()
    }



    override fun onDestroy() {
        super.onDestroy()
        if (mRtcEngine != null) {
            mRtcEngine!!.leaveChannel();
            RtcEngine.destroy();
            mRtcEngine = null;
        }
    }

}