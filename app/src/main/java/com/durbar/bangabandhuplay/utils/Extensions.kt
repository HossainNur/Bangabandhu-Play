package com.durbar.bangabandhuplay.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings

fun Context.checkInternet(): Boolean {
    val isInternetAvailable = NetworkUtils.isInternetAvailable(this)
    if (!isInternetAvailable) {
        AlertDialog.Builder(this)
            .setTitle("No Internet Connection!!")
            .setMessage("Please check your internet connection and try again.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                // redirecting to wifi setting
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            .setCancelable(false)
            .show()
    }
    return isInternetAvailable
}