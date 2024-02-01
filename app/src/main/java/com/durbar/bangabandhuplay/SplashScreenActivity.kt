package com.durbar.bangabandhuplay

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.durbar.bangabandhuplay.databinding.ActivitySplashScreenBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging

class SplashScreenActivity : AppCompatActivity() {
    private var binding: ActivitySplashScreenBinding? = null

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
            if (isGranted) Toast.makeText(this,"Granted", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)

        // notification
        handlePushNotification()

        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

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
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
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


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val SPLASH_TIME_OUT = 1000
    }
}