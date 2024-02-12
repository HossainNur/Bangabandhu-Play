package com.durbar.bangabandhuplay.ui.live

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.durbar.bangabandhuplay.MainActivity
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.ActivityVideoBinding
import com.durbar.bangabandhuplay.utils.Constants.APP_ID
import com.durbar.bangabandhuplay.utils.Constants.END_CALL_PRESSED
import com.durbar.bangabandhuplay.utils.Constants.TOKEN
import com.durbar.bangabandhuplay.utils.NetworkUtil
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import java.lang.Exception

class VideoActivity : AppCompatActivity() {

    private var mRtcEngine: RtcEngine? = null
    private var userRole = 1
    private var token : String? = null
    private var appId: String? = null
    private var channelName : String? = null
    val networkUtil: NetworkUtil = NetworkUtil.getInstance(this)
    private lateinit var binding : ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRole = intent.getIntExtra("UserRole", -1)
        channelName = intent.getStringExtra("channel_name")
        appId = intent.getStringExtra("appId")
        token = intent.getStringExtra("token")
        if (!networkUtil.isNetworkAvailable()) {
            NetworkUtil.showNoInternetDialog(this);
        }
        initAgoraEngineAndJoinChannel()
    }

    override fun onPause() {
        super.onPause()
        if (mRtcEngine != null){
            mRtcEngine!!.leaveChannel()
            RtcEngine.destroy()
            mRtcEngine = null
        }
        finish()
    }
    override fun onDestroy() {
        super.onDestroy()
        if (mRtcEngine != null){
            mRtcEngine!!.leaveChannel()
            RtcEngine.destroy()
            mRtcEngine = null
        }
    }

    private fun initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine()

        mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
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
            runOnUiThread { onRemoteUserLeft() }
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            runOnUiThread { println("Join channel success: $uid") }
        }
    }

    private fun initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(baseContext, appId, mRtcEventHandler)
        } catch (e: Exception) {
            println("Exception: ${e.message}")
        }
    }


    fun onSwitchCameraClicked(view: View) {
        mRtcEngine!!.switchCamera()
    }

    fun onEndCalledClicked(view: View) {
        END_CALL_PRESSED = true
        startActivity(Intent(this, MainActivity::class.java))
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

}