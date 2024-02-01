package com.durbar.bangabandhuplay;


import static com.durbar.bangabandhuplay.R.id.familyMemberFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.durbar.bangabandhuplay.databinding.ActivityMainBinding;
import com.durbar.bangabandhuplay.ui.family_member.FamilyMemberFragment;
import com.durbar.bangabandhuplay.ui.live.LiveStreamingViewModel;
import com.durbar.bangabandhuplay.ui.live.StreamingActivity;
import com.durbar.bangabandhuplay.ui.live.VideoActivity;
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.durbar.bangabandhuplay.utils.NavigationHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean doubleBackToExitPressedOnce = false;
    private boolean liveClose = false;
    private LiveStreamingViewModel viewModel;
    private NavController navController;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int delayTime = 5000;
    private RtcEngine mRtcEngine;
    private int userRole = 1;
    private String token = null;
    private String appId = null;
    private String channelName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel =  new ViewModelProvider(this).get(LiveStreamingViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close);
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        setSupportActionBar(binding.homeToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        binding.appTopBarLayout.setVisibility(View.VISIBLE);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);

        binding.ivSearch.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SearchResultActivity.class));
        });

        //NAVIGATION DRAWER ITEM SELECTED LISTENER
        binding.navDrawer.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == familyMemberFragment) {
                navController.navigate(R.id.familyMemberFragment);
                NavigationHelper.getINSTANCE().setAppBarLayout(binding.appTopBarLayout);
                unCheckableBottomNavigation();
            } else if (item.getItemId() == R.id.live) {
                startActivity(new Intent(getApplicationContext(), StreamingActivity.class));
            } else if (item.getItemId() == R.id.pathshala) {
                navController.navigate(R.id.pathshala);
                unCheckableBottomNavigation();
            } else if (item.getItemId() == R.id.tunes) {
                navController.navigate(R.id.tunes);
                unCheckableBottomNavigation();
            } else {
                navController.navigate(R.id.photoGalleryFragment);
                unCheckableBottomNavigation();
            }


            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    navController.navigate(R.id.navigation_home);
                    binding.navView.getMenu().getItem(Constants.HOME).setCheckable(true);
                } else if (item.getItemId() == R.id.navigation_movies) {
                    navController.navigate(R.id.navigation_movies);
                    binding.navView.getMenu().getItem(Constants.MOVIES).setCheckable(true);
                } else {
                    navController.navigate(R.id.navigation_tvShows);
                    binding.navView.getMenu().getItem(Constants.DOCUMENTARY).setCheckable(true);
                }
                return true;
            }
        });

        checkCurrentBottomNav();

        binding.liveStreamingClose.setOnClickListener(v -> {
            binding.liveStreamingContainer.setVisibility(View.GONE);
            liveClose = true;
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delayTime);
                fetchActiveLive();
            }
        }, delayTime);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void  fetchActiveLive() {
        viewModel.getLiveStreaming().observe(this,data -> {
            try {
                if (data != null && data.getStatus() == 1){

                    channelName = data.getChannelName();
                    token = data.getToken();
                    appId = data.getAppId();
                    userRole = 0;

                    if (channelName != null && token != null && appId != null){
                        if (liveClose == false){
                            binding.liveStreamingContainer.setVisibility(View.VISIBLE);
                            initAgoraEngineAndJoinChannel();
                        }
                    }
                }else {
                    binding.liveStreamingContainer.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }

    private void initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine();

        if (mRtcEngine != null) {
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
            mRtcEngine.setClientRole(userRole);

            mRtcEngine.enableVideo();
            if (userRole == 1) {
                setupLocalVideo();
            } else {
                View localVideoCanvas = findViewById(R.id.local_video_view_container);
                localVideoCanvas.setVisibility(View.INVISIBLE);
            }
            joinChannel();
        }
    }

    private void setupLocalVideo() {
        FrameLayout container = findViewById(R.id.local_video_view_container);
        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        surfaceView.setZOrderMediaOverlay(true);
        container.addView(surfaceView);

        VideoCanvas videoCanvas = new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0);
        mRtcEngine.setupLocalVideo(videoCanvas);
    }


    private void joinChannel() {
        if (mRtcEngine != null) {
            mRtcEngine.joinChannel(token, channelName, null, 0);
        }
    }

    private void initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), appId, mRtcEventHandler);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onUserJoined(int uid, int elapsed) {
            runOnUiThread(() -> setupRemoteVideo(uid));
        }

        @Override
        public void onUserOffline(int uid, int reason) {
            runOnUiThread(() -> onRemoteUserLeft());
        }

        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            runOnUiThread(() -> System.out.println("Join channel success: " + uid));
        }
    };

    private void setupRemoteVideo(int uid) {
        FrameLayout container = findViewById(R.id.remote_video_view_container);
        if (container.getChildCount() >= 1) {
            return;
        }

        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        container.addView(surfaceView);

        VideoCanvas videoCanvas = new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid);
        mRtcEngine.setupRemoteVideo(videoCanvas);

        surfaceView.setTag(uid);
    }

    private void onRemoteUserLeft() {
        FrameLayout container = findViewById(R.id.remote_video_view_container);
        container.removeAllViews();
    }

    private void checkCurrentBottomNav() {

        if (Constants.IS_FROM_PLAYER){
        switch (NavigationHelper.getINSTANCE().getCurrentFragment()) {
            case Constants.HOME_FRAGMENT:
                navController.navigate(R.id.navigation_home);
                break;
            case Constants.MOVIES_FRAGMENT:
                navController.navigate(R.id.navigation_movies);
                break;
            case Constants.DOCUMENTARY_FRAGMENT:
                navController.navigate(R.id.navigation_tvShows);
                break;
        }

        }

    }


    private void unCheckableBottomNavigation() {
        binding.navView.getMenu().getItem(Constants.HOME).setCheckable(false);
        binding.navView.getMenu().getItem(Constants.MOVIES).setCheckable(false);
        binding.navView.getMenu().getItem(Constants.DOCUMENTARY).setCheckable(false);
    }

    private void checkBottomMenuCheckable(){
        super.onBackPressed();
        switch (NavigationHelper.getINSTANCE().getCurrentFragment()) {
            case Constants.HOME_FRAGMENT:
                binding.navView.getMenu().getItem(Constants.HOME).setCheckable(true);
                break;
            case Constants.MOVIES_FRAGMENT:
                binding.navView.getMenu().getItem(Constants.MOVIES).setCheckable(true);
                break;
            case Constants.DOCUMENTARY_FRAGMENT:
                binding.navView.getMenu().getItem(Constants.DOCUMENTARY).setCheckable(true);
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        final String[] currentFragment = {""};
        NavController navControllerBack = Navigation.findNavController((FragmentActivity) this, R.id.nav_host_fragment_activity_main);
        navControllerBack.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                Log.e("onDestinationChanged", "onDestinationChanged: " + destination.getLabel());
                currentFragment[0] = destination.getLabel().toString();
            }
        });

        if ("fragment_pdf_view".equals(currentFragment[0])) {
            super.onBackPressed();
        }else if ("fragment_family_member_deatils".equals(currentFragment[0])) {
            super.onBackPressed();
        }else if ("fragment_pdf_web_view".equals(currentFragment[0])) {
            super.onBackPressed();
        }
        else if ("fragment_photo_gallery".equals(currentFragment[0])) {
            checkBottomMenuCheckable();
        } else if ("fragment_pathshala".equals(currentFragment[0])) {
            checkBottomMenuCheckable();
        }
        else if ("fragment_tunes".equals(currentFragment[0])) {
            checkBottomMenuCheckable();
        } else if ("fragment_family_member".equals(currentFragment[0])) {
            checkBottomMenuCheckable();
        } else {
            if (doubleBackToExitPressedOnce) {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                clearALLData();
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Click twice to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    private void clearALLData() {
        int p = android.os.Process.myPid();
        android.os.Process.killProcess(p);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRtcEngine != null) {
            mRtcEngine.leaveChannel();
            RtcEngine.destroy();
            mRtcEngine = null;
        }
    }
}