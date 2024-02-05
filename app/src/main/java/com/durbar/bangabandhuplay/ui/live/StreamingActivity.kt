package com.durbar.bangabandhuplay.ui.live

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.durbar.bangabandhuplay.MainActivity
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.live.Data
import com.durbar.bangabandhuplay.databinding.ActivityStreamingBinding

class StreamingActivity : AppCompatActivity() {
    private var binding: ActivityStreamingBinding? = null
    private var viewModel: LiveStreamingViewModel? = null
    private var userRole = 0
    private var from_Notification:String = ""  // notification purpose
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStreamingBinding.inflate(
            layoutInflater
        )
        viewModel = ViewModelProvider(this).get(LiveStreamingViewModel::class.java)
        setContentView(binding!!.root)

        from_Notification = intent.getStringExtra("data")?: ""   // notification purpose

        val userRadioButton = findViewById<View>(R.id.radio_group) as RadioGroup
        val checked = userRadioButton.checkedRadioButtonId
        val audienceButton = findViewById<View>(R.id.audience_rb) as RadioButton
        userRole = if (checked == audienceButton.id) 0 else 1
        fetchActiveLive()
    }

    private fun fetchActiveLive() {
        viewModel!!.liveStreaming.observe(this) { data: Data? ->
            try {
                if (data != null && data.status == 1) {
                    val channelName = data.channelName
                    val token = data.token
                    val appId = data.appId
                    startActivity(
                        Intent(applicationContext, VideoActivity::class.java)
                            .putExtra("UserRole", userRole)
                            .putExtra("appId", appId).putExtra("channel_name", channelName)
                            .putExtra("token", token)
                    )
                    finish()
                } else {
                    Toast.makeText(applicationContext, "No live Right now ", Toast.LENGTH_SHORT).show()
                    // If, user or receiver click notification after live has ended, then go to Main Activity
                    if (from_Notification!=""){
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}