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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.durbar.bangabandhuplay.R.id
import com.durbar.bangabandhuplay.databinding.ActivityMainBinding
import com.durbar.bangabandhuplay.ui.live.StreamingActivity
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.NavigationHelper
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var doubleBackToExitPressedOnce = false
    private var navController: NavController? = null

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
            if (isGranted) Toast.makeText(this,"Granted", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        // notification
        handlePushNotification()

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

}