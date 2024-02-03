package com.durbar.bangabandhuplay.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.SplashScreenActivity
import com.durbar.bangabandhuplay.ui.live.StreamingActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.Date
import kotlin.random.Random

class MyFirebaseMessagingService  : FirebaseMessagingService() {
    override fun onNewToken(token: String) {

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.e("fdgfd", "" + remoteMessage.data.toString())
        removeBrokenChannel()
        initNotificationChannel()
        // generateNotification(remoteMessage.data["title"] ?: "title")
        //showNotification(remoteMessage)
        Log.e("remoteMessage", "onMessageReceived: " )
        handleNotification(remoteMessage)

    }


    private fun initNotificationChannel() {
        val value = "/raw/cutom_rington_1"
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val channelName = getString(R.string.general_channel_title)
        val channelDescription = getString(R.string.general_channel_description)
        val importance = NotificationManagerCompat.IMPORTANCE_HIGH
        val channel = NotificationChannelCompat.Builder(CHANNEL_ID, importance).apply {
            setName(channelName)
            setDescription(channelDescription)
            setSound(
                Uri.parse(
                    "${ContentResolver.SCHEME_ANDROID_RESOURCE}://${this@MyFirebaseMessagingService.packageName}$value"
                ),
                Notification.AUDIO_ATTRIBUTES_DEFAULT
            ) //after changing the ringtone you got to change the CHANNEL_ID as well to see the new ringtone effect
        }
        NotificationManagerCompat.from(this).createNotificationChannel(channel.build())
    }


    private fun removeBrokenChannel() {
        NotificationManagerCompat.from(this)
            .deleteNotificationChannel(BROKEN_CHANNEL_ID)
    }

    private fun handleNotification(remoteMessage: RemoteMessage){

        val params = remoteMessage.data
        val title = params["title"]?:""
        val body = params["body"]?:""
        // val mPref = PreferencesHelper(applicationContext)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val m = (Date().time / 1000L % Int.MAX_VALUE).toInt()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = CHANNEL_ID
            val channelName = "Channel Name"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(
                channelId, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }
        var bigPictureStyle: NotificationCompat.BigPictureStyle? = null
        var bigTextStyle: NotificationCompat.BigTextStyle? = null
        var icon: Bitmap? = null
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
        mBuilder.setSmallIcon(R.drawable.bangabandhu_play_logo)
        // mBuilder.setLargeIcon(R.drawable.asset_6)
        mBuilder.setContentTitle(title)
        /* if (img != null) {
             icon = img
             bigPictureStyle = NotificationCompat.BigPictureStyle()
             bigPictureStyle.setBigContentTitle(messageTitle)
             bigPictureStyle.setSummaryText(Html.fromHtml(messageBody).toString())
             bigPictureStyle.bigPicture(icon)
             mBuilder.setStyle(bigPictureStyle)
             mBuilder.setLargeIcon(icon)
         } else {
             bigTextStyle = NotificationCompat.BigTextStyle()
             bigTextStyle.setBigContentTitle(messageTitle)
             bigTextStyle.bigText(messageBody)
             mBuilder.setStyle(bigTextStyle)
         }*/
        bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle(title)
        bigTextStyle.bigText(body)
        mBuilder.setStyle(bigTextStyle)

        /*mBuilder.setSound(
            Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${this.packageName}/${R.raw.custom_rington_1}")
        )*/

        mBuilder.setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        // mBuilder.setSound(defaultSoundUri)
        mBuilder.setContentText(body)
        mBuilder.priority = Notification.PRIORITY_MAX
        mBuilder.color = ContextCompat.getColor(applicationContext, R.color.white)
        mBuilder.setAutoCancel(true)

        // directing receiver to the desired activity. while notification pop up will be clicked
        val intent = Intent(this, StreamingActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra("data", "fromoutside")
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(intent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0, if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(resultPendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.notify(1, mBuilder.build())
        } else {
            notificationManager.notify(m, mBuilder.build())
        }
    }

    companion object {
        const val BROKEN_CHANNEL_ID: String = "general_channel_new"
        const val CHANNEL_ID: String = "general_channel_new_qwert"
    }
}
