package com.durbar.bangabandhuplay.ui.live

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.utils.Constants.APP_ID
import com.durbar.bangabandhuplay.utils.Constants.TOKEN
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import java.lang.Exception

class VideoActivity : AppCompatActivity() {
    private var mRtcEngine: RtcEngine? = null
    private var channelName: String? = "nurchannel"
    private var userRole = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        initAgoraEngineAndJoinChannel()
    }


    override fun onDestroy() {
        super.onDestroy()
        mRtcEngine!!.leaveChannel()
        RtcEngine.destroy()
        mRtcEngine = null
    }

    private fun initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine()

        mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        mRtcEngine!!.setClientRole(userRole)

        mRtcEngine!!.enableVideo()
        if (userRole == 1){
            setupLocalVideo()
            Toast.makeText(applicationContext, "role 1 boardcast", Toast.LENGTH_SHORT).show()
        }
        else {
            val localVideoCanvas = findViewById<View>(R.id.local_video_view_container)
            localVideoCanvas.isVisible = false
            Toast.makeText(applicationContext, "audiance ", Toast.LENGTH_SHORT).show()
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
            mRtcEngine = RtcEngine.create(baseContext, APP_ID, mRtcEventHandler)
        } catch (e: Exception) {
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
        mRtcEngine!!.joinChannel(TOKEN, channelName, null, 0)
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