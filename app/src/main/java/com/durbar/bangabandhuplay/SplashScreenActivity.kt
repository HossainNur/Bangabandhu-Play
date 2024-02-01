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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)


        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val SPLASH_TIME_OUT = 1000
    }
}