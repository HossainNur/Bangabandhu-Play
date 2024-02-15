package com.durbar.bangabandhuplay.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

fun Context.checkInternet(): Boolean {
    val isInternetAvailable = NetworkUtils.isInternetAvailable(this)
    if (!isInternetAvailable) {
        AlertDialog.Builder(this)
            .setTitle("No Internet Connection!!")
            .setMessage("Please check your internet connection and try again.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                // redirecting to wifi setting
              //  startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            .setCancelable(false)
            .show()
    }
    return isInternetAvailable
}


fun Activity.observeInternetConnection() {
    val connectivityLiveData= ConnectivityLiveData(application)
    connectivityLiveData.observe(this as LifecycleOwner, Observer { isAvailable ->
        if (!isAvailable) {
            AlertDialog.Builder(this)
                .setTitle("No Internet !!")
                .setMessage("Disconnected or slow internet, Please Check your internet connection")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    // redirecting to wifi setting
                    //  startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                }
                .setCancelable(false)
                .show()
        }
    })
}


fun Activity.showAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String = "OK",
    negativeButtonText: String = "No",
    isCancelable: Boolean = false
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialog, _ ->
            dialog.dismiss()
            // You can add further actions here if needed
        }
        .setNegativeButton(negativeButtonText){dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(isCancelable)
        .show()
}
