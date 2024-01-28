package com.durbar.bangabandhuplay;


import static com.durbar.bangabandhuplay.R.id.familyMemberFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.durbar.bangabandhuplay.databinding.ActivityMainBinding;
import com.durbar.bangabandhuplay.ui.family_member.FamilyMemberFragment;
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
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean doubleBackToExitPressedOnce = false;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();*/
        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/

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
                    Constants.CURRENT_FRAGMENT_BOTTOM = Constants.HOME;
                } else if (item.getItemId() == R.id.navigation_movies) {
                    navController.navigate(R.id.navigation_movies);
                    binding.navView.getMenu().getItem(Constants.MOVIES).setCheckable(true);
                    Constants.CURRENT_FRAGMENT_BOTTOM = Constants.MOVIES;
                } else {
                    navController.navigate(R.id.navigation_tvShows);
                    binding.navView.getMenu().getItem(Constants.DOCUMENTARY).setCheckable(true);
                    Constants.CURRENT_FRAGMENT_BOTTOM = Constants.DOCUMENTARY;
                }
                return true;
            }
        });

    }

    private void unCheckableBottomNavigation() {
        binding.navView.getMenu().getItem(Constants.HOME).setCheckable(false);
        binding.navView.getMenu().getItem(Constants.MOVIES).setCheckable(false);
        binding.navView.getMenu().getItem(Constants.DOCUMENTARY).setCheckable(false);
    }

    private void checkableBottomNavigation() {
        if (Constants.CURRENT_FRAGMENT_BOTTOM == Constants.HOME){
            binding.navView.getMenu().getItem(Constants.HOME).setCheckable(true);
        }else if (Constants.CURRENT_FRAGMENT_BOTTOM == Constants.MOVIES){
            binding.navView.getMenu().getItem(Constants.MOVIES).setCheckable(true);
        }else if (Constants.CURRENT_FRAGMENT_BOTTOM == Constants.DOCUMENTARY){
            binding.navView.getMenu().getItem(Constants.DOCUMENTARY).setCheckable(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click twice to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }*/

    @Override
    public void onBackPressed() {
        final String[] currentFragment = {""};
        NavController navControllerBack = Navigation.findNavController((FragmentActivity) this, R.id.nav_host_fragment_activity_main);
        navControllerBack.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                Log.e("onDestinationChanged", "onDestinationChanged: " + destination.getLabel());  // fragment's name can be found in logcat
                currentFragment[0] = destination.getLabel().toString();
            }
        });

        if ("fragment_pathshala".equals(currentFragment[0])) {
            super.onBackPressed();
            checkableBottomNavigation();
        }else if ("fragment_pdf_view".equals(currentFragment[0])){
            super.onBackPressed();
        } else if ("fragment_family_member".equals(currentFragment[0])) {
            super.onBackPressed();
           checkableBottomNavigation();
        }
        else if ("fragment_family_member_deatils".equals(currentFragment[0])) {
            super.onBackPressed();
        }
        else if ("fragment_photo_gallery".equals(currentFragment[0])) {
            super.onBackPressed();
            checkableBottomNavigation();
        } else if ("fragment_tunes".equals(currentFragment[0])) {
            super.onBackPressed();
            checkableBottomNavigation();
        }
        else {
            if (doubleBackToExitPressedOnce) {
                Intent a = new Intent(Intent.ACTION_MAIN);
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

    private void clearALLData(){  // clearing all data when app is closed
        int p = android.os.Process.myPid();
        android.os.Process.killProcess(p);
    }
}